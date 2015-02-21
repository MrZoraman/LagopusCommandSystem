package com.lagopusempire.lagopuscommandsystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MrZoraman
 */
class Syntax
{
    private final Syntax root;
    private final Map<String, Syntax> children = new HashMap<>();
    
    private ICommand command = null;
    
    public Syntax(Syntax root, String[] path, ICommand command)
    {
        this.root = root;
        
        if(path.length == 0)
        {
            throw new UnsupportedOperationException("Cannot create syntax with an empty path!");
        }
        
        if(path.length > 1)
        {
            String[] subPath = Arrays.copyOfRange(path, 1, path.length);
            children.put(path[0], new Syntax(this, subPath, command));
        }
        else
        {
            this.command = command;
        }
    }
}
