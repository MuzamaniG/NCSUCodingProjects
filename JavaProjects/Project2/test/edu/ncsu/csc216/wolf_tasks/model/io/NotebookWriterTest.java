package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Tests the NotebookWriter class
 * @author mhgausi
 * @author ojain
 */
class NotebookWriterTest {

	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter
	 */
	@Test
	void testNotebookWriter() {
		
		TaskList listA = new TaskList("ATaskList", 0);
		TaskList list1 = new TaskList("Tasks1", 0);
		TaskList list2 = new TaskList("Tasks2", 0);
		
		Task task1 = new Task("Task1", "Task1Description", true, false);
		Task task2 = new Task("Task2", "Task2Description", true, true);
		Task task3 = new Task("Task3", "Task3Description", false, false);
		Task task4 = new Task("Task4", "Task4Description", false, true);
		Task task5 = new Task("Task5", "Task5Description", true, false);
		
		list1.addTask(task1);
		list1.addTask(task2);
		list2.addTask(task3);
		list2.addTask(task4);
		list2.addTask(task5);
		
		ISortedList<TaskList> list = new SortedList<TaskList>();
		list.add(listA);
		list.add(list1);
		list.add(list2);
		
		NotebookWriter.writeNotebookFile(new File("test-files/actual_out.txt"), "Notebook", list);

		assertTrue(checkFiles("test-files/expected_out.txt", "test-files/actual_out.txt"));
				
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 * @return true if files are equal
	 */
	private boolean checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				if (!expScanner.nextLine().equals(actScanner.nextLine())) {
					return false;
				}
			}
			expScanner.close();
			actScanner.close();
			return true;
		} catch (IOException e) {
			fail("Error reading files.");
			return false;
		}
	}

}