package com.infosys.dao;
import com.infosys.pojo.Task;
import java.util.*;


public class TaskDAO {

    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
        // Task[] afterAdd = Arrays.copyOf(tasks, tasks.length + 1);
        // afterAdd[afterAdd.length - 1] = task;
        // tasks = afterAdd;
        // return tasks;
    }

    public boolean delete(int taskId) {
        boolean delete = false;

        if (tasks.isEmpty()) {
            return delete;
        }
        else {
            tasks.remove(searchById(taskId));
            delete  = true;
        }
        return delete;
    }

    public void update( int taskId, Task updatedTask) {
        int index = 0;
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                tasks.set(index, updatedTask);
            }
            else index++;
        }
    }

    public Task searchById(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public Task searchByName(String username) {
        for(Task task: tasks) {
            if(task != null && task.getAssignedTo().equals(username)) {
                return task;
            }
        }
        return null;
    }

    public void sortTasks(boolean ascending, List<Task> sortedTasks) {
        if (ascending) {
            Collections.sort(sortedTasks);
        } else {
            Collections.sort(sortedTasks, Collections.reverseOrder());
        }
    }

    public Set<String> findDuplicates() {
        Set<String> duplicates = new TreeSet<>();

        for (Task task: tasks) {
            if (task.getTaskTitle().equals(task.getTaskTitle())) {
                duplicates.add(task.getTaskTitle());
            }
        }
        return duplicates;
    }

}
