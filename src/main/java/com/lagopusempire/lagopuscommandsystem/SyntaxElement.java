package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.ElementParser;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author MrZoraman
 */
class SyntaxElement<T>
{
    private final Map<String, SyntaxElement> children = new HashMap<>();
    
    private T command = null;
    
    public SyntaxElement() { }
    
    void addSyntax(String[] path, T command)
    {
        final ElementParser parser = new ElementParser(path[0]);
        final String[] pathElements = parser.parse();
        final String[] subPath = Arrays.copyOfRange(path, 1, path.length);
        
        for(String pathElement : pathElements)
        {
            if(!children.containsKey(pathElement))
            {
                children.put(pathElement, new SyntaxElement());
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
    
    CommandResult matchCommand(String path, List<String> preArgs)
    {
        path = path.trim();
        final SyntaxMatchPackage bestMatchPack = findBestMatch(path);
        
        if(bestMatchPack == null || bestMatchPack.bestMatch == null)
        {
            CommandResult result = new CommandResult();
            result.command = command;
            result.args = path.split(" ");
            result.preArgs = preArgs.toArray(new String[preArgs.size()]);
            return result;
        }
        
        if(bestMatchPack.wildcard != null)
        {
            preArgs.add(bestMatchPack.wildcard);
        }
        
        final int matchIndex = bestMatchPack.matchIndex;
        final SyntaxElement bestMatch = bestMatchPack.bestMatch;
        
        return bestMatch.matchCommand(path.substring(matchIndex, path.length()), preArgs);
    }
    
    private class SyntaxMatchPackage
    {
        int matchIndex;
        SyntaxElement bestMatch;
        String wildcard;
    }
    
    private SyntaxMatchPackage findBestMatch(String path)
    {
        if(path.isEmpty()) return null;
        
        final Set<String> childrenSyntaxPaths = children.keySet();
        final char[] pathChars = path.toCharArray();
        
        int highestIndexMatch = 0;
        SyntaxElement bestMatch = null;
        String wildcard = null;
        
        for(String childSyntaxPath : childrenSyntaxPaths)
        {
            if(childSyntaxPath.equals("*")) continue;
            
            final char[] childSyntaxPathChars = childSyntaxPath.toCharArray();
            
            int index = 0;
            while(index < childSyntaxPathChars.length 
                    && index < pathChars.length
                    && childSyntaxPathChars[index] == pathChars[index])
                index++;
            
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
                String firstWord = getFirstWord(path);
                highestIndexMatch = firstWord.length();
                wildcard = firstWord;
            }
        }
        
        SyntaxMatchPackage pack = new SyntaxMatchPackage();
        pack.bestMatch = bestMatch;
        pack.matchIndex = highestIndexMatch;
        pack.wildcard = wildcard;
        return pack;
    }
    
    private String getFirstWord(String string)
    {
        if(!string.contains(" ")) return string;
        
        return string.split(" ")[0];
    }
    
    void print(int level)
    {
        for(String path : children.keySet())
        {
            printPreSpacing(level);
            SyntaxElement child = children.get(path);
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
