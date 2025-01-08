package edu.ncsu.csc216.issue_manager.model.command;

/**
 * Command class; contains command values and resolutions that alter the FSM
 * @author mhgausi
 */
public class Command {

	/** 
	 * Enumeration CommandValue containing different commands causing transition in
	 * our FSM
	 */
	public enum CommandValue { ASSIGN, CONFIRM, RESOLVE, VERIFY, REOPEN }

	/**
	 * Enumeration Resolution containing possible resolution types
	 */
	public enum Resolution { FIXED, DUPLICATE, WONTFIX, WORKSFORME }
	
	/** String value for FIXED resolution */
	public static final String R_FIXED = "Fixed";
	/** String value for DUPLICATE resolution */
	public static final String R_DUPLICATE = "Duplicate";
	/** String value for WONTFIX resolution */
	public static final String R_WONTFIX = "WontFix";
	/** String value for WORKSFORME resolution */
	public static final String R_WORKSFORME = "WorksForMe";
	
	/** ID of owner */
	private String ownerId;
	/** Note attached to command */
	private String note;
	/** Command's value */
	private CommandValue c; 
	/** Command's resolution */
	private Resolution r; 
	
	/**
	 * Constructs command with its value, owner ID, resolution, and attached note
	 * @param c CommandValue
	 * @param ownerId ID of owner
	 * @param r Resolution of command
	 * @param note attached to command
	 * @throws IllegalArgumentException for invalid CommandValue, ownerId, resolution, or note string
	 */
	public Command(CommandValue c, String ownerId, Resolution r, String note) {
		if (c == null || c == CommandValue.ASSIGN && (ownerId == null || ownerId.isEmpty()) 
				|| c == CommandValue.RESOLVE && r == null || note == null || note.isEmpty()) {
			throw new IllegalArgumentException("Invalid information.");
		}
		this.c = c;
		this.ownerId = ownerId;
		this.r = r;
		this.note = note;
	}
	
	/** 
	 * Getter for CommandValue
	 * @return c current CommandValue
	 */
	public CommandValue getCommand() {
		return c;
	}
	
	/**
	 * Getter for Resolution
	 * @return r current type of resolution
	 */
	public Resolution getResolution() {
		return r;
	}
	
	/** 
	 * Getter for owner's id
	 * @return ownerId ID associated with owner
	 */
	public String getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Getter for note string
	 * @return note attached to command
	 */
	public String getNote() {
		return note;
	}
	

}
