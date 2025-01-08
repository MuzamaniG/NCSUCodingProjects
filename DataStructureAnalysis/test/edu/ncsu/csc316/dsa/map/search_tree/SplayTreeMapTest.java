package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class SplayTreeMapTest {

	/** Tree being tested */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(5, (int) tree.root().getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(2, tree.size());
        assertEquals(3, (int) tree.root().getElement().getKey());
        assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());

        tree.put(7, "seven");
        assertEquals(3, tree.size());
        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());

        tree.put(6, "six");
        assertEquals(4, tree.size());
        assertEquals(6, (int) tree.root().getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());

        tree.put(8, "eight");
        assertEquals(5, tree.size());
        assertEquals(8, (int) tree.root().getElement().getKey());


        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
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
    	 tree.put(5, "five");
         tree.put(3, "three");
         tree.put(7, "seven");
         
         assertEquals("five", tree.get(5));
         assertEquals(5, (int) tree.root().getElement().getKey());

         assertEquals("three", tree.get(3));
         assertEquals(3, (int) tree.root().getElement().getKey());
         assertEquals(5, (int) tree.right(tree.root()).getElement().getKey());

         assertEquals("seven", tree.get(7));
         assertEquals(7, (int) tree.root().getElement().getKey());
         assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
         assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(8, "eight");

        tree.remove(2);
        assertEquals(6, tree.size());
        assertNull(tree.get(2));

        tree.remove(4);
        assertEquals(5, tree.size());
        assertNull(tree.get(4));

        tree.remove(5);
        assertEquals(4, tree.size());
        assertNull(tree.get(5));

        assertEquals(3, (int) tree.root().getElement().getKey());
       

        tree.remove(6);
        assertEquals(3, tree.size());
        assertNull(tree.get(6));

        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(8, (int) tree.right(tree.root()).getElement().getKey());

        tree.remove(7);
        assertEquals(2, tree.size());
        assertNull(tree.get(7));

        assertEquals(3, (int) tree.root().getElement().getKey());
        assertEquals(8, (int) tree.right(tree.root()).getElement().getKey());

        tree.remove(8);
        assertEquals(1, tree.size());
        assertNull(tree.get(8));

        assertEquals(3, (int) tree.root().getElement().getKey());
        
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
}