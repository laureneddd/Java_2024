package dao;

import java.util.*;
import pojo.Song;

public class SongDao {

    private List<Song> list = new ArrayList<Song>();

    public void addSong(String name, String artist, String genre, String album){

        Song song = new Song();

        song.setName(name);
        song.setArtist(artist);
        song.setGenre(genre);
        song.setAlbum(album);

        list.add(song);
    }

    public void display(){
        for(Song song: list) {
            System.out.println(song);
        }
    }

    public List<Song> searchByArtist(String artist){
        List<Song> artistList = new ArrayList<>();

        for(Song song: list) {
            if(song.getArtist().equals(artist)) {
                artistList.add(song);
            }
        }

        return artistList;
    }

    public List<Song> searchByGenre(String genre){
        List<Song> genreList = new ArrayList<>();

        for(Song song: list) {
            if(song.getGenre().equals(genre)) {
                genreList.add(song);
            }
        }

        return genreList;
    }

    public List<Song> searchByAlbum(String album){
        List<Song> albumList = new ArrayList<>();

        for(Song song: list) {
            if(song.getAlbum().equals(album)) {
                albumList.add(song);
            }
        }

        return albumList;
    }

    public List<Song> searchByName(String name) {
        List<Song> result = new ArrayList<>();
        for (Song song : list) {
            if (song.getName().equalsIgnoreCase(name)) {
                result.add(song);
            }
        }
        return result;
    }

}
