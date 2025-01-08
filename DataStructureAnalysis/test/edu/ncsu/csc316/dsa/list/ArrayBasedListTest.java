package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class ArrayBasedListTest {

	/** ArrayBasedList being tested */
    private ArrayBasedList<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /** Tests the checkIndex and checkIndexForAdd methods */
    @Test
    public void testCheckIndex() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.checkIndex(-1));
    	assertThrows(IndexOutOfBoundsException.class, () -> list.checkIndex(1));
    	assertThrows(IndexOutOfBoundsException.class, () -> list.checkIndexForAdd(-1));
    	assertThrows(IndexOutOfBoundsException.class, () -> list.checkIndexForAdd(1));
    }
    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	list.add(0, "first");
    	list.add(1, "second");
    	list.add(2, "third");
    	list.add(3, "fourth");
    	list.addLast("random");
    	assertEquals(list.get(4), "random");
    	list.addLast("random2");
    	assertEquals(list.get(5), "random2");
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	list.add(0, "first");
    	list.add(1, "second");
    	list.add(2, "third");
    	list.add(3, "fourth");
    	assertEquals(list.last(), "fourth");
    	list.removeLast();
    	assertEquals(list.last(), "third");
    	list.removeLast();
    	assertEquals(list.last(), "second");
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	list.addFirst("first");
    	assertEquals(list.get(0), "first");
    	list.addFirst("second");
    	assertEquals(list.get(1), "first");
    	assertEquals(list.get(0), "second");
    	list.addFirst("third");
    	assertEquals(list.get(2), "first");
    	assertEquals(list.get(1), "second");
    	assertEquals(list.get(0), "third");
    	list.addFirst("fourth");
    	assertEquals(list.get(3), "first");
    	assertEquals(list.get(2), "second");
    	assertEquals(list.get(1), "third");
    	assertEquals(list.get(0), "fourth");
    	
    	assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    	
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	list.add(0, "first");
    	list.add(1, "second");
    	list.add(2, "third");
    	list.add(3, "fourth");
    	list.removeFirst();
    	assertEquals(list.first(), "second");
    	list.removeFirst();
    	assertEquals(list.first(), "third");
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        list.addLast("two");
        it = list.iterator();
        it.next();
        it.remove();
        assertTrue(list.size() == 1);

        //continue this test case
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	list.add(0, "first");
    	list.add(1, "second");
    	list.add(2, "third");
    	list.add(3, "fourth");
    	list.remove(2);
    	assertEquals(list.get(2), "fourth");
    	list.remove(0);
    	assertEquals(list.get(0), "second");
    	assertEquals(list.get(1), "fourth");
    	assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	list.add(0, "first");
    	list.add(1, "second");
    	list.add(2, "third");
    	list.add(3, "fourth");
    	list.removeFirst();
    	assertEquals(list.get(0), "second");
    	assertEquals(list.get(1), "third");
    	assertEquals(list.get(2), "fourth");
    	list.removeFirst();
    	assertEquals(list.get(0), "third");
    	assertEquals(list.get(1), "fourth");
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	list.add(0, "first");
    	list.add(1, "second");
    	list.add(2, "third");
    	list.add(3, "fourth");
    	list.removeLast();
    	assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    	list.removeLast();
    	assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    	list.removeLast();
    	assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    	list.removeLast();
    	assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "negative"));
        list.add(0, "one");
        list.add(1, "two");
        list.add(2, "three");
        assertEquals("one", list.set(0, "ONE"));
        assertEquals("ONE", list.get(0));
        assertEquals("two", list.set(1, "TWO"));
        assertEquals("TWO", list.get(1));
        assertEquals("three", list.set(2, "THREE"));
        assertEquals("THREE", list.get(2));
    }
}
