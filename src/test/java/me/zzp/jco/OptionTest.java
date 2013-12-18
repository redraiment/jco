package me.zzp.jco;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @author redraiment
 */
public class OptionTest {
    @Option
    private boolean sort;

    @Option(shortName = "b", name = "block-size")
    private String blockSize;

    private Option defaultOption;
    private Option option;
    
    @Before
    public void setUp() {
        try {
            defaultOption = OptionTest.class.getDeclaredField("sort").getAnnotation(Option.class);
            option = OptionTest.class.getDeclaredField("blockSize").getAnnotation(Option.class);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testShortName() {
        assertEquals("", defaultOption.shortName());
        assertEquals("b", option.shortName());
    }

    @Test
    public void testName() {
        assertEquals("", defaultOption.name());
        assertEquals("block-size", option.name());
    }    
}
