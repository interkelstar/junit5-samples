import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class SimpleTest {

    //************
    //Testing Tags
    //************
    @Test
    @Tag("jenkins")
    void jenkinsOnly() {
        fail("fail");
    }
    
    //*******************
    //Testing Assumptions
    //*******************
    @Test
    public void assumptions() {
        assumeTrue(2==3);
        // ...
    }
    
    @Test
    public void assumptionsAction() {
        assumingThat(2==2, () -> System.out.println("Two is really two!"));
        // ...
    }
    
    //******************
    //Testing Assertions
    //******************
    @Test
    public void assertions() {

        //classic assertEquals
        assertEquals("Java", new TestEntity().getLanguage());

        //lambda for message 
        assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");

        //lambda for condition
        assertTrue(() -> "".isEmpty(), "string should be empty");

        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
        assertAll(
                () -> assertEquals("John", new TestEntity().getFirstName()),
                () -> assertEquals("Doe", new TestEntity().getLastName())
        );
    }

    //***************
    //Testing Timeout
    //***************
    @Test
    void timeoutExceeded() {
        // execution exceeded timeout of 10 ms by 90 ms
        assertTimeout(ofMillis(10), () -> Thread.sleep(100));
    }
    
    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> Thread.sleep(100));
    }
    
    //*****************
    //Testing Exception
    //*****************
    @Test
    void exception() {
        assertThrows(NullPointerException.class, () -> ((Object) null).toString());
    }
    
    class TestEntity {
        String getFirstName() {return "John";}
        String getLastName() {return "Doe";}
        String getLanguage() {return "Java";}
    }
}
