package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/** 
* Class for abstract lists of tasks; contains name of task list and number of completed tasks 
* @author mhgausi
* @author ojain
*/
public abstract class AbstractTaskList {

	/** Name of task list */
	private String taskListName;
	
	/** Number of completed tasks */
	private int completedCount;
	
	/** Swap List of tasks */
	private ISwapList<Task> tasks;
	
	/**
	* Constructor for AbstractTaskList; 
	* Sets the fields from the parameters and constructs a SwapList for the Tasks.
	* @param taskListName name of task list
	* @param completedCount number of completed tasks
	* @throws IllegalArgumentException if completedCount is less than 0 or the name is null/empty string
	*/
	public AbstractTaskList(String taskListName, int completedCount) {
		setTaskListName(taskListName);
		if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		this.completedCount = completedCount;
		tasks = new SwapList<Task>();
	}

	/**
	 * Getter for name of task list
	 * @return the taskListName
	 */
	public String getTaskListName() {
		return taskListName;
	}

	/**
	 * Setter for taskListName
	 * @param taskListName the taskListName to set
	 * @throws IllegalArgumentException if the name parameter is null or an empty string
	 */
	public void setTaskListName(String taskListName) {
		if (taskListName == null || "".equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.taskListName = taskListName;
	}
	
	/** 
	* Getter for ISwapList of tasks 
	* @return ISwapList of tasks
	*/
	public ISwapList<Task> getTasks() {
		return tasks;
	}
	
	/**
	* Getter for number of completed tasks
	* @return int number of completed tasks 
	*/
	public int getCompletedCount() {
		return completedCount;
	}
	
	/**
	* Adds task to list 
	* @param task Task being added
	*/
	public void addTask(Task task) {
		tasks.add(task);
		task.addTaskList(this);
	}
	
	/** 
	* Removes the Task at the given index.
	* @param idx index of task
	* @return task at given index 
	*/
	public Task removeTask(int idx) {
		return tasks.remove(idx);
	}
	
	/** 
	* Returns the Task at the given index.
	* @param idx index of task
	* @return task at given index 
	*/
	public Task getTask(int idx) {
		return tasks.get(idx);
	}
	
	/**
	* Completes the given task 
	* @param task Task being completed
	*/
	public void completeTask(Task task) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i) == task) {
				removeTask(i);
				break;
			}
		}
		completedCount++;
	}
	
	/** 
	* Getter for tasks as 2D array 
	* @return 2D string array of tasks
	*/
	public abstract String[][] getTasksAsArray();
	
}