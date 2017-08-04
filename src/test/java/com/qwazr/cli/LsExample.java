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

public class LsExample {

	@Option(opt = "a", longOpt = "all", desc = "do not hide entries starting with .")
	boolean all;

	@Option(opt = "A", longOpt = "almost-all", desc = "do not list implied . and ..")
	boolean almostAll;

	@Option(opt = "b", longOpt = "escape", desc = "print octal escapes for nongraphic characters")
	boolean escape;

	@Option(longOpt = "block-size", desc = "use SIZE-byte blocks", hasArg = true, argName = "SIZE")
	int blockSize;

	@Option(opt = "B", longOpt = "ignore-backups", desc = "do not list implied entried ending with ~")
	boolean ignoreBackup;

	@Option(opt = "c",
			desc = "with -lt: sort by, and show, ctime (time of last " +
					"modification of file status information) with " +
					"-l:show ctime and sort by name otherwise: sort " + "by ctime")
	boolean ctime;

	@Option(opt = "C", desc = "list entries by columns")
	boolean byColumn;

}
