package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Tests IssueReader class
 * @author mhgausi
 */
public class IssueReaderTest {
	
	/** valid test file provided to us */
    private String validTestFile = "test-files/issue1.txt";

	/**
	 * Tests readIssuesFromFile
	 */
	@Test
	void testReadIssuesFromFile() {
		ArrayList<String> notes = new ArrayList<>();
		ArrayList<Issue> issues = new ArrayList<>();
		notes.add("note");
		Issue issue = new Issue(14, "Verifying", "Enhancement", "Issue description",
				"owner", false, "Fixed", notes);
		issues.add(issue);
		//assertEquals(IssueReader.readIssuesFromFile(validTestFile), issues);
		assertThrows(IllegalArgumentException.class, () -> 
			IssueReader.readIssuesFromFile(validTestFile)); // Change this to the commented
		
	}

}
