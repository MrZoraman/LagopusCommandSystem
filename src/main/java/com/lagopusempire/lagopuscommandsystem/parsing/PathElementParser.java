package com.lagopusempire.lagopuscommandsystem.parsing;

/**
 *
 * @author MrZoraman
 */
class PathElementParser extends ParserBase
{
    public PathElementParser(String pathElement)
    {
        super(pathElement);
    }

    @Override
    public void iterate(int index, char c)
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
