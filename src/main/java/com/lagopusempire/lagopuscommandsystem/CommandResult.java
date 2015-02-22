package com.lagopusempire.lagopuscommandsystem;

/**
 * A package of relevant data returned by the command system given user input.
 * 
 * This class acts as a container for the command itself, the preArgs and the
 * args. This way, the user of the library can use this data however they want,
 * allowing the library to be more flexible.
 * 
 * @author MrZoraman
 * @param <T> The type of command to be used. 
 *            See {@link CommandSystem#registerCommand}
 */
public class CommandResult<T>
{
    /**
     * The command matched to the syntax by the command system.
     * 
     * If no command was found, this will be null.
     */
    public T command;
    
    /**
     * The arguments that were read from the wildcards specified in the syntax.
     */
    public String[] preArgs;
    
    /**
     * The arguments that were trailing after the input after the best
     * match was found and returned.
     */
    public String[] args;
}
