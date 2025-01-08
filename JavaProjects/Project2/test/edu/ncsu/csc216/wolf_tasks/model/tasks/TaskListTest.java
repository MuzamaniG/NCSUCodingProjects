package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the TaskList class
 * @author mhgausi 
 * @author ojain
 */
class TaskListTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList
	 */
	@Test
	void testTaskList() {
		Task task1 = new Task("name1", "details1", false, false);
		Task task2 = new Task("name2", "details2", true, false);
		Task task3 = new Task("name3", "details3", true, true);
		Task task4 = new Task("name4", "details4", false, true);
		
		TaskList list = new TaskList("list", 3);
		list.addTask(task1);
		list.addTask(task2);
		list.addTask(task3);
		list.addTask(task4);
		
		String[][] exp = new String[4][2];
		for (int i = 1; i < 5; i++) {
			exp[i - 1][0] = "" + i;
			exp[i - 1][1] = "name" + i;
		}
		
		assertArrayEquals(exp, list.getTasksAsArray());
		
		TaskList list2 = new TaskList("list2", 9);
		TaskList list3 = new TaskList("list", 4);
		TaskList list4 = new TaskList("abc", 6);
		
		assertTrue(list.compareTo(list2) < 0);
		assertEquals(0, list.compareTo(list3));
		assertTrue(list.compareTo(list4) > 0);
	}

}