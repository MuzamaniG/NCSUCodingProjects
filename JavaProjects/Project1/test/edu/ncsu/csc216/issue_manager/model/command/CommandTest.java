package edu.ncsu.csc216.issue_manager.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * Tests Command class
 * @author mhgausi
 */
public class CommandTest {

	/**
	 * Tests command constructor
	 */
	@Test
	void testCommand() {
		assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, "")); 
		assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, null)); 
		assertThrows(IllegalArgumentException.class, () -> new Command(null, 
				"mhgausi", Resolution.FIXED, "note")); 
		assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.RESOLVE, 
				"mhgausi", null, "note")); 
		assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, 
				"", Resolution.FIXED, "note")); 
	}

	/**
	 * Tests getCommand
	 */
	@Test
	void testGetCommand() {
		Command command = new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, "note");
		Command command2 = new Command(CommandValue.CONFIRM, 
				"mhgausi", Resolution.FIXED, "note");
		assertEquals(command.getCommand(), CommandValue.ASSIGN);
		assertEquals(command2.getCommand(), CommandValue.CONFIRM);
	}

	/**
	 * Tests getResolution
	 */
	@Test
	void testGetResolution() {
		Command command = new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, "note");
		Command command2 = new Command(CommandValue.CONFIRM, 
				"mhgausi", Resolution.DUPLICATE, "note");
		Command command3 = new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.WONTFIX, "note");
		Command command4 = new Command(CommandValue.CONFIRM, 
				"mhgausi", Resolution.WORKSFORME, "note");
		
		assertEquals(command.getResolution(), Resolution.FIXED);
		assertEquals(command2.getResolution(), Resolution.DUPLICATE);
		assertEquals(command3.getResolution(), Resolution.WONTFIX);
		assertEquals(command4.getResolution(), Resolution.WORKSFORME);
		
		
	}

	/**
	 * Tests getOwnerId
	 */
	@Test
	void testGetOwnerId() {
		Command command = new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, "note");
		Command command2 = new Command(CommandValue.ASSIGN, 
				"fakeid", Resolution.FIXED, "note");
		assertEquals(command.getOwnerId(), "mhgausi");
		assertEquals(command2.getOwnerId(), "fakeid");
		
	}

	/**
	 * Tests getNote
	 */
	@Test
	void testGetNote() {
		Command command = new Command(CommandValue.ASSIGN, 
				"mhgausi", Resolution.FIXED, "note");
		Command command2 = new Command(CommandValue.ASSIGN, 
				"fakeid", Resolution.FIXED, "fakenote");
		assertEquals(command.getNote(), "note");
		assertEquals(command2.getNote(), "fakenote");
	}

}
