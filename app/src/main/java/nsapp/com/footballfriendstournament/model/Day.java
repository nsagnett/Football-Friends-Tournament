package nsapp.com.footballfriendstournament.model;

import android.content.Context;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.championnat.League;

public class Day extends ArrayList<Match> {

    private static int uniqueID = 0;

    public Day(Context context, ArrayList<Team> teams) {
        uniqueID++;
        if (teams.size() % 2 != 0) {
            int index = 0;
            Team team1 = null;
            for (Team e : teams) {
                if (e.exempt()) {
                    e.setExempt(false);
                    team1 = e;
                    teams.remove(e);
                    break;
                }
            }

            if (team1 == null) {
                team1 = teams.remove(index);
            }

            while (teams.size() != 1) {
                Team team2 = teams.get(index);
                String idMatch = String.format(context.getString(R.string.id_match_format), team1.getId(), team2.getId());
                String idMatchReverse = String.format(context.getString(R.string.id_match_format), team2.getId(), team1.getId());
                if (!League.idsMatches.contains(idMatch) && !League.idsMatches.contains(idMatchReverse)) {
                    League.idsMatches.add(idMatch);
                    teams.remove(index);
                    if (team1.atHome()) {
                        add(new Match(team2, team1));
                        team2.setAtHome(true);
                        team1.setAtHome(false);
                    } else {
                        add(new Match(team1, team2));
                        team1.setAtHome(true);
                        team2.setAtHome(false);
                    }
                    index = 0;
                } else {
                    index++;
                }
            }
            if (!teams.isEmpty()) {
                teams.get(0).setExempt(true);
            }
        } else if (uniqueID < teams.size()) {
            int indexEquipeDomicile = 0;
            int indexEquipeExterieure = 1;
            while (!teams.isEmpty()) {
                Team team1 = teams.get(indexEquipeDomicile);
                Team team2 = teams.get(indexEquipeExterieure);
                String idMatch = String.format(context.getString(R.string.id_match_format), team1.getId(), team2.getId());
                String idMatchReverse = String.format(context.getString(R.string.id_match_format), team2.getId(), team1.getId());
                if (team1.getId() != team2.getId() &&
                        !League.idsMatches.contains(idMatch) &&
                        !League.idsMatches.contains(idMatchReverse)) {
                    League.idsMatches.add(idMatch);
                    teams.remove(team1);
                    teams.remove(team2);
                    if (team1.atHome()) {
                        add(new Match(team2, team1));
                        team1.setAtHome(false);
                        team2.setAtHome(true);
                    } else {
                        add(new Match(team1, team2));
                        team1.setAtHome(true);
                        team2.setAtHome(false);
                    }
                    indexEquipeDomicile = 0;
                    indexEquipeExterieure = 1;
                } else if (indexEquipeExterieure == teams.size() - 1) {
                    indexEquipeDomicile++;
                    indexEquipeExterieure = 1;
                } else {
                    indexEquipeExterieure++;
                }
            }
        }
    }

    public Day(ArrayList<Match> matches) {
        super(matches);
        uniqueID++;
    }
}
