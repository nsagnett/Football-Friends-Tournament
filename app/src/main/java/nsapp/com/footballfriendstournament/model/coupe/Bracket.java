package nsapp.com.footballfriendstournament.model.coupe;

import java.util.ArrayList;
import java.util.Random;

import nsapp.com.footballfriendstournament.model.Match;
import nsapp.com.footballfriendstournament.model.Team;

public class Bracket {

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addTeam(Team t) {
        if (waitingTeam == null) {
            waitingTeam = t;
        } else {
            Random random = new Random();
            if (random.nextBoolean()) {
                matches.add(new Match(waitingTeam, t));
            } else {
                matches.add(new Match(t, waitingTeam));
            }
            waitingTeam = null;
        }
    }

    private ArrayList<Match> matches = new ArrayList<>();
    private Team waitingTeam;
}
