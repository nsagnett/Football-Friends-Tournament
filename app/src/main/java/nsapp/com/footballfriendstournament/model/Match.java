package nsapp.com.footballfriendstournament.model;

public class Match {

    public Team getHome() {
        return home;
    }

    public Team getOutside() {
        return outside;
    }

    private Team home;
    private Team outside;
    private boolean isFinished;

    public Match() {
        this.home = null;
        this.outside = null;
    }

    public Match(Team home, Team outside) {
        this.home = home;
        this.outside = outside;
    }

    public void finalScoreLeague(int homeGoals, int outsideGoals, int homeRedCards, int homeYellowCards, int outsideRedCards, int outsideYellowCards) {
        if (homeGoals > outsideGoals) {
            home.victoire(homeGoals, outsideGoals, homeRedCards, homeYellowCards);
            outside.defeat(outsideGoals, homeGoals, outsideRedCards, outsideYellowCards);
        } else if (homeGoals < outsideGoals) {
            home.defeat(homeGoals, outsideGoals, homeRedCards, homeYellowCards);
            outside.victoire(outsideGoals, homeGoals, outsideRedCards, outsideYellowCards);
        } else {
            home.matchNul(homeGoals, outsideGoals, homeRedCards, homeYellowCards);
            outside.matchNul(outsideGoals, homeGoals, outsideRedCards, outsideYellowCards);
        }
        isFinished = true;
    }

    public Team winnerMatchCup(int homeGoals, int outsideGoals) {
        return homeGoals > outsideGoals ? home
                : outsideGoals > homeGoals ? outside
                : null;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
