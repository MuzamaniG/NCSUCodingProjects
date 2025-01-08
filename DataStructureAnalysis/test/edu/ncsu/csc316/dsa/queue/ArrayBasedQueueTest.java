package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class ArrayBasedQueueTest {

	/** Queue being tested */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(queue.front(), "one");
        queue.enqueue("two");
        assertEquals(2, queue.size());

    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        queue.enqueue("test");
        queue.enqueue("test2");
        assertEquals(queue.size(), 2);
        assertEquals(queue.dequeue(), "test");
        assertEquals(queue.size(), 1);
        

    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
        queue.enqueue("test");
        queue.enqueue("test2");
        assertEquals(queue.front(), "test");
        queue.enqueue("test3");
        queue.enqueue("test4");
        assertEquals(queue.front(), "test");
        queue.dequeue();
        assertEquals(queue.front(), "test2");
    }

}
