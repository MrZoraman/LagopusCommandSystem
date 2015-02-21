package com.lagopusempire.lagopuscommandsystem;

import java.util.Scanner;

/**
 *
 * @author MrZoraman
 */
public class Main
{
    public static void main(String[] _args)
    {
        Scanner scan = new Scanner(System.in);
        LagopusCommandSystem lcs = new LagopusCommandSystem();
        
        lcs.registerCommand("a", (preargs, args) -> {System.out.println("Hello!");});
        lcs.registerCommand("a b", (preargs, args) -> {System.out.println("wah!");});
        //TODO: 'ab' = wah!
        lcs.registerCommand("b", (preargs, args) -> {System.out.println("Hello again!!");});
        
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
}
