package com.infosys.dao;
import com.infosys.pojo.Task;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


public class TaskDAO {
    private Task[] tasks = new Task[5];

    // public TaskDAO(int initialSize) {
    //     tasks = new Task[initialSize]; 
    // }

    public Task[] getTasks() {
        return tasks;
    }

    public Task[] add(Task task) {
        Task[] afterAdd = Arrays.copyOf(tasks, tasks.length + 1);
        afterAdd[afterAdd.length - 1] = task;
        tasks = afterAdd;
        return tasks;
    }

    public boolean delete(int taskId) {
        boolean delete = false;

        if (tasks.length == 0) {
            System.out.println("You have no tasks today.");
            return false;
        }

        Task[] afterDel = new Task[tasks.length - 1];
        int newIndex = 0;

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].getTaskId() != taskId) {
                if (newIndex < afterDel.length) {
                    afterDel[newIndex++] = tasks[i];
                }
            } else if (tasks[i] != null && tasks[i].getTaskId() == taskId) {
                delete = true;
            }
        }

        if (delete) {
            tasks = afterDel;
        } else {
            System.out.println("Task with ID " + taskId + " not found.");
        }

        return delete;
    }

    public Task[] update(Task[] originalTasks, int taskId, Task updatedTask) {
        for (int i = 0; i < originalTasks.length; i++) {
            if (originalTasks[i].getTaskId() == taskId) {
                originalTasks[i] = updatedTask;
            }
        }
        return originalTasks;
    }

    public Task searchById(Task[] originalTasks, int taskId) {
        for (Task task : originalTasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public Task searchByName(Task[] oriTasks, String username) {
        for(Task task: oriTasks) {
            if(task != null && task.getAssignedTo().equals(username)) {
                return task;
            }
        }
        return null;
    }

    public void sortTasks(Task[] tasks, boolean ascending) {
        if (ascending) {
            Arrays.sort(tasks, Comparator.comparing(Task::getTaskId));
        } else {
            Arrays.sort(tasks, Comparator.comparing(Task::getTaskId).reversed());
        }
    }

    public Set<String> findDuplicates(Task[] tasks) {
        Set<String> duplicates = new TreeSet<>();
        for (int i = 0; i < tasks.length - 1; i++) {
            for (int j = i + 1; j < tasks.length; j++) {
                if (tasks[i].getTaskTitle().equals(tasks[j].getTaskTitle())) {
                    duplicates.add(tasks[i].getTaskTitle());
                }
            }
        }
        return duplicates.isEmpty() ? null : duplicates;
    }
}
