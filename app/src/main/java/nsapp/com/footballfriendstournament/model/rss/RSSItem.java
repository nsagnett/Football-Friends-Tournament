package nsapp.com.footballfriendstournament.model.rss;

import java.io.Serializable;

public class RSSItem implements Serializable {

    private String title;
    private String description;
    private String link;
    private String source;

    public RSSItem(String title, String description, String link, String source) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getSource() {
        return source;
    }
}
