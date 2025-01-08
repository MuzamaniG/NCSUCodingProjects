package edu.ncsu.csc216.issue_manager.model.issue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests Issue class
 * @author mhgausi
 */
public class IssueTest {


	/**
	 * Tests Issue constructor
	 */
	@Test
	void testIssueConstructor() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note");
		Issue issue = new Issue(1, IssueType.ENHANCEMENT, "summary", "note");
		assertEquals(issue.getIssueId(), 1);
		assertEquals(issue.getIssueType(), "Enhancement");
		assertEquals(issue.getSummary(), "summary");	
	}

	/**
	 * Tests getIssueId and setIssueId
	 */
	@Test
	void testGetAndSetIssueId() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals(issue.getIssueId(), 1);
		assertThrows(IllegalArgumentException.class, () -> new Issue(0, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes));
	}

	/**
	 * Tests getStateName
	 */
	@Test
	void testGetStateName() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		Issue issue2 = new Issue(1, "Working", "Enhancement", "testsummary", "mhgausi", 
				false, "Fixed", notes);
		Issue issue3 = new Issue(1, "Confirmed", "Bug", "testsummary", "mhgausi", 
				true, "Fixed", notes);
		Issue issue4 = new Issue(1, "Verifying", "Enhancement", "testsummary", "mhgausi", 
				false, "Fixed", notes);
		Issue issue5 = new Issue(1, "Closed", "Enhancement", "testsummary", "mhgausi", 
				false, "Fixed", notes);
		assertEquals("New", issue.getStateName());
		assertEquals("Working", issue2.getStateName());
		assertEquals("Confirmed", issue3.getStateName());
		assertEquals("Verifying", issue4.getStateName());
		assertEquals("Closed", issue5.getStateName());
		assertThrows(IllegalArgumentException.class, () -> new Issue(1, "FakeState", "Enhancement", "testsummary", "mhgausi", 
				false, "Fixed", notes));
		assertThrows(IllegalArgumentException.class, () -> new Issue(1, "", "Enhancement", "testsummary", "mhgausi", 
				false, "Fixed", notes));
		
		
	}

	/**
	 * Tests getIssueType and setIssueType
	 */
	@Test
	void testGetAndSetIssueType() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals("Enhancement", issue.getIssueType());
		Issue issue2 = new Issue(1, "New", "Bug", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals("Bug", issue2.getIssueType());
		assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", 
				"", "testsummary", "mhgausi", false, "Fixed", notes));
		assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", 
				"faketype", "testsummary", "mhgausi", false, "Fixed", notes));
	}

	/**
	 * Tests getResolution and setResolution
	 */
	@Test
	void testGetAndSetResolution() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		Issue issue2 = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "Duplicate", notes);
		Issue issue3 = new Issue(1, "Confirmed", "Bug", "testsummary", "mhgausi", 
				true, "Fixed", notes);
		Issue issue4 = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WorksForMe", notes);
		assertEquals("WontFix", issue.getResolution());
		assertEquals("Duplicate", issue2.getResolution());
		assertEquals("Fixed", issue3.getResolution());
		assertEquals("WorksForMe", issue4.getResolution());
		assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", 
				"Enhancement", "testsummary", "mhgausi", false, "FakeResolution", notes));
	}

	/**
	 * Tests getOwner and setOwner
	 */
	@Test
	void testGetAndSetOwner() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals("mhgausi", issue.getOwner());
		// assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", "Enhancement", "testsummary", "", 
		//		false, "Fixed", notes));
	}

	/**
	 * Tests getSummary and setSummary
	 */
	@Test
	void testGetAndSetSummary() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals("testsummary", issue.getSummary());
		assertThrows(IllegalArgumentException.class, () -> new Issue(1, "Confirmed", "Enhancement", "", "mhgausi", 
				false, "Fixed", notes));
	}

	/**
	 * Tests getNotes
	 */
	@Test
	void testGetNotes() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals(notes, issue.getNotes());
	}

	/**
	 * Tests getNotesString
	 */
	@Test
	void testGetNotesString() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals(issue.getNotesString(), "-note1\n");
	}

	/**
	 * Tests isConfirmed
	 */
	@Test
	void testIsConfirmed() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertFalse(issue.isConfirmed());
		Issue issue2 = new Issue(2, "New", "Bug", "testsummary", "mhgausi", 
				true, "WontFix", notes);
		assertTrue(issue2.isConfirmed());
	}

	/**
	 * Tests toString
	 */
	@Test
	void testToString() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertEquals(issue.toString(), "*1,New,Enhancement,testsummary,mhgausi,false,WontFix");
	}

	/**
	 * Tests update method
	 */
	@Test
	void testUpdate() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("note1");
		Issue issue = new Issue(1, "Confirmed", "Bug", "testsummary", "mhgausi", 
				true, "Fixed", notes);
		Issue issue10 = new Issue(1, "Working", "Enhancement", "testsummary", "mhgausi", 
				false, "Fixed", notes);
		assertEquals(issue.getStateName(), "Confirmed");
		Command command = new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, "note");
		Command command2 = new Command(CommandValue.RESOLVE, 
				"mhgausi", Resolution.WONTFIX, "note");
		Command command3 = new Command(CommandValue.RESOLVE, 
				"mhgausi", Resolution.WORKSFORME, "note");
		Command command4 = new Command(CommandValue.CONFIRM, 
				"mhgausi", Resolution.FIXED, "note");
		Command command5 = new Command(CommandValue.VERIFY, 
				"mhgausi", Resolution.FIXED, "note");
		Command command6 = new Command(CommandValue.REOPEN, 
				"mhgausi", Resolution.FIXED, "note");
		issue.update(command);
		assertEquals(issue.getStateName(), "Working");
		assertThrows(UnsupportedOperationException.class, () -> 
		issue10.update(new Command(CommandValue.RESOLVE, "mhgausi", 
				Resolution.WORKSFORME, "note")));
		issue.update(command2);
		assertEquals(issue.getStateName(), "Closed");
		Issue issue2 = new Issue(1, "Confirmed", "Bug", "testsummary", "mhgausi", 
				true, "WontFix", notes);
		assertThrows(UnsupportedOperationException.class, () -> 
				issue2.update(command3));
		issue2.update(command2);
		assertEquals(issue2.getStateName(), "Closed");
		Issue issue3 = new Issue(1, "New", "Enhancement", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		Issue issue4 = new Issue(1, "New", "Bug", "testsummary", "mhgausi", 
				false, "WontFix", notes);
		assertThrows(UnsupportedOperationException.class, () ->
			issue3.update(command3));
		assertThrows(UnsupportedOperationException.class, () -> 
			issue4.update(command));
		issue3.update(command2);
		assertEquals(issue3.getStateName(), "Closed");
		issue4.update(command4);
		assertEquals(issue4.getStateName(), "Confirmed");
		Issue issue5 = new Issue(1, "Verifying", "Bug", "testsummary", "mhgausi", 
				true, "Fixed", notes);
		Issue issue6 = new Issue(1, "Verifying", "Bug", "testsummary", "mhgausi", 
				false, "Fixed", notes);
		Issue issue7 = new Issue(1, "Closed", "Enhancement", "testsummary", "mhgausi", 
				false, "Fixed", notes);
		Issue issue8 = new Issue(1, "Closed", "Bug", "testsummary", null, 
				true, "Fixed", notes);
		Issue issue9 = new Issue(1, "Closed", "Bug", "testsummary", "", 
				false, "Fixed", notes);
		issue5.update(command5);
		issue6.update(command4);
		issue7.update(command6);
		issue8.update(command6);
		issue9.update(command6);
		assertEquals(issue5.getStateName(), "Closed");
		assertEquals(issue6.getStateName(), "Working");	
		assertEquals(issue7.getStateName(), "Working");
		assertEquals(issue8.getStateName(), "Confirmed");
		assertEquals(issue9.getStateName(), "New");
		issue5.update(command6);
		assertEquals(issue5.getStateName(), "Working");

	}

}
