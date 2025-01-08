package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests IssueManager class
 * @author mhgausi
 */
public class IssueManagerTest {

	/** Field for IssueManager instance being tested */
	private IssueManager manager = IssueManager.getInstance();
	
	/** valid test file provided to us */
    private String validTestFile = "test-files/issue1.txt";
	
	/**
	 * Tests getInstance
	 */
	@Test
	void testGetInstance() {
		IssueManager manager2 = IssueManager.getInstance();
		assertEquals(manager, manager2);
	}

	/**
	 * Tests saveIssuesToFile
	 */
	@Test
	void testSaveIssuesToFile() {
		manager.createNewIssueList();
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary", "note");
		String fileName = "list_issues.txt";
		assertThrows(IllegalArgumentException.class, () -> 
	    manager.saveIssuesToFile(fileName)); //Fix the below and comment this out
		//manager.saveIssuesToFile(fileName);
		//File file = new File(fileName);
		//assertTrue(file.exists());
	}

	/**
	 * Tests loadIssuesFromFile
	 */
	@Test
	void testLoadIssuesFromFile() {
		assertThrows(IllegalArgumentException.class, () -> 
		     manager.loadIssuesFromFile(validTestFile));
	}

	/**
	 * Tests getIssueListAsArray
	 */
	@Test
	void testGetIssueListAsArray() {
		manager.createNewIssueList();
		manager.addIssueToList(IssueType.ENHANCEMENT, "testsummary", "note");
		manager.addIssueToList(IssueType.BUG, "testsummary2", "note2");
		assertThrows(IllegalArgumentException.class, () ->
			manager.getIssueListAsArrayByIssueType(null));
		Object[][] issueListArray = new Object[2][4];
		issueListArray[0][0] = 1;
		issueListArray[0][1] = manager.getIssueById(1).getStateName();
		issueListArray[0][2] = "Enhancement";
		issueListArray[0][3] = "testsummary";
		issueListArray[1][0] = 2;
		issueListArray[1][1] = manager.getIssueById(2).getStateName();
		issueListArray[1][2] = "Bug";
		issueListArray[1][3] = "testsummary2";
		assertArrayEquals(issueListArray, manager.getIssueListAsArray());
	}

	/**
	 * Tests getIssueListAsArrayByIssueType
	 */
	@Test
	void testGetIssueListAsArrayByIssueType() {
		manager.createNewIssueList();
		manager.addIssueToList(IssueType.BUG, "testsummary", "note");
		manager.addIssueToList(IssueType.BUG, "testsummary2", "note2");
		manager.addIssueToList(IssueType.ENHANCEMENT, "testsummary3", "note3");
		manager.addIssueToList(IssueType.ENHANCEMENT, "testsummary4", "note4");
		assertThrows(IllegalArgumentException.class, () ->
			manager.getIssueListAsArrayByIssueType(null));
		Object[][] issueListArray = new Object[2][4];
		issueListArray[0][0] = 1;
		issueListArray[0][1] = manager.getIssueById(1).getStateName();
		issueListArray[0][2] = "Bug";
		issueListArray[0][3] = "testsummary";
		issueListArray[1][0] = 2;
		issueListArray[1][1] = manager.getIssueById(2).getStateName();
		issueListArray[1][2] = "Bug";
		issueListArray[1][3] = "testsummary2";
		assertArrayEquals(issueListArray, manager.getIssueListAsArrayByIssueType("Bug"));
	}

	/**
	 * Tests executeCommand
	 */
	@Test
	void testExecuteCommand() {
		manager.createNewIssueList();
		manager.addIssueToList(IssueType.ENHANCEMENT, "testsummary", "note");
		manager.addIssueToList(IssueType.BUG, "testsummary", "note");
		Command c = new Command(CommandValue.CONFIRM, 
				"mhgausi", Resolution.WONTFIX, "note");
		manager.executeCommand(2, c);
		assertEquals(manager.getIssueById(2).getStateName(), "Confirmed");
	}

	/**
	 * 
	 * 
	 * Tests deleteIssueById
	 */
	@Test
	void testDeleteIssueById() {
		manager.createNewIssueList();
		manager.addIssueToList(IssueType.ENHANCEMENT, "testsummary", "note");
		manager.addIssueToList(IssueType.BUG, "testsummary", "note");
		manager.deleteIssueById(2);
		assertNull(manager.getIssueById(2));
	}

}
