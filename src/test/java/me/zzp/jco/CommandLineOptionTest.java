package me.zzp.jco;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author redraiment
 */
public class CommandLineOptionTest {
    public static class Employee {
        @Option
        @Required
        private Integer id;

        @Option(shortName = "n")
        private String firstName;
        
        @Option(name = "last-name")
        private String lastName;

        @Option(shortName = "m", name = "manager")
        private Employee[] managers;
        
        public Employee() {
            id = 0;
        }

        public Employee(String id) {
            this.id = Integer.parseInt(id);
        }
    }

    private Employee e;

    @Before
    public void setUp() {
        e = new Employee();
    }

    @Test
    public void testRequired() {
        try {
            CommandLineOption.assign(e, new String[0]);
            fail("Should not get to here");
        } catch (MissingRequiredOptionException e) {
            assertEquals("Option `id' is required", e.getMessage());
        } catch (Exception e) {
            fail("Should not throw any other exception: ".concat(e.getMessage()));
        }
        
        try {
            CommandLineOption.assign(e, new String[] {"--id", "1"});
            assertEquals(1, e.id.intValue());
        } catch (Exception e) {
            fail("Should not throw any exceptions: ".concat(e.getMessage()));
        }
    }
    
    @Test
    public void testShortName() {
        try {
            CommandLineOption.assign(e, new String[] {
                "--id", "1",
                "-n", "Joe"
            });
            assertEquals(1, e.id.intValue());
            assertEquals("Joe", e.firstName);
        } catch (Exception e) {
            fail("Should not throw any exceptions: ".concat(e.getMessage()));
        }
    }
    
    @Test
    public void testLoneName() {
        try {
            CommandLineOption.assign(e, new String[] {
                "--id", "1",
                "--first-name", "Joe",
                "--last-name", "Zhang"
            });
            assertEquals(1, e.id.intValue());
            assertEquals("Zhang", e.lastName);
            assertEquals("Joe", e.firstName);
        } catch (Exception e) {
            fail("Should not throw any exceptions: ".concat(e.getMessage()));
        }
    }
    
    @Test
    public void testArray() {
        try {
            CommandLineOption.assign(e, new String[] {
                "--id", "1",
                "-m", "2",
                "--manager", "3",
                "-m", "123"
            });
            assertEquals(1, e.id.intValue());
            assertNotNull(e.managers);
            assertEquals(3, e.managers.length);
            assertEquals(2, e.managers[0].id.intValue());
            assertEquals(3, e.managers[1].id.intValue());
            assertEquals(123, e.managers[2].id.intValue());
        } catch (Exception e) {
            fail("Should not throw any exceptions: ".concat(e.getMessage()));
        }
    }
}
