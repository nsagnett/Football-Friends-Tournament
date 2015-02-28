package nsapp.com.footballfriendstournament.model;

public class Team {

    private static int uniqueID = 0;
    private final int id;
    private boolean exempt;
    private String name;
    private int matchesCount;
    private int wins;
    private int draws;
    private int defeats;
    private int goalsFor;
    private int goalsAgainst;
    private int yellowCards;
    private int redCards;
    private String goalDifference;
    private int points;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public String getGoalDifference() {
        return goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public boolean exempt() {
        return exempt;
    }

    public void setExempt(boolean exempt) {
        this.exempt = exempt;
    }

    public int getMatchesCount() {
        return matchesCount;
    }

    private void updateStats(int goalsFor, int goalsAgainst, int redCards, int yellowCards) {
        this.matchesCount++;
        this.goalsFor += goalsFor;
        this.goalsAgainst += goalsAgainst;
        this.redCards += redCards;
        this.yellowCards += yellowCards;
        int goalDiff = (this.goalsFor - this.goalsAgainst);
        this.goalDifference = goalDiff > 0 ? "+" + goalDiff :
                goalDiff < 0 ? "-" + goalDiff :
                        String.valueOf(goalDiff);
    }

    public void victoire(int nbButsPour, int nbButsContre, int cartonsRouges, int cartonsJaunes) {
        updateStats(nbButsPour, nbButsContre, cartonsRouges, cartonsJaunes);
        wins++;
        points += 3;
    }

    public void matchNul(int nbButsPour, int nbButsContre, int cartonsRouges, int cartonsJaunes) {
        updateStats(nbButsPour, nbButsContre, cartonsRouges, cartonsJaunes);
        draws++;
        points++;
    }

    public void defeat(int nbButsPour, int nbButsContre, int cartonsRouges, int cartonsJaunes) {
        updateStats(nbButsPour, nbButsContre, cartonsRouges, cartonsJaunes);
        defeats++;
    }

    public Team(String name) {
        uniqueID++;
        id = uniqueID;
        this.name = name;
        matchesCount = 0;
        wins = 0;
        defeats = 0;
        draws = 0;
        goalsAgainst = 0;
        goalsFor = 0;
        yellowCards = 0;
        redCards = 0;
    }

    public Team(String name, int wins, int draws, int defeats, int goalsFor, int goalsAgainst, int yellowCards, int redCards, String goalDifference, int points) {
        this(name);
        this.name = name;
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.goalDifference = goalDifference;
        this.points = points;
    }
}
