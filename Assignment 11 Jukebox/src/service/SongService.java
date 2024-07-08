package service;

import dao.SongDao;
import exception.SongException;
import java.util.*;
import pojo.Song;

/*
 * features for JukeBox to add songs, display all songs and search by the categories of artist, genre or album
 */

public class SongService {

    private Scanner scanner = new Scanner(System.in);
    private SongDao songdao = new SongDao();

    public void add() throws SongException {
        System.out.println("\n<Song Menu>Enter the name of song: ");
        String name = scanner.nextLine();
        System.out.println("\n<Song Menu>Enter the artist of song: ");
        String artist = scanner.nextLine();
        System.out.println("\n<Song Menu>Enter the genre of song: ");
        String genre = scanner.nextLine();
        System.out.println("\n<Song Menu>Enter the album of song: ");
        String album = scanner.nextLine();

        songdao.addSong(name, artist, genre, album);
    }

    public void display() throws SongException {
        songdao.display();
    }

    public void search() throws SongException {
        try {
            System.out.println("\n<Song Menu> \nEnter 1 to search by Artist \nEnter 2 to search by Genre \nEnter 3 to search by Album");
            String choice = scanner.nextLine();
            List<Song> resultList = new ArrayList<>();

            switch (choice) {
                case "1":
                    System.out.println("\n<Song Menu>Enter the artist you want to search: ");
                    String artist = scanner.nextLine();
                    resultList = songdao.searchByArtist(artist);
                    break;
                case "2":
                    System.out.println("\n<Song Menu>Enter the genre you want to search: ");
                    String genre = scanner.nextLine();
                    resultList = songdao.searchByGenre(genre);
                    break;
                case "3":
                    System.out.println("\n<Song Menu>Enter the album you want to search: ");
                    String album = scanner.nextLine();
                    resultList = songdao.searchByAlbum(album);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    return;
            }

            if (resultList.isEmpty()) {
                System.out.println("No songs found.");
            } else {
                for (Song song : resultList) {
                    System.out.println(song);
                }
            }
        } catch (Exception e) {
            throw new SongException("An error occurred... " + e.getMessage());
        }
    }
}
