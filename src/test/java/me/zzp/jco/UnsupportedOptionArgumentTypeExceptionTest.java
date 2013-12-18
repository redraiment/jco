package me.zzp.jco;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author redraiment
 */
public class UnsupportedOptionArgumentTypeExceptionTest {
    @Test
    public void testThrow() {
        try {
            throw new UnsupportedOptionArgumentTypeException(Class.class);
        } catch (UnsupportedOptionArgumentTypeException e) {
            assertEquals("Type `java.lang.Class' is unsupported, need a constructor with java.lang.String", e.getMessage());
        } catch (Exception e) {
            fail("Unknow exception: ".concat(e.getMessage()));
        }
    }
}
