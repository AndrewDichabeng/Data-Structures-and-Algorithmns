import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author  :   Andrew Dichabeng
 * file     :   ChangeTest
 * description  :   This file conducts unit testing for the Change class.
 */

public class ChangeTest {

    private Change c1;
    private Change c2;
    private Change c3;

    @Before
    public void setUp() {
        final int amount1 = 1;
        final int amount2 = 5;
        final int amount3 = 10;

        c1 = new Change(amount1);
        c2 = new Change(amount2);
        c3 = new Change(amount3);
    }

    @Test
    public void getAmount() throws Exception {
        assertEquals(1, c1.getAmount());
        assertEquals(5, c2.getAmount());
        assertEquals(10, c3.getAmount());
    }

    @Test
    public void getCounter() throws Exception {
        assertEquals(1, c1.getCounter());
        assertEquals(2, c2.getCounter());
        assertEquals(4, c3.getCounter());
    }

    @Test
    public void setAmount() throws Exception {
        assertEquals(1, c1.getAmount());
        c1.setAmount(23);
        assertEquals(23, c1.getAmount());

        assertEquals(5, c2.getAmount());
        c2.setAmount(32);
        assertEquals(32, c2.getAmount());

        assertEquals(10, c3.getAmount());
        c3.setAmount(45);
        assertEquals(45, c3.getAmount());
    }

    @Test
    public void setCounter() throws Exception {
        assertEquals(1, c1.getCounter());
        c1.setCounter(1024);
        assertEquals(1024, c1.getCounter());

        assertEquals(2, c2.getCounter());
        c2.setCounter(2048);
        assertEquals(2048, c2.getCounter());

        assertEquals(4, c3.getCounter());
        c3.setCounter(64);
        assertEquals(64, c3.getCounter());
    }

}