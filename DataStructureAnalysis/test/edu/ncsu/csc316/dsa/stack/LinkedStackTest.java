package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the LinkedStack class
 */
public class LinkedStackTest {

	/** LinkedStack being tested */
	private Stack<String> stack;
	
    /**
     * Create a new instance of a linked list-based stack before each test case executes
     */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}
	
    /**
     * Test the output of the push(e) behavior
     */ 
	@Test
	public void testPush() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        stack.pop();
		assertTrue(stack.isEmpty());
		stack.push("test");
		assertEquals(stack.top(), "test");
		stack.push("test2");
		assertEquals(stack.top(), "test2");

	}

    /**
     * Test the output of the pop() behavior, including expected exceptions
     */
    @Test
    public void testPop() {
    	
        assertEquals(0, stack.size());
        try {
            stack.pop();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        assertTrue(stack.isEmpty());
        stack.push("test");
        stack.push("test2");
        assertEquals(stack.pop(), "test2");
        assertEquals(stack.pop(), "test");
        assertTrue(stack.isEmpty());
    }

    /**
     * Test the output of the top() behavior, including expected exceptions
     */
    @Test
    public void testTop() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        stack.push("test");
        assertEquals(stack.top(), "test");
        stack.push("test2");
        assertEquals(stack.top(), "test2");
    }

    /**
     * Tests the size method
     */
    @Test
    public void testSize() {
        assertTrue(stack.isEmpty());
        stack.push("test");
        assertEquals(stack.size(), 1);
        stack.push("test2");
        assertEquals(stack.size(), 2);
        stack.pop();
        assertEquals(stack.size(), 1);
    }

    /**
     * Tests the isEmpty method
     */
    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("test");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}
