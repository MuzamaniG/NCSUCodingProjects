package edu.ncsu.csc216.wolf_tasks.model.notebook;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Tests the Notebook class
 * @author mhgausi 
 * @author ojain
 */
class NotebookTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook
	 */
	@Test
	void testNotebook() {
		assertThrows(IllegalArgumentException.class, () -> new Notebook(null));
		assertThrows(IllegalArgumentException.class, () -> new Notebook(""));
		assertThrows(IllegalArgumentException.class, () -> new Notebook("Active Tasks"));
		Notebook book = new Notebook("book name");
		assertEquals("book name", book.getNotebookName());
		assertTrue(book.isChanged());
		
		TaskList list1 = new TaskList("Active Tasks", 9);
		TaskList list2 = new TaskList("School Tasks", 9);
		TaskList list3 = new TaskList("Work Tasks", 9);
		
		assertThrows(IllegalArgumentException.class, () -> book.addTaskList(list1));
		book.addTaskList(list2);
		assertThrows(IllegalArgumentException.class, () -> book.editTaskList("Active Tasks"));
		book.addTaskList(list3);
		assertThrows(IllegalArgumentException.class, () -> book.addTaskList(list2));
		assertEquals(list3, book.getCurrentTaskList());
		assertTrue(book.isChanged());
		
		assertEquals(3, book.getTaskListsNames().length);
		String[] arr = new String[3];
		arr[0] = "Active Tasks";
		arr[1] = "School Tasks";
		arr[2] = "Work Tasks";
		assertArrayEquals(arr, book.getTaskListsNames());
		
		arr[0] = "Active Tasks";
		arr[1] = "Academy Tasks";
		arr[2] = "School Tasks";
		book.editTaskList("Academy Tasks");
		assertArrayEquals(arr, book.getTaskListsNames());
		
		Task task1 = new Task("name1", "details", false, false);
		Task task2 = new Task("name2", "details", false, true);
		Task task3 = new Task("newName", "newDetails", false, true);

		book.addTask(task1);
		book.addTask(task2);
		assertEquals(task1, book.getCurrentTaskList().getTask(0));
		assertEquals(task2, book.getCurrentTaskList().getTask(1));
		book.editTask(0, "newName", "newDetails", false, true);
		assertEquals(task3.getTaskName(), book.getCurrentTaskList().getTask(0).getTaskName());
		assertEquals(task3.getTaskDescription(), book.getCurrentTaskList().getTask(0).getTaskDescription());
		
		assertTrue(book.isChanged());
		
		book.setCurrentTaskList("Active Tasks");
		assertThrows(IllegalArgumentException.class, () -> book.removeTaskList());
		book.setCurrentTaskList("School Tasks");
		book.removeTaskList();
		assertEquals("Active Tasks", book.getCurrentTaskList().getTaskListName());
		assertTrue(book.isChanged());
		book.saveNotebook(new File("test-files/test_save.txt"));
		assertFalse(book.isChanged());
	}

}