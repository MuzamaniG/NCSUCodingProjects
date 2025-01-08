package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author mhgaausi
 *
 */
public class AVLTreeMapTest {

	/** Tree to be tested */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(10, "A");
        tree.put(20, "B");
        tree.put(30, "C");

        assertEquals(20, (int) tree.root().getElement().getKey()); 
        tree.put(50, "E"); 

        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
        tree.put(25, "F"); 

        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(25, (int) tree.left(tree.right(tree.root())).getElement().getKey());

        assertEquals(5, tree.size());
        assertFalse(tree.isEmpty());

        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
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
        assertTrue(tree.isEmpty());

        tree.put(10, "A");
        tree.put(20, "B");
        tree.put(30, "C");

        assertEquals("A", tree.get(10));
        assertEquals("B", tree.get(20));
        assertEquals("C", tree.get(30));

        assertNull(tree.get(40));
    }
    
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        tree.put(10, "A");
        tree.put(20, "B");
        tree.put(30, "C");
        tree.put(40, "D");
        tree.put(50, "E");
        tree.put(25, "F");

        tree.remove(50);
        assertEquals(5, tree.size());
        assertNull(tree.get(50));

        tree.remove(40);
        assertEquals(4, tree.size());
        assertNull(tree.get(40));

        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(25, (int) tree.left(tree.right(tree.root())).getElement().getKey());

        tree.remove(20); 
        assertEquals(25, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());

        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
}