package nsapp.com.footballfriendstournament.model.championnat;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.model.Match;
import nsapp.com.footballfriendstournament.model.Team;

class Calendar {

    private final ArrayList<Day> days = new ArrayList<>();

    private final ArrayList<Team> firstLineTeams = new ArrayList<>();
    private final ArrayList<Team> secondLineTeams = new ArrayList<>();

    public Calendar(final ArrayList<Team> teams) {
        int length = teams.size();

        boolean isAtHome = true;
        for (int i = 0; i < length / 2; i++) {
            firstLineTeams.add(teams.get(i));
            secondLineTeams.add(teams.get(length - (i + 1)));
            isAtHome = !isAtHome;
        }

        boolean isFirstTime = true;
        while (isFirstTime || firstLineTeams.get(1).getId() != 2) {
            isFirstTime = false;
            days.add(new Day(firstLineTeams, secondLineTeams));
            rotateTeams();
        }

        // TODO Choisir aller ou aller retour
        ArrayList<Match> backMatches = new ArrayList<>();
        ArrayList<Day> tempDays = new ArrayList<Day>() {{
            addAll(days);
        }};
        for (Day day : tempDays) {
            for (Match m : day) {
                backMatches.add(new Match(m.getOutside(), m.getHome()));
            }
            days.add(new Day(backMatches));
        }
    }

    private void rotateTeams() {
        firstLineTeams.add(1, secondLineTeams.get(0));
        secondLineTeams.remove(0);
        secondLineTeams.add(firstLineTeams.get(firstLineTeams.size() - 1));
        firstLineTeams.remove(firstLineTeams.size() - 1);
    }

    public ArrayList<Day> getDays() {
        return days;
    }
}
