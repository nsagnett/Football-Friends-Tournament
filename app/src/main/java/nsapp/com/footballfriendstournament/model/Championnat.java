package nsapp.com.footballfriendstournament.model;

import android.content.Context;

import java.util.ArrayList;

public class Championnat {

    public static ArrayList<String> idsMatches = new ArrayList<>();

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }

    private ArrayList<Equipe> equipes = new ArrayList<>();
    private Calendrier calendrier;
    private Classement classement;

    public Championnat(Context context, final ArrayList<Equipe> equipes) {
        this.equipes.addAll(equipes);
        calendrier = new Calendrier(context, this.equipes);
        classement = new Classement(this.equipes);
    }

    public Journee getJournee(int index) {
        return calendrier.get(index);
    }

    public void scoreFinal(int indexJournee, int indexMatch, int nbButsD, int nbButsE, int cartonsRougesD, int cartonsJaunesD, int cartonsRougesE, int cartonsJaunesE) {
        calendrier.get(indexJournee).get(indexMatch).scoreFinal(nbButsD, nbButsE, cartonsRougesD, cartonsJaunesD, cartonsRougesE, cartonsJaunesE);
        classement.miseAJour();
        equipes.removeAll(equipes);
        equipes.addAll(classement);
    }
}
