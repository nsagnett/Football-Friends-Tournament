package nsapp.com.footballfriendstournament.model.coupe;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.model.Match;
import nsapp.com.footballfriendstournament.model.Team;

public class Cup implements Competition {

    private ArrayList<Match> initListMatches = new ArrayList<>();
    private int cupPhase = 0;


    public ArrayList<Match> getInitListMatches() {
        return initListMatches;
    }

    public Bracket getCurrentWinnerBracket() {
        return winnerBracket.get(cupPhase);
    }

    public Bracket getCurrentLoserBracket() {
        return loserBracket.get(cupPhase);
    }

    private ArrayList<Bracket> winnerBracket = new ArrayList<Bracket>();
    private ArrayList<Bracket> loserBracket = new ArrayList<Bracket>();


    public Cup(final ArrayList<Team> teams) {
        int length = teams.size();
        for (int i = 0; i < length - 1; i += 2) {
            initListMatches.add(new Match(teams.get(i), teams.get(i + 1)));
        }
        winnerBracket.add(new Bracket());
        loserBracket.add(new Bracket());
    }

    public void finalScore(int indexMatch, int goalsHome, int goalsOutside) {
        if (goalsHome > goalsOutside) {
            winnerBracket.get(cupPhase).addTeam(initListMatches.get(indexMatch).getHome());
            loserBracket.get(cupPhase).addTeam(initListMatches.get(indexMatch).getOutside());
        } else if (goalsOutside > goalsHome) {
            winnerBracket.get(cupPhase).addTeam(initListMatches.get(indexMatch).getOutside());
            loserBracket.get(cupPhase).addTeam(initListMatches.get(indexMatch).getHome());
        }
    }

    public void nextStep() {
        ArrayList<Match> matches = winnerBracket.get(cupPhase).getMatches();
        for (Match m : matches) {
            m.getHome().setGoalsFor(0);
            m.getOutside().setGoalsFor(0);
        }

        matches = loserBracket.get(cupPhase).getMatches();
        for (Match m : matches) {
            m.getHome().setGoalsFor(0);
            m.getOutside().setGoalsFor(0);
        }
        winnerBracket.add(new Bracket());
        loserBracket.add(new Bracket());
        cupPhase++;
    }
}
