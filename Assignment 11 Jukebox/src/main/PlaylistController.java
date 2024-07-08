package main;

import java.util.Scanner;
import service.PlaylistService;

public class PlaylistController {
    private PlaylistService playlistService = new PlaylistService();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n<Playlist Menu> \nEnter 1 to Create or Add to Playlist \nEnter 2 to View Playlist Contents \nEnter 9 to Return Main Menu \nEnter 0 to Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    playlistService.addToPlaylist();
                    break;
                case "2":
                    playlistService.displayPlaylist();
                    break;
                case "0":
                    System.out.println("Bye! Have a nice day!");
                    System.exit(0);
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 0.");
                    break;
            }
        }
    }
}
