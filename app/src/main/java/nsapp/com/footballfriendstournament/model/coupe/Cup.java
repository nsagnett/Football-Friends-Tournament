package nsapp.com.footballfriendstournament.model.coupe;

import java.util.ArrayList;
import java.util.Random;

import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.model.Team;
import nsapp.com.footballfriendstournament.model.Match;

public class Cup implements Competition {

    public enum CupRank {
        QUARTERFINAL, SEMIFINAL, FINAL
    }

    private static Cup instance;

    private CupRank cupRank;
    private ArrayList<Team> quartersTeams = new ArrayList<>();
    private ArrayList<Team> semiTeams = new ArrayList<>();
    private ArrayList<Team> finalTeams = new ArrayList<>();

    private final ArrayList<Match> quartersMatches = new ArrayList<>();
    private final ArrayList<Match> semiMatches = new ArrayList<>();
    private Match finalMatch;

    public ArrayList<Team> getQuartersTeams() {
        return quartersTeams;
    }

    public void setQuartersTeams(ArrayList<Team> quartersTeams) {
        this.quartersTeams = quartersTeams;
    }

    public ArrayList<Team> getSemiTeams() {
        return semiTeams;
    }

    public void setSemiTeams(ArrayList<Team> semiTeams) {
        this.semiTeams = semiTeams;
    }

    public ArrayList<Team> getFinalTeams() {
        return finalTeams;
    }

    public void setFinalTeams(ArrayList<Team> finalTeams) {
        this.finalTeams = finalTeams;
    }

    public static Cup getInstance(ArrayList<Team> teams) {
        if (instance == null) {
            instance = new Cup(teams);
        }
        return instance;
    }

    private Cup(final ArrayList<Team> teams) {
        Random random = new Random();
        switch (teams.size()) {
            case 2:
                cupRank = CupRank.FINAL;
                finalTeams.addAll(teams);
                finalMatch = new Match(teams.remove(0), teams.remove(0));
                break;
            case 3:
                cupRank = CupRank.SEMIFINAL;
                semiTeams.addAll(teams);
                semiMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                finalTeams.add(teams.get(0));
                break;
            case 4:
                cupRank = CupRank.SEMIFINAL;
                semiTeams.addAll(teams);
                semiMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                semiMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                break;
            case 5:
                cupRank = CupRank.QUARTERFINAL;
                quartersTeams.addAll(teams);
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                semiTeams.add(teams.get(0));
                break;
            case 6:
                // Sur un match, gagnant -> Finale
                cupRank = CupRank.QUARTERFINAL;
                quartersTeams.addAll(teams);
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                break;
            case 7:
                cupRank = CupRank.QUARTERFINAL;
                quartersTeams.addAll(teams);
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                semiTeams.add(teams.get(0));
                break;
            case 8:
                cupRank = CupRank.QUARTERFINAL;
                quartersTeams.addAll(teams);
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                quartersMatches.add(new Match(teams.remove(random.nextInt(teams.size())), teams.remove(random.nextInt(teams.size()))));
                break;
            default:
                // Impossible
                break;
        }
    }
}
