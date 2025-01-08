package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class SeparateChainingHashMapTest {

    /** 'Testing' Map used (no randomization) to check ordering of contents */
    private Map<Integer, String> testMap;
    
    /** 'Production' Map (with randomization) to check correctness of ADT behaviors */
    private Map<Integer, String> prodMap;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
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
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        testMap = new SeparateChainingHashMap<Integer, String>(7, true);
        prodMap = new SeparateChainingHashMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, testMap.size());
        assertTrue(testMap.isEmpty());
        assertNull(testMap.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(testMap.put(4, "string4"));
        assertEquals(2, testMap.size());
        assertFalse(testMap.isEmpty());
        it = testMap.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
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
        
        assertTrue(testMap.isEmpty());
        testMap.put(1, "string1");
        testMap.put(2, "string2");
        testMap.put(3, "string3");
        
        assertEquals(testMap.get(1), "string1");
        assertEquals(testMap.get(2), "string2");
        assertEquals(testMap.get(3), "string3");
        
        assertTrue(prodMap.isEmpty());
        prodMap.put(1, "string1");
        prodMap.put(2, "string2");
        prodMap.put(3, "string3");
        prodMap.put(4, "string4");
        prodMap.put(5, "string5");
        prodMap.put(6, "string6");
        prodMap.put(7, "string7");
        prodMap.put(8, "string8");
        prodMap.put(9, "string9");
        prodMap.put(10, "string10");
        prodMap.put(11, "string11");
        prodMap.put(12, "string12");
        prodMap.put(13, "string13");
        prodMap.put(14, "string14");
        prodMap.put(15, "string15");
        prodMap.put(16, "string16");
        prodMap.put(17, "string17");
        prodMap.put(18, "string18");
        
        assertEquals(prodMap.get(1), "string1");
        assertEquals(prodMap.get(2), "string2");
        assertEquals(prodMap.get(3), "string3");
        assertEquals(prodMap.get(18), "string18"); //testing resize

        // You should also use the prodMap to check that get works
        // as expected when randomization is used internally.
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(testMap.isEmpty());
        testMap.put(1, "string1");
        testMap.put(2, "string2");
        testMap.put(3, "string3");
        
        assertEquals(testMap.get(1), "string1");
        testMap.remove(1);
        assertNull(testMap.get(1));
        assertNull(testMap.remove(1));
        
        assertTrue(prodMap.isEmpty());
        prodMap.put(1, "string1");
        prodMap.put(2, "string2");
        prodMap.put(3, "string3");
        
        assertEquals(prodMap.get(3), "string3");
        prodMap.remove(3);
        assertNull(prodMap.remove(3));

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