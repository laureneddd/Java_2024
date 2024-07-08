package main;

import exception.PodcastException;
import java.util.Scanner;
import service.PodcastService;

public class PodcastController {
    public void menu() throws PodcastException{
        Scanner scanner = new Scanner(System.in);
        PodcastService podcastService = new PodcastService();

        while (true) {
            try {
                System.out.println("\n<Podcast Menu> \nEnter 1 to Add Podcast \nEnter 2 to Display Podcasts by Celebrity \nEnter 3 to Search Podcasts by Celebrity \nEnter 4 to Search Podcasts by Date \nEnter 9 to Return Main Menu \nEnter 0 to Exit");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        podcastService.addPodcast();
                        break;
                    case "2":
                        podcastService.displayByCelebrity();
                        break;
                    case "3":
                        podcastService.searchByCelebrity();
                        break;
                    case "4":
                        podcastService.searchByDate();
                        break;
                    case "9":
                        return;
                    case "0":
                        System.out.println("Bye! Have a nice day!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3, 4, or 0.");
                        break;
                }
            } catch (PodcastException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
