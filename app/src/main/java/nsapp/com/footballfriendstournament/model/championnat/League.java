package nsapp.com.footballfriendstournament.model.championnat;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.model.Team;

public class League implements Competition {

    public static ArrayList<String> idsMatches = new ArrayList<>();

    private static League instance;

    private ArrayList<Team> teams = new ArrayList<>();
    private Calendar calendar;
    private Ranking ranking;

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public static League getInstance(Context context, ArrayList<Team> teams) {
        if (instance == null) {
            instance = new League(context, teams);
        }
        return instance;
    }

    private League(Context context, final ArrayList<Team> teams) {
        this.teams.addAll(teams);
        int length = this.teams.size();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            Team t = this.teams.get(i);
            for (int j = 0; j < length; j++) {
                if (i != j) {
                    boolean atHome = random.nextBoolean();
                    t.getContextMatches().add(new ContextMatch(this.teams.get(j), atHome));
                }
            }
        }

        calendar = new Calendar(context, this.teams);
        ranking = new Ranking(this.teams);
    }

    public Day getDay(int index) {
        return calendar.get(index);
    }

    public void finalScore(int indexDay, int indexMatch, int goalsHome, int goalsOutside, int redCardsHome, int yellowCardsHome, int redCardsOutside, int yellowCardsOutside) {
        calendar.get(indexDay).get(indexMatch).finalScoreLeague(goalsHome, goalsOutside, redCardsHome, yellowCardsHome, redCardsOutside, yellowCardsOutside);
        teams = ranking.miseAJour();
    }
}
