package com.lagopusempire.lagopuscommandsystem.parsing;

/**
 *
 * @author MrZoraman
 */
public class SpaceParser extends SyntaxParserBase
{
    public SpaceParser(String syntax)
    {
        super(syntax);
    }
    
    private boolean inBraces = false;
    private int openBraceIndex = 0;

    @Override
    protected void iterate(int index, char c)
    {
        switch(c)
        {
            case '{':
                if(inBraces) throw new ParseFailException("Braces are already open!", script, index, openBraceIndex);
                inBraces = true;
                openBraceIndex = index;
                break;
            case '}':
                if(!inBraces) throw new ParseFailException("Braces don't match!", script, index);
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
    
    @Override
    protected void finished()
    {
        if(inBraces) throw new ParseFailException("Braces are still open!", script, openBraceIndex);
    }
}
