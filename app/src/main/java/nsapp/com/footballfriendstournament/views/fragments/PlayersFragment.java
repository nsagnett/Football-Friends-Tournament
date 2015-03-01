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

    private String[] array;

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
                final int playersCount = Integer.parseInt(array[playersCountPicker.getValue()]);
                if (competitionType.equals(LEAGUE)) {
                    competition = new League(new ArrayList<Team>() {{
                        for (int i = 0; i < playersCount; i++) {
                            add(new Team("Equipe " + i));
                        }
                    }}, true);
                } else {
                    competition = new Cup(new ArrayList<Team>() {{
                        for (int i = 1; i <= playersCount; i++) {
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
        playersCountPicker.setMinValue(0);
        if (competitionType != null && competitionType.equals(LEAGUE)) {
            array = getResources().getStringArray(R.array.pairNumberArray);
            playersCountPicker.setMaxValue(array.length - 1);
            playersCountPicker.setDisplayedValues(array);
        } else {
            array = getResources().getStringArray(R.array.cupNumberArray);
            playersCountPicker.setMaxValue(array.length - 1);
            playersCountPicker.setDisplayedValues(array);
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
