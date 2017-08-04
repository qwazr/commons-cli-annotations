/*
 * Copyright 2015-2017 Emmanuel Keller / QWAZR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qwazr.cli;

import com.qwazr.binder.setter.FieldSetter;
import com.qwazr.utils.AnnotationsUtils;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.cli.Option.UNINITIALIZED;

public class Parser<T> {

	private class FieldOption {

		final String key;
		final boolean withArg;
		final FieldSetter setter;

		FieldOption(String key, boolean withArg, Field field) {
			this.key = key;
			this.withArg = withArg;
			this.setter = FieldSetter.of(field);
		}

	}

	private final Options options;
	private final List<FieldOption> fieldOptions;
	private final Class<? extends T> optionsClass;

	public Parser(final Class<? extends T> optionsClass) throws ParseException, NoSuchMethodException {
		this.options = new Options();
		this.fieldOptions = new ArrayList<>();
		this.optionsClass = optionsClass;

		AnnotationsUtils.browseFieldsRecursive(optionsClass, field -> {
			final Option option = field.getAnnotation(Option.class);
			if (option != null) {

				final String opt;
				if (option.opt().isEmpty()) {
					if (option.longOpt().isEmpty())
						opt = field.getName();
					else
						opt = null;
				} else
					opt = option.opt();

				final org.apache.commons.cli.Option cliOption = org.apache.commons.cli.Option.builder(opt)
						.argName(option.argName().isEmpty() ? null : option.argName())
						.desc(option.desc().isEmpty() ? null : option.desc())
						.hasArg(option.hasArg())
						.numberOfArgs(option.numberOfArgs() != UNINITIALIZED ?
								option.numberOfArgs() :
								option.hasArg() || option.optionalArg() ? 1 : UNINITIALIZED)
						.longOpt(option.longOpt().isEmpty() ? null : option.longOpt())
						.optionalArg(option.optionalArg())
						.valueSeparator(option.valueSeparator())
						.required(option.required())
						.build();

				options.addOption(cliOption);
				field.setAccessible(true);
				fieldOptions.add(new FieldOption(opt != null ? opt : option.longOpt(),
						option.hasArg() || option.numberOfArgs() > 0 || option.optionalArg(), field));
			}
		});
	}

	public void fill(CommandLine commandLine, T options) throws ParseException, IllegalAccessException {
		for (FieldOption fieldOption : fieldOptions) {
			Object value;
			if (fieldOption.withArg) {
				value = commandLine.getOptionValues(fieldOption.key);
			} else
				value = commandLine.hasOption(fieldOption.key);
			fieldOption.setter.setValue(options, value);
		}
	}

	public void parseAndFill(T options, String... arguments) throws ParseException, IllegalAccessException {
		fill(new DefaultParser().parse(this.options, arguments), options);
	}

	public T parse(String... arguments) throws ParseException, IllegalAccessException, InstantiationException {
		final T optionsInstance = optionsClass.newInstance();
		parseAndFill(optionsInstance, arguments);
		return optionsInstance;
	}

	public Options getOptions() {
		return options;
	}

	public void printHelp(HelpFormatter formatter, String command) {
		formatter.printHelp(command, options);
	}

	public void printHelp(String command) {
		printHelp(new HelpFormatter(), command);
	}
}
