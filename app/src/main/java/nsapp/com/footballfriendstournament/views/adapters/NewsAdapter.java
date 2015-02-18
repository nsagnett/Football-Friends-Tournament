package nsapp.com.footballfriendstournament.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.rss.RSSItem;

public class NewsAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<RSSItem> rssItems = new ArrayList<>();

    public NewsAdapter(Context context, ArrayList<RSSItem> rssItems) {
        this.context = context;
        this.rssItems.addAll(rssItems);
    }

    @Override
    public int getCount() {
        return rssItems.size();
    }

    @Override
    public RSSItem getItem(int position) {
        return rssItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news_holder, null);
        }

        TextView newsTitle = (TextView) convertView.findViewById(R.id.newsTitle);
        TextView newsDescription = (TextView) convertView.findViewById(R.id.newsDescription);
        TextView newsSource = (TextView) convertView.findViewById(R.id.newsSource);

        RSSItem rssItem = rssItems.get(position);

        newsTitle.setText(rssItem.getTitle());
        newsDescription.setText(rssItem.getDescription());
        newsSource.setText(rssItem.getSource());

        return convertView;
    }
}
