package com.infosys.controller;
import com.infosys.service.*;
import com.infosys.pojo.*;
import java.util.Scanner;


public class Controller {
    static Scanner scanner = new Scanner(System.in);
    static User[] user;
    static Task[] tasks;
    private static TaskService taskService;
    private static UserService userService;

    public static void main(String[] args) {
        userService = new UserService();
        taskService = new TaskService(5);

        user = new User[5];
        tasks = new Task[0];

        userService.register(user);

        System.out.println("\nEnter 1 login as a Visitor \nEnter 2 login as a Client");
        int logStatic = scanner.nextInt();
        scanner.nextLine();

        // users can choose to login as a visitor or client
        switch (logStatic) { 
            case 1:
                userService.visitorLog(user); //Visitor Menu with visitor role update
                Controller.VisitorMenu();
                break;
            case 2:
                userService.clientLog(user);  //Client Menu with client role update
                Controller.clientMenu();
                break;
            default:
                System.out.println("Your input is invalid."); 
        }
    }


    // Menu for visitor
    public static void VisitorMenu(){

        while (true) {
            System.out.println("\n<Visitor Menu>\nEnter 1 to Check Assigned Tasks\nEnter 2 to Sort your Tasks\nEnter 3 to Mark your Tasks\nEnter 4 to Log In\nEnter 6 to Check Incompleted Task\\n" + //
                                "Enter 5 to Check Completed Task\nEnter 0 to Exit");
            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                taskService.searchAssignName(tasks, userService.visitorName());
                    break;
                case 2:
                    taskService.sortVisitorTask(tasks, userService.visitorName());
                    break;
                case 3:
                    taskService.markTaskAsCompleted(userService.visitorName(), tasks);
                    break;
                case 4:
                    userService.clientLog(user);  //Client Menu with client role update
                    Controller.clientMenu();
                    break;
                case 5:
                    taskService.displayCompletedOrIncompleteTasks(tasks, userService.visitorName(), true);
                    break;
                case 6:
                    taskService.displayCompletedOrIncompleteTasks(tasks, userService.visitorName(), false);
                    break;
                case 0:
                    exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }

        
    }

    // Menu for client
    public static void clientMenu(){
        System.out.println("\n<Client Menu> Please enter the number of tasks: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();

        taskService = new TaskService(taskNum);

        

        while (true) {
            System.out.println("\n<Client Menu>\nEnter 1 to Add Tasks\nEnter 2 to Update Tasks\nEnter 3 to Delete Tasks\nEnter 4 to Search Tasks\nEnter 5 to Display Tasks\nEnter 6 to Sort Tasks\nEnter 7 to Check for Duplicates Tasks\nEnter 8 to Assign Tasks\nEnter 9 to LogOut\nEnter 10 to Add User\nEnter 11 to Delete User\nEnter 12 to Update User\nEnter 13 to Visit as a Guest\nEnter 0 to Exit");
            String menuCommand = scanner.nextLine();

            switch (menuCommand) {
                case "1":
                    tasks = taskService.add(tasks);
                    break;
                case "2":
                    tasks = taskService.update(tasks);
                    break;
                case "3":
                    tasks = taskService.delete(tasks);
                    break;
                case "4":
                    taskService.searchById(tasks);
                    break;
                case "5":
                    taskService.display(tasks);
                    break;
                case "6":
                    taskService.sortClientTask(tasks, userService.clientName());
                    break;
                case "7":
                    taskService.repeatCheck(tasks);
                    break;
                case "8":
                    tasks = taskService.assignTask(tasks);
                    break;
                case "9":
                    userService.logOut();
                    break;
                    // return; //this return will exit...
                case "10":
                    userService.add();
                    break;
                case "11":
                    userService.delete();
                    break;
                case "12":
                    userService.updateUserName();
                    break;
                case "13":
                    VisitorMenu();
                    break;
                case "0":
                    exit();
                    System.exit(0);
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }

    }

    public static void exit() {
        System.out.println("Bye! Enjoy your day!");
    }
}

