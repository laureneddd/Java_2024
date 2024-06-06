import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static TaskService taskService;
    private static UserService userService;
    private static Task[] tasks = new Task[0];

    public static void main(String[] args) {
        userService = new UserService();
        taskService = new TaskService(5);

        User[] user = new User[5];

        userService.register(user);

        System.out.println("\nEnter 1 login as a Visitor \nEnter 2 login as a Client");
        int logStatic = scanner.nextInt();
        scanner.nextLine();

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

    public static void VisitorMenu(){
        System.out.println("\n<Visitor Menu>");
        String visitorname = userService.visitorName();
        taskService.searchAssignName(tasks, visitorname);
    }



    public static void clientMenu(){
        userService.clientName();

        System.out.println("\n<Client Menu> Please enter the number of tasks: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();

        taskService = new TaskService(taskNum);

        

        while (true) {
            System.out.println("\n<Client Menu> Enter 1 to Add Tasks\nEnter 2 to Update Tasks\nEnter 3 to Delete Tasks\nEnter 4 to Search Tasks\nEnter 5 to Display Tasks\nEnter 6 to Sort Tasks\nEnter 7 to Check for Duplicates Tasks\nEnter 8 to Assign Tasks\nEnter 9 to LogOut\nEnter 10 to Add User\nEnter 11 to Delete User\nEnter 12 to Update User\nEnter to Visit as a Guest\nEnter 0 to Exit");
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
                    taskService.search(tasks);
                    break;
                case "5":
                    taskService.display(tasks);
                    break;
                case "6":
                    taskService.tasksInOrder(tasks);
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

