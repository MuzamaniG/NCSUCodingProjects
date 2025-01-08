package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the ActiveTaskList class
 * @author mhgausi 
 * @author ojain
 */
class ActiveTaskListTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList
	 */
	@Test
	void testActiveTaskList() {
		ActiveTaskList list = new ActiveTaskList();
		assertEquals("Active Tasks", list.getTaskListName());
		assertEquals(0, list.getCompletedCount());
		
		assertThrows(IllegalArgumentException.class, () -> list.setTaskListName("name"));
		list.setTaskListName("Active Tasks");
		assertEquals("Active Tasks", list.getTaskListName());
		
		Task task1 = new Task("name1", "details1", false, false);
		Task task2 = new Task("name2", "details2", true, false);
		Task task3 = new Task("name3", "details3", false, true);
		Task task4 = new Task("name4", "details4", true, true);
		
		list.addTask(task4);
		list.addTask(task3);
		assertThrows(IllegalArgumentException.class, () -> list.addTask(task2));
		assertThrows(IllegalArgumentException.class, () -> list.addTask(task1));
		
		String[][] exp = new String[2][2];
		exp[0][0] = "Active Tasks";
		exp[0][1] = "name4";
		exp[1][0] = "Active Tasks";
		exp[1][1] = "name3";
		assertArrayEquals(exp, list.getTasksAsArray());
	}

}