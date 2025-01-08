package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the AbstractTaskList class
 * @author mhgausi 
 * @author ojain
 */
class AbstractTaskListTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList
	 */
	@Test
	void testAbstractTaskList() {
		assertThrows(IllegalArgumentException.class, () -> new TaskList("name", -3));
		assertThrows(IllegalArgumentException.class, () -> new TaskList(null, 3));
		assertThrows(IllegalArgumentException.class, () -> new TaskList("", 3));
		
		AbstractTaskList taskList = new TaskList("listName", 3);
		assertEquals(0, taskList.getTasks().size());
		assertEquals(3, taskList.getCompletedCount());
		
		Task task = new Task("name", "details", false, false);
		taskList.addTask(task);
		assertEquals(1, taskList.getTasks().size());
		assertEquals("listName", task.getTaskListName());
		
		assertEquals(task, taskList.getTask(0));
		
		assertEquals(task, taskList.removeTask(0));
		assertEquals(0, taskList.getTasks().size());
		taskList.completeTask(task);
		assertEquals(taskList.getCompletedCount(), 4);
	}

}