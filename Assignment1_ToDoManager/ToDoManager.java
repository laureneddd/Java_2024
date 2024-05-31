import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

class ToDoManager {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        //Enter an user name
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        String[] tasks = new String[5];
        
        ToDoManager manager = new ToDoManager();
        
        tasks = manager.tasksInput(tasks);
        manager.tasksInOrder(tasks);
        manager.repeatCheck(tasks);
    }
    
    //Enter 5 user's tasks
    public String[] tasksInput(String[] string){
        System.out.println("\nPlease enter your 5 tasks seperated by comma:");
        String s = scanner.nextLine();
        string = s.split(",");
        
        //Make sure to enter 5 tasks
        while(string.length != 5){
            System.out.println("\nYour input is invalid. Please enter 5 tasks seperated by commas:");
            s = scanner.nextLine();
            string = s.split(",");
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
}