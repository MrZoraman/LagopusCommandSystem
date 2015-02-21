package com.lagopusempire.lagopuscommandsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MrZoraman
 */
class Syntax
{
    private final Map<String, Syntax> children = new HashMap<>();
    
    private ICommand command = null;
    
    public Syntax() { }
    
    public void addSyntax(String[] path, ICommand command)
    {
        final String pathElement = path[0];
        if(!children.containsKey(pathElement))
        {
            children.put(pathElement, new Syntax());
        }
        
        if(path.length > 1)
        {
            final String[] subPath = Arrays.copyOfRange(path, 1, path.length);
            children.get(pathElement).addSyntax(subPath, command);
        }
        else
        {
            children.get(pathElement).command = command;
        }
    }
    
    public void print(int level)
    {
        for(String path : children.keySet())
        {
            printPreSpacing(level);
            Syntax child = children.get(path);
            System.out.println(path + ": " + (child.command != null));
            child.print(level + 1);
        }
    }
    
    private void printPreSpacing(int level)
    {
        for(int ii = 0; ii < level; ii++)
        {
            System.out.print("    ");
        }
    }
}
