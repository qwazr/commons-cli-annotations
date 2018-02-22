# commons-cli-annotations

[![Coverage Status](https://coveralls.io/repos/github/qwazr/commons-cli-annotations/badge.svg?branch=master)](https://coveralls.io/github/qwazr/commons-cli-annotations?branch=master)

This library let you use annotations to parse command line arguments.

It uses the robust [Apache Commons CLI](https://commons.apache.org/proper/commons-cli/) in backend.

License Apache 2.0

## Ant Example

The Java applications Ant will be used here to illustrate how to create the Options required.

The following is the definition of the options using the Option annotations:

```java
import com.qwazr.cli.Option;
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
```

The parse method of Parser<T> is used to parse the command line arguments.

It returns a new "AntExample" instance and fill the properties with the detected arguments.

```java
import com.qwazr.cli.Parser;

public class MyApplication {
	
    public static void main(String[] args) {
    	
        // Build the parser
        Parser<AntExample> parser = new Parser<>(AntExample.class);
       
        // Parse the arguments
        AntExample antExample = parser.parse(args);
        if (antExample.help)
        	parser.printHelp("ant");
    }
}
```

The parser can also print a formatted help message to System.out.

Here is an example of output:

```
usage: ant
 -buildfile <file>       use given buildfile
 -D <property=value>     use value for given property
 -debug                  print debugging information
 -emacs                  produce logging information without adornments
 -find <arg>             search for buildfile towards the root of the
                         filesystem and use it
 -help                   print this message
 -listener <classname>   add an instance of class as a project listener
 -logfile <file>         use given file for log
 -logger <classname>     the class which it to perform logging
 -projecthelp            print project help information
 -quiet                  be extra quiet
 -verbose                be extra verbose
 -version                print the version information and exit
```

## Maven integration

```xml
 <dependency>
    <groupId>com.qwazr</groupId>
    <artifactId>commons-cli-annotations</artifactId>
    <version>1.0.1</version>
</dependency>
```

## JAVADOC

[Javadoc API documentation](https://qwazr.github.io/commons-cli-annotations/apidocs/)