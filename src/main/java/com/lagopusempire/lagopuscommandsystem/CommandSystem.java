package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.ParseFailException;
import com.lagopusempire.lagopuscommandsystem.parsing.SpaceParser;
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
    private boolean safeParsingMode = true;
    
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
     * @return True if the command was registered successfully. False if there
     *         was a syntax error. If there is a parsing error, info will be
     *         printed to the system.err stream. This is only relevant if
     *         the command system is set to unsafe mode. <b>This method will
     *         always return true in safe mode.</b>
     * 
     * @throws ParseFailException If the error handling mode is set to safe
     *                            (default), then this runtime exception will
     *                            be thrown.
     */
    public boolean registerCommand(String syntax, T command)
    {
        final SpaceParser parser = new SpaceParser(syntax);
        
        try
        {
            root.addSyntax(parser.parse(), command);
        }
        catch (ParseFailException e)
        {
            if(safeParsingMode)
            {
                throw e;
            }
            else
            {
                e.print(syntax, System.err);
                return false;
            }
        }
        
        return true;
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
        return root.matchCommand(input, new ArrayList<String>());
    }
    
    /**
     * Sets the syntax error handling mode.
     * 
     * If the syntax error mode is set to safe, then a runtime exception
     * will be thrown when a syntax error occurs. If the mode is set to
     * unsafe, then a message will be printed to the System.err prinstsream, and
     * the {@link #registerCommand} will return false, but operation will
     * continue.
     * 
     * @param mode The mode to set the command system. to. True sets the mode
     * to safe, while false sets it to unsafe.
     */
    public void setSafeParsingMode(boolean mode)
    {
        this.safeParsingMode = mode;
    }
}
