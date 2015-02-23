package nsapp.com.footballfriendstournament.views.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.rss.RSSController;
import nsapp.com.footballfriendstournament.model.rss.RSSItem;
import nsapp.com.footballfriendstournament.model.tools.Tool;
import nsapp.com.footballfriendstournament.views.adapters.NewsAdapter;

public class NewsFragment extends AbstractFragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private static final int SHOWING_ARTICLES_DELAY = 3000;
    private static int COUNT_VISIBLE_ITEMS;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private ArrayList<RSSItem> rssItemsLimited = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private int preLast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        onSetupModel(view);

        onRefresh();

        return view;
    }

    @Override
    protected void onSetupModel(View inflatedView) {
        super.onSetupModel(inflatedView);
        setTitle(getString(R.string.news));
        COUNT_VISIBLE_ITEMS = 10;
        if (Tool.news != null && Tool.news.size() > 1) {
            rssItemsLimited.addAll(Tool.getLimitedItems(Tool.news, COUNT_VISIBLE_ITEMS));
        }
        onSetupView(inflatedView);
    }

    @Override
    protected void onSetupView(View inflatedView) {
        listView = (ListView) inflatedView.findViewById(R.id.newsListView);
        newsAdapter = new NewsAdapter(mainActivity, rssItemsLimited);
        listView.setAdapter(newsAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) inflatedView.findViewById(R.id.newsSwipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
        onSetupListener();
    }

    @Override
    protected void onSetupListener() {
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mainActivity, ArticleActivity.class);
        intent.putExtra(INDEX_ARTICLE, position);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        setVisibleRefreshIndicator();
        runAsyncTask();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final int lastItem = firstVisibleItem + visibleItemCount;
        if (lastItem == totalItemCount) {
            if (preLast != lastItem) {
                preLast = lastItem;
                setVisibleRefreshIndicator();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setTitle(getString(R.string.news));
                        COUNT_VISIBLE_ITEMS += 10;
                        swipeRefreshLayout.setRefreshing(false);
                        if (!Tool.news.isEmpty()) {
                            rssItemsLimited.clear();
                            rssItemsLimited.addAll(Tool.getLimitedItems(Tool.news, COUNT_VISIBLE_ITEMS));
                            newsAdapter.notifyDataSetChanged();
                        }
                    }
                }, SHOWING_ARTICLES_DELAY);
            }
        }
    }

    private void runAsyncTask() {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] params) {
                Tool.news.addAll(RSSController.getRSSItems());
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                swipeRefreshLayout.setRefreshing(false);
                rssItemsLimited.clear();
                if (Tool.news.size() > 1) {
                    rssItemsLimited.addAll(Tool.getLimitedItems(Tool.news, COUNT_VISIBLE_ITEMS));
                } else {
                    rssItemsLimited.add(new RSSItem(getString(R.string.error_loading), getString(R.string.error_loading), "", ""));
                }
                setTitle(getString(R.string.news));
                newsAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private void setVisibleRefreshIndicator() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                setTitle(getString(R.string.loading));
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // Required
    }
}
