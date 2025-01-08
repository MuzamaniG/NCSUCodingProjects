package edu.ncsu.csc216.issue_manager.model.io;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * IO class for reading from files and returning ArrayList of Issues
 * 
 * @author mhgausi
 */
public class IssueReader {

    /**
     * Reads issues from file
     * 
     * @param fileName name of file being read from
     * @return ArrayList of issues read
     * @throws IllegalArgumentException for any exceptions found
     */
    public static ArrayList<Issue> readIssuesFromFile(String fileName) {
        ArrayList<Issue> issues = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(fileName))) {
            sc.useDelimiter("\\r?\\n?[*]");
            while (sc.hasNext()) {
                issues.add(processIssue(sc.next()));
            }
            return issues;
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to read file.");
        }
    }

    /**
     * Processes issues for readIssuesFromFile method
     * 
     * @param line being read
     * @return issue processed
     */
    private static Issue processIssue(String line) {
        Issue issue;
        try (Scanner sc = new Scanner(line)) {
            sc.useDelimiter(",");
            int id = Integer.parseInt(sc.next());
            String state = sc.next().trim();
            String issueType = sc.next().trim();
            String summary = sc.next().trim();
            String owner = sc.next().trim();
            boolean confirmed = Boolean.parseBoolean(sc.next().trim());
            String resolution = sc.next().trim();

            sc.useDelimiter("\r?\n?[-]");
            ArrayList<String> notes = new ArrayList<>();
            while (sc.hasNext()) {
                String note = sc.next().trim();
                if (!note.isEmpty()) {
                    notes.add(note);
                }
            }

            issue = new Issue(id, state, issueType, summary, owner, confirmed, resolution, notes);
            return issue;

        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to read file.");
        }
    }
}
