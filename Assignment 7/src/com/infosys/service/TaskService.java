package com.infosys.service;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.infosys.dao.TaskDAO;
import com.infosys.exception.ArrayFullException;
import com.infosys.exception.StringReadException;
import com.infosys.exception.TaskNotFound;
import com.infosys.pojo.Task;

public class TaskService {
    private TaskDAO taskDAO;
    private Scanner scanner;
    private int count = 0;
    private static final int MAX_TASKS= 5;

    public TaskService() {
        taskDAO = new TaskDAO();
        scanner = new Scanner(System.in);
    }

    public void nameInput() throws StringReadException{
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        if(name == null){
            throw new StringReadException("The username cannot be null");
        }
    }

    //enter the number of tasks
    public int numInput() throws InputMismatchException{
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Please enter an integer: ");
                count = scanner.nextInt();
                validInput = true; 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); 
            }
        }
        return count;

    }

    //add a new task
    public Task tasksInput() throws StringReadException, InputMismatchException, ArrayFullException{
        int taskId = 0;

        if (taskDAO.getTasks().length >= MAX_TASKS) {
            throw new ArrayFullException("Task array is full. Cannot add more tasks.");
        }
    
        Task newTask = null;
    
        try {
            System.out.println("\nPlease enter the task ID: ");
            taskId = scanner.nextInt();
            scanner.nextLine(); 
    
            System.out.println("\nPlease enter the task title: ");
            String taskTitle = scanner.nextLine();
    
            System.out.println("\nPlease enter the task text: ");
            String taskText = scanner.nextLine();
    
            System.out.println("\nPlease enter the assignee: ");
            String assignedTo = scanner.nextLine();
    
            if (taskTitle.isEmpty() || taskText.isEmpty()) {
                throw new StringReadException("The task title or text cannot be empty.");
            } else if (assignedTo.isEmpty()) {
                throw new StringReadException("The assignee name cannot be empty.");
            }
    
            newTask = new Task(taskId, taskTitle, taskText, assignedTo, null);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); 
            newTask = tasksInput(); 
        } catch (StringReadException e) {
            System.out.println("Error: " + e.getMessage());
            newTask = tasksInput(); 
        }
    
        return newTask;
    }

    //display all tasks in list
    public void display(Task[] tasks) {
        if (tasks.length == 0) {
            System.out.println("Your task list is empty.");
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public Task[] add(Task[] originalTasks) throws StringReadException, ArrayFullException{
        
        Task newTask = tasksInput();
        return taskDAO.add(newTask);
    }

    public Task[] update(Task[] originalTasks) throws StringReadException, ArrayFullException, InputMismatchException{
        
        int taskId = 0;

        try {
            System.out.println("\nPlease enter the task ID to update: ");
            taskId = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }


        Task updatedTask = tasksInput();
        return taskDAO.update(originalTasks, taskId, updatedTask);
    }

    public Task[] delete(Task[] originalTasks) throws InputMismatchException{

        int taskId = 0;

        try {
            System.out.println("\nPlease enter the task ID to delete: ");
            taskId = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }

        if (taskDAO.delete(taskId)) {
            System.out.println("\nSuccessfully deleted task with ID: " + taskId);
        }
        return taskDAO.getTasks();
    }

    public void searchById(Task[] originalTasks) throws InputMismatchException, TaskNotFound{
        int taskId = 0;
        try {
            System.out.println("\nPlease enter the task ID to search: ");
            taskId = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }

        Task task = taskDAO.searchById(originalTasks, taskId);
        if (task != null) {
            System.out.println("\nThe task you are looking for is: " + task);
        } else {
            throw new TaskNotFound("\nNo tasks found with the given ID.");
        }
    }

    public void searchAssignName(Task[] oriTask, String username) throws TaskNotFound{
        Task task = taskDAO.searchByName(oriTask, username);
        if (task != null) {
            System.out.println("\nThe task you are looking for is: " + task);
        } else {
            throw new TaskNotFound("\nNo tasks assigned with the given username.");
        }
    }

    public void tasksInOrder(Task[] tasks) {
        System.out.println("\nPlease enter the order (a for ascending, d for descending): ");
        String order = scanner.nextLine();

        while (!order.equals("a") && !order.equals("d")) {
            System.out.println("\nInvalid input, please try again: ");
            order = scanner.nextLine();
        }

        switch(order){
            case "a":
                boolean ascending = order.equals("a");
                taskDAO.sortTasks(tasks, ascending);
                display(tasks);
            case "d":
                boolean descending = order.equals("d");
                taskDAO.sortTasks(tasks, descending);
                display(tasks);
        }

    }

    public void repeatCheck(Task[] tasks) {
        Set<String> duplicates = taskDAO.findDuplicates(tasks);
        if (duplicates.isEmpty()) {
            System.out.println("\nNo duplicate tasks found.");
        } else {
            System.out.println("\nDuplicate task titles:");
            for (String title : duplicates) {
                System.out.println(title);
            }
        }
    }

    //a client can assign task to a visitor
    public Task[] assignTask(Task[] tasks) {
        System.out.println("\nPlease enter the task ID to reassign: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPlease enter the new assignee's name: ");
        String name = scanner.nextLine();

        for (Task task : tasks) {
            if (task != null && task.getTaskId() == taskId) {
                task.setAssignedTo(name);
                System.out.println("\nSuccessfully assigned task with ID: " + taskId + " to " + name);
            }
        }
        return tasks;
    }

    //mark tasks as completed
    public void markTaskAsCompleted(String username, Task[] tasks) throws TaskNotFound{
        boolean taskFound = false;

        for (Task task : tasks) {
            if (task != null && task.getAssignedTo().equals(username)) {
                task.setTaskStatus("completed");
                System.out.println("Task has been marked as completed.");
                taskFound = true;
                break; // Exit loop once task is found and marked
            }
        }
    
        if (!taskFound) {
            throw new TaskNotFound("No tasks found assigned to " + username);
        }
    }
    

    //sort visitor's task
    public void sortVisitorTask(Task[] tasks, String visitorName) {
        Task[] visitorList = new Task[tasks.length];

        for(int i = 0; i < tasks.length; i++){
            Task task = taskDAO.searchByName(tasks, visitorName);
            if (task != null) {
                visitorList[i] = task;
            }
            
        }
        tasksInOrder(visitorList);
    }

    //sort a client's tasks
    public void sortClientTask(Task[] tasks, String clientName) {
        Task[] clientList = new Task[tasks.length];
        int index = 0;

        for(Task task: tasks){
            if (task != null && task.getAssignedTo().equals(clientName)) {
                clientList[index++] = task;
            }   
        }

        for (int i = index; i < clientList.length; i++) {
            clientList[i] = null;
        }
        tasksInOrder(clientList);
    }


    //display tasks marked as completed or uncompleted
    public void displayCompletedOrIncompleteTasks(Task[] tasks, String username, boolean completed) {
        if (completed) {
            System.out.println("Completed tasks for " + username + ":");
            for (Task task : tasks) {
                if (task != null && task.getAssignedTo().equals(username) && task.getTaskStatus().equals("completed")) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("Incomplete tasks for " + username + ":");
            for (Task task : tasks) {
                if (task != null && task.getAssignedTo().equals(username) && !task.getTaskStatus().equals("Incompleted")) {
                    System.out.println(task);
                }
            }
        }
    }
    
    
}
