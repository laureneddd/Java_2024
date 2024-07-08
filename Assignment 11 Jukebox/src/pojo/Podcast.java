package pojo;

import java.util.*;

public class Podcast {
    private String celebrity;
    private Date publishDate;

    public String getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(String celebrity) {
        this.celebrity = celebrity;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Podcast: " + "celebrity is: " + celebrity + " publish date is: " + publishDate;
    }
}
