package com.lagopusempire.lagopuscommandsystem;

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
        
        lcs.registerCommand("a", (preargs, args) -> {System.out.println("Hello!");});
        lcs.registerCommand("a b", (preargs, args) -> {System.out.println("wah!");});
        //TODO: 'ab' = wah!
        lcs.registerCommand("b", (preargs, args) -> {System.out.println("Hello again!");});
        lcs.registerCommand("b c|d", (preargs, args) -> {System.out.println("woah!");});
        lcs.registerCommand("e f|g h", (preargs, args) -> {System.out.println("dayum!");});
        
        lcs.registerCommand("i j|{k l} m", (preargs, args) -> {System.out.println("woot!");});
        
        lcs.registerCommand("n * o", (preargs, args) -> {System.out.println("The world is broken!");});
        
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
