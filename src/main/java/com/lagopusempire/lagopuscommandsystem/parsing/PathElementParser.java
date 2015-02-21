package com.lagopusempire.lagopuscommandsystem.parsing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrZoraman
 */
class PathElementParser
{
    private final String pathElement;
    
    public PathElementParser(String pathElement)
    {
        this.pathElement = pathElement;
    }
    
    public String[] parse()
    {
        List<String> pathElements = new ArrayList<>();
        
        char[] chars = pathElement.toCharArray();
        final StringBuilder builder = new StringBuilder();
        for(int ii = 0; ii < chars.length; ii++)
        {
            switch(chars[ii])
            {
                case '|':
                    if(builder.length() > 0)
                    {
                        pathElements.add(builder.toString());
                        builder.setLength(0);
                    }
                    continue;
                default:
                    builder.append(chars[ii]);
            }
        }
        if(builder.length() > 0)
        {
            pathElements.add(builder.toString());
        }
        
        return pathElements.toArray(new String[pathElements.size()]);
    }
}
