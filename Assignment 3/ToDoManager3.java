import java.util.*;

class Main{
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        ToDoManager manager = new ToDoManager();
        manager.nameInput();
        int taskNum = manager.numInput();
        Task[] tasks = new Task[taskNum];
        
        for(int i = 0; i < taskNum; i++){
            tasks[i] = manager.tasksInput();
        }
        
        while(true) {
            
            System.out.println("\nMenu: Add  Update  Delete  Search  Display \nOrder  Duplicates  Assign  <Type '0' to Exit>");
            String menuCommand = scanner.nextLine();
            
            switch(menuCommand){
                case "Add":
                    tasks = manager.add(tasks);
                    break;
                case "Update":
                    tasks = manager.update(tasks);
                    break;
                case "Delete":
                    tasks = manager.delete(tasks);
                    break;
                case "Search":
                    manager.search(tasks);
                    break;
                case "Display":
                    manager.display(tasks);
                    break;
                case "Assign":
                    manager.assignedTo(tasks);
                    break;
                case "Order":
                    manager.tasksInOrder(tasks);
                    break;
                case "Duplicates":
                    manager.repeatCheck(tasks);
                    break;
                case "0":
                    manager.exit();
                    System.exit(0);

 
            }
        }
    }
}

class Task{
    int taskId;
    String taskTitle;
    String taskText;
    String assignedTo;    
}

class ToDoManager{
    Scanner scanner = new Scanner(System.in);
    
    //Enter an user name
    public void nameInput(){
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
    }
    
    //Record the total task number
    public int numInput(){
        int cont = 0;
        try{
            System.out.println("\nPlease enter your task number: ");
            cont = scanner.nextInt();
            //**consume new line, otherwise tasks input breaks
            scanner.nextLine(); 
        }catch(java.util.InputMismatchException e){
            System.out.println("\nYour input is invalid. Please enter a valid number: ");
            scanner.nextLine(); 
            cont = scanner.nextInt();
            scanner.nextLine(); 
        }
        return cont;
    }
    
    //Enter tasks 
    public Task tasksInput(){
        
        Task task = new Task();
        
        int task_Id = 0;
        try{
            System.out.println("\nPlease enter your TaskId: ");
            task_Id = scanner.nextInt();
            scanner.nextLine(); 
        }catch(java.util.InputMismatchException e) {
            System.out.println("\nYour input is invalid. Please enter a valid TaskId number: ");
            scanner.nextLine(); 
            task_Id = scanner.nextInt();
            scanner.nextLine(); 
        }
        
        System.out.println("\nPlease enter the TaskTitle of your No." + task_Id + " task:");
        String task_Title = scanner.nextLine();
                               
        System.out.println("\nPlease enter the TaskText of your No." + task_Id + " task:");
        String task_Text = scanner.nextLine();
            
        System.out.println("\nPlease enter the Assignee of your No." + task_Id + " task:");
        String task_AssignedTo = scanner.nextLine();
            
        task.taskId = task_Id;
        task.taskTitle = task_Title;
        task.taskText = task_Text;
        task.assignedTo = task_AssignedTo;

        return task; 
    }
    
    //Display all tasks
    public void display(Task[] originalTasks){
        if(originalTasks.length == 0){
            System.out.println("Your task list is empty.");
        }

        for(Task str: originalTasks){
            System.out.println(str.taskId + " " + str.taskTitle + " " + str.taskText + " " + str.assignedTo);
        }
    }
    
    //Add new task
    public Task[] add(Task[] originalTasks){
        Task addNew = new Task();
        
        addNew = tasksInput();

        Task[] afterAdd = Arrays.copyOf(originalTasks, originalTasks.length + 1);
        afterAdd[afterAdd.length - 1] = addNew;
        System.out.println("\nYou have successfully added a new task today:\n " + addNew.taskId + " " + addNew.taskTitle + " " + addNew.taskText + " " + addNew.assignedTo);
        return afterAdd;
    }
    
    //Update a new task and replace the original one
    public Task[] update(Task[] originalTasks){
        
        System.out.println("\nPlease enter the TaskTitle you wanna update: ");
        String target = scanner.nextLine();

        System.out.println("\nPlease enter the new taskId: ");
        int updateTaskId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("\nPlease enter the new taskTitle: ");
        String updateTitle = scanner.nextLine();
        
        System.out.println("\nPlease enter the new taskText: ");
        String updateText = scanner.nextLine();
        
        System.out.println("\nPlease enter the new Assignee: ");
        String updateAssigned = scanner.nextLine();
        
        boolean targetExist = true;

        for(int i = 0; i < originalTasks.length; i++){
            if(originalTasks[i].taskTitle.equals(target)){
                originalTasks[i].taskId = updateTaskId;
                originalTasks[i].taskTitle = updateTitle;
                originalTasks[i].taskText = updateText;
                originalTasks[i].assignedTo = updateAssigned;
                targetExist = false;
            }
        }
        if(targetExist){
            System.out.println("Sorry, there are no tasks that meet your criteria.");
        }
        System.out.println("\nYou have successfully updated a task today: " + updateTaskId + " " + updateTitle + " " + updateText + " " + updateAssigned);
        return originalTasks;
    }
    
