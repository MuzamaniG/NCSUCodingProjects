package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SwapList class
 * @author mhgausi 
 * @author ojain
 */
class SwapListTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.util.SwapList
	 */
	@Test
	void testSwapList() {
		SwapList<String> list = new SwapList<String>();
		assertEquals(0, list.size());
		
		list.add("hello");
		assertEquals(1, list.size());
		assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals(1, list.size());
		list.add("hey");
		assertEquals(2, list.size());
		list.add("hi");
		assertEquals(3, list.size());
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals("hey", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("hi", list.get(1));
		
		list.add("hey");
		assertEquals("hello", list.get(0));
		assertEquals("hi", list.get(1));
		assertEquals("hey", list.get(2));
		
		list.moveUp(2);
		assertEquals("hello", list.get(0));
		assertEquals("hey", list.get(1));
		assertEquals("hi", list.get(2));
		
		list.moveUp(0);
		assertEquals("hello", list.get(0));
		assertEquals("hey", list.get(1));
		assertEquals("hi", list.get(2));
		
		list.moveDown(1);
		assertEquals("hello", list.get(0));
		assertEquals("hi", list.get(1));
		assertEquals("hey", list.get(2));
		
		list.moveDown(2);
		assertEquals("hello", list.get(0));
		assertEquals("hi", list.get(1));
		assertEquals("hey", list.get(2));
		
		list.moveToFront(2);
		assertEquals("hey", list.get(0));
		assertEquals("hello", list.get(1));
		assertEquals("hi", list.get(2));
		
		list.moveToFront(0);
		assertEquals("hey", list.get(0));
		assertEquals("hello", list.get(1));
		assertEquals("hi", list.get(2));
		
		list.moveToBack(0);
		assertEquals("hello", list.get(0));
		assertEquals("hi", list.get(1));
		assertEquals("hey", list.get(2));
		
		list.moveToBack(2);
		assertEquals("hello", list.get(0));
		assertEquals("hi", list.get(1));
		assertEquals("hey", list.get(2));
		
	}

}