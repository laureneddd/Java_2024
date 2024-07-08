package main;

import java.util.Scanner;

import exception.PodcastException;
import exception.SongException;

public class MainController {
    private static Scanner scanner = new Scanner(System.in);
    private static PodcastController podcastController = new PodcastController();
    private static PlaylistController playlistController = new PlaylistController();
    private static SongController songController = new SongController();

    public static void main(String[] args) throws SongException, PodcastException {
        while (true) {
            System.out.println("\nEnter 1 for Podcast Menu \nEnter 2 for Playlist Menu \nEnter 3 for Song Menu \nEnter 0 to Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    podcastController.menu();
                    break;
                case "2":
                    playlistController.menu();
                    break;
                case "3":
                    songController.menu();
                    break;
                case "0":
                    System.out.println("Exiting Application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 0.");
                    break;
            }
        }
    }
}