    //Delete a task 
    public Task[] delete(Task[] originalTasks){
        System.out.println("\nPlease enter the TaskTitle you wanna delete: ");
        String target = scanner.nextLine();
        int index = 0;
        Task[] afterDelTasks = Arrays.copyOf(originalTasks, originalTasks.length - 1);
        
        int indexOfNew = 0;
        int indexOftarget = 0;
        for(int i = 0; i < originalTasks.length; i++){
            if(!originalTasks[i].taskTitle.equals(target)){
                indexOfNew++;
                afterDelTasks[indexOfNew].taskId = originalTasks[i].taskId;
                afterDelTasks[indexOfNew].taskTitle = originalTasks[i].taskTitle;
                afterDelTasks[indexOfNew].taskText = originalTasks[i].taskText;
                afterDelTasks[indexOfNew].assignedTo = originalTasks[i].assignedTo;
            }
            else{
                index = i;
            }
        } 
        System.out.println("\nYou have successfully deleted a task today: " + originalTasks[index].taskId + " " + originalTasks[index].taskTitle + " " + originalTasks[index].taskText + " " +originalTasks[index].assignedTo);
        
        return afterDelTasks;
    }
    
    //Search a task and return its index
    public void search(Task[] originalTasks){
        System.out.println("\nPlease enter the TaskTitle you wanna search: ");
        String target = scanner.nextLine();
        boolean targetExist = true;

        for(int i = 0; i < originalTasks.length; i++){
            if(originalTasks[i].taskTitle.equals(target)){
                System.out.println("\nThe task you are looking for is: " + originalTasks[i].taskId + " " + originalTasks[i].taskTitle + " " +originalTasks[i].taskText + " " + originalTasks[i].assignedTo);
                targetExist = false;
            }
        }
        if(targetExist){
            System.out.println("\nSorry, there is no task that meet your criteria.");
        }
    }
    
    //Put tasks TaskTitle into ascending or descending order   
    public void tasksInOrder(Task[] tasks){
        System.out.println("\nPlease type 'a' with ascending order, or type 'd' with descending order");
        String order = scanner.nextLine();
        
        //Make sure the order input is valid
        while(!order.equals("a") && !order.equals("d")){
            System.out.println("\nYour input is invalid, please try again:");
            order = scanner.nextLine();
        }
        
        if(tasks.length == 1){
            System.out.println("\nThere is only one task: ");
            for(Task t: tasks){
                System.out.println(t.taskId + " " + t.taskTitle + " " + t.taskText + " " + t.assignedTo);
            }            
        }
        else{
            if(order.equals("a")){
            Arrays.sort(tasks, Comparator.comparing(task -> task.taskTitle));
            System.out.println("\nThe ascending order based on TaskTitle: ");
            for(Task t: tasks){
                System.out.println(t.taskId + " " + t.taskTitle + " " + t.taskText + " " + t.assignedTo);
            }
        }
            else if(order.equals("d")){
                Arrays.sort(tasks, Comparator.comparing((Task task) -> task.taskTitle).reversed());
                System.out.println("\nThe descending order based on TaskTitle: ");
                for(Task t: tasks){
                    System.out.println(t.taskId + " " + t.taskTitle + " " + t.taskText + " " + t.assignedTo);
                }
            }
        }
    }
    
    //Find out duplicates from tasks
    public void repeatCheck(Task[] tasks){
        Set<String> duplicates  = new TreeSet<>();
        boolean repeatExist = false;
        int repeatCont = 0;
        
        for(int i = 0; i < tasks.length - 1; i++){
            for(int j = i + 1; j < tasks.length; j++){
                if(tasks[i].taskTitle.equals(tasks[j].taskTitle)){
                    duplicates.add(tasks[i].taskTitle);
                    repeatExist = true;
                    repeatCont++;
                }
            }
        }
        
        //Avoid duplicates in the display of repeated tasks
        if(!duplicates.isEmpty()){
            int k = 0;
            for(String s: duplicates){
                for(int i = 0; i < tasks.length - 1; i++){
                    if(tasks[i].taskTitle.equals(s)){
                        System.out.println("\nTaskTitle of the repeated task: " +  tasks[i].taskTitle);
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("\nYou don't have repeated tasks today.");
        }
        if(repeatExist == true){
            System.out.println("\nYou have " + repeatCont + " repeated tasks from your ToDoManager.");
        }
    }
    
    //Assign an existed task to another user
    public Task[] assignedTo(Task[] originalTasks){
        
        System.out.println("\nPlease enter the TaskTitle of existed task that would be reassigned: ");
        String title = scanner.nextLine();
        System.out.println("\nPlease enter the name of person you wanna assign the task to: ");
        String name = scanner.nextLine();
        
        for(int i = 0; i < originalTasks.length; i++){
            if(originalTasks[i].taskTitle.equals(title)){
                originalTasks[i].assignedTo = name;
            }
        }
        System.out.println("\nYou have successfully assigned Task:" + title + " to " + name + " today.");
        return originalTasks;
    }
    
    //Exit this application
    public void exit(){
        System.out.println("\nBye! Enjoy your day!");
    }
}

