package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
* Task class; contains the information about each individual task including the taskName, taskDescription, if the task is recurring, and if the task is active. 
* The Task additionally contains an ISwapList of AbstractTaskLists called taskLists. The Task class implements the Cloneable interface.
* @author mhgausi
* @author ojain
*/
public class Task implements Cloneable {

	/** Name of task */
	private String taskName;
	/** Description of task */
	private String taskDescription;
	/** Boolean for whether task is recurring */
	private boolean recurring;
	/** Boolean for whether task is active */
	private boolean active;
	/** List of abstract task lists */
	private ISwapList<AbstractTaskList> taskLists;
	
	/** 
	* Constructs task by setting name, details, recurring status, and active status parameters to fields
	* @param taskName name of task
	* @param taskDetails task details
	* @param recurring boolean status for whether task is recurring
	* @param active boolean status for whether task is active
	* @throws IllegalArgumentException if taskName, taskDetails, recurring, or active are invalid
	*/ 
	public Task(String taskName, String taskDetails, boolean recurring, boolean active) {
		setTaskName(taskName);
		setTaskDescription(taskDetails);
		setRecurring(recurring);
		setActive(active);
		this.taskLists = new SwapList<AbstractTaskList>();
	}

	/**
	 * Getter for name of task
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Setter for name of task
	 * @param taskName the taskName to set
	 * @throws IllegalArgumentException for null or empty taskName
	 */
	public void setTaskName(String taskName) {
		if (taskName == null || "".equals(taskName)) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskName = taskName;
	}

	/**
	 * Getter for taskDescription
	 * @return taskDescription
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * Setter for description of task
	 * @param taskDescription the taskDescription to set
	 * @throws IllegalArgumentException for null taskDescription
	 */
	public void setTaskDescription(String taskDescription) {
		if (taskDescription == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskDescription = taskDescription;
	}

	/**
	 * Checks if task is recurring
	 * @return task recurring status
	 */
	public boolean isRecurring() {
		return recurring;
	}

	/**
	 * Sets task recurring status
	 * @param recurring boolean status for whether task is recurring 
	 */
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	/**
	 * Checks if task is active
	 * @return active status for task
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets active status for task
	 * @param active boolean for whether task is active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/** 
	* Getter for name of first task list in list of task lists
	* @return name of first task list containing task
	*/
	public String getTaskListName() {
		if (taskLists == null || taskLists.size() == 0) {
			return "";
		}
		return taskLists.get(0).getTaskListName();
	}
	
	/**
	*  If the AbstractTaskList is NOT already registered with the Task the AbstractTaskList is added to the end of the taskLists field. 
	* @param taskList AbstractTaskList being added
	* @throws IllegalArgumentException if taskList is null/
	*/
	public void addTaskList(AbstractTaskList taskList) {
		if (taskList == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		boolean found = false;
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).equals(taskList)) {
				found = true;
				break;
			}
		}
		if (!found) {
			taskLists.add(taskList);
		}
	}
	
	/**
	* Completes the Task and notifies the taskLists by sharing the current Task instance via the TaskList.completeTask(Task) method. 
	* If the Task is recurring, the Task is cloned and the cloned Task is added to each registered AbstractTaskList.
	* @throws IllegalArgumentException if Task cannot be cloned
	*/
	public void completeTask() {
		Task clone = null;
		if (recurring) {
			try {
				clone = (Task) clone();
			}
			catch (CloneNotSupportedException e) {
				throw new IllegalArgumentException("Clone not supported.");
			}
		}
		for (int i = 0; i < taskLists.size(); i++) {
			taskLists.get(i).completeTask(this);
			if (recurring && clone != null) {
				taskLists.get(i).addTask(clone);
			}
		}
	}
	
	/**
	* Returns a copy of the Task
	* @return Object clone of task 
	* @throws CloneNotSupportedException if there are no AbstractTaskLists registered with the Task
	*/
	public Object clone() throws CloneNotSupportedException {
		if (taskLists.size() == 0) {
			throw new CloneNotSupportedException("Cannot clone.");
		}
		Task newTask  = this;
		return newTask;
	}

	/** 
	* Converts task to String
	* @return String representation of task
	*/
	public String toString() {
		String ret = "* " + getTaskName();
		if (recurring) {
			ret = ret + ",recurring";
		}
		if (active) {
			ret = ret + ",active";
		}
		if (!"".equals(getTaskDescription())) {
			ret = ret + "\n" + getTaskDescription();
		}
		return ret;
	}
	
}