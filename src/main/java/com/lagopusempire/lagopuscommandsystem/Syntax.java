package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.PathElementParser;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        final PathElementParser parser = new PathElementParser(path[0]);
        final String[] pathElements = parser.parse();
        final String[] subPath = Arrays.copyOfRange(path, 1, path.length);
        
        for(String pathElement : pathElements)
        {
            if(!children.containsKey(pathElement))
            {
                children.put(pathElement, new Syntax());
            }

            if(path.length > 1)
            {
                children.get(pathElement).addSyntax(subPath, command);
            }
            else
            {
                children.get(pathElement).command = command;
            }
        }
    }
    
    public ICommand matchCommand(String path)
    {
        path = path.trim();
        final SyntaxMatchPackage bestMatchPack = findBestMatch(path);
        
        if(bestMatchPack == null || bestMatchPack.bestMatch == null) return command;
        
        final int matchIndex = bestMatchPack.matchIndex;
        final Syntax bestMatch = bestMatchPack.bestMatch;
        
//        if(bestMatch == null)
//        {
//            if(children.keySet().contains("*"))
//            {
//                String str = removeFirstWord(path);
//                return children.get("*").matchCommand(str);
//            }
//        }
        
        return bestMatch.matchCommand(path.substring(matchIndex, path.length()));
    }
    
    private class SyntaxMatchPackage
    {
        int matchIndex;
        Syntax bestMatch;
    }
    
    @SuppressWarnings("empty-statement")
    private SyntaxMatchPackage findBestMatch(String path)
    {
        if(path.isEmpty()) return null;
        
        final Set<String> childrenSyntaxPaths = children.keySet();
        final char[] pathChars = path.toCharArray();
        
        int highestIndexMatch = 0;
        Syntax bestMatch = null;
        for(String childSyntaxPath : childrenSyntaxPaths)
        {
            if(childSyntaxPath.equals("*")) continue;
            
            final char[] childSyntaxPathChars = childSyntaxPath.toCharArray();
            int index = 0;
            for(; index < childSyntaxPathChars.length && childSyntaxPathChars[index] == pathChars[index]; index++);//EMPTY STATEMENT
            
            if(index > highestIndexMatch)
            {
                highestIndexMatch = index;
                bestMatch = children.get(childSyntaxPath);
            }
        }
        
        if(bestMatch == null)
        {
            if(childrenSyntaxPaths.contains("*"))
            {
                bestMatch = children.get("*");
                highestIndexMatch = getFirstWordLength(path);
            }
        }
        
        SyntaxMatchPackage pack = new SyntaxMatchPackage();
        pack.bestMatch = bestMatch;
        pack.matchIndex = highestIndexMatch;
        return pack;
    }
    
    private int getFirstWordLength(String string)
    {
        if(!string.contains(" ")) return string.length();
        
        return string.split(" ")[0].length();
    }
    
    private String removeFirstWord(String string)
    {
        if(!string.contains(" ")) return string;
        
        final String[] parts = string.split(" ");
        return string.substring(parts[0].length(), string.length());
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
