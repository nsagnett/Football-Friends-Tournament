package nsapp.com.footballfriendstournament.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.model.Match;
import nsapp.com.footballfriendstournament.model.Team;
import nsapp.com.footballfriendstournament.model.championnat.League;
import nsapp.com.footballfriendstournament.model.coupe.Cup;
import nsapp.com.footballfriendstournament.views.adapters.MatchesAdapter;

public class CupPhaseFragment extends AbstractFragment {

    private ListView listView;

    private MatchesAdapter matchesAdapter;

    private Cup cup;

    public static CupPhaseFragment newInstance() {
        CupPhaseFragment playersFragment = new CupPhaseFragment();
        Bundle args = new Bundle();
        playersFragment.setArguments(args);
        return playersFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_cup_phase, container, false);
        onSetupModel(view);
        return view;
    }

    @Override
    protected void onSetupModel(View inflatedView) {
        super.onSetupModel(inflatedView);
        cup = (Cup) mainActivity.getCompetition();
        ArrayList<Match> matches = new ArrayList<>();
        matches.addAll(cup.getInitListMatches());
        matchesAdapter = new MatchesAdapter(mainActivity, matches);
        onSetupView(inflatedView);
    }

    @Override
    protected void onSetupView(View inflatedView) {
        listView = (ListView) inflatedView.findViewById(R.id.listMatches);
        listView.setAdapter(matchesAdapter);
        onSetupListener();
    }

    @Override
    protected void onSetupListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
