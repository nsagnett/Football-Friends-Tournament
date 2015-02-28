package nsapp.com.footballfriendstournament.model.championnat;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.model.Team;

public class League implements Competition {

    public static ArrayList<String> idsMatches = new ArrayList<>();

    private static League instance;

    private ArrayList<Team> teams = new ArrayList<>();
    private final Calendar calendar;
    private final Ranking ranking;

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public static League getInstance(ArrayList<Team> teams) {
        if (instance == null) {
            instance = new League(teams);
        }
        return instance;
    }

    private League(final ArrayList<Team> teams) {
        this.teams.addAll(teams);
        calendar = new Calendar(this.teams);
        ranking = new Ranking(this.teams);
    }

    public Day getDay(int index) {
        return calendar.getDays().get(index);
    }

    public void finalScore(int indexDay, int indexMatch, int goalsHome, int goalsOutside, int redCardsHome, int yellowCardsHome, int redCardsOutside, int yellowCardsOutside) {
        calendar.getDays().get(indexDay).get(indexMatch).finalScoreLeague(goalsHome, goalsOutside, redCardsHome, yellowCardsHome, redCardsOutside, yellowCardsOutside);
        teams = ranking.miseAJour();
    }
}
