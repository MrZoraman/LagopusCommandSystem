package com.lagopusempire.lagopuscommandsystem.parsing;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * This runtime exception is thrown if the command system experiences a 
 * parsing error.
 *
 * @author MrZoraman
 */
public class ParseFailException extends RuntimeException
{
    private final int[] parseFailIndexes;
    int indexRetrieved = 0;
    
    public ParseFailException(String message, int... parseFailIndex)
    {
        super(message);
        this.parseFailIndexes = parseFailIndex;
        Arrays.sort(parseFailIndexes);
    }
    
    /**
     * Prints the error to the specified print stream, along with relevant
     * debug info.
     * 
     * This method provides useful info such as the nature of the syntax
     * error, along with pointing to the exact point(s) where the parser
     * experienced a problem. The stack trace is also printed.
     * 
     * @param problemLine This is the line that the command system failed to
     *                    parse.
     * @param stream      The stream to print error and other info to.
     */
    public void print(String problemLine, PrintStream stream)
    {
        stream.println(problemLine);
        int arrowsPrinted = 0;
        int index = 0;
        for(int ii = 0; ii < parseFailIndexes.length; ii++)
        {
            for(; index < parseFailIndexes[ii] - arrowsPrinted; index++)
            {
                stream.print(" ");
            }
            stream.print("^");
            arrowsPrinted++;
        }
        stream.println();
        printStackTrace(stream);
    }
}
