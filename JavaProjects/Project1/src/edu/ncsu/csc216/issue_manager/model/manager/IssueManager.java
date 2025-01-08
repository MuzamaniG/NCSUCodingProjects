package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.io.IssueWriter;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Issue manager class; manages issues with deletion, addition, saving, and loading
 * functions, as well as command execution
 * @author mhgausi
 */
public class IssueManager {
	
	/** Singleton instance of IssueManager */
	private static IssueManager instance = new IssueManager();
	
	/** List of issues */
	private IssueList issueList;

    /**
     * Gets instance of IssueManager
     * @return IssueManager instance
     */
    public static IssueManager getInstance() {
        if (instance == null) {
            instance = new IssueManager();
        }
        return instance;
    }
    
    /**
     * Private constructor for IssueManager
     */
	private IssueManager() {
		
	}
	
	/**
	 * Saves issues to given file
	 * @param fileName name of file being saved to
	 * @throws IllegalArgumentException if issues cannot be written to file
	 */
	public void saveIssuesToFile(String fileName) {
		IssueWriter.writeIssuesToFile(fileName, issueList.getIssues());
	}
	
	/**
	 * Loads issues from file using IO classes
	 * @param fileName name of file
	 */
	public void loadIssuesFromFile(String fileName) {
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(fileName);
		issueList.addIssues(issues);
	}
	
	/**
	 * Creates new list of issues
	 */
	public void createNewIssueList() {
		issueList = new IssueList();
	}
	
	/**
	 * Gets list of issues as array
	 * @return 2D array of issues
	 */
	public Object[][] getIssueListAsArray() {
		Object[][] issueListArray = new Object[issueList.getIssues().size()][4];
		for (int i = 0; i < issueList.getIssues().size(); i++) {
			issueListArray[i][0] = getIssueById(i + 1).getIssueId();
			issueListArray[i][1] = getIssueById(i + 1).getStateName();
			issueListArray[i][2] = getIssueById(i + 1).getIssueType();
			issueListArray[i][3] = getIssueById(i + 1).getSummary();
		}
		return issueListArray;
	}
	
	/**
	 * Gets issue list as an array by the issue type
	 * @param type of issue
	 * @return 2D array of objects with 1 row per Issue
	 * @throws IllegalArgumentException for invalid issue type
	 */
	public Object[][] getIssueListAsArrayByIssueType(String type) {
		if (type == null) {
			throw new IllegalArgumentException("Invalid type.");
		}
		Object[][] issueListArray = new Object[issueList.getIssuesByType(type).size()][4];
		for (int i = 0; i < issueList.getIssues().size(); i++) {
			if (issueList.getIssueById(i + 1).getIssueType().equals(type)) {
				issueListArray[i][0] = getIssueById(i + 1).getIssueId();
				issueListArray[i][1] = getIssueById(i + 1).getStateName();
				issueListArray[i][2] = getIssueById(i + 1).getIssueType();
				issueListArray[i][3] = getIssueById(i + 1).getSummary();
			}
		}
		return issueListArray;
	}
	
	/**
	 * Gets issue using given ID
	 * @param id of issue
	 * @return issue with corresponding ID
	 */
	public Issue getIssueById(int id) {
		return issueList.getIssueById(id);
	}
	
	/**
	 * Executes given command on issue by ID
	 * @param id of issue being commanded
	 * @param c Command being executed
	 */
	public void executeCommand(int id, Command c) {
		issueList.executeCommand(id, c);
	}
	/**
	 * Deletes issue given its ID
	 * @param id of issue
	 */
	public void deleteIssueById(int id) {
		issueList.deleteIssueById(id);
	}
	
	/**
	 * Adds issue to the list
	 * @param type IssueType
	 * @param summary of issue being added
	 * @param note attached to issue
	 */
	public void addIssueToList(IssueType type, String summary, String note) {
		this.issueList.addIssue(type, summary, note);
	}
	
}
