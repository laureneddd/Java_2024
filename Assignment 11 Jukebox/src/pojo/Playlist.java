package pojo;

import java.util.*;

public class Playlist {
    private String name;
    private List<Object> contents; 

    public Playlist(String name) {
        this.name = name;
        this.contents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Object> getContents() {
        return contents;
    }

    public void addContent(Object content) {
        this.contents.add(content);
    }

    @Override
    public String toString() {
        return "Playlist: " + name + " contents=" + contents;
    }
}
