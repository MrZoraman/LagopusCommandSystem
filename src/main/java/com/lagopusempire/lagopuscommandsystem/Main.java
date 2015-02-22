package com.lagopusempire.lagopuscommandsystem;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author MrZoraman
 */
public class Main
{
    public static void main(String[] args)
    {
        new Main();
    }
    
    public Main()
    {
        Scanner scan = new Scanner(System.in);
        LagopusCommandSystem lcs = new LagopusCommandSystem();
        
        lcs.registerCommand("a", new CommandTester("Hello!"));
        lcs.registerCommand("a b", new CommandTester("Wah!"));
        //TODO: 'ab' = wah!
        lcs.registerCommand("b", new CommandTester("Hello again!"));
        lcs.registerCommand("b c|d", new CommandTester("woah!"));
        lcs.registerCommand("e f|g h", new CommandTester("dayum!"));
        
        lcs.registerCommand("i j|{k l} m", new CommandTester("woot!"));
        
        lcs.registerCommand("n * o", new CommandTester("the world is broken!"));
        
        while(true)
        {
            System.out.print("> ");
            String input = scan.nextLine();
            if(input.equalsIgnoreCase("exit"))
                break;
            else
                lcs.execute(input);
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
//            System.out.println("preArgs: " + Arrays.toString(preArgs));
            System.out.println("args: " + Arrays.toString(args));
            System.out.println("Message: " + str);
        }
    }
}
