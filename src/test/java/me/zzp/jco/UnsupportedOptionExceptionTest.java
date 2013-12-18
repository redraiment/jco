package me.zzp.jco;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author redraiment
 */
public class UnsupportedOptionExceptionTest {
    @Test
    public void testThrow() {
        try {
            throw new UnsupportedOptionException("--unknow-option");
        } catch (UnsupportedOptionException e) {
            assertEquals("option `--unknow-option' is unsupported", e.getMessage());
        } catch (Exception e) {
            fail("Unknow exception: ".concat(e.getMessage()));
        }
    }
}
