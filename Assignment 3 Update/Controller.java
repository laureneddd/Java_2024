import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private TaskService taskService;

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }

    public void run() {
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.println("\nPlease enter the number of tasks: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();

        taskService = new TaskService(taskNum);
        Task[] tasks = new Task[0];

        while (true) {
            System.out.println("\nEnter 1 to Add\nEnter 2 to Update\nEnter 3 to Delete\nEnter 4 to Search\nEnter 5 to Display\nEnter 6 to Sort\nEnter 7 to Check for Duplicates\nEnter 8 to Assign\nEnter 0 to Exit");
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
                case "0":
                    exit();
                    System.exit(0);
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }
    }

    private void exit() {
        System.out.println("Bye! Enjoy your day!");
    }
}
