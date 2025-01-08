package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * Class representing issues to be managed, containing issue ID,
 * summary, owner, confirmed staus, and notes ArrayList
 * @author mhgausi
 */
public class Issue {
	
	/** Enumeration for types of issues */
	public enum IssueType { ENHANCEMENT, BUG }
	
	/** String representing enhancement issue type */
	public static final String I_ENHANCEMENT = "Enhancement";
	
	/** String representing bug issue type */
	public static final String I_BUG = "Bug";
	
	/** Issue's ID */
	private int issueId;
	
	/** Issue's summary */
	private String summary;
	
	/** Issue's owner */
	private String owner;
	
	/** Issue's confirmed status */
	private boolean confirmed;
	
	/** Issue's current state */
	private IssueState state;
	
	/** Issue's current type */
	private IssueType issueType;
	
	/** Issue's resolution */
	private Resolution resolution;
	
	/** Field for NewState instance of issue */
	private NewState newState = new NewState();
	
	/** Field for ConfirmedState instance of issue */
	private ConfirmedState confirmedState = new ConfirmedState();
	
	/** Field for ClosedState instance of issue */
	private ClosedState closedState = new ClosedState();
	
	/** Field for VerifyingState instance of issue */
	private VerifyingState verifyingState = new VerifyingState();
	
	/** Field for WorkingState instance of issue */
	private WorkingState workingState = new WorkingState();
	
	/** List of issue notes */
	private ArrayList<String> notes;
	
	/** String for New state */
	public static final String NEW_NAME = "New";
	/** String for Working state */
	public static final String WORKING_NAME = "Working";
	/** String for Confirmed state */
	public static final String CONFIRMED_NAME = "Confirmed";
	/** String for Verifying state */
	public static final String VERIFYING_NAME = "Verifying";
	/** String for Closed state */
	public static final String CLOSED_NAME = "Closed";
	
	
	/**
	 * Constructor for Issue given type, summary, and note
	 * @param id Issue's ID
	 * @param issueType Issue's type
	 * @param summary Issue's summary
	 * @param note Issue's note
	 * @throws IllegalArgumentException for invalid id, issueType, summary, or note
	 */
	public Issue(int id, IssueType issueType, String summary, String note) {
	
		notes = new ArrayList<>();
		setSummary(summary);
		setIssueId(id);
		if (issueType == null) {
			throw new IllegalArgumentException("Invalid type.");
		}
		this.issueType = issueType;
		setConfirmed(false);
		this.owner = null;
		setState(NEW_NAME);
		addNote(note);
		
		
		
	}
	
	/**
	 * Constructor for Issue given id, state, type, summary, owner, confirmation, resolution,
	 * and notes
	 * @param id Issue ID
	 * @param state Issue state
	 * @param issueType Issue's type
	 * @param summary Issue summary
	 * @param owner Issue owner
	 * @param confirmed Issue confirmation status
	 * @param resolution Issue resolution
	 * @param notes Issue notes
	 * @throws IllegalArgumentException for invalid id, state, issueType, summary,
	 * owner, confirmed status, resolution, or notes ArrayList
	 */
	public Issue(int id, String state, String issueType, String summary, String owner, boolean confirmed,
			String resolution, ArrayList<String> notes) {
		setIssueId(id);
		setState(state);
		setIssueType(issueType);
		setSummary(summary);
		setOwner(owner);
		if ("".equals(owner)) {
			this.owner = null;
		}
		if ((this.state == workingState || this.state == verifyingState)
				&& this.owner == null) {
			throw new IllegalArgumentException();
		}
		setConfirmed(confirmed);
		if ((!isConfirmed() || this.issueType != IssueType.BUG) &&
			this.state == confirmedState) {
			throw new IllegalArgumentException();
		}
		if (!isConfirmed() && this.state == workingState &&
				this.issueType == IssueType.BUG) {
			throw new IllegalArgumentException();
		}

		setResolution(resolution);
		if (this.resolution == Resolution.FIXED && this.state == newState) {
			throw new IllegalArgumentException();
		}
		if (this.resolution != Resolution.FIXED && !resolution.isEmpty() && this.state == verifyingState) {
			throw new IllegalArgumentException();
		}
		if (this.issueType == IssueType.ENHANCEMENT && (isConfirmed() || this.state == confirmedState)) {
			throw new IllegalArgumentException();
		}
		if ((this.state == closedState || this.state == verifyingState) && this.resolution == null) {
			throw new IllegalArgumentException();
		}
		setNotes(notes);

	}
	
