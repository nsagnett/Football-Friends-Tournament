package nsapp.com.footballfriendstournament.model.rss;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RSSController {

    private static final String EUROSPORT = "http://www.eurosport.fr";
    private static final String LEQUIPE = "http://www.lequipe.fr";

    private static final ArrayList<String> URLS = new ArrayList<String>() {{
        add("http://www.francefootball.fr/rss/feed.xml");
        add("http://www.eurosport.fr/football/rss.xml");
        add("http://www.lequipe.fr/rss/actu_rss_Football.xml");
    }};

    private static InputStream getRSSFeed(String url) {
        try {
            return new URL(url).openConnection().getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<RSSItem> getRSSItems() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();
        XmlPullParserFactory factory;
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            for (String url : URLS) {
                xpp.setInput(getRSSFeed(url), "UTF_8");
                boolean insideItem = false;
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (insideItem && xpp.getName().equalsIgnoreCase("title")) {
                            titles.add(xpp.nextText());
                        } else if (insideItem && xpp.getName().equalsIgnoreCase("link")) {
                            links.add(xpp.nextText());
                        } else if (insideItem && xpp.getName().equalsIgnoreCase("description")) {
                            descriptions.add(xpp.nextText());
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }
                    eventType = xpp.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<RSSItem> rssItems = new ArrayList<>();
        int length = titles.size();
        for (int i = 0; i < length; i++) {
            if (links.get(i).contains(LEQUIPE)) {
                rssItems.add(new RSSItem(titles.get(i), descriptions.get(i), links.get(i), "L'Equipe"));
            } else if (links.get(i).contains(EUROSPORT)) {
                rssItems.add(new RSSItem(titles.get(i), descriptions.get(i), links.get(i), "Eurosport"));
            } else {
                rssItems.add(new RSSItem(titles.get(i), descriptions.get(i), links.get(i), "France Football"));
            }
        }
        return rssItems;
    }
}
