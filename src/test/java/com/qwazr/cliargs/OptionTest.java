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

import com.qwazr.utils.RandomUtils;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class OptionTest {

	static class OptionsArguments {

		@Option(name = "t", longOpt = "test")
		private final String test = null;

		@Option(name = "b", longOpt = "bool", hasArg = false)
		private final Boolean bool = null;

	}

	private final Parser<OptionsArguments> parser;

	public OptionTest() throws NoSuchMethodException, ParseException {
		parser = new Parser<>(OptionsArguments.class);
	}

	@Test
	public void fieldTest() throws ParseException, IllegalAccessException {

		final String testArgs = RandomUtils.alphanumeric(5);

		OptionsArguments optArgs = new OptionsArguments();
		parser.parseAndFill(optArgs, "-t", testArgs);
		Assert.assertEquals(testArgs, optArgs.test);

		optArgs = new OptionsArguments();
		parser.parseAndFill(optArgs, "--test", testArgs);
		Assert.assertEquals(testArgs, optArgs.test);
	}

	@Test
	public void boolTest() throws ParseException, IllegalAccessException {

		OptionsArguments optArgs = new OptionsArguments();
		parser.parseAndFill(optArgs, "-b");
		Assert.assertEquals(true, optArgs.bool);

		optArgs = new OptionsArguments();
		parser.parseAndFill(optArgs, "-bool");
		Assert.assertEquals(true, optArgs.bool);
	}
}
