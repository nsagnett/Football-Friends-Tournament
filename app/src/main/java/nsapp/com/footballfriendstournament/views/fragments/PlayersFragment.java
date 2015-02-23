package nsapp.com.footballfriendstournament.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.model.Team;
import nsapp.com.footballfriendstournament.model.championnat.League;
import nsapp.com.footballfriendstournament.model.coupe.Cup;

public class PlayersFragment extends AbstractFragment {

    private NumberPicker playersCountPicker;

    private String competitionType;

    public static PlayersFragment newInstance(String competitionType) {
        PlayersFragment playersFragment = new PlayersFragment();
        Bundle args = new Bundle();
        args.putString(COMPETITION_TYPE, competitionType);
        playersFragment.setArguments(args);
        return playersFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        onSetupModel(view);

        Button button = (Button) view.findViewById(R.id.goButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Competition competition;
                if (competitionType.equals(LEAGUE)) {
                    competition = League.getInstance(mainActivity, new ArrayList<Team>() {{
                        for (int i = 0; i < playersCountPicker.getValue(); i++) {
                            add(new Team("Equipe " + i));
                        }
                    }});
                } else {
                    competition = Cup.getInstance(new ArrayList<Team>() {{
                        for (int i = 1; i <= playersCountPicker.getValue(); i++) {
                            add(new Team("Equipe " + i));
                        }
                    }});
                }
                mainActivity.setCompetition(competition);
            }
        });
        return view;
    }

    @Override
    protected void onSetupModel(View inflatedView) {
        super.onSetupModel(inflatedView);
        competitionType = getArguments().getString(COMPETITION_TYPE);
        onSetupView(inflatedView);
    }

    @Override
    protected void onSetupView(View inflatedView) {
        playersCountPicker = (NumberPicker) inflatedView.findViewById(R.id.playersNumberPicker);
        playersCountPicker.setMinValue(2);
        if (competitionType != null && competitionType.equals(LEAGUE)) {
            playersCountPicker.setMaxValue(24);
        } else {
            playersCountPicker.setMaxValue(8);
        }
        playersCountPicker.setWrapSelectorWheel(false);
        onSetupListener();
    }

    @Override
    protected void onSetupListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
