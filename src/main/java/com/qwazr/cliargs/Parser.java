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

package com.qwazr.cliargs;

import com.qwazr.binder.setter.FieldSetter;
import com.qwazr.utils.AnnotationsUtils;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Parser<T> {

	private class FieldOption {

		final Option option;
		final FieldSetter setter;

		FieldOption(Option option, FieldSetter setter) {
			this.option = option;
			this.setter = setter;
		}
	}

	private final Options options;
	private final List<FieldOption> fieldOptions;

	public Parser(final Class<T> optionsClass) throws ParseException, NoSuchMethodException {
		options = new Options();
		fieldOptions = new ArrayList<>();

		AnnotationsUtils.browseFieldsRecursive(optionsClass, field -> {
			final Option option = field.getAnnotation(Option.class);
			if (option != null) {
				options.addOption(newOption(option));
				field.setAccessible(true);
				fieldOptions.add(new FieldOption(option, FieldSetter.of(field)));
			}
		});
	}

	org.apache.commons.cli.Option newOption(final Option option) {
		return org.apache.commons.cli.Option.builder(option.name())
				.argName(option.argName())
				.desc(option.desc())
				.hasArg(option.hasArg())
				.longOpt(option.longOpt())
				.optionalArg(option.optionalArg())
				.valueSeparator(option.valueSeparator())
				.required(option.required())
				.type(option.type())
				.build();
	}

	public void parseAndFill(T options, String... arguments) throws ParseException, IllegalAccessException {
		final CommandLine commandLine = new DefaultParser().parse(this.options, arguments);
		for (FieldOption fieldOption : fieldOptions) {
			Object value;
			if (fieldOption.option.hasArg())
				value = commandLine.getParsedOptionValue(fieldOption.option.name());
			else
				value = commandLine.hasOption(fieldOption.option.name());
			fieldOption.setter.setValue(options, value);
		}
	}

}
