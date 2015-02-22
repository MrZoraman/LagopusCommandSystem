package com.lagopusempire.lagopuscommandsystem.parsing;

/**
 *
 * @author MrZoraman
 */
public class ElementParser extends SyntaxParserBase
{
    public ElementParser(String pathElement)
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
