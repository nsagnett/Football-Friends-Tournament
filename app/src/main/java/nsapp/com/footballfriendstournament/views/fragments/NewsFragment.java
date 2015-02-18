package nsapp.com.footballfriendstournament.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.GetNewsCallBack;
import nsapp.com.footballfriendstournament.model.rss.RSSItem;
import nsapp.com.footballfriendstournament.views.adapters.NewsAdapter;

public class NewsFragment extends AbstractFragment implements AdapterView.OnItemClickListener {

    private ArrayList<RSSItem> rssItems = new ArrayList<>();
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ListView listView = (ListView) view.findViewById(R.id.newsListView);
        rssItems.addAll(mainActivity.getNewsItems());

        newsAdapter = new NewsAdapter(mainActivity, rssItems);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(this);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.newsSwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainActivity.runAsyncTask(new GetNewsCallBack() {
                    @Override
                    public void onFinished() {
                        swipeRefreshLayout.setRefreshing(false);
                        rssItems.clear();
                        rssItems.addAll(mainActivity.getNewsItems());
                        newsAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_dark);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mainActivity, ArticleActivity.class);
        intent.putExtra(INDEX_ARTICLE, position);
        intent.putExtra(ARTICLES, mainActivity.getNewsItems());
        startActivity(intent);
    }
}
