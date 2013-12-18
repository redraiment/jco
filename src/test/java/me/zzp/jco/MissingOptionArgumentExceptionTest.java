package me.zzp.jco;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author redraiment
 */
public class MissingOptionArgumentExceptionTest {
    @Test
    public void testThrow() {
        try {
            throw new MissingOptionArgumentException("age", Integer.class);
        } catch (MissingOptionArgumentException e) {
            assertEquals("Option `age' need a `java.lang.Integer' type argument", e.getMessage());
        } catch (Exception e) {
            fail("Unknow exception: ".concat(e.getMessage()));
        }
    }
}
