package edu.ncsu.csc216.wolf_tasks.model.io;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;

/**
 * Tests the NotebookReader class
 * @author mhgausi 
 * @author ojain
 */
class NotebookReaderTest {

	/** invalid test file */
	private File invalidFile = new File("test-files/notebook3.txt");
	
	/** valid test file */
	private File validFile = new File("test-files/notebook0.txt");
	
	/**
	 * Test method for edu.ncsu.csc216.wolf_tasks.model.io.NotebookReader
	 */
	@Test
	void testNotebookReader() {
		assertThrows(IllegalArgumentException.class, () -> 
			NotebookReader.readNotebookFile(invalidFile));
		try {
			assertEquals("Summer Plans", NotebookReader.readNotebookFile(validFile).getNotebookName());
		}
		catch (Exception e) {
			fail();
		}
		
		Notebook book = NotebookReader.readNotebookFile(new File("test-files/notebook1.txt"));
		assertEquals("School", book.getNotebookName());
		book.setCurrentTaskList("Active Tasks");
		assertEquals(5, book.getCurrentTaskList().getTasks().size());
		
		book = NotebookReader.readNotebookFile(new File("test-files/notebook2.txt"));
		assertEquals("School", book.getNotebookName());
		book.setCurrentTaskList("Active Tasks");
		assertEquals(3, book.getCurrentTaskList().getTasks().size());

		assertEquals(1, NotebookReader.readNotebookFile(new File("test-files/notebook4.txt")).getTaskListsNames().length);
	}

}