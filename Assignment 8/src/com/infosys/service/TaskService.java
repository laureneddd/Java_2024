package com.infosys.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

    public TaskService() {
        taskDAO = new TaskDAO();
        scanner = new Scanner(System.in);
    }

    //enter username
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

    //display all tasks in list
    public void display() {
        
        if (taskDAO.getTasks().isEmpty()) {
            System.out.println("\nYour task list is empty.");
        }
        else {
            for (Task task : taskDAO.getTasks()) {
                System.out.println("\nYour tasks: " + task);
            }
        }
    }

    public void add() throws StringReadException, ArrayFullException{
        int taskId;
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
            System.out.println("Invalid input. Your input should be a valid integer.");
        } catch (StringReadException e) {
            System.out.println("Error: " + e.getMessage());
        }
        taskDAO.add(newTask);
    }

    public void update() throws StringReadException, ArrayFullException, InputMismatchException{
        
        int taskId = 0;

        try {
            System.out.println("\nPlease enter the task ID to update: ");
            taskId = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }

        Task updatedTask = taskDAO.searchById(taskId);
        taskDAO.update(taskId, updatedTask);
    }


    public void delete() throws InputMismatchException{

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
        else{
            System.out.println("\nYou failed to delete a task.");
        }

    }

    public void searchById() throws InputMismatchException, TaskNotFound{
        int taskId = 0;
        try {
            System.out.println("\nPlease enter the task ID to search: ");
            taskId = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }

        Task task = taskDAO.searchById(taskId);
        if (task != null) {
            System.out.println("\nThe task you are looking for is: " + task);
        } else {
            throw new TaskNotFound("\nNo tasks found with the given ID.");
        }
    }

    public void searchAssignName(String username) throws TaskNotFound{
        Task task = taskDAO.searchByName(username);
        if (task != null) {
            System.out.println("\nThe task you are looking for is: " + task);
        } else {
            throw new TaskNotFound("\nNo tasks assigned with the given username.");
        }
    }

    public void tasksInOrder(List<Task> tasks) {
        System.out.println("\nPlease enter the order (a for ascending, d for descending): ");
        String order = scanner.nextLine();

        while (!order.equals("a") && !order.equals("d")) {
            System.out.println("\nInvalid input, please try again: ");
            order = scanner.nextLine();
        }

        switch(order){
            case "a":
                boolean ascending = order.equals("a");
                taskDAO.sortTasks(ascending, tasks);
                System.out.println(tasks);

            case "d":
                boolean descending = order.equals("d");
                taskDAO.sortTasks(descending, tasks);
                System.out.println(tasks);
        }

    }

    public void repeatCheck() {
        Set<String> duplicates = taskDAO.findDuplicates();
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
    public void assignTask() {
        System.out.println("\nPlease enter the task ID to reassign: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPlease enter the new assignee's name: ");
        String name = scanner.nextLine();

        for (Task task : taskDAO.getTasks()) {
            if (task != null && task.getTaskId() == taskId) {
                task.setAssignedTo(name);
                System.out.println("\nSuccessfully assigned task with ID: " + taskId + " to " + name);
            }
        }

    }

    //mark tasks as completed
    public void markTaskAsCompleted(String username) throws TaskNotFound{
        boolean taskFound = false;

        for (Task task : taskDAO.getTasks()) {
            if (task != null && task.getAssignedTo().equals(username)) {
                task.setTaskStatus("completed");
                System.out.println("Task has been marked as completed.");
                taskFound = true;
                break; 
            }
        }
    
        if (!taskFound) {
            throw new TaskNotFound("No tasks found assigned to " + username);
        }
    }
    

    //sort visitor's task
    public void sortVisitorTask(String visitorName) {
        List<Task> visitorList = new ArrayList<>();

        for(Task task: taskDAO.getTasks()){
            if (task != null && task.getAssignedTo().equals(visitorName)) {
                visitorList.add(task);
            }
        }
        if(visitorList.isEmpty()){
            System.out.println(visitorName + ", your task list is empty.");
        }
        else {
            tasksInOrder(visitorList);
        }
    }

    //sort a client's tasks
    public void sortClientTask(String clientName) {
        List<Task> clientList = new ArrayList<>();

        for(Task task: taskDAO.getTasks()){
            if (task != null) {
                clientList.add(task);
            }   
        }
        if(clientList.isEmpty()){
            System.out.println(clientList + ", your task list is empty.");
        }
        else {
            tasksInOrder(clientList);
        }
    }


    //display tasks marked as completed or uncompleted
    public void displayCompletedOrIncompleteTasks(String username, boolean completed) {
        if (completed) {
            System.out.println("Completed tasks for " + username + ":");
            for (Task task : taskDAO.getTasks()) {
                if (task != null && task.getAssignedTo().equals(username) && task.getTaskStatus().equals("completed")) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("Incomplete tasks for " + username + ":");
            for (Task task : taskDAO.getTasks()) {
                if (task != null && task.getAssignedTo().equals(username) && !task.getTaskStatus().equals("Incompleted")) {
                    System.out.println(task);
                }
            }
        }
    }
    
    
}
