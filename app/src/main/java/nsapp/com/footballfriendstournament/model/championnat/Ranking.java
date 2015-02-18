package nsapp.com.footballfriendstournament.model.championnat;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.model.Team;

public class Ranking extends ArrayList<Team> {

    public Ranking(ArrayList<Team> teams) {
        addAll(teams);
    }

    public ArrayList<Team> miseAJour() {
        ArrayList<Team> optTeams = new ArrayList<>(this);
        removeAll(this);

        // Selon les points
        while (!optTeams.isEmpty()) {
            int index = -1;
            int taille = optTeams.size();
            int max = -1;
            for (int i = 0; i < taille; i++) {
                int pts = optTeams.get(i).getPoints();
                if (max < pts) {
                    max = pts;
                    index = i;
                }
            }
            add(optTeams.remove(index));
        }

        int taille = size();
        for (int i = 1; i < taille; i++) {
            Team team1 = get(i - 1);
            Team team2 = get(i);

            if (team1.getPoints() == team2.getPoints()) {
                // Selon la différence de buts
                int goalDiffTeam1 = Integer.parseInt(team1.getGoalDifference());
                int goalDiffTeam2 = Integer.parseInt(team2.getGoalDifference());
                if (goalDiffTeam1 < goalDiffTeam2) {
                    set(i - 1, team2);
                    set(i, team1);
                }
                // Selon le nombre de buts pour
                else if (team1.getGoalDifference().equals(team2.getGoalDifference()) && team1.getGoalsFor() < team2.getGoalsFor()) {
                    set(i - 1, team2);
                    set(i, team1);
                }
                // Selon le nombre de buts contre
                else if (team1.getGoalsFor() == team2.getGoalsFor() && team1.getGoalsAgainst() > team2.getGoalsAgainst()) {
                    set(i - 1, team2);
                    set(i, team1);
                }
                // Selon le nombre de victoires
                else if (team1.getGoalsAgainst() == team2.getGoalsAgainst() && team1.getWins() < team2.getWins()) {
                    set(i - 1, team2);
                    set(i, team1);
                }
                // Selon le nombre de nuls
                else if (team1.getWins() == team2.getWins() && team1.getDraws() < team2.getDraws()) {
                    set(i - 1, team2);
                    set(i, team1);
                }
                // Selon le nombre de defaites
                else if (team1.getDraws() == team2.getDraws() && team1.getDefeats() > team2.getDefeats()) {
                    set(i - 1, team2);
                    set(i, team1);
                }
                // Selon le nombre de cartons rouges
                else if (team1.getDefeats() == team2.getDefeats() && team1.getRedCards() > team2.getRedCards()) {
                    set(i - 1, team2);
                    set(i, team1);
                }
                // Selon le nombre de cartons jaunes
                else if (team1.getRedCards() == team2.getRedCards() && team1.getYellowCards() > team2.getYellowCards()) {
                    set(i - 1, team2);
                    set(i, team1);
                }
            }
        }
        return this;
    }
}
