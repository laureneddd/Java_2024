package main;

import exception.SongException;
import java.util.*;
import service.SongService;

public class SongController {
    public void menu() throws SongException {
        Scanner scanner  = new Scanner(System.in);
        SongService songservice = new SongService();

        while (true) {
            try {
                System.out.println("\n<Song Menu> \nEnter 1 Add Songs \nEnter 2 Display All Songs \nEnter 3 Search Songs \nEnter 9 Return to Main Menu \nEnter 0 Exit");
                String choice = scanner.nextLine();
    
    
                switch (choice) {
                    case "1":
                        songservice.add();
                        break;
                    case "2":
                        songservice.display();
                        break;
                    case "3":
                        songservice.search();
                        break;
                    case "9":
                        return;
                    case "0":
                        System.out.println("Bye! Have a nice day!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3, or 0.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
            
        }
    }
}
