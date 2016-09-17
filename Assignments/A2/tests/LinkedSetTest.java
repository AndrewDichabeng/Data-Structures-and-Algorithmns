import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The unit testing of the LinkedSet class using JUnit 4.
 * @author  Andrew Dichabeng
 * @version 1.0
 */

public class LinkedSetTest {

    private LinkedSet<Integer> integerSet;
    private LinkedSet<String>  stringSet;

    @Before
    public void setUp() {
        integerSet = new LinkedSet<>();
        stringSet = new LinkedSet<>();

        integerSet.add(1);
        stringSet.add("A");
    }

    @Test
    public void add() throws Exception {

        assertEquals(1, integerSet.getCurrentSize());   /* assert the pre-condition */
        assertEquals(1, stringSet.getCurrentSize());

        integerSet.add(2);                              /* add an element to the list */
        stringSet.add("B");

        assertEquals(2, integerSet.getCurrentSize());   /* assert the post-condition */
        assertEquals(2, stringSet.getCurrentSize());
    }

    @Test
    public void remove() throws Exception {

        assertEquals(1, integerSet.getCurrentSize());   /* assert the pre-condition */
        assertEquals(1, stringSet.getCurrentSize());

        assertTrue(integerSet.remove(1));               /* assert true */
        assertTrue(stringSet.remove("A"));

        assertEquals(0, integerSet.getCurrentSize());   /* assert the post-condition */
        assertEquals(0, stringSet.getCurrentSize());

        assertFalse(integerSet.remove(5));               /* assert false */
        assertFalse(stringSet.remove("AB"));
    }

    @Test
    public void remove1() throws Exception {

        assertEquals(1, integerSet.getCurrentSize());   /* assert the pre-condition */
        assertEquals(1, stringSet.getCurrentSize());

        assertSame(1, integerSet.remove());
        assertSame("A", stringSet.remove());

        assertEquals(0, integerSet.getCurrentSize());   /* assert the post-condition */
        assertEquals(0, stringSet.getCurrentSize());
    }

    @Test
    public void clear() throws Exception {

        /* add 3 values to the set and the size = 4 */
        this.integerSet.add(0);
        this.stringSet.add("B");

        this.integerSet.add(2);
        this.stringSet.add("C");

        this.integerSet.add(3);
        this.stringSet.add("D");

        /* assert the size has increased to 4 */
        assertEquals(4, this.integerSet.getCurrentSize());
        assertEquals(4, this.stringSet.getCurrentSize());

        /* clear the set and the size = 0 */
        this.integerSet.clear();
        this.stringSet.clear();

        /* assert the size has been reduced to 0 */
        assertEquals(0, this.integerSet.getCurrentSize());
        assertEquals(0, this.stringSet.getCurrentSize());
    }

    @Test
    public void contains() throws Exception {

        String [] stringArray = {"a", "b", "c", "d"};       /* an array of Strings to be added */

        for (int i = 0; i < 10; i++) {                      /* add integers to the set */
            this.integerSet.add(i);
        }

        for (int i = 0; i < this.integerSet.getCurrentSize(); i++) {    /* check if the set contains all the integers */
            assertTrue(this.integerSet.contains(i));
        }

        for (int i = 0; i < stringArray.length; i++) {      /* add the string from the stringArray to the stringSet */
            this.stringSet.add(stringArray[i]);
        }

        for (String s: stringArray) {                       /* assert that stringSet contains all of the strings from the stringArray*/
            assertTrue(this.stringSet.contains(s));
        }

        assertFalse(this.integerSet.contains(100));         /* assert false cases */
        assertFalse(this.stringSet.contains("XYZ"));
    }

    @Test
    public void getCurrentSize() throws Exception {

        assertEquals(1, integerSet.getCurrentSize());
        assertEquals(1, stringSet.getCurrentSize());
    }

    @Test
    public void isEmpty() throws Exception {

        assertFalse(integerSet.isEmpty());
        assertFalse(stringSet.isEmpty());

        integerSet.remove();
        stringSet.remove();

        assertTrue(integerSet.isEmpty());
        assertTrue(stringSet.isEmpty());
    }

    @Test
    public void isFull() throws Exception {
        /* TODO : Improve test (The MAX_VALUE is too large for performance. ) */
        for (int i = 0; i <= 100; i ++) {
            this.integerSet.add(i);
            assertFalse(this.integerSet.isFull());
        }
    }

