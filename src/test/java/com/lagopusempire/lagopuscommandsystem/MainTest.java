package com.lagopusempire.lagopuscommandsystem;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author MrZoraman
 */
public class MainTest
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(MainTest.class);
        for(Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }
    }
    
//    @Test
//    public void syntaxPrintTest()
//    {
//        Syntax root = new Syntax();
//        
//        String[] cmd1 = {"a", "b", "c"};
//        String[] cmd2 = {"a", "b", "d"};
//        String[] cmd3 = {"a", "b", "e"};
//        String[] cmd4 = {"a", "f"};
//        String[] cmd5 = {"a", "g"};
//        String[] cmd6 = {"h"};
//        String[] cmd7 = {"a"};
//        
//        root.addSyntax(cmd1, new TestCommand());
//        root.addSyntax(cmd2, new TestCommand());
//        root.addSyntax(cmd3, new TestCommand());
//        root.addSyntax(cmd4, new TestCommand());
//        root.addSyntax(cmd5, new TestCommand());
//        root.addSyntax(cmd6, new TestCommand());
//        root.addSyntax(cmd7, new TestCommand());
//        
//        root.print(0);
//        
//        System.out.println("---");
//        String[] cmd8 = {"a", "b|c", "d"};
//        root = new Syntax();
//        root.addSyntax(cmd8, new TestCommand());
//        root.print(0);
//    }
//    
//    private class TestCommand implements ICommand
//    {
//        @Override
//        public void execute(String[] preArgs, String[] args)
//        {
//        }
//    }
}
