package com.infosys.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.infosys.exception.ArrayFullException;
import com.infosys.exception.StringReadException;
import com.infosys.exception.TaskNotFound;
import com.infosys.exception.ValidationLoginException;
import com.infosys.service.TaskService;
import com.infosys.service.UserService;


public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static TaskService taskService;
    private static UserService userService;

    public static void main(String[] args) {
        userService = new UserService();
        taskService = new TaskService();

        try {
            userService.register();
        } catch (StringReadException e) {
            System.out.println(e.getMessage());
        }

        boolean login = false;
        

        while (!login) {
            System.out.println("\nEnter 1 login as a Visitor \nEnter 2 login as a Client");
    
            // users can choose to login as a visitor or client
            try {
                int logStatic = scanner.nextInt();
                scanner.nextLine();
                
                switch (logStatic) { 
                    case 1 :
                        userService.visitorLog(); //Visitor Menu with visitor role update
                        startVisitorThread();
                        // Controller.VisitorMenu();
                        login = true;
                        break;
                    case 2 :
                        userService.clientLog();  //Client Menu with client role update
                        startClientThread();
                        // Controller.clientMenu();
                        login = true;
                        break;
                    default : 
                        System.out.println("Your input is invalid."); 
                }
            } catch (ValidationLoginException | StringReadException e) {
                // e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                // e.printStackTrace();
                System.out.println("Failed to enter an integer: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    // Menu for visitor
    synchronized public static void VisitorMenu() throws InputMismatchException {

        while (true) {
            System.out.println(" \n<Visitor Menu>\nEnter 1 to Check Assigned Tasks\nEnter 2 to Sort your Tasks\nEnter 3 to Mark your Tasks\nEnter 4 to Log In\nEnter 6 to Check Incompleted Task\nEnter 5 to Check Completed Task\\n" + //
                                "Enter 0 to Exit" );
            int command = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (command) {
                    case 1:
                        taskService.searchAssignName(userService.visitorName());
                        break;
                    case 2:
                        taskService.sortVisitorTask(userService.visitorName());
                        break;
                    case 3:
                        taskService.markTaskAsCompleted(userService.visitorName());
                        break;
                    case 4:
                        userService.clientLog();  //Client Menu with client role update
                        startClientThread();
                        return;
                    case 5:
                        taskService.displayCompletedOrIncompleteTasks(userService.visitorName(), true);
                        break;
                    case 6:
                        taskService.displayCompletedOrIncompleteTasks(userService.visitorName(), false);
                        break;
                    case 0:
                        exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid command, please try again.");
                }
            } catch (ValidationLoginException | StringReadException | TaskNotFound e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("\nYour input is invalid: " + e.getMessage());
                scanner.nextLine();
            }
        }
        
    }

    // Menu for client
    synchronized public static void clientMenu() throws InputMismatchException {
        System.out.println("\n<Client Menu> Please enter the number of tasks: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();


        taskService = new TaskService();

        while (true) {
            System.out.println("\n<Client Menu>\nEnter 1 to Add Tasks\nEnter 2 to Update Tasks\nEnter 3 to Delete Tasks\nEnter 4 to Search Tasks\nEnter 5 to Display Tasks\nEnter 6 to Sort Tasks\nEnter 7 to Check for Duplicates Tasks\nEnter 8 to Assign Tasks\nEnter 9 to LogOut\nEnter 10 to Add User\nEnter 11 to Delete User\nEnter 12 to Update User\nEnter 13 to Visit as a Guest\nEnter 0 to Exit");
            String menuCommand = scanner.nextLine();

            try {
                switch (menuCommand) {
                    case "1":
                        taskService.add();
                        break;
                    case "2":
                        taskService.update();
                        break;
                    case "3":
                        taskService.delete();
                        break;
                    case "4":
                        taskService.searchById();
                        break;
                    case "5":
                        taskService.display();
                        break;
                    case "6":
                        taskService.sortClientTask(userService.clientName());
                        break;
                    case "7":
                        taskService.repeatCheck();
                        break;
                    case "8":
                        taskService.assignTask();
                        break;
                    case "9":
                        userService.logOut();
                        return;
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
                        startVisitorThread();
                        // VisitorMenu();
                        break;
                    case "0":
                        exit();
                        System.exit(0);
                    default:
                        System.out.println("Invalid command, please try again.");
                }
            } catch (StringReadException | TaskNotFound | ArrayFullException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer: " + e.getMessage());
            }
        }
    }

    public static void exit() {
        System.out.println("Bye! Enjoy your day!");
    }

    // visitor thread after user login as a visitor
    public static void startVisitorThread() {
        Runnable visitorRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    VisitorMenu();
                } catch (InputMismatchException e) {
                }
            }
        };
    
        Thread visitorThread = new Thread(visitorRunnable);
        visitorThread.start();
    }

    // client thread after user login as a client
    public static void startClientThread() {
        Runnable clientRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    clientMenu();
                } catch (InputMismatchException e) {
                }
                
            }
        };
    
        Thread clientThread = new Thread(clientRunnable);
        clientThread.start();
    }
}


