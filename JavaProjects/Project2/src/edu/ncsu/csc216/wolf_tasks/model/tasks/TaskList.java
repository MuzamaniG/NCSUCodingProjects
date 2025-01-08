package edu.ncsu.csc216.wolf_tasks.model.tasks;

/** 
* Class for list of tasks; extends AbstractTaskList and implements Comparable interface
* @author mhgausi
* @author ojain 
*/
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {

	/**
	* Constructor for TaskList; calls superconstructor to set fields 
	* @param taskListName name of task list
	* @param completedCount number of completed tasks 
	*/
	public TaskList(String taskListName, int completedCount) {
		super(taskListName, completedCount);
	}
	
	/** 
	* Getter for tasks as 2D String Array
	* @return 2D String array where the first column is the priority of the Task, starting at 1, and the name of the Task.
	*/
	public String[][] getTasksAsArray() {
		String[][] arr = new String[this.getTasks().size()][2];
		for (int i = 0; i < this.getTasks().size(); i++) {
			arr[i][0] = String.valueOf(i + 1);
			arr[i][1] = this.getTasks().get(i).getTaskName();
		}
		return arr;
	}
	
	/**
	* Compares the names of the TaskLists.
	* @param taskList TaskList being compared to current
	* @return int based on list similarity
	*/
	public int compareTo(TaskList taskList) {
		return this.getTaskListName().compareTo(taskList.getTaskListName());
	}
	
}