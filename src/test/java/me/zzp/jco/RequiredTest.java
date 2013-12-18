package me.zzp.jco;

import java.lang.reflect.Field;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author redraiment
 */
public class RequiredTest {
    @Required
    private String requiredOption;
    private String optionalOption;

    @Test
    public void testRequired() {
        try {
            Field requiredOption = RequiredTest.class.getDeclaredField("requiredOption");
            Required required = requiredOption.getAnnotation(Required.class);
            assertNotNull(required);
        } catch (NoSuchFieldException e) {
            fail(e.getMessage());
        } catch (SecurityException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testOptional() {
        try {
            Field optionalOption = RequiredTest.class.getDeclaredField("optionalOption");
            Required optional = optionalOption.getAnnotation(Required.class);
            assertNull(optional);
        } catch (NoSuchFieldException e) {
            fail(e.getMessage());
        } catch (SecurityException e) {
            fail(e.getMessage());
        }
    }
}
