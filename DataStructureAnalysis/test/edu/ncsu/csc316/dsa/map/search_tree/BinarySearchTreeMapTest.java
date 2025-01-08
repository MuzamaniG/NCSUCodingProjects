package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class BinarySearchTreeMapTest {

	/** Tree being tested */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int) tree.root().getElement().getKey());
        assertEquals("one", tree.root().getElement().getValue());
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals("two", tree.right(tree.root()).getElement().getValue());
        tree.put(0, "zero");
        assertEquals(3, tree.size());
        assertEquals(0, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals("zero", tree.left(tree.root()).getElement().getValue());
        tree.put(1, "onenew");
        assertEquals(3, tree.size());
        assertEquals("onenew", tree.root().getElement().getValue());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        assertNull(tree.get(2));
        tree.put(2, "two");
        assertEquals("two", tree.get(2));
        tree.put(0, "zero");
        assertEquals("zero", tree.get(0));
        tree.put(1, "onenew");
        assertEquals("onenew", tree.get(1));

    }
    

    /**
     * Test the toString and entrySet methods.
     */
    @Test
    public void testToStringAndEntrySet() {
        String expectedString = "BalanceableBinaryTree[\nnull\n]";
        assertEquals(expectedString, tree.toString());
        tree.put(1, "string1");
        Iterator<Map.Entry<Integer, String>> it = tree.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int) entry.getKey());
        assertEquals("string1", (String) entry.getValue());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(0, "zero");
        tree.put(-1, "minus one");
        tree.put(3, "three");
        assertEquals(5, tree.size());
        assertNull(tree.remove(10));
        assertEquals(5, tree.size());
        assertEquals("minus one", tree.remove(-1));
        assertEquals(4, tree.size());
        assertEquals("zero", tree.remove(0));
        assertEquals(3, tree.size());
        assertEquals("two", tree.remove(2));
        assertEquals(2, tree.size());
        assertEquals("one", tree.remove(1));
        assertEquals(1, tree.size());
        assertEquals(3, (int) tree.root().getElement().getKey());
        assertEquals("three", tree.remove(3));
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
    }
    

    /**
     * Test the rotate method.
     */
    @Test
    public void testRotate() {
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        
        Position<Entry<Integer, String>> root = tree.root();
        Position<Entry<Integer, String>> rightChild = tree.right(root);
        
        tree.rotate(rightChild);
        
        assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());
    }

    /**
     * Test the restructure method.
     */
    @Test
    public void testRestructure() {
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");

        Position<Entry<Integer, String>> root = tree.root();
        Position<Entry<Integer, String>> rightChild = tree.right(root);
        Position<Entry<Integer, String>> rightRightChild = tree.right(rightChild);

        tree.restructure(rightRightChild);

        assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());
    }
}
