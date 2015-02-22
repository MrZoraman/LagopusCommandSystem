package com.lagopusempire.lagopuscommandsystem.parsing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrZoraman
 */
public abstract class SyntaxParserBase
{
    private final String script;
    
    private final StringBuilder builder = new StringBuilder();
    private final List<String> elements = new ArrayList<>();
    
    public SyntaxParserBase(String script)
    {
        this.script = script;
    }
    
    protected abstract void iterate(int index, char c);
    
    protected void finished() { }
    
    protected void flush()
    {
        if(builder.length() > 0)
        {
            elements.add(builder.toString());
            builder.setLength(0);
        }
    }
    
    protected void push(char c)
    {
        builder.append(c);
    }
    
    public String[] parse()
    {
        char[] chars = script.toCharArray();
        int ii = 0;
        for(ii = 0; ii < chars.length; ii++)
        {
            iterate(ii, chars[ii]);
        }
        
        finished();
        
        flush();
        
        return elementsToArray();
    }
    
    private String[] elementsToArray()
    {
        return elements.toArray(new String[elements.size()]);
    }
}