    @Test
    public void toArray() throws Exception {

        String [] stringArray = {"A", "AB", "BC", "CD", "DE", "EF"};    /* include "A" from the setUp method */
        int [] integerArray = {1, 37, 59, 87, 63, 92, 105};             /* include 1 from the setUp method */

        for (String s : stringArray ) {                                 /* initialize the string set */
            this.stringSet.add(s);
        }

        for (int i : integerArray) {                                    /* initialize the integer set */
            this.integerSet.add(i);
        }

        /* calls to the method */
        String [] strSetArray = this.stringSet.toArray();
        Integer [] intSetArray = this.integerSet.toArray();

        /* comparing the two arrays */
        for (int i = 0; i < stringArray.length; i++ ) {
            assertEquals(stringArray[i], strSetArray[i]);               /* stringArray against strSetArray */
        }

        for (int i = 0; i < integerArray.length; i++ ) {
            assertEquals(integerArray[i], (int) intSetArray[i]);        /* integerArray against intSetArray */
        }

    }

    @Test
    public void union() throws Exception {

        int [] intArray = {67, 98, 87, 66, 54};
        int [] unionIntSet = {1, 0, 2, 3, 67, 98, 87, 66, 54};          /* expected union int set */

        String [] stringArray = {"AB", "BC", "CD", "DE"};
        String [] unionStringSet = {"A", "AB", "BC", "CD", "DE"};       /* expected union string set */

        LinkedSet<Integer> setA = new LinkedSet<>();
        LinkedSet<String> setC = new LinkedSet<>();

        /* add values to setA and setC */
        for (int i: intArray) { setA.add(i); }
        for (String s : stringArray) { setC.add(s); }

        /* add values 0 to 3 to the integerSet */
        for (int i = 0; i < 4; i++) { this.integerSet.add(i); }

        SetInterface<Integer> setB = this.integerSet.union(setA);   /* create a union of setA and setB (A u B)*/
        SetInterface<String> setD = this.stringSet.union(setC);     /* create a union of setC and setD (C u B)*/

        for (int i = 0; i < setB.getCurrentSize(); i++) {
            assertEquals(unionIntSet[i], (int)setB.toArray()[i]);   /* setB contains the union set, assert the set */
        }

        for (int i = 0; i < setD.getCurrentSize(); i++) {
            assertEquals(unionStringSet[i], setD.toArray()[i]);     /* setD contains the union set, assert the set */
        }

    }

    @Test
    public void intersection() throws Exception {

        int [] intArray = {1, 67, 98, 87, 66, 54, 92};
        int [] intersectionIntSet = {1, 67, 92, 54};

        String [] stringArray = {"A", "AB", "BC", "CD", "DE"};
        String [] intersectionStringSet = {"A", "AB", "DE"};

        LinkedSet<Integer> setA = new LinkedSet<>();
        LinkedSet<String> setC = new LinkedSet<>();

        for (int i : intArray) { setA.add(i); }                                 /* add integer values to set */
        this.integerSet.add(67);
        this.integerSet.add(92);
        this.integerSet.add(54);

        for (String s : stringArray) { setC.add(s); }                           /* add string values to set */
        this.stringSet.add("AB");
        this.stringSet.add("DE");

        SetInterface<Integer> setB = this.integerSet.intersection(setA);        /* create the intersection of setA and setB (A n B) */
        SetInterface<String> setD = this.stringSet.intersection(setC);          /* create the intersection of setC and setD (C n D) */

        for (int i = 0; i < setB.getCurrentSize(); i++) {
            assertEquals(intersectionIntSet[i], (int)setB.toArray()[i]);        /* assert the integer intersection set is the same as expected */
        }

        for (int i = 0; i < setD.getCurrentSize(); i++) {
            assertEquals(intersectionStringSet[i], setD.toArray()[i]);          /* assert the string intersection set is the same as expected*/
        }
    }

    @Test
    public void difference() throws Exception {

        int [] intArray = {1, 67, 98, 87, 66, 54, 92};
        int [] differenceIntSet = {98, 87, 66};

        String [] stringArray = {"A", "AB", "BC", "CD", "DE"};
        String [] differenceStringSet = {"BC", "CD"};

        LinkedSet<Integer> setA = new LinkedSet<>();
        LinkedSet<String> setC = new LinkedSet<>();

        for (int i : intArray) { setA.add(i); }                               /* add integer values to set */
        this.integerSet.add(67);
        this.integerSet.add(92);
        this.integerSet.add(54);

        for (String s : stringArray) { setC.add(s); }                         /* add string values to set */
        this.stringSet.add("AB");
        this.stringSet.add("DE");

        SetInterface<Integer> setB = this.integerSet.difference(setA);        /* create the difference between setA and setB */
        SetInterface<String> setD = this.stringSet.difference(setC);          /* create the difference between setC and setD */

        for (int i = 0; i < setB.getCurrentSize(); i++) {
            assertEquals(differenceIntSet[i], (int)setB.toArray()[i]);        /* assert the integer difference set is the same as expected */
        }

        for (int i = 0; i < setD.getCurrentSize(); i++) {
            assertEquals(differenceStringSet[i], setD.toArray()[i]);          /* assert the string difference set is the same as expected*/
        }
    }

}