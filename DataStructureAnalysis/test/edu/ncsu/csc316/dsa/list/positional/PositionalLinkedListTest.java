package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class PositionalLinkedListTest {

	/** PositionalLinkedList being tested */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including any expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
    }
    
    /**
     * Test the output of the last() behavior, including any expected exceptions
     */
    @Test
    public void testLast() {
        assertNull(list.last());
        list.addFirst("First");
        list.addLast("Middle");
        assertEquals(list.last().getElement(), "Middle");
        list.addLast("Last");
        assertEquals(list.last().getElement(), "Last");
        
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
    	
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        Iterator<Position<String>> it = list.positions().iterator();
        assertEquals(it.next().getElement(), "one");
        list.addFirst("New");
        it = list.positions().iterator();
        assertEquals(it.next().getElement(), "New");

    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.addLast("one");
        assertEquals(1, list.size());
        list.addFirst("First");
        list.addLast("Middle");
        assertEquals(list.last().getElement(), "Middle");
        list.addLast("Last");
        assertEquals(list.last().getElement(), "Last");
       
    }
    
    /**
     * Test the output of the before(position) behavior, including any expected exceptions
     */ 
    @Test
    public void testBefore() {
        list.addFirst("First");
        list.addLast("Last");
        assertEquals(list.before(list.last()), list.first());
    }
    
    /**
     * Test the output of the after(position) behavior, including any expected exceptions
     */     
    @Test
    public void testAfter() {
        list.addFirst("First");
        list.addLast("Middle");
        list.addLast("Last");
        
        assertEquals(list.after(list.before(list.last())), list.last());
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testAddBefore() {
        list.addFirst("First");
        list.addLast("Last");
        assertEquals(list.before(list.last()), list.first());
        list.addBefore(list.last(), "test");
        assertEquals(list.before(list.last()).getElement(), "test");
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testAddAfter() {
        list.addFirst("First");
        list.addLast("Last");
        assertEquals(list.before(list.last()), list.first());
        list.addAfter(list.first(), "test");
        assertEquals(list.after(list.first()).getElement(), "test");
    }
    
    /**
     * Test the output of the set(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testSet() {
        list.addFirst("First");
        list.set(list.last(), "Last");
        assertEquals(list.last().getElement(), "Last");
    }
    
    /**
     * Test the output of the remove(position) behavior, including any expected exceptions
     */     
    @Test
    public void testRemove() {
        list.addFirst("First");
        list.addLast("Middle");
        String middle = list.last().getElement();
        list.addLast("Last");
        String last = list.last().getElement();
        assertEquals(list.remove(list.last()), last);
        assertEquals(list.last().getElement(), middle);
        assertEquals(list.remove(list.last()), middle);
       
        list.remove(list.first());
        assertFalse(list.iterator().hasNext());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including any expected exceptions
     */     
    @Test
    public void testIterator() {
    	
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
        assertEquals("one", list.first().getElement());
        
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
        assertTrue(list.size() == 2);
        it.next();
        it.remove();
        assertTrue(list.size() == 1);

        // Use your ArrayBasedList and SinglyLinkedList test cases as a guide
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including any expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
    }

}