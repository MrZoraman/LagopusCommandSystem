package com.lagopusempire.lagopuscommandsystem;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MrZoraman
 */
public class MainTest
{
    @Test
    public void virtualPathTest()
    {
        CommandSystem<Integer> lcs = new CommandSystem<>();
        
        lcs.registerCommand("", 0);
        lcs.registerCommand("a", 1);
        lcs.registerCommand("a b", 2);
        lcs.registerCommand("b", 3);
        lcs.registerCommand("b c|d", 4);
        lcs.registerCommand("e f|g h", 5);
        lcs.registerCommand("i j|{k l}", 6);
        lcs.registerCommand("n * o", 7);
        lcs.registerCommand("p * q * s", 8);
        lcs.registerCommand("t * * v", 9);
        lcs.registerCommand("t * w v", 10);
        
        lcs.registerCommand("{home set}|sethome", 11);
        lcs.registerCommand("home", 12);
        lcs.registerCommand("homenwinnigish", 13);
        
        lcs.setUnknownCommand(14);
        
        assertEquals(0, (long) lcs.getCommand("").command.get());
        assertEquals(1, (long) lcs.getCommand("a").command.get());
        assertEquals(2, (long) lcs.getCommand("a b").command.get());
        assertEquals(3, (long) lcs.getCommand("b").command.get());
        assertEquals(4, (long) lcs.getCommand("b c").command.get());
        assertEquals(4, (long) lcs.getCommand("b d").command.get());
        assertEquals(5, (long) lcs.getCommand("e f h").command.get());
        assertEquals(5, (long) lcs.getCommand("e g h").command.get());
        assertEquals(6, (long) lcs.getCommand("i j").command.get());
        assertEquals(6, (long) lcs.getCommand("i k l").command.get());
        assertEquals(7, (long) lcs.getCommand("n ? o").command.get());
        assertEquals(8, (long) lcs.getCommand("p ? q ? s").command.get());
        assertEquals(9, (long) lcs.getCommand("t ? ? v").command.get());
        assertEquals(10, (long) lcs.getCommand("t ? w v").command.get());
        assertEquals(11, (long) lcs.getCommand("home set").command.get());
        assertEquals(11, (long) lcs.getCommand("sethome").command.get());
        assertEquals(12, (long) lcs.getCommand("home").command.get());
        assertEquals(13, (long) lcs.getCommand("homenwinnigish").command.get());
        //assertEquals(14, (long) lcs.getCommand("stuff").command);
    }
    
    @Test
    public void testUnkownCommand()
    {
        CommandSystem<Integer> lcs = new CommandSystem<>();
        CommandResult<Integer> cmdResult = lcs.getCommand("stuff");
        assertNotNull(cmdResult);
        assertFalse(cmdResult.command.isPresent());
    }
    
    @Test
    public void testArguments()
    {
        CommandSystem<Integer> lcs = new CommandSystem<>();
        lcs.registerCommand("cmd", 0);
        
        CommandResult<Integer> result = lcs.getCommand("cmd arg0 arg1 arg2");
        assertEquals(0, (long) result.command.get());
        assertEquals("arg0", result.args[0]);
        assertEquals("arg1", result.args[1]);
        assertEquals("arg2", result.args[2]);
    }
    
    @Test
    public void testPreArguments()
    {
        CommandSystem<Integer> lcs = new CommandSystem<>();
        lcs.registerCommand("cmd * part2", 0);
        
        CommandResult<Integer> result = lcs.getCommand("cmd test part2");
        assertEquals(0, (long) result.command.get());
        assertEquals("test", result.preArgs[0]);
    }
}
