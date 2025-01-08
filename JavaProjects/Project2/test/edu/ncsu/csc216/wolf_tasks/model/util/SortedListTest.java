package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SortedList class
 * @author mhgausi 
 * @author ojain
 */
class SortedListTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.util.SortedList
	 */
	@Test
	void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		
		list.add("hey");
		assertEquals(1, list.size());
		assertThrows(NullPointerException.class, () -> list.add(null));
		assertThrows(IllegalArgumentException.class, () -> list.add("hey"));
		assertEquals(1, list.size());
		list.add("hello");
		assertEquals(2, list.size());
		assertEquals("hey", list.get(1));
		assertEquals("hello", list.get(0));
		list.add("hi");
		assertEquals(3, list.size());
		assertEquals("hello", list.get(0));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals("hello", list.remove(0));
		assertEquals(2, list.size());
		assertEquals("hey", list.get(0));
		assertEquals("hi", list.remove(1));
		assertEquals(1, list.size());
		
		list.add("hi");
		assertTrue(list.contains("hi"));
		assertTrue(list.contains("hey"));
		assertFalse(list.contains("hello"));
		
	}

}