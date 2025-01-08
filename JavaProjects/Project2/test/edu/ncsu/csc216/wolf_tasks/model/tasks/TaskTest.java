package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Task class
 * @author mhgausi 
 * @author ojain
 */
class TaskTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.tasks.Task
	 */
	@Test
	void testTask() {
		assertThrows(IllegalArgumentException.class, () -> new Task(null, "details", true, true));
		assertThrows(IllegalArgumentException.class, () -> new Task(null, "details", true, true));
		assertThrows(IllegalArgumentException.class, () -> new Task("name", null, true, true));
		
		Task task = new Task("name", "details", true, true);
		assertThrows(CloneNotSupportedException.class, () -> task.clone());
		assertThrows(IllegalArgumentException.class, () -> task.completeTask());
		assertEquals("name", task.getTaskName());
		assertEquals("details", task.getTaskDescription());
		assertTrue(task.isRecurring());
		assertTrue(task.isActive());
		
		
		assertThrows(IllegalArgumentException.class, () -> task.addTaskList(null));
		TaskList list1 = new TaskList("List 1", 4);
		task.addTaskList(list1);
		assertEquals("List 1", task.getTaskListName());
		try {
			assertEquals(task, task.clone());
		}
		catch (CloneNotSupportedException e) {
			fail();
		}
		
		TaskList list2 = new TaskList("List 2", 9);
		task.addTaskList(list2);
		assertEquals("List 1", task.getTaskListName());
		
		assertEquals("* name,recurring,active\ndetails", task.toString());
		Task task2 = new Task("NAME", "description", false, true);
		assertEquals("* NAME,active\ndescription", task2.toString());
		Task task3 = new Task("NAME", "description", true, false);
		assertEquals("* NAME,recurring\ndescription", task3.toString());
		Task task4 = new Task("NAME", "description", false, false);
		assertEquals("* NAME\ndescription", task4.toString());
		Task task5 = new Task("name", "", true, false);
		assertEquals("* name,recurring", task5.toString());
	}

}