/*
 * Copyright 2017 Emmanuel Keller / QWAZR
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

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class OptionTest {

	@Test
	public void antExampleTest() throws ParseException, ReflectiveOperationException {

		final Parser<AntExample> parser = new Parser<>(AntExample.class);

		AntExample antExample;

		antExample = parser.parse("-buildfile", "build.xml");
		Assert.assertEquals("build.xml", antExample.buildfile);

		antExample = parser.parse("-Djava-prop=\"hello-world\"", "-Djava-prop2=16");
		Assert.assertNotNull(antExample.properties);
		Assert.assertEquals("\"hello-world\"", antExample.properties.get("java-prop"));
		Assert.assertEquals("16", antExample.properties.get("java-prop2"));

		antExample = parser.parse("-listener", "listener1.class", "-listener", "listener2.class");
		Assert.assertNotNull(antExample.listener);
		Assert.assertTrue(antExample.listener.contains("listener1.class"));
		Assert.assertTrue(antExample.listener.contains("listener2.class"));
	}

	@Test
	public void lsExampleTest() throws ParseException, ReflectiveOperationException {

		final Parser<LsExample> parser = new Parser<>(LsExample.class);

		LsExample lsExample;

		lsExample = parser.parse("-A");
		Assert.assertEquals(true, lsExample.almostAll);

		lsExample = parser.parse("--almost-all");
		Assert.assertEquals(true, lsExample.almostAll);

		lsExample = parser.parse("--block-size=10");
		Assert.assertEquals(10, lsExample.blockSize);
	}

	@Test
	public void printHelpTest() throws ParseException, ReflectiveOperationException {
		final Parser<AntExample> parser = new Parser<>(AntExample.class);

		AntExample antExample = parser.parse("-help");
		Assert.assertNotNull(parser.getOptions());

		Assert.assertTrue(antExample.help);
		if (antExample.help)
			parser.printHelp("ant");

	}
}
