package pojo;

public class Song implements Comparable<Song>{
    private String name;
    private String artist;
    private String genre;
    private String album;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getAlbum(){
        return album;
    }

    public void setAlbum(String album){
        this.album = album;
    }

    @Override
    public String toString(){
        return "The song is: " + name + " The artist is: " + artist + " The genre is: " + genre + " The album is: " + album;
    }

    @Override
    public int compareTo(Song other) {
        return this.name.compareTo(other.name);
    }

}
