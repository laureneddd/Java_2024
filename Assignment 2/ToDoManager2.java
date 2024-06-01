import java.util.*;

class ToDoManager {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        ToDoManager manager = new ToDoManager();
        manager.nameInput();
        int taskNum = manager.numInput();
        String[] tasks = new String[taskNum];
        
        tasks = manager.tasksInput(tasks, taskNum);
        while(true){
            System.out.println("\nMenu: 'Add' 'Update' 'Delete' 'Search' 'Display' 'Order' 'Duplicates' 'Type '0' to Exit'");
            String menuCommand = scanner.nextLine();
            
            if(menuCommand.equals("Add")){
                tasks = manager.add(tasks);
            }
            else if(menuCommand.equals("Update")){
                tasks = manager.update(tasks);
            }
            else if(menuCommand.equals("Delete")){
                tasks = manager.delete(tasks);
            }
            else if(menuCommand.equals("Search")){
                manager.search(tasks);
            }
            else if(menuCommand.equals("Display")){
                manager.display(tasks);
            }
            else if(menuCommand.equals("Order")){
                 manager.tasksInOrder(tasks);
            }
            else if(menuCommand.equals("Duplicates")){
                manager.repeatCheck(tasks);
            }
            else if(menuCommand.equals("0")){
                manager.exit();
                break;
            }
            else{
                System.out.println("\nPlease type '0' to exit.");
                int exit = scanner.nextInt();
                if(exit == 0){
                    System.out.println("\nBye! Enjoy your day!");
                    break;
                }
            }
        }
    }
    
    //Enter an user name
    public void nameInput(){
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
    }
    
    //Record the total task number
    public int numInput(){
        System.out.println("\nPlease enter your task number: ");
        int cont = scanner.nextInt();
        scanner.nextLine(); 
        
        return cont;
    }
    
    //Enter tasks 
    public String[] tasksInput(String[] string, int length){
        
        System.out.println("\nPlease enter your " + length + " tasks seperated by comma:");
        String str = scanner.nextLine();
        string = str.split(",");
        
        //Make sure to enter tasks with correct number
        while(string.length != length){
            System.out.println("\nYour input is invalid. Please enter " + length + " tasks seperated by commas:");
            str = scanner.nextLine();
            string = str.split(",");
        }
        return string; 
    }
    
    //Put tasks into ascending or descending order   
    public void tasksInOrder(String[] string){
        System.out.println("\nPlease type 'a' with ascending order, or type 'd' with descending order");
        String order = scanner.nextLine();
        
        //Make sure the order input is valid
        while(!order.equals("a") && !order.equals("d")){
            System.out.println("\nYour input is invalid, please try again:");
            order = scanner.nextLine();
        }
        
        if(order.equals("a")){
            Arrays.sort(string);
            System.out.println("\nThe ascending order: ");
            for(String str: string){
                System.out.println(str);
            }
        }
        else if(order.equals("d")){
            Arrays.sort(string, Collections.reverseOrder());
            System.out.println("\nThe descending order: ");
            for(String str: string){
                System.out.println(str);
            }
        }
    }
    
    //Find out duplicates from tasks
    public void repeatCheck(String[] string){
        Set<String> duplicates  = new TreeSet<>();
        boolean repeatExist = false;
        int repeatCont = 0;
        
        for(int i=0; i<string.length-1; i++){
            if(string[i].equals(string[i+1])){
                duplicates.add(string[i]);
                repeatExist = true;
                repeatCont++;
            }
        }
        
        //Avoid duplicates in the display of repeated tasks
        if(!duplicates.isEmpty()){
            for(String str: duplicates){
                System.out.println("\nRepeated task: " + str);
            }
        }
        if(repeatExist == true){
            System.out.println("\nYou have " + repeatCont + " repeated tasks from your ToDoManager.");
        }
    }
    
    //Display all tasks
    public void display(String[] originalTasks){
        StringBuilder displayTask = new StringBuilder();
        for(String str: originalTasks){
            displayTask.append(str).append(" ");
        }
        System.out.println("\nYour tasks contain: " + displayTask);
    }
    
    //Add new task
    public String[] add(String[] originalTasks){
        System.out.println("\nPlease enter the task you wanna add: ");
        String newTask = scanner.nextLine();
        String[] afterAdd = Arrays.copyOf(originalTasks, originalTasks.length + 1);
        afterAdd[afterAdd.length - 1] = newTask;
        
        return afterAdd;
    }
    
    //Update a new task and replace the original one
    public String[] update(String[] originalTasks){
        System.out.println("\nPlease enter the task you wanna update: ");
        String target = scanner.nextLine();
        System.out.println("\nPlease enter the new task: ");
        String updateTask = scanner.nextLine();
        boolean targetExist = true;

        for(int i = 0; i < originalTasks.length; i++){
            if(originalTasks[i].equals(target)){
                originalTasks[i] = updateTask;
                targetExist = false;
            }
        }
        if(targetExist){
            System.out.println("Sorry, there are no tasks that meet your criteria.");
        }
       
        return originalTasks;
    }
    
    //Delete a task 
    public String[] delete(String[] originalTasks){
        System.out.println("\nPlease enter the task you wanna delete: ");
        String target = scanner.nextLine();
        int index = 0;
        String[] afterDelTasks = Arrays.copyOf(originalTasks, originalTasks.length - 1);
        
        for(int i = 0; i < originalTasks.length; i++){
            if(!originalTasks[i].equals(target)){
                afterDelTasks[index++] = originalTasks[i];
            }
        } 
        return afterDelTasks;
    }
    
    //Search a task and return its index
    public void search(String[] originalTasks){
        System.out.println("\nPlease enter the task you wanna search: ");
        String target = scanner.nextLine();
        boolean targetExist = true;

        for(int i = 0; i < originalTasks.length; i++){
            if(originalTasks[i].equals(target)){
                System.out.println("\nThe task " + "'" + originalTasks[i] + "'" + " is No." + i+1 + " task today.");
                targetExist = false;
            }
        }
        if(targetExist){
            System.out.println("\nSorry, there is no task that meet your criteria.");
        }
    }
    
    //Exit this application
    public void exit(){
        System.out.println("\nBye! Enjoy your day!");
    }
    
}
