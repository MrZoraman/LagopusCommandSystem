# Lagopus Command System Library
Powerful command parsing system!

This is NOT a command line argument parsing library. Rather, this library
is designed to help parse commands that are inputted by the user during
the runtime of an application. Useful for things like server consoles
or the likes.

This library was written to be usable in any project. As a result of that,
There's a little bit more you have to do on your side, so it's not plug
and play. However, you also get a large amount of control over how the
command system works.

# Javadoc
I have [javadocs](http://jd.lagopusempire.com/lcs/)!
If it's not documented, then you shoudln't need to use it.

# Simple code example
First you need a class that the command system will manage. This can be 
anything you want.
```java
interface ICommand
{
    public void execute(String[] preArgs, String[] args);
}
```

Now for the actual use of the library.
```java
public class SimpleCommandSystem
{
    private CommandSystem<ICommand> cs = new CommandSystem<>();

    public SimpleCommandSystem()
    {
        cs.registerCommand("test", (preArgs, args) -> {System.out.println("test complete!");});
    }

    public void onCommand(String input)
    {
        CommandResult<ICommand> result = cs.getCommand(input);
        if(result.command == null) System.out.println("Unknown command!");

        result.command.execute(result.preArgs, result.args);
    }
}
```

When onCommand("test") is called, the command system will match "test" to the corresponding command (in our case, it just simply prints "test complete!"). 
Of course, our command syntaxes and syntax trees can be much more complex.

# syntax parsing
The command system takes the corresponding command syntax and parses it to make it easier for you to make complex command trees.
Some examples are listed below:

`a b c` -> the user command would be matched to someone inputting "a b c"

`a b|c d` -> this matches the input to both inputs "a b d" and "a c d"

`a b|{c d}` -> this matches the input to both "a b" and "a c d"

`a * c` -> this matches input "a * c", where * can be anything. Wildcard inputs are put in the preArgs array.

There are more examples in the unit tests.

# maven
Add this to your repositories:
```xml
<repository>
    <id>Lagopus Empire Repo</id>
    <name>Lagopus Empire Repo-releases</name>
    <url>http://repo.lagopusempire.com/artifactory/lagopus-empire-repo</url>
</repository>
```
Add this to your dependencies
```xml
<dependency>
    <artifactId>LagopusCommandSystem</artifactId>
    <groupId>com.lagopusempire</groupId>
    <version>1.2</version>
</dependency>
```
