package nsapp.com.footballfriendstournament.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.tools.Tool;

public class ArticleActivity extends ActionBarActivity {

    private static final String INDEX_ARTICLE = "indexArticle";

    private WebView webView;
    private int indexArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        indexArticle = getIntent().getIntExtra(INDEX_ARTICLE, 0);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onResume() {
        super.onResume();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Tool.news.get(indexArticle).getLink());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_arrow_top) {
            indexArticle = indexArticle == 0 ? Tool.news.size() - 1 : indexArticle - 1;
        } else if (item.getItemId() == R.id.action_arrow_bottom) {
            indexArticle = indexArticle == Tool.news.size() - 1 ? 0 : indexArticle + 1;
        }
        onResume();
        return super.onOptionsItemSelected(item);
    }
}
