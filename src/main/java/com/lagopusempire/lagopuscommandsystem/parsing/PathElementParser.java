package com.lagopusempire.lagopuscommandsystem.parsing;

/**
 *
 * @author MrZoraman
 */
public class PathElementParser extends ParserBase
{
    public PathElementParser(String pathElement)
    {
        super(pathElement);
    }

    @Override
    protected void iterate(int index, char c)
    {
        switch(c)
        {
            case '|':
                flush();
                break;
            default:
                push(c);
        }
    }
}
