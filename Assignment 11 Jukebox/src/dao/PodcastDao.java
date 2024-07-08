package dao;

import java.util.*;
import pojo.Podcast;

public class PodcastDao {
    private List<Podcast> list = new ArrayList<>();

    public void addPodcast(String celebrity, Date date){

        Podcast podcast = new Podcast();

        podcast.setCelebrity(celebrity);
        podcast.setPublishDate(date);

        list.add(podcast);
    }

    public void displayByCelebrity(String celebrity) {
        for (Podcast podcast: list) {
            if(podcast.getCelebrity().equalsIgnoreCase(celebrity)) {
                System.out.println(podcast);
            }
        }
    }

    public List<Podcast> searchByCelebrity(String celebrity) {
        List<Podcast> celebrityList = new ArrayList<>();

        for (Podcast podcast: list) {
            if(podcast.getCelebrity().equalsIgnoreCase(celebrity)) {
                celebrityList.add(podcast);
            }
        }

        return celebrityList;
    }

    public List<Podcast> searchByDate(Date date) {
        List<Podcast> dateList = new ArrayList<>();

        for (Podcast podcast: list) {
            if(podcast.getPublishDate().equals(date)) {
                dateList.add(podcast);
            }
        }

        return dateList;
    }

    public List<Podcast> searchByName(String name) {
        List<Podcast> result = new ArrayList<>();
        for (Podcast podcast : list) {
            if (podcast.getCelebrity().equalsIgnoreCase(name)) {
                result.add(podcast);
            }
        }
        return result;
    }
}
