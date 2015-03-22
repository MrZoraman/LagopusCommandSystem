package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.ParseFailException;
import com.lagopusempire.lagopuscommandsystem.parsing.SpaceParser;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Primary class for the library.
 * 
 * Commands are submitted to the command system,
 * and when given user input, it will try to produce the correct corresponding
 * command object.
 * 
 * @author MrZoraman
 * @param <T> The type of command object that the command system will use.
 *            This allows the command system more flexibility by making
 *            it less implementation specific. When a command executed,
 *            you will be given an object of type T back, which you can
 *            then do with as you please.
 */
public class CommandSystem<T>
{
    private final SyntaxElement<T> root = new SyntaxElement<>();
    private T unknownCommand = null;
    
    /**
     * Registers a command to the command system.
     * 
     * @param syntax  This is the pattern that the command system will use to
     *                match the command with the user input. <b>See the 
     *                readme.md for examples.</b> Command syntaxes can have 
     *                various predefined arguments, to make up complex command 
     *                trees. To have the parser ignore spaces, put the arguments
     *                in curly braces. The parser will make sure the curly
     *                braces are used properly as to avoid mistakes made by
     *                the programmer. To have the command tree branch off into
     *                multiple aliases, use the rod symbol (|) to specify
     *                multiple command/parameter aliases. Wildcards can be
     *                specified for parameters by using a '*' symbol.
     * @param command The command that will be returned if user input matches
     *                the specified syntax.
     * 
     * @throws ParseFailException If the syntax is invalid, this exception
     * will be thrown. This exception will have all the info needed to figure out
     * what went wrong.
     */
    public void registerCommand(String syntax, T command)
    {
        final SpaceParser parser = new SpaceParser(syntax);
        root.addSyntax(parser.parse(), command);
    }
    
    /**
     * Matches user input to a given command and returns the result.
     * 
     * @param input The user input. The command system will find the best match
     *              given the various syntaxes specified in the
     *              {@link #registerCommand} method.
     * @return The result object that matches the input given. This will never
     *         be null. To check if a command was matched or not, check if
     *         {@link CommandResult#command} is null.
     * 
     * @see com.lagopusempire.lagopuscommandsystem.CommandResult
     */
    public CommandResult<T> getCommand(String input)
    {
        final CommandResult<T> result = root.matchCommand(input, new ArrayList<String>());
        
        if(result.command == null && unknownCommand != null)
        {
            result.args = input.split(" ");
            result.preArgs = new String[0];
            result.command = unknownCommand;
        }
        
        return result;
    }
    
    /**
     * Sets the command to be executed if the command system fails to find a
     * command match.
     * 
     * If the command system fails to find a match, this command will be 
     * executed. This can also be seen as the root node command, so if the
     * user were to input a 'command' that is totally empty, this command will
     * be executed.
     * 
     * The arguments will be filled with whatever the user typed in.
     * 
     * @param unknownCommand The command to execute when the command system
     *                       cannot find a suitable command to execute.
     */
    public void setUnknownCommand(T unknownCommand)
    {
        this.unknownCommand = unknownCommand;
    }
    
    /**
     * Prints the command tree to a prinstream.
     * 
     * This prints out the structure of the command tree. It shows what the
     * command system sees, so you can get an idea on the behavior of the
     * command system when the user types in commands. This is intended to be
     * a useful debugging tool.
     * 
     * @param stream The print stream to print the output to.
     */
    public void printCommandTree(PrintStream stream)
    {
        final String unknownCommandString = unknownCommand == null
                ? "null"
                : unknownCommand.getClass().getSimpleName();
        
        stream.println("UNKNOWN_COMMAND: " + unknownCommandString);
        root.print(stream, 0);
    }

    /**
     * Checks if the command system is case sensitive or not.
     * @return True if it is case sensitive, false if it is not case sensitive.
     * 
     * @see #setCaseSensitive(boolean) setCaseSensitive
     */
    public boolean isCaseSensitive()
    {
        return SyntaxElement.isCaseSensitive();
    }

    /**
     * Sets if the command system is case sensitive or not.
     * 
     * When the command system is not case sensitive, case will be ignored
     * when matching the command. Arguments and preArguments will retain their
     * case.
     * 
     * Case sensitivity is set to false by default.
     * 
     * @param isCaseSensitive True, then the command system will be case
     * sensitive. False and it will not be.
     */
    public void setCaseSensitive(boolean isCaseSensitive)
    {
        SyntaxElement.setCaseSensitive(isCaseSensitive);
    }
}