	/**
	 * Sets the issue state based on the string name of the state
	 * @param state being set
	 * @throws IllegalArgumentException for invalid states
	 */
	private void setState(String state) {
		if (state == null || state.isEmpty()) {
			throw new IllegalArgumentException("Invalid state.");
		}
		if (state.equals(NEW_NAME)) {
		    this.state = newState;
		} else if (state.equals(CONFIRMED_NAME)) {
		    this.state = confirmedState;
		} else if (state.equals(CLOSED_NAME)) {
		    this.state = closedState;
		} else if (state.equals(VERIFYING_NAME)) {
		    this.state = verifyingState;
		} else if (state.equals(WORKING_NAME)) {
		    this.state = workingState;
		} else {
		    throw new IllegalArgumentException("Invalid state.");
		}
	}
	
	/**
	 * Setter for issue type
	 * @param type of issue
	 */
	private void setIssueType(String type) {
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("Invalid type.");
		}
		if (type.equals(I_ENHANCEMENT)) {
			this.issueType = IssueType.ENHANCEMENT;
		}
		else if (type.equals(I_BUG)) {
			this.issueType = IssueType.BUG;
		}
		else {
			throw new IllegalArgumentException("Invalid type.");
		}
	}
	
	/**
	 * Setter for issue ID
	 * @param id of issue
	 * @throws IllegalArgumentException if the ID is less than 1
	 */
	private void setIssueId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		this.issueId = id;
	}
	
	/**
	 * Setter for summary
	 * @param summary of issue
	 * @throws IllegalArgumentException if summary is null or empty string
	 */
	private void setSummary(String summary) {
		if (summary == null || summary.isEmpty()) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		this.summary = summary;
	}
	
	/**
	 * Setter for owner
	 * @param owner of issue
	 * @throws IllegalArgumentException if owner is empty string
	 */
	private void setOwner(String owner) {
		//if (owner.isEmpty()) {
		//	throw new IllegalArgumentException("Issue cannot be created.");
		//}
		this.owner = owner;
	}
	
	private void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	/**
	 * Private setter for type of Resolution
	 * @param resolution string representation of Resolution
	 */
	private void setResolution(String resolution) {
		if (resolution == null) {
			throw new IllegalArgumentException("Invalid resolution.");
		}
		switch (resolution) {
			case Command.R_FIXED: 
				this.resolution = Resolution.FIXED;
				break;
			case Command.R_DUPLICATE:
				this.resolution = Resolution.DUPLICATE;
				break;
			case Command.R_WONTFIX:
				this.resolution = Resolution.WONTFIX;
				break;
			case Command.R_WORKSFORME:
				this.resolution = Resolution.WORKSFORME;
				break;
			case "":
				this.resolution = null;
				break;
			default:
				throw new IllegalArgumentException("Invalid resolution.");
				
		}
	}
	
	private void setNotes(ArrayList<String> notes) {
		if (notes == null || notes.isEmpty()) {
			throw new IllegalArgumentException("Invalid notes.");
		}
		this.notes = notes;
	}

	/**
	 * Getter for issue ID
	 * @return issueId 
	 */
	public int getIssueId() {
		return issueId;
	}
	
	/**
	 * Getter for state name
	 * @return name of state
	 */
	public String getStateName() {
		if (state == newState) {
			return NEW_NAME;
		}
		if (state == workingState) {
			return WORKING_NAME;
		}
		if (state == confirmedState) {
			return CONFIRMED_NAME;
		}
		if (state == verifyingState) {
			return VERIFYING_NAME;
		}
		if (state == closedState) {
			return CLOSED_NAME;
		}
		return null;
		//throw new IllegalArgumentException("Invalid state.");
	}
	
	/**
	 * Getter for issue type
	 * @return type of issue
	 * @throws IllegalArgumentException for invalid type of issue
	 */
	public String getIssueType() {
		if (issueType == IssueType.ENHANCEMENT) {
			return I_ENHANCEMENT;
		}
		if (issueType == IssueType.BUG) {
			return I_BUG;
		}
		return null;
		//throw new IllegalArgumentException("Invalid issue type.");
	}
	
	/**
	 * Getter for issue resolution 
	 * @return issue resolution
	 * @throws IllegalArgumentException for invalid resolution
	 */
	public String getResolution() {
		if (resolution == Resolution.FIXED) {
			return Command.R_FIXED;
		}
		if (resolution == Resolution.WORKSFORME) {
			return Command.R_WORKSFORME;
		}
		if (resolution == Resolution.WONTFIX) {
			return Command.R_WONTFIX;
		}
		if (resolution == Resolution.DUPLICATE) {
			return Command.R_DUPLICATE;
		}
		return null;
	}
	
	/**
	 * Getter for owner of issue
	 * @return owner
	 */
	public String getOwner() {
		return owner; 
	}
	
	/** 
	 * Getter for summary of issue
	 * @return summary of issue
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Getter for ArrayList of notes
	 * @return notes ArrayList
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}
	
	/**
	 * Getter for notes string 
	 * @return string of notes
	 */
	public String getNotesString() {
		String notesString = "";
		for (int i = 0; i < notes.size(); i++) {
			String note = notes.get(i);
			notesString += "-" + note + "\n";
		}
		return notesString;
	}
	
	/**
	 * Checks if issue is confirmed
	 * @return confirmation status of issue
	 */
	public boolean isConfirmed() {
		return confirmed; 
	}
	/**
	 * Turns Issue into a string representation of itself
	 * @return String of issue information
	 */
	public String toString() {
		String typeString = "";
		if (issueType == IssueType.ENHANCEMENT) {
			typeString = I_ENHANCEMENT;
		}
		if (issueType == IssueType.BUG) {
			typeString = I_BUG;
		}
		return "*" + String.valueOf(issueId) + "," + getStateName() + "," +
				typeString + "," + getSummary() + "," + getOwner() + "," +
				String.valueOf(confirmed) + "," + getResolution();
		
	}
	
	/**
	 * Adds note to ArrayList of notes
	 * @param note being added
	 */
	private void addNote(String note) {
		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException("Invalid note.");
		}
		notes.add("[" + getStateName() + "] " + note);
		
	}
	
	/**
	 * Updates issue given command
	 * @param c Command given
	 * @throws UnsupportedOperationException if the transition
	 * is inappropriate for the FSM
	 */
	public void update(Command c) {
		this.state.updateState(c);
	}
	
	/**
	 * Interface for states in the Issue State Pattern.  All 
	 * concrete issue states must implement the IssueState interface.
	 * The IssueState interface should be a private interface of the 
	 * Issue class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface IssueState {
		
		/**
		 * Update the Issue based on the given Command.
		 * An UnsupportedOperationException is throw if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Issue's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
	
	private class ClosedState implements IssueState {
		private ClosedState() {
			
		}
		/**
		 * Updates the issue based on the given Command.
		 * @param c Command given
		 */
		@Override
		public void updateState(Command c) {
			CommandValue command = c.getCommand();
			setResolution(Command.R_FIXED);
			
			if (command == CommandValue.REOPEN) {
				resolution = null;
				if (issueType == IssueType.ENHANCEMENT && owner 
						!= null && !owner.isEmpty()) {
					setState(WORKING_NAME);
					addNote(c.getNote());
					return;
				}
				if (issueType == IssueType.BUG) {
					if (confirmed && owner != null && !owner.isEmpty()) {
						setState(WORKING_NAME);
						addNote(c.getNote());
						return;
					}
					if (confirmed && (owner == null || owner.isEmpty())) {
						setState(CONFIRMED_NAME);
						addNote(c.getNote());
						return;
					}
				}
				if (owner == null || owner.isEmpty() && !confirmed) {
					setState(NEW_NAME);
					addNote(c.getNote());
					return;
				}
			}
			owner = "owner";
			throw new UnsupportedOperationException();
			
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		
		@Override
		public String getStateName() {
			return CLOSED_NAME;
		}
	}
	private class WorkingState implements IssueState {
		private WorkingState() {
			
		}
		/**
		 * Updates the issue based on the given Command.
		 * @param c Command given
		 */
		@Override
		public void updateState(Command c) {
			CommandValue command = c.getCommand();
			Resolution r = c.getResolution();
			switch (r) {
				case FIXED:
					setState(VERIFYING_NAME);
					addNote(c.getNote());
					setOwner(c.getOwnerId());
					break;
				case WONTFIX:
					if (command == CommandValue.RESOLVE) {
						setState(CLOSED_NAME);
						addNote(c.getNote());
						setOwner(c.getOwnerId());
						break;
					}
				default:
					if (issueType != IssueType.BUG && r ==
							Resolution.WORKSFORME) {
						throw new UnsupportedOperationException();
					}
					setState(CLOSED_NAME);
					addNote(c.getNote());
					setOwner(c.getOwnerId());
			}
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return WORKING_NAME;
		}
	}
	
	private class NewState implements IssueState {
		private NewState() {
			
		}
		/**
		 * Updates the issue based on the given Command.
		 * @param c Command given
		 */
		@Override
		public void updateState(Command c) {
			CommandValue command = c.getCommand();
			Resolution r = c.getResolution();
			switch (command) {
				case ASSIGN:
					if (getIssueType().equals(I_ENHANCEMENT)) {
						setState(WORKING_NAME);
						setOwner(c.getOwnerId());
						addNote(c.getNote());
						break;
					}
					if (getIssueType().equals(I_BUG)) {
						throw new UnsupportedOperationException();
					}
				case CONFIRM:
					if (getIssueType().equals(I_BUG)) {
						setState(CONFIRMED_NAME);
						setConfirmed(true);
						addNote(c.getNote());
						break;
					}
					else if (getIssueType().equals(I_ENHANCEMENT)) {
						throw new UnsupportedOperationException();
					}
				case RESOLVE:
					if (r == Resolution.WORKSFORME && getIssueType().equals(I_ENHANCEMENT)) {
						throw new UnsupportedOperationException();
					}
					else if (resolution == Resolution.FIXED) {
						throw new UnsupportedOperationException();
					}
					else if (resolution != Resolution.FIXED) {
						resolution = r;
						setState(CLOSED_NAME);
						addNote(c.getNote());
						break;
					}

				default: 
					throw new UnsupportedOperationException();
			}		
		}
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return NEW_NAME;
		}
	}
	
	private class VerifyingState implements IssueState {
		private VerifyingState() {
			
		}
		/**
		 * Updates the issue based on the given Command.
		 * @param c Command given
		 */
		@Override
		public void updateState(Command c) {
			CommandValue command = c.getCommand();
			if (command == CommandValue.VERIFY) {
				setState(CLOSED_NAME);
				addNote(c.getNote());
				return;
			}
			else {
				setState(WORKING_NAME);
				addNote(c.getNote());
				resolution = null;
			}
			
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return VERIFYING_NAME;
		}
	}
	private class ConfirmedState implements IssueState {
		private ConfirmedState() {
			
		}
		/**
		 * Updates the issue based on the given Command.
		 * @param c Command given
		 */
		@Override
		public void updateState(Command c) {
			CommandValue command = c.getCommand();
			Resolution r = c.getResolution();
			switch (command) {
			case ASSIGN:
				setState(WORKING_NAME);
				addNote(c.getNote());
				setOwner(c.getOwnerId());
				break;
			default:
				if (r == Resolution.WONTFIX) {
					setState(CLOSED_NAME);
					addNote(c.getNote());
					break;
				}
				throw new UnsupportedOperationException();
			}
		}
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return CONFIRMED_NAME;
		}
	}
	
	
	
}
