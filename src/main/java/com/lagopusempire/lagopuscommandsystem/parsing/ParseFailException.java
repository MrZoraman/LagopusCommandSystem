package com.lagopusempire.lagopuscommandsystem.parsing;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;

/**
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
