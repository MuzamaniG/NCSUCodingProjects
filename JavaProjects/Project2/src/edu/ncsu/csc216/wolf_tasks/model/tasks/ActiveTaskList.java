package edu.ncsu.csc216.wolf_tasks.model.tasks;

/** 
* Class for list of Active Tasks called "Active Tasks"; extends AbstractTaskList for construction
* @author mhgausi
* @author ojain 
*/
public class ActiveTaskList extends AbstractTaskList {

	/** Unchangeable name of active tasks list */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/** 
	* Constructor for ActiveTaskList; calls super constructor
	*/
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
	}
	
	/**
	* Adds task to active task list 
	* @param t Task being added 
	* @throws IllegalArgumentException if task is not active
	*/
	@Override
	public void addTask(Task t) {
		if (!t.isActive()) {
			throw new IllegalArgumentException("Cannot add task to Active Tasks.");
		}
		super.addTask(t);
	}
	
	/**
	* Setter for name of task list
	* @param taskListName name of task list
	*/
	@Override
	public void setTaskListName(String taskListName) {
		if (!ACTIVE_TASKS_NAME.equals(taskListName)) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		super.setTaskListName(taskListName);
	}
	
	/**
	* Getter for ActiveTaskList as 2D String Array
	* @return 2D String array of tasks where the first column is the name of the TaskList that the Task belongs to 
	* (or at least the TaskList at index 0) and the name of the Task
	*/
	public String[][] getTasksAsArray() {
		String[][] arr = new String[this.getTasks().size()][2];
		for (int i = 0; i < this.getTasks().size(); i++) {
			arr[i][0] = this.getTasks().get(i).getTaskListName();
			arr[i][1] = this.getTasks().get(i).getTaskName();
		}
		return arr;
	}
	
	/**
	* Clears the ActiveTaskList of all Tasks.
	*/
	public void clearTasks() {
		while (getTasks().size() != 0) {
			removeTask(0);
		}
	}
	
}