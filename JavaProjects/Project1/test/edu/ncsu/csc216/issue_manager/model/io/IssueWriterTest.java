package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Tests IssueWriter class
 * @author mhgausi
 */
public class IssueWriterTest {

	/**
	 * Tests writeIssuesToFile
	 */
	@Test
	void testWriteIssuesToFile() {
		ArrayList<Issue> issues = new ArrayList<>();
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note");
		Issue issue = new Issue(1, "Confirmed", "Bug", "testsummary", "mhgausi", 
				true, "Fixed", notes);
		Issue issue2 = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "Duplicate", notes);
		issues.add(issue);
		issues.add(issue2);
		
		String fileName = "list_issues.txt";
		IssueWriter.writeIssuesToFile(fileName, issues);
		File file = new File(fileName);
		
		assertTrue(file.exists());
		
        String invalidFileName = "/invalid/directory/fake_issues.txt";
        assertThrows(IllegalArgumentException.class, () -> 
        	IssueWriter.writeIssuesToFile(invalidFileName, issues));

		
	}

}
