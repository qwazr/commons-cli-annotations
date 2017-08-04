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

import com.qwazr.utils.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Option {

	String opt() default StringUtils.EMPTY;

	String desc() default StringUtils.EMPTY;

	String longOpt() default StringUtils.EMPTY;

	boolean hasArg() default false;

	int numberOfArgs() default UNINITIALIZED;

	String argName() default StringUtils.EMPTY;

	boolean optionalArg() default false;

	boolean required() default false;

	char valueSeparator() default '=';

	int UNLIMITED = org.apache.commons.cli.Option.UNLIMITED_VALUES;
	int UNINITIALIZED = org.apache.commons.cli.Option.UNINITIALIZED;

}