package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class RedBlackTreeMapTest {

	/** Tree to be tested */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        assertEquals(1, (int) tree.root().getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals("two", tree.get(2));
        assertEquals(1, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertEquals("three", tree.get(3));
        assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());

        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases

        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        
        assertNull(tree.get(4));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        
        assertEquals(3, tree.size());
        
        tree.remove(2);
        assertEquals(2, tree.size());
        assertNull(tree.get(2));
        assertEquals(3, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        
        tree.remove(1);
        assertEquals(1, tree.size());
        assertNull(tree.get(1));
        assertEquals(3, (int) tree.root().getElement().getKey());
        
        tree.remove(3);
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    /** Tests remedyDoubleBlack method */
    @Test
    public void testRemedyDoubleBlack() {
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(12, "twelve");
        tree.put(20, "twenty");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.remove(20);
        assertNull(tree.get(20));
        assertEquals(8, tree.size());

        tree.remove(15);
        assertNull(tree.get(15));
        assertEquals(7, tree.size());
        
        tree.remove(7);
        assertNull(tree.get(7));
        assertEquals(6, tree.size());
        tree.remove(12);
        assertNull(tree.get(12));
        assertEquals(5, tree.size());
        tree.remove(10);
        assertNull(tree.get(10));
        assertEquals(4, tree.size());

    }

}
