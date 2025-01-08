package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
* Class for reading Notebooks from file
* @author mhgausi
* @author ojain
*/
public class NotebookReader {
	
	/**
	* Reads notebook contents from a file
	* @param file File being read from
	* @return the processed Notebook
	* @throws IllegalArgumentException if file cannot be loaded/read from
	*/
	public static Notebook readNotebookFile(File file) {
		Scanner sc;
		String wholeFile = "";
		try {
			sc = new Scanner(file);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		String firstLine = sc.nextLine();
		if (firstLine.charAt(0) != '!') {
			sc.close();
			throw new IllegalArgumentException("Unable to load file.");
		}
		Notebook book = new Notebook(firstLine.substring(2));
		while (sc.hasNextLine()) {
			wholeFile = wholeFile + sc.nextLine() + "\n";
		}
		sc.close();
		sc = new Scanner(wholeFile);
		sc.useDelimiter("\\r?\\n?[#]");
		while (sc.hasNext()) {
			try {
				book.addTaskList(processTaskList(sc.next().substring(1)));
			} catch (Exception e) {
				// do nothing
			}
		}
		sc.close();
		book.setCurrentTaskList("Active Tasks");
		return book;
	}
	
	/**
	* Private method for processing task list from string given by file 
	* @param s the String being read from
	* @return the processed TaskList
	*/
    private static TaskList processTaskList(String s) {
    	TaskList list = null;
		Scanner sc = new Scanner(s);
		String firstLine = sc.nextLine();
		Scanner fl = new Scanner(firstLine);
		fl.useDelimiter(",");
		try {
			list = new TaskList(fl.next(), fl.nextInt());
		} catch (Exception e) {
			// do nothing
		}
		fl.close();
		sc.useDelimiter("\\r?\\n?[*]");
		while (sc.hasNext()) {
			processTask(list, sc.next().substring(1));
		}
		sc.close();
		return list;
    }
	
	/**
	* Private method for processing task from string and adding it to the task list
	* @param list the AbstractTaskList to add the Task to
	* @param s the String being read from
	* @return the processed Task
	*/
	private static Task processTask(AbstractTaskList list, String s) {
    	Task task = null;
		Scanner sc = new Scanner(s);
		String firstLine = sc.nextLine();
		if (firstLine.charAt(0) == ',') {
			sc.close();
			return null;
		}
		Scanner fl = new Scanner(firstLine);
		fl.useDelimiter(",");
		String name = "";
		String one = "";
		String two = "";
		try {
			name = fl.next();
			one = fl.next();
			two = fl.next();
		} catch (Exception e) {
			// do nothing
		}
		fl.close();
		Boolean recurring = false;
		Boolean active = false;
		if ("active".equals(one) || "active".equals(two)) {
			active = true;
		}
		if ("recurring".equals(one) || "recurring".equals(two)) {
			recurring = true;
		}
		String description = "";
		while (sc.hasNextLine()) {
			description = description + sc.nextLine() + "\n";
		}
		sc.close();
		try {
			task = new Task(name, description, recurring, active);
			list.addTask(task);
		} catch (Exception e) {
			// do nothing
		}
		return task;
	}
	
}