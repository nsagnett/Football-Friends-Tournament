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

        String competitionType = getArguments().getString(COMPETITION_TYPE);

        final NumberPicker picker = (NumberPicker) view.findViewById(R.id.playersNumberPicker);
        Button button = (Button) view.findViewById(R.id.goButton);

        picker.setMinValue(2);
        if (competitionType != null && competitionType.equals(LEAGUE)) {
            picker.setMaxValue(24);
        }else {
            picker.setMaxValue(8);
        }
        picker.setWrapSelectorWheel(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Competition competition = null;
                if (getArguments().getString(COMPETITION_TYPE, null).equals(LEAGUE)) {
                    competition = League.getInstance(mainActivity, new ArrayList<Team>() {{
                        for (int i = 1; i <= picker.getValue(); i++) {
                            add(new Team("Equipe " + i));
                        }
                    }});
                } else {
                    competition = Cup.getInstance(new ArrayList<Team>() {{
                        for (int i = 1; i <= picker.getValue(); i++) {
                            add(new Team("Equipe " + i));
                        }
                    }});
                }
                mainActivity.setCompetition(competition);
            }
        });
        return view;
    }
}
