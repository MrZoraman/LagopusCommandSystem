package com.lagopusempire.lagopuscommandsystem.parsing;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author MrZoraman
 */
public class PathElementParserTest
{
    @Test
    public void testPathElementSplitting()
    {
        String str = "|a|b b||c|d|e||";
        ElementParser parser = new ElementParser(str);
        String[] parts = parser.parse();
        String[] expected = {"a", "b b", "c", "d", "e"};
        
        Assert.assertEquals(5, parts.length);
        Assert.assertArrayEquals(expected, parts);
    }
}
