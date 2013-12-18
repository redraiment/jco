package me.zzp.jco;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author redraiment
 */
public class IllegalOptionArgumentExceptionTest {
    @Test
    public void testThrow() {
        try {
            throw new IllegalOptionArgumentException("redraiment");
        } catch (IllegalOptionArgumentException e) {
            assertEquals("Cannot convert `redraiment' to target type", e.getMessage());
        } catch (Exception e) {
            fail("Unknow exception: ".concat(e.getMessage()));
        }
    }
}
