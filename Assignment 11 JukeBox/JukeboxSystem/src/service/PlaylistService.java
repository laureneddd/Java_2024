package service;

import dao.PlaylistDao;
import dao.PodcastDao;
import dao.SongDao;
import java.util.List;
import java.util.Scanner;
import pojo.Playlist;
import pojo.Podcast;
import pojo.Song;

/*
 * features for Playlist to add songs or podcast to a playlist, and display all contents inside
 * a user could create more than one playlists
 */

public class PlaylistService {
    private Scanner scanner = new Scanner(System.in);
    private PlaylistDao playlistDao = new PlaylistDao();
    private SongDao songDao = new SongDao();
    private PodcastDao podcastDao = new PodcastDao();

    public void addToPlaylist() {
        System.out.println("\n<Playlist Menu>Enter the name of the playlist: ");
        String playlistName = scanner.nextLine();
        Playlist playlist = playlistDao.getPlaylist(playlistName);

        if (playlist == null) {
            playlist = new Playlist(playlistName);
            playlistDao.addPlaylist(playlist);
            System.out.println("New playlist '" + playlistName + "' created.");
        }

        System.out.println("\n<Playlist Menu>Enter 1 to add a Song \nEnter 2 to add a Podcast");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("\n<Playlist Menu>Enter the name of the song: ");
                String songName = scanner.nextLine();
                List<Song> songs = songDao.searchByName(songName);
                if (songs.isEmpty()) {
                    System.out.println("Song not found.");
                } else {
                    playlist.addContent(songs.get(0)); 
                    System.out.println("Song added to playlist.");
                }
                break;
            case "2":
                System.out.println("\n<Playlist Menu>Enter the name of the podcast: ");
                String podcastName = scanner.nextLine();
                List<Podcast> podcasts = podcastDao.searchByName(podcastName);
                if (podcasts.isEmpty()) {
                    System.out.println("Podcast not found.");
                } else {
                    playlist.addContent(podcasts.get(0)); 
                    System.out.println("Podcast added to playlist.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public void displayPlaylist() {
        System.out.println("\n<Playlist Menu>Enter the name of the playlist: ");
        String playlistName = scanner.nextLine();
        Playlist playlist = playlistDao.getPlaylist(playlistName);

        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }

        List<Object> contents = playlist.getContents();
        if (contents.isEmpty()) {
            System.out.println("The playlist is empty.");
        } else {
            for (Object content : contents) {
                System.out.println(content);
            }
        }
    }
}
