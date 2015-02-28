package nsapp.com.footballfriendstournament.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nsapp.com.footballfriendstournament.views.activities.MainActivity;

public abstract class AbstractFragment extends Fragment implements View.OnClickListener {

    static final String COMPETITION_TYPE = "competitionType";
    static final String INDEX_ARTICLE = "indexArticle";
    static final String LEAGUE = "League";
    static final String CUP = "Cup";

    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    void onSetupModel(View inflatedView) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.invalidateOptionsMenu();
    }

    protected abstract void onSetupView(View inflatedView);

    protected abstract void onSetupListener();

    void setTitle(String title) {
        mainActivity.getSupportActionBar().setTitle(title);
    }
}
