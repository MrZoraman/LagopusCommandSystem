package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.ParseFailException;
import com.lagopusempire.lagopuscommandsystem.parsing.SyntaxParser;

/**
 *
 * @author MrZoraman
 */
public class LagopusCommandSystem
{
    private final Syntax root = new Syntax();
    
    public boolean registerCommand(String syntax, ICommand command)
    {
        final SyntaxParser parser = new SyntaxParser(syntax);
        
        try
        {
            root.addSyntax(parser.parse(), command);
        }
        catch (ParseFailException e)
        {
            System.err.println(syntax);
            for(int ii = 0; ii < e.getIndexFailedAt(); ii++)
            {
                System.err.print(" ");
            }
            System.err.println("^ ");
            e.printStackTrace(System.err);
            return false;
        }
        
        return true;
    }
}
