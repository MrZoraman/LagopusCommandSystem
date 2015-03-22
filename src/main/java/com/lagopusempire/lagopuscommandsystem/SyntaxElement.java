package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.ElementParser;
import java.io.PrintStream;
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
    private static final int TREE_SPACER = 4;
    
    private static boolean caseSensitive = false;

    private final Map<String, SyntaxElement> children = new HashMap<>();
    
    protected T command = null;
    
    SyntaxElement() { }
    
    void addSyntax(String[] path, T command)
    {
        if(path.length == 0)
        {
            this.command = command;
            return;
        }
        
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
            final CommandResult<T> result = new CommandResult<>();
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
        
        outer:
        for(String childSyntaxPath : childrenSyntaxPaths)
        {
            if(childSyntaxPath.equals("*")) continue;
            
            final char[] childSyntaxPathChars = childSyntaxPath.toCharArray();
            
            int index = 0;
            while(index < childSyntaxPathChars.length && index < pathChars.length)
            {
                final char childChar = caseSensitive 
                        ? childSyntaxPathChars[index] 
                        : Character.toLowerCase(childSyntaxPathChars[index]);
                final char pathChar = caseSensitive 
                        ? pathChars[index] 
                        : Character.toLowerCase(pathChars[index]);
                
                if(childChar == pathChar)
                {
                    index++;
                }
                else if(childChar == ' ' || pathChar == ' ')
                {
                    continue outer;
                }
                else
                {
                    break;
                }
            }
            
            if(childSyntaxPathChars.length - 1 > index && childSyntaxPathChars[index + 1] != ' ')
                continue;
            
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
    
    void print(PrintStream stream, int level)
    {
        for(String path : children.keySet())
        {
            printPreSpacing(stream, level);
            SyntaxElement child = children.get(path);
            
            final String commandString = child.command == null 
                    ? "null" 
                    : child.command.getClass().getSimpleName();
            
            stream.println(path + ": " + commandString);
            child.print(stream, level + 1);
        }
    }
    
    private void printPreSpacing(PrintStream stream, int level)
    {
        for(int spacesToPrint = 0;
                spacesToPrint < TREE_SPACER * level;
                spacesToPrint++)
        {
            stream.print(" ");
        }
    }

    public static boolean isCaseSensitive()
    {
        return caseSensitive;
    }

    public static void setCaseSensitive(boolean caseSensitive)
    {
        SyntaxElement.caseSensitive = caseSensitive;
    }
}
