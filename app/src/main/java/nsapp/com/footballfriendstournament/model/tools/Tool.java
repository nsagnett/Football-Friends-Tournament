package nsapp.com.footballfriendstournament.model.tools;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.model.rss.RSSItem;

public class Tool {

    public static final ArrayList<RSSItem> news = new ArrayList<>();

    public static ArrayList<RSSItem> getLimitedItems(ArrayList<RSSItem> list, int count) {
        ArrayList<RSSItem> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(list.get(i));
        }
        return result;
    }
}
