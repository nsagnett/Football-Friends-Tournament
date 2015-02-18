package nsapp.com.footballfriendstournament.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nsapp.com.footballfriendstournament.views.activities.MainActivity;

public abstract class AbstractFragment extends Fragment {

    protected static final String COMPETITION_TYPE = "competitionType";
    protected static final String INDEX_ARTICLE = "indexArticle";
    protected static final String ARTICLES = "articles";
    protected static final String LEAGUE = "League";
    protected static final String CUP = "Cup";

    protected MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.invalidateOptionsMenu();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
