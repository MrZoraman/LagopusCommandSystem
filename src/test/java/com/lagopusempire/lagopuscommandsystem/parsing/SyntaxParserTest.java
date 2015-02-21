package com.lagopusempire.lagopuscommandsystem.parsing;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author MrZoraman
 */
public class SyntaxParserTest
{
    @Test
    public void testElementPartSplitting()
    {
        String str = "a b c {d e} f {g h i j}";
        SyntaxParser parser = new SyntaxParser(str);
        
        String[] expected = {"a", "b", "c", "d e", "f", "g h i j"};
        String[] actual = parser.parse();
        
        Assert.assertArrayEquals(expected, actual);
    }
}
