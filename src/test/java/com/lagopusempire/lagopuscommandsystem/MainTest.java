package com.lagopusempire.lagopuscommandsystem;

import java.util.Arrays;
import java.util.Scanner;
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
        
        new MainTest().testRun();
    }
    
    public void testRun()
    {
        try (Scanner scan = new Scanner(System.in))
        {
            CommandSystem<ICommand> lcs = new CommandSystem<>();
            
            lcs.registerCommand("a", new CommandTester("Hello!"));
            lcs.registerCommand("a b", new CommandTester("Wah!"));
            //TODO: 'ab' = wah!
            lcs.registerCommand("b", new CommandTester("Hello again!"));
            lcs.registerCommand("b c|d", new CommandTester("woah!"));
            lcs.registerCommand("e f|g h", new CommandTester("dayum!"));
            
            lcs.registerCommand("i j|{k l} m", new CommandTester("woot!"));
            
            lcs.registerCommand("n * o", new CommandTester("the world is broken!"));
            
            lcs.registerCommand("p * q * s", new CommandTester("I don't even know at this point!"));
            
            lcs.registerCommand("t * * v", new CommandTester("why?!"));
            
            lcs.registerCommand("t * w v", new CommandTester("uwot"));
            
//        lcs.registerCommand("x } oops", new CommandTester("oops"));
        lcs.registerCommand("y {oops {", new CommandTester("woops"));
//        lcs.registerCommand("z { lady dady da", new CommandTester("dang"));
            
            while(true)
            {
                System.out.print("> ");
                String input = scan.nextLine();
                if(input.equalsIgnoreCase("exit"))
                    break;
                else
                {
                    CommandResult<ICommand> result = lcs.getCommand(input);
                    if(result.command == null)
                    {
                        System.out.println("Command not found!");
                    }
                    else
                    {
                        result.command.execute(result.preArgs, result.args);
                    }
                }
            }
        }
    }
    
    class CommandTester implements ICommand
    {
        private final String str;
        
        public CommandTester(String str)
        {
            this.str = str;
        }
        
        @Override
        public void execute(String[] preArgs, String[] args)
        {
            System.out.println("Command executed!");
            System.out.println("preArgs: " + Arrays.toString(preArgs));
            System.out.println("args: " + Arrays.toString(args));
            System.out.println("Message: " + str);
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
