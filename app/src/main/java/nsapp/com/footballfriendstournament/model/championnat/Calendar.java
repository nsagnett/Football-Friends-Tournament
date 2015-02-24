package nsapp.com.footballfriendstournament.model.championnat;

import android.content.Context;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.model.Team;
import nsapp.com.footballfriendstournament.model.Match;

public class Calendar extends ArrayList<Day> {

    public Calendar(Context context, final ArrayList<Team> teams) {
        int taille = teams.size();
        for (int i = 1; i < taille; i++) {
            add(new Day(context, new ArrayList<Team>() {{
                addAll(teams);
            }}));
        }

        taille = size();
        for (int i = 0; i < taille; i++) {
            Day day = get(i);
            ArrayList<Match> matches = new ArrayList<>();
            for (Match m : day) {
                matches.add(new Match(m.getOutside(), m.getHome()));
            }
            add(new Day(matches));
        }
    }
}
