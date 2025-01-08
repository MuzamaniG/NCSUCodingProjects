package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Class for list of issues being handled by manager.
 * Used to add issues, get issues, execute commands, and delete issues
 * @author mhgausi
 */
public class IssueList {

	/** Number of issues in list */
	private int counter;
	
	/** ArrayList of issues being used for class */
	private ArrayList<Issue> issues;
	
	/**
	 * Constructor for ArrayList of issues
	 */
	public IssueList() {
		issues = new ArrayList<>();
		counter = 1;
	}
	
	/**
	 * Adds issue to list of issues
	 * @param type of issue
	 * @param summary of issue
	 * @param note attached to issue
	 * @return next ID number stored in the counter
	 */
	public int addIssue(IssueType type, String summary, String note) {
		Issue issue = new Issue(counter, type, summary, note);
		issues.add(counter - 1, issue);
		counter++;
		return counter - 1;
	}
	
	/**
	 * Adds issues to IssueList
	 * @param issues list of issues being added
	 */
	public void addIssues(ArrayList<Issue> issues) {
		for (int i = 0; i < issues.size(); i++) {
			addIssue(issues.get(i));
		}
	}
	
	/**
	 * Private method for adding issue object to IssueList
	 * @param issue being added
	 */
	private void addIssue(Issue issue) {
		issues.add(issue);
	}
	
	/**
	 * Getter for ArrayList of issues
	 * @return ArrayList of issues
	 */
	public ArrayList<Issue> getIssues() {
		return issues;
	}
	
	/**
	 * Getter for ArrayList of issues based on type of issue
	 * @param type of issue
	 * @return ArrayList of issues with the same type
	 */
	public ArrayList<Issue> getIssuesByType(String type) {
		if (type == null) {
			throw new IllegalArgumentException("Invalid type.");
		}
		ArrayList<Issue> typeIssues = new ArrayList<>();
		for (int i = 0; i < issues.size(); i++) {
			Issue issue = issues.get(i);
			if (issue.getIssueType().equals(type)) {
				typeIssues.add(issue);
			}
		}
		return typeIssues;
	}
	
	/**
	 * Gets issue object based on its ID
	 * @param id of Issue
	 * @return Issue based on ID or null if there is no issue with said ID
	 */
	public Issue getIssueById(int id) {
		for (int i = 0; i < issues.size(); i++) {
			Issue issue = issues.get(i);
			if (issue.getIssueId() == id) {
				return issue;
			}
		}
		return null;
	}
	
	/**
	 * Executes command on issue based on ID and given command
	 * @param id of issue
	 * @param c Command being executed
	 */
	public void executeCommand(int id, Command c) {
		getIssueById(id).update(c);
	}
	
	/**
	 * Deletes issue from list based on its ID
	 * @param id of issue being deleted
	 */
	public void deleteIssueById(int id) {
		for (int i = 0; i < issues.size(); i++) {
			Issue issue = issues.get(i);
			if (issue.getIssueId() == id) {
				issues.remove(issue);
			}
		}
	}
	
}
