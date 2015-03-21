package com.lagopusempire.lagopuscommandsystem;

import java.io.PrintStream;

/**
 *
 * @author MrZoraman
 */
public class RootSyntaxElement<T> extends SyntaxElement<T>
{
    @Override
    void print(PrintStream stream, int level)
    {
        final String commandString = command == null 
                    ? "null" 
                    : command.getClass().getSimpleName();
        
        stream.println(": " + commandString);
        
        super.print(stream, level);
    }
}
