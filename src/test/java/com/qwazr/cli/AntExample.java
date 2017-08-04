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

import java.util.Map;
import java.util.Set;

public class AntExample {

	@Option(desc = "print this message")
	boolean help;

	@Option(desc = "print project help information")
	boolean projecthelp;

	@Option(desc = "print the version information and exit")
	boolean version;

	@Option(desc = "be extra quiet")
	boolean quiet;

	@Option(desc = "be extra verbose")
	boolean verbose;

	@Option(desc = "print debugging information")
	boolean debug;

	@Option(desc = "produce logging information without adornments")
	boolean emacs;

	@Option(desc = "use given file for log", argName = "file", hasArg = true)
	String logfile;

	@Option(desc = "the class which it to perform logging", argName = "classname", hasArg = true)
	String logger;

	@Option(desc = "add an instance of class as a project listener",
			argName = "classname",
			hasArg = true,
			numberOfArgs = Option.UNLIMITED)
	Set<String> listener;

	@Option(desc = "use given buildfile", argName = "file", hasArg = true, valueSeparator = ' ')
	String buildfile;

	@Option(desc = "search for buildfile towards the root of the filesystem and use it", hasArg = true)
	String find;

	@Option(opt = "D",
			argName = "property=value",
			hasArg = true,
			numberOfArgs = 2,
			valueSeparator = '=',
			desc = "use value for given property")
	Map<String, String> properties;

}
