package com.lagopusempire.lagopuscommandsystem;

import com.lagopusempire.lagopuscommandsystem.parsing.ParseFailException;
import com.lagopusempire.lagopuscommandsystem.parsing.SyntaxParser;
import java.util.ArrayList;

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
            String[] s = parser.parse();
            root.addSyntax(s, command);
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
    
    public void execute(String input)
    {
        CommandResult result = root.matchCommand(input, new ArrayList<>());
        ICommand cmd = result.command;
        if(cmd == null)
            System.out.println("Command not found!");
        else
            cmd.execute(result.preArgs, result.args);
    }
}
