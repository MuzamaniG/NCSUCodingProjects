package edu.ncsu.csc216.wolf_tasks.model.notebook;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;

/**
* Notebook class; Contains an ISortedList of TaskLists, one ActiveTaskList, 
* an AbstractTaskList for the currentTaskList, a notebookName, and boolean flag 
* that keeps track of if the Notebook has been changed since the last save.
*
* @author mhgausi
* @author ojain
*/ 
public class Notebook {

	/** Name of notebook */
	private String notebookName;
	
	/** Boolean for whether notebook has been changed */
	private boolean isChanged;
	
	/** ISortedList of TaskLists */
	private ISortedList<TaskList> taskLists;
	
	/** List of active tasks */
	private ActiveTaskList activeTaskList; 
	
	/** List of current tasks */
	private AbstractTaskList currentTaskList;
	
	/**
	* Constructor for a Notebook with the given name. The taskLists field is constructed as a SortedList and the activeTaskList 
	* is constructed and is set to the currentTaskList.  isChanged is initialized to true.
	* An IAE is thrown if the notebookName is null, empty, or matches ACTIVE_TASKS_NAME.
	* @param notebookName name of notebook
	* @throws IllegalArgumentException for invalid notebookName
	*/
	public Notebook(String notebookName) {
		setNotebookName(notebookName);
		taskLists = new SortedList<TaskList>();
		activeTaskList = new ActiveTaskList();
		currentTaskList = activeTaskList;
		setChanged(true);
	}
	
	/**
	* Saves notebook to file
	* @param f File being saved to
	*/ 
	public void saveNotebook(File f) {
		NotebookWriter.writeNotebookFile(f, notebookName, taskLists);
		setChanged(false);
	}
	
	/**
	* Getter for name of the notebook
	* @return String name of notebook
	*/
	public String getNotebookName() {
		return notebookName;
	}
	
	/**
	* Private setter for notebook name
	* @param notebookName String name of notebook being set
	* @throws IllegalArgumentException if the notebookName is null, empty, or matches "Active Tasks"
	*/
	private void setNotebookName(String notebookName) {
		if (notebookName == null || "".equals(notebookName) || "active tasks".equals(notebookName.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.notebookName = notebookName;
	}
	
	/**
	* Checks if notebook has changed 
	* @return boolean isChanged for whether notebook has changed
	*/
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	* Sets whether notebook has changed
	* @param isChanged boolean for whether notebook has changed
	*/
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	/**
	* Adds TaskList to notebook
	* @param taskList TaskList being added 
	* @throws IllegalArgumentException if the name of the new TaskList matches an existing one
	*/
	public void addTaskList(TaskList taskList) {
		for (int i = 0; i < getTaskListsNames().length; i++) {
			if (getTaskListsNames()[i].equalsIgnoreCase(taskList.getTaskListName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		taskLists.add(taskList);
		setCurrentTaskList(taskList.getTaskListName());
		setChanged(true);
		getActiveTaskList();
	}
	
	/**
	* Getter for String array of task list names
	* @return String array taskListsNames
	*/
	public String[] getTaskListsNames() {
		String[] arr = new String[taskLists.size() + 1];
		arr[0] = "Active Tasks";
		for (int i = 0; i < taskLists.size(); i++) {
			arr[i + 1] = taskLists.get(i).getTaskListName();
		}
		return arr;
	}
	
	/**
	* Private getter for active task list
	* Does not return anything but makes sure that
	* the active task list remains in sorted order
	*/
	private void getActiveTaskList() {
		activeTaskList.clearTasks(); // clears initial task list at the beginning
		for (int i = 0; i < taskLists.size(); i++) { // iterates through each task list
			AbstractTaskList listOfTasks = taskLists.get(i); 
			for (int j = 0; j < listOfTasks.getTasks().size(); j++) { //iterates through each task
				if (listOfTasks.getTask(j).isActive()) {
					activeTaskList.addTask(listOfTasks.getTask(j)); //adds task to the cleared list if active
				}
			}
		}
	}
	
	/**
	* Setter for current task list
	* @param taskListName String name of current task list
	*/
	public void setCurrentTaskList(String taskListName) {
		getActiveTaskList();
		currentTaskList = activeTaskList;
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskListName.equals(taskLists.get(i).getTaskListName())) {
				currentTaskList = taskLists.get(i);
			}
		}
	}
	
	/**
	* Getter for current TaskList
	* @return AbstractTaskList currentTaskList
	*/
	public AbstractTaskList getCurrentTaskList() {
		getActiveTaskList();
		return currentTaskList;
	}
	
	/**
	* Edits TaskList based on its name
	* @param taskListName name of TaskList
	*/
	public void editTaskList(String taskListName) {
		if (currentTaskList instanceof ActiveTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskListName.toLowerCase().equals(taskLists.get(i).getTaskListName().toLowerCase()) 
					|| currentTaskList.getTaskListName().equals(taskListName) || 
					"active tasks".equals(taskListName.toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		TaskList list = (TaskList) currentTaskList;
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).equals(currentTaskList)) {
				list = taskLists.remove(i);
				break;
			}
		}
		list.setTaskListName(taskListName);
		taskLists.add(list);
		getActiveTaskList();
	}
	
	/**
	* Removes the currentTaskList and then set to the activeTaskList. isChanged is updated to true.
	* @throws IllegalArgumentException if the currentTaskList is the activeTaskList
	*/
	public void removeTaskList() {
		if (currentTaskList instanceof ActiveTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		}
		if (taskLists.contains((TaskList) currentTaskList)) {
			for (int i = 0; i < taskLists.size(); i++) {
				if (taskLists.get(i).equals(currentTaskList)) {
					taskLists.remove(i);
					break;
				}
			}
			setCurrentTaskList("Active Tasks");
			getActiveTaskList();
			setChanged(true);
		}
	}
	
	/** 
	* Adds task to currentTaskList
	* @param task Task being added 
	*/
	public void addTask(Task task) {
		if (currentTaskList instanceof TaskList) {
			currentTaskList.addTask(task);
			if (task.isActive()) {
				getActiveTaskList();
			}
			setChanged(true);
		}
	}
	
	/**
	* Edits the Task if the currentTaskList is a TaskList. 
	* @param idx index of task being edited
	* @param taskName name of task
	* @param taskDescription description of task
	* @param recurring boolean whether task is recurring
	* @param active boolean whether task is active
	*/
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		if (currentTaskList instanceof TaskList) {
			currentTaskList.getTask(idx).setTaskName(taskName);
			currentTaskList.getTask(idx).setTaskDescription(taskDescription);
			currentTaskList.getTask(idx).setRecurring(recurring);
			currentTaskList.getTask(idx).setActive(active);
			getActiveTaskList();
			setChanged(true);
		}
	}
	
}