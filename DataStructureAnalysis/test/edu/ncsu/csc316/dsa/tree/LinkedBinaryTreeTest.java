package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;


/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class LinkedBinaryTreeTest {

	/** Tree to be tested */
    private LinkedBinaryTree<String> tree;
    
    /** First position to be tested in tree */
    private Position<String> one;
    /** Second position to be tested in tree */
    private Position<String> two;
    /** Third position to be tested in tree */
    private Position<String> three;
    /** Fourth position to be tested in tree */
    private Position<String> four;
    /** Fifth position to be tested in tree */
    private Position<String> five;
    /** Sixth position to be tested in tree */
    private Position<String> six;
    /** Seventh position to be tested in tree */
    private Position<String> seven;
    /** Eighth position to be tested in tree */
    private Position<String> eight;
    /** Ninth position to be tested in tree */
    private Position<String> nine;
    /** Tenth position to be tested in tree */
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        tree.set(one, "newOne");
        assertEquals("newOne", one.getElement());

        tree.set(five, "newFive");
        assertEquals("newFive", five.getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(2, tree.numChildren(ten));
        assertEquals(0, tree.numChildren(six));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals("one", tree.parent(two).getElement());
        assertEquals("two", tree.parent(ten).getElement());
        assertEquals("three", tree.parent(four).getElement());
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(three, tree.sibling(two));
        assertEquals(seven, tree.sibling(five));
        assertNull(tree.sibling(one)); 
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertFalse(tree.isInternal(six));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(nine));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
     
        String[] expected = new String[10];
        expected[0] = "one";
        expected[1] = "two";
        expected[2] = "six";
        expected[3] = "ten";
        expected[4] = "seven";
        expected[5] = "five";
        expected[6] = "three";
        expected[7] = "four";
        expected[8] = "eight";
        expected[9] = "nine";
        
        String[] actual = new String[10];
        int i = 0;
        Iterable<Position<String>> pre = tree.preOrder();
        for (Position<String> pos : pre) {
            actual[i] = pos.getElement();
            i++;
        }
        
        assertArrayEquals(expected, actual);
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        
        String[] expected = {"six", "seven", "five", "ten", "two", "eight", "nine", "four", "three", "one"};
        String[] actual = new String[10];
        int i = 0;
        
        Iterable<Position<String>> postOrderPositions = tree.postOrder();
        for (Position<String> pos : postOrderPositions) {
            actual[i++] = pos.getElement();
        }
        
        assertArrayEquals(expected, actual);
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        String[] expected = new String[10];
        expected[0] = "six";
        expected[1] = "two";
        expected[2] = "seven";
        expected[3] = "ten";
        expected[4] = "five";
        expected[5] = "one";
        expected[6] = "eight";
        expected[7] = "four";
        expected[8] = "nine";
        expected[9] = "three";
        
        String[] actual = new String[10];
        int i = 0;
        Iterable<Position<String>> in = tree.inOrder();
        for (Position<String> pos : in) {
            actual[i] = pos.getElement();
            i++;
        }
        
        assertArrayEquals(expected, actual);
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertTrue(tree.isEmpty());
        try {
            tree.remove(one);
            fail("Expected an exception when trying to remove from an empty tree.");
        } catch (IllegalArgumentException e) {
            //empty, expected
        }
        assertNull(tree.root());
    }
    
    /** Tests the levelOrder traversal */
    @Test
    public void testLevelOrder() {
        createTree();
        String[] expected = new String[10];
        expected[0] = "one";
        expected[1] = "two";
        expected[2] = "three";
        expected[3] = "six";
        expected[4] = "ten";
        expected[5] = "four";
        expected[6] = "seven";
        expected[7] = "five";
        expected[8] = "eight";
        expected[9] = "nine";
        
        String[] actual = new String[10];
        int i = 0;
        Iterable<Position<String>> in = tree.levelOrder();
        for (Position<String> pos : in) {
            actual[i] = pos.getElement();
            i++;
        }
        
        assertArrayEquals(expected, actual);
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	createTree();
        tree.addLeft(six, "newLeft");
        assertEquals("newLeft", tree.left(six).getElement());
        assertThrows(IllegalArgumentException.class, () -> tree.addLeft(six, "leftAgain"));
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	createTree();
        tree.addRight(nine, "newRight");
        assertEquals("newRight", tree.right(nine).getElement());
        assertThrows(IllegalArgumentException.class, () -> tree.addRight(nine, "leftAgain"));
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertEquals("eight", tree.remove(eight));
        assertNull(tree.parent(eight));
        assertEquals("four", tree.remove(four));
        assertEquals("nine", tree.left(three).getElement()); 
        assertThrows(IllegalArgumentException.class, () -> tree.remove(two));
    }
    
    /** Tests toString */
    @Test
    public void testToString() {
        createTree();
        String actual = tree.toString();
        String expected = "LinkedBinaryTree[\n" +
                          "one\n" +
                          " two\n" +
                          "  six\n" +
                          "  ten\n" +
                          "   seven\n" +
                          "   five\n" +
                          " three\n" +
                          "  four\n" +
                          "   eight\n" +
                          "   nine\n" +
                          "]";
        assertEquals(expected, actual);
    }

}