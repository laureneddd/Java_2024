package service;

import dao.PodcastDao;
import exception.PodcastException;
import java.time.*;
import java.time.format.*;
import java.util.*;
import pojo.Podcast;

/*
 * features for Podcast to create podcast with celebrity and date; display by celebrity and search by celebrity or date
 */

public class PodcastService {
    private Scanner scanner = new Scanner(System.in);
    private PodcastDao podcastDao = new PodcastDao();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public void addPodcast() throws PodcastException {
        try {
            System.out.println("\n<Podcast Menu>Enter the celebrity of the podcast: ");
            String celebrity = scanner.nextLine();

            Date date = new Date(); 
            podcastDao.addPodcast(celebrity, date);
        } catch (Exception e) {
            throw new PodcastException("An error occurred... " + e.getMessage());
        }
    }

    public void displayByCelebrity() throws PodcastException {
        try {
            System.out.println("\n<Podcast Menu>Enter the celebrity to display podcasts: ");
            String celebrity = scanner.nextLine();
            podcastDao.displayByCelebrity(celebrity);
        } catch (Exception e) {
            throw new PodcastException("An error occurred... " + e.getMessage());
        }
    }

    public void searchByCelebrity() throws PodcastException {
        try {
            System.out.println("\n<Podcast Menu>Enter the celebrity to search podcasts: ");
            String celebrity = scanner.nextLine();
            List<Podcast> resultList = podcastDao.searchByCelebrity(celebrity);

            if (resultList.isEmpty()) {
                System.out.println("No podcasts found for the given celebrity.");
            } else {
                for (Podcast podcast : resultList) {
                    System.out.println(podcast);
                }
            }
        } catch (Exception e) {
            throw new PodcastException("An error occurred... " + e.getMessage());
        }
    }

    // transfer the input date from String to LocalDate to Date, then search from the list
    public void searchByDate() throws PodcastException {
        try {
            System.out.println("\n<Podcast Menu>Enter a date to search podcasts (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            LocalDate localDate = LocalDate.parse(date, dateFormatter); 
            Date publishDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); 
            List<Podcast> resultList = podcastDao.searchByDate(publishDate);

            if (resultList.isEmpty()) {
                System.out.println("No podcasts found for the given date.");
            } else {
                for (Podcast podcast : resultList) {
                    System.out.println(podcast);
                }
            }
        } catch (DateTimeParseException e) {
            throw new PodcastException("Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            throw new PodcastException("An error occurred... " + e.getMessage());
        }
    }
}
