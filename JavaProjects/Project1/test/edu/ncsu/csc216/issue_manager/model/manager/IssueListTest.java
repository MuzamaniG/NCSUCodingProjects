package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;


/**
 * Tests IssueList class
 * @author mhgausi
 */
public class IssueListTest {

	/**
	 * Tests addIssue 
	 */
	@Test
	void testAddIssue() {
		IssueList issues = new IssueList();
		assertEquals(1, issues.addIssue(IssueType.ENHANCEMENT, "notes", "summary"));
		assertEquals(2, issues.addIssue(IssueType.ENHANCEMENT, "notes", "summary"));
		assertEquals(3, issues.addIssue(IssueType.ENHANCEMENT, "notes", "summary"));
		assertEquals(4, issues.addIssue(IssueType.ENHANCEMENT, "notes", "summary"));
	}

	/**
	 * Tests addIssues
	 */
	@Test
	void testAddAndGetIssues() {
		IssueList issues = new IssueList();
		ArrayList<Issue> issueArrayList = new ArrayList<>();
		Issue issue1 = new Issue(1, IssueType.ENHANCEMENT, "notes", "summary");
		Issue issue2 = new Issue(2, IssueType.ENHANCEMENT, "notes", "summary");
		Issue issue3 = new Issue(3, IssueType.ENHANCEMENT, "notes", "summary");
		Issue issue4 = new Issue(4, IssueType.ENHANCEMENT, "notes", "summary");
		issueArrayList.add(issue1);
		issueArrayList.add(issue2);
		issueArrayList.add(issue3);
		issueArrayList.add(issue4);
		issues.addIssues(issueArrayList);
		assertEquals(issueArrayList, issues.getIssues());
	}


	/**
	 * Tests getIssuesByType
	 */
	@Test
	void testGetIssuesByType() {
		IssueList issues = new IssueList();
		issues.addIssue(IssueType.ENHANCEMENT, "notes", "summary");
		issues.addIssue(IssueType.BUG, "notes2", "summary2");
		issues.addIssue(IssueType.ENHANCEMENT, "notes3", "summary3");
		issues.addIssue(IssueType.BUG, "notes4", "summary4");
		ArrayList<Issue> issueList = issues.getIssues();
		issueList.remove(0); //removes first enhancement issue
		issueList.remove(1); //removes second enhancement issue since first Bug shifts
		 
		assertEquals(issueList, issues.getIssuesByType("Bug"));
	}

	/**
	 * Tests getIssueById
	 */
	@Test
	void testGetIssueById() {
		IssueList issues = new IssueList();
		 issues.addIssue(IssueType.ENHANCEMENT, "notes", "summary");
		 issues.addIssue(IssueType.ENHANCEMENT, "notes2", "summary2");
		 issues.addIssue(IssueType.ENHANCEMENT, "notes3", "summary3");
		 issues.addIssue(IssueType.ENHANCEMENT, "notes4", "summary4");
		 
		 ArrayList<Issue> issueList = issues.getIssues();
		 
		 assertEquals(issues.getIssueById(2), issueList.get(1));
		 assertEquals(issues.getIssueById(3), issueList.get(2));
		 assertEquals(issues.getIssueById(4), issueList.get(3));
		 assertNull(issues.getIssueById(5));
		 
	}

	/**
	 * Tests executeCommand
	 */
	@Test
	void testExecuteCommand() {
		IssueList issues = new IssueList();
		ArrayList<Issue> issueList = new ArrayList<>();
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		Issue issue2 = new Issue(2, "Confirmed", "Bug", "testsummary", "mhgausi", 
				true, "Fixed", notes);
		issueList.add(issue);
		issueList.add(issue2);
		issues.addIssues(issueList);
		Command c = new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, "note");
		issues.executeCommand(2, c);
		assertEquals(issues.getIssueById(2).getStateName(), "Working");
		
	}

	/**
	 * Tests deleteIssueById
	 */
	@Test
	void testDeleteIssueById() {
		IssueList issues = new IssueList();
		issues.addIssue(IssueType.ENHANCEMENT, "notes", "summary");
		issues.addIssue(IssueType.BUG, "notes2", "summary2");
		issues.addIssue(IssueType.ENHANCEMENT, "notes3", "summary3");
		issues.addIssue(IssueType.BUG, "notes4", "summary4");
		issues.deleteIssueById(1);
		issues.deleteIssueById(3);
		assertNull(issues.getIssueById(1));
		assertNull(issues.getIssueById(3));
	}

}
