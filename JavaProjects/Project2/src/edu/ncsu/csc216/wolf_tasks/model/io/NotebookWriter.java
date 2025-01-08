package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
* NotebookWriter class; in charge of writing notebooks to given files 
* @author mhgausi
* @author ojain
*/ 
public class NotebookWriter {
	
	/**
	* Receives a File with the file name to write to, the name of the notebook, and an ISortedList of TaskLists to write to file. 
	* @param file File being written to
	* @param notebook String name of notebook
	* @param taskLists ISortedList of TaskLists to write to file
	* @throws IllegalArgumentException for any exceptions during the writing process
	*/
    public static void writeNotebookFile(File file, String notebook, ISortedList<TaskList> taskLists) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println("! " + notebook);
            for (int i = 0; i < taskLists.size(); i++) {
                TaskList taskList = taskLists.get(i);
                writer.println("# " + taskList.getTaskListName() + "," + taskList.getCompletedCount());
                for (int j = 0; j < taskList.getTasks().size(); j++) {
                    writer.println(taskList.getTask(j).toString());
                }
            }

            writer.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to save file.");
        }
    }
}