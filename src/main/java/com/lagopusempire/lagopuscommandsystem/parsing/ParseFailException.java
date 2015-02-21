package com.lagopusempire.lagopuscommandsystem.parsing;

/**
 *
 * @author MrZoraman
 */
public class ParseFailException extends RuntimeException
{
    private final int parseFailIndex;
    
    public ParseFailException(String message, int parseFailIndex)
    {
        super(message);
        this.parseFailIndex = parseFailIndex;
    }
    
    public int getIndexFailedAt()
    {
        return parseFailIndex;
    }
}
