package nsapp.com.footballfriendstournament.model.coupe;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.model.Match;
import nsapp.com.footballfriendstournament.model.Team;

public class Cup implements Competition {

    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Match> initBracket = new ArrayList<>();


    public Cup(final ArrayList<Team> teams) {
        this.teams.addAll(teams);
        int length = this.teams.size();
        for (int i = 0; i < length - 1; i += 2) {
            initBracket.add(new Match(this.teams.get(i), this.teams.get(i + 1)));
        }
    }
}
