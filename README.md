# commons-cli-annotations

This library add support of annotations to Apache Commons CLI.

License Apache 2.0

## Ant Example

```shell
ant [options] [target [target2 [target3] ...]]
  Options: 
  -help                  print this message
  -projecthelp           print project help information
  -version               print the version information and exit
  -quiet                 be extra quiet
  -verbose               be extra verbose
  -debug                 print debugging information
  -emacs                 produce logging information without adornments
  -logfile <file>        use given file for log
  -logger <classname>    the class which is to perform logging
  -listener <classname>  add an instance of class as a project listener
  -buildfile <file>      use given buildfile
  -D<property>=<value>   use value for given property
  -find <file>           search for buildfile towards the root of the
                         filesystem and use it
```

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

```java
public class MyApplication {
	
    public static void main(String[] args) {
    	
        // Build the parser
        Parser<AntExample> parser = new Parser<>(AntExample.class);
       
        // Parse the arguments
        AntExample antExample = parser.parse(args);
    }
}
```

## Maven integration

```xml
 <dependency>
    <groupId>com.qwazr</groupId>
    <artifactId>commons-cli-annotations</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```