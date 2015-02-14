package nsapp.com.footballfriendstournament.model;

import android.content.Context;

import java.util.ArrayList;

public class Calendrier extends ArrayList<Journee> {

    public Calendrier(Context context, final ArrayList<Equipe> equipes) {
        int count = 0;
        int taille = equipes.size();
        while (count < taille) {
            add(new Journee(context, new ArrayList<Equipe>() {{
                addAll(equipes);
            }}));
            count++;
        }

        taille = size();
        for (int i = 0; i < taille; i++) {
            Journee journee = get(i);
            ArrayList<Match> matches = new ArrayList<>();
            for (Match m : journee) {
                matches.add(new Match(m.getExterieure(), m.getDomicile()));
            }
            add(new Journee(matches));
        }
    }
}
