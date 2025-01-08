package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class LinearProbingHashMapTest {

    /** 'Testing' Map used (no randomization) to check placement of entries in the hash table */
    private Map<Integer, String> testMap;
    
    /** 'Production' Map (with randomization) to check correctness of ADT behaviors */
    private Map<Integer, String> prodMap;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        testMap = new LinearProbingHashMap<Integer, String>(7, true);
        prodMap = new LinearProbingHashMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, testMap.size());
        assertTrue(testMap.isEmpty());
        
        assertNull(testMap.put(1, "one"));
        assertEquals(1, testMap.size());
        assertEquals("one", testMap.get(1));

        assertNull(testMap.put(2, "two"));
        assertEquals(2, testMap.size());
        assertEquals("two", testMap.get(2));

        assertNull(testMap.put(3, "three"));
        assertEquals(3, testMap.size());
        assertEquals("three", testMap.get(3));
        
        assertNull(testMap.put(9, "nine")); 
        assertEquals(4, testMap.size());
        assertEquals("nine", testMap.get(9));
        
        
        // You should create some collisions to check that entries
        // are placed in the correct buckets.
        //
        // You should also use the prodMap to check that put works
        // as expected when randomization is used internally. You can't
        // check the placement of entries within the hash table,
        // but you can still check that put gives the correct outputs when
        // randomization is used internally.
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
       assertTrue(testMap.isEmpty());
        
        testMap.put(1, "one");
        testMap.put(2, "two");
        testMap.put(3, "three");
        testMap.put(4, "nine"); 

        assertEquals("one", testMap.get(1));
        assertEquals("two", testMap.get(2));
        assertEquals("three", testMap.get(3));
        assertEquals("nine", testMap.get(4));
        
        assertNull(testMap.get(6));
        prodMap.put(1, "one");
        prodMap.put(2, "two");
        prodMap.put(3, "three");

        assertEquals("one", prodMap.get(1));
        assertEquals("two", prodMap.get(2));
        assertEquals("three", prodMap.get(3));
        assertNull(prodMap.get(5));

        // You should also use the prodMap to check that get works
        // as expected when randomization is used internally.
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(testMap.isEmpty());
        testMap.put(1, "one");
        testMap.put(2, "two");
        testMap.put(3, "three");
        testMap.put(9, "nine"); 

        assertEquals("two", testMap.remove(2));
        assertEquals(3, testMap.size());
        assertNull(testMap.get(2));
        assertEquals("one", testMap.remove(1));
        assertEquals(2, testMap.size());
        assertNull(testMap.get(1));
        assertNull(testMap.remove(2)); 
        
        prodMap.put(1, "one");
        prodMap.put(2, "two");
        prodMap.put(3, "three");

        assertEquals("two", prodMap.remove(2));
        assertEquals(2, prodMap.size());
        assertNull(prodMap.get(2));

        // You should also use the prodMap to check that remove works
        // as expected when randomization is used internally. You can't
        // check the placement of entries within the hash table,
        // but you can still check that remove gives the correct outputs when
        // randomization is used internally.  
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertTrue(testMap.isEmpty());
        testMap.put(1, "string1");
        testMap.put(2, "string2");
        testMap.put(3, "string3");
        
        Iterator<Integer> it = testMap.iterator();
        assertTrue(it.next() == 1);
        assertTrue(it.next() == 2);
        assertTrue(it.next() == 3);
        assertFalse(it.hasNext());
        
        testMap.remove(2);
        it = testMap.iterator();
        assertTrue(it.next() == 1);
        assertTrue(it.next() == 3);
        assertFalse(it.hasNext());
    }   
}