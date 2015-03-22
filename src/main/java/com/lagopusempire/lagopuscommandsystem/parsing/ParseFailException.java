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
    int indexRetrieved = 0;//TODO: what
    private String problemSyntax = null;
    
    public ParseFailException(String message, int... parseFailIndex)
    {
        super(message);
        this.parseFailIndexes = parseFailIndex;
        Arrays.sort(parseFailIndexes);
    }
    
    public void setProblemSyntax(String problemSyntax)
    {
        this.problemSyntax = problemSyntax;
    }
    
    public String getProblemSyntax(String problemSyntax)
    {
        return problemSyntax;
    }
    
    public void printInfo(PrintStream stream)
    {
        stream.println(problemSyntax);
        stream.println(makeProblemArrows('^'));
        printStackTrace(stream);
    }
    
    public String makeProblemArrows(char arrow)
    {
        final StringBuilder builder = new StringBuilder(problemSyntax.length());
        
        int parseFailIndex = 0;
        for(int ii = 0; ii < problemSyntax.length(); ii++)
        {
            if(parseFailIndexes[parseFailIndex] == ii)
            {
                builder.append(arrow);
                parseFailIndex++;
            }
            else
            {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
