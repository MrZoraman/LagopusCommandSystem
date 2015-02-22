package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.ParseFailException;
import com.lagopusempire.lagopuscommandsystem.parsing.SyntaxParser;
import java.util.ArrayList;

/**
 *
 * @author MrZoraman
 */
public class LagopusCommandSystem<T>
{
    private final Syntax<T> root = new Syntax<>();
    
    public boolean registerCommand(String syntax, T command)
    {
        final SyntaxParser parser = new SyntaxParser(syntax);
        
        try
        {
            root.addSyntax(parser.parse(), command);
        }
        catch (ParseFailException e)
        {
            e.print(syntax, System.err);
            return false;
        }
        
        return true;
    }
    
    public CommandResult<T> getCommand(String input)
    {
        return root.matchCommand(input, new ArrayList<>());
    }
}
