package com.lagopusempire.lagopuscommandsystem.parsing;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * This runtime exception is thrown if the command system experiences a parsing
 * error.
 *
 * @author MrZoraman
 */
public class ParseFailException extends RuntimeException
{

    private final int[] parseFailIndexes;
    private final String problemSyntax;

    public ParseFailException(String message, String problemSyntax, int... parseFailIndex)
    {
        super(message);
        this.parseFailIndexes = parseFailIndex;
        this.problemSyntax = problemSyntax;

        Arrays.sort(parseFailIndexes);
    }

    /**
     * Gets the command syntax that the parser failed to parse successfully.
     *
     * @return problemSyntax The syntax that caused the problem. This will equal
     * whatever was passed in the
     * {@link com.lagopusempire.lagopuscommandsystem.CommandSystem#registerCommand registerCommand}
     * method.
     */
    public String getProblemSyntax()
    {
        return problemSyntax;
    }

    /**
     * Prints basic info to a prinstream.
     * 
     * This is a quick way to dump all of the exception's relevant info into
     * a prinstream. Info includes the syntax that failed to be parsed,
     * The location of the errors and the stack trace. If this method
     * does not format things to your liking, this class offers the methods to
     * build the error message yourself.
     * 
     * @param stream The stream to print the output to
     * 
     * @see #getProblemSyntax getProblemSyntax
     * @see #makeProblemArrows makeProblemArrows
     */
    public void printInfo(PrintStream stream)
    {
        stream.println(problemSyntax);
        stream.println(makeProblemArrows('^'));
        printStackTrace(stream);
    }

    /**
     * Makes a string that contains arrows that point directly to where the
     * parser failed.
     * 
     * If the string that is returned with this method is aligned with the 
     * problem syntax, the arrows will point to the exact location where the
     * parser failed.
     * 
     * @param arrow The character to use as the pointer. 
     * The {@link #printInfo(java.io.PrintStream) printInfo} method uses '^'.
     * 
     * @return The string containing the arrows at the correct locations.
     */
    public String makeProblemArrows(char arrow)
    {
        final StringBuilder builder = new StringBuilder(problemSyntax.length());

        int parseFailIndex = 0;
        for (int ii = 0; ii < problemSyntax.length(); ii++)
        {
            if (parseFailIndexes[parseFailIndex] == ii)
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
