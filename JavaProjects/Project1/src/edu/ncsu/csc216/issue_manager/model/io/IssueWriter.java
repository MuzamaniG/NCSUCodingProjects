package edu.ncsu.csc216.issue_manager.model.io;

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.List;


import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * IO class in charge of writing issues to files
 * @author mhgausi
 */
public class IssueWriter {
	
	/**
	 * Writes issues to file given name and issue
	 * @param fileName name of file being written to
	 * @param issues ArrayList of issues being written from
	 */
	public static void writeIssuesToFile(String fileName, List<Issue> issues) {
        try (PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(fileName)))) {
            for (Issue issue : issues) {{
                fileWriter.println("*" + issue.toString() + issue.getNotesString());
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to save file.");
        }
    }
}
