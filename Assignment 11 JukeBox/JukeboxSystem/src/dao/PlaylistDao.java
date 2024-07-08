package dao;

import pojo.Playlist;

import java.util.HashMap;
import java.util.Map;

public class PlaylistDao {
    private Map<String, Playlist> playlists = new HashMap<>();

    public void addPlaylist(Playlist playlist) {
        playlists.put(playlist.getName(), playlist);
    }

    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }

    public Map<String, Playlist> getAllPlaylists() {
        return playlists;
    }
}
