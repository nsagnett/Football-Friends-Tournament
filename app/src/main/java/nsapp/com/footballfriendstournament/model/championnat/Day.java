package nsapp.com.footballfriendstournament.model.championnat;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import nsapp.com.footballfriendstournament.model.Match;
import nsapp.com.footballfriendstournament.model.Team;

public class Day extends ArrayList<Match> {

    private static int uniqueID = 0;

    public Day(ArrayList<Team> firstLineTeams, ArrayList<Team> secondLineTeams) {
        uniqueID++;
        int length = firstLineTeams.size();
        Log.d("FOOTBALLFRIENDS", "===========Journée " + uniqueID + "==============");
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            if (random.nextBoolean()) {
                Log.d("FOOTBALLFRIENDS", "Match : " + firstLineTeams.get(i).getName() + " - " + secondLineTeams.get(i).getName());
                add(new Match(firstLineTeams.get(i), secondLineTeams.get(i)));
            } else {
                Log.d("FOOTBALLFRIENDS", "Match : " + secondLineTeams.get(i).getName() + " - " + firstLineTeams.get(i).getName());
                add(new Match(secondLineTeams.get(i), firstLineTeams.get(i)));
            }
        }
    }

    public Day(ArrayList<Match> matches) {
        super(matches);
        uniqueID++;
    }
}
