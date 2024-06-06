import java.util.Scanner;
import java.util.Set;

public class TaskService {
    private TaskDAO taskDAO;
    private Scanner scanner;

    public TaskService(int initialSize) {
        taskDAO = new TaskDAO(initialSize);
        scanner = new Scanner(System.in);
    }

    public void nameInput() {
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
    }

    public int numInput() {
        int count = 0;
        try {
            System.out.println("\nPlease enter the number of tasks: ");
            count = scanner.nextInt();
            scanner.nextLine();
        } catch (java.util.InputMismatchException e) {
            System.out.println("\nInvalid input, please enter a valid number: ");
            scanner.nextLine();
            count = scanner.nextInt();
            scanner.nextLine();
        }
        return count;
    }

    public Task tasksInput() {
        System.out.println("\nPlease enter the task ID: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPlease enter the task title: ");
        String taskTitle = scanner.nextLine();

        System.out.println("\nPlease enter the task text: ");
        String taskText = scanner.nextLine();

        System.out.println("\nPlease enter the assignee: ");
        String assignedTo = scanner.nextLine();

        return new Task(taskId, taskTitle, taskText, assignedTo);
    }

    public void display(Task[] tasks) {
        if (tasks.length == 0) {
            System.out.println("Your task list is empty.");
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public Task[] add(Task[] originalTasks) {
        Task newTask = tasksInput();
        return taskDAO.add(newTask);
    }

    public Task[] update(Task[] originalTasks) {
        System.out.println("\nPlease enter the task ID to update: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        Task updatedTask = tasksInput();
        return taskDAO.update(originalTasks, taskId, updatedTask);
    }

    public Task[] delete(Task[] originalTasks) {
        System.out.println("\nPlease enter the task ID to delete: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        if (taskDAO.delete(taskId)) {
            System.out.println("\nSuccessfully deleted task with ID: " + taskId);
        }
        return taskDAO.getTasks();
    }

    public void search(Task[] originalTasks) {
        System.out.println("\nPlease enter the task ID to search: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        Task task = taskDAO.searchById(originalTasks, taskId);
        if (task != null) {
            System.out.println("\nThe task you are looking for is: " + task);
        } else {
            System.out.println("\nNo tasks found with the given ID.");
        }
    }

    public void searchAssignName(Task[] oriTask, String username){
        Task task = taskDAO.searchByName(oriTask, username);
        if (task != null) {
            System.out.println("\nThe task you are looking for is: " + task);
        } else {
            System.out.println("\nNo tasks assigned with the given username.");
        }
    }

    public void tasksInOrder(Task[] tasks) {
        System.out.println("\nPlease enter the order (a for ascending, d for descending): ");
        String order = scanner.nextLine();

        while (!order.equals("a") && !order.equals("d")) {
            System.out.println("\nInvalid input, please try again: ");
            order = scanner.nextLine();
        }

        boolean ascending = order.equals("a");
        taskDAO.sortTasks(tasks, ascending);
        display(tasks);
    }

    public void repeatCheck(Task[] tasks) {
        Set<String> duplicates = taskDAO.findDuplicates(tasks);
        if (duplicates == null) {
            System.out.println("\nNo duplicate tasks found.");
        } else {
            System.out.println("\nDuplicate task titles:");
            for (String title : duplicates) {
                System.out.println(title);
            }
        }
    }

    public Task[] assignTask(Task[] tasks) {
        System.out.println("\nPlease enter the task ID to reassign: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPlease enter the new assignee's name: ");
        String name = scanner.nextLine();

        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setAssignedTo(name);
                System.out.println("\nSuccessfully assigned task with ID: " + taskId + " to " + name);
            }
        }
        return tasks;
    }
}
