package com.lagopusempire.lagopuscommandsystem.parsing;

/**
 *
 * @author MrZoraman
 */
public class SyntaxParser extends ParserBase
{
    public SyntaxParser(String syntax)
    {
        super(syntax);
    }
    
    private boolean inBraces = false;

    @Override
    public void iterate(int index, char c)
    {
        switch(c)
        {
            case '{':
                inBraces = true;
                break;
            case '}':
                if(!inBraces) throw new ParseFailException("Parsing error! Braces don't match!", index);
                inBraces = false;
                break;
            case ' ':
                if(!inBraces)
                {
                    flush();
                    break;
                }
                //fall through
            default:
                push(c);
        }
    }
}
