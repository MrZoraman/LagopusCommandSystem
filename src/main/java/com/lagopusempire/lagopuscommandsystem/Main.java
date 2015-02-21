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
        
        lcs.registerCommand("a", this::a);
        lcs.registerCommand("a b", this::a_b);
        //TODO: 'ab' = wah!
        lcs.registerCommand("b", this::b);
        
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
    
    public void a(String[] preargs, String[] args)
    {
        System.out.println("Hello!");
    }
    
    public void a_b(String[] preargs, String[] args)
    {
        System.out.println("wah!");
    }
    
    public void b(String[] preargs, String[] args)
    {
        System.out.println("Hello again!");
    }
}
