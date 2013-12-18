package me.zzp.jco;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author redraiment
 */
public class MissingRequiredOptionExceptionTest {
    @Test
    public void testThrow() {
        try {
            throw new MissingRequiredOptionException("name");
        } catch (MissingRequiredOptionException e) {
            assertEquals("Option `name' is required", e.getMessage());
        } catch (Exception e) {
            fail("Unknow exception: ".concat(e.getMessage()));
        }
    }
}
