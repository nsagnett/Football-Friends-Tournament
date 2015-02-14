package nsapp.com.footballfriendstournament.model;

import java.util.ArrayList;

public class Classement extends ArrayList<Equipe> {

    public Classement(ArrayList<Equipe> equipes) {
        addAll(equipes);
    }

    public void miseAJour() {
        ArrayList<Equipe> optEquipes = new ArrayList<>(this);
        removeAll(this);

        // Selon les points
        while (!optEquipes.isEmpty()) {
            int index = -1;
            int taille = optEquipes.size();
            int max = -1;
            for (int i = 0; i < taille; i++) {
                int pts = optEquipes.get(i).getPoints();
                if (max < pts) {
                    max = pts;
                    index = i;
                }
            }
            add(optEquipes.remove(index));
        }

        int taille = size();
        for (int i = 1; i < taille; i++) {
            Equipe equipe1 = get(i - 1);
            Equipe equipe2 = get(i);

            if (equipe1.getPoints() == equipe2.getPoints()) {
                // Selon la différence de buts
                if (equipe1.getDifferenceButs() < equipe2.getDifferenceButs()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
                // Selon le nombre de buts pour
                else if (equipe1.getDifferenceButs() == equipe2.getDifferenceButs() && equipe1.getButsPour() < equipe2.getButsPour()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
                // Selon le nombre de buts contre
                else if (equipe1.getButsPour() == equipe2.getButsPour() && equipe1.getButsContre() > equipe2.getButsContre()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
                // Selon le nombre de victoires
                else if (equipe1.getButsContre() == equipe2.getButsContre() && equipe1.getVictoires() < equipe2.getVictoires()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
                // Selon le nombre de nuls
                else if (equipe1.getVictoires() == equipe2.getVictoires() && equipe1.getNuls() < equipe2.getNuls()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
                // Selon le nombre de defaites
                else if (equipe1.getNuls() == equipe2.getNuls() && equipe1.getDefaites() > equipe2.getDefaites()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
                // Selon le nombre de cartons rouges
                else if (equipe1.getDefaites() == equipe2.getDefaites() && equipe1.getCartonsRouges() > equipe2.getCartonsRouges()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
                // Selon le nombre de cartons jaunes
                else if (equipe1.getCartonsRouges() == equipe2.getCartonsRouges() && equipe1.getCartonsJaunes() > equipe2.getCartonsJaunes()) {
                    set(i - 1, equipe2);
                    set(i, equipe1);
                }
            }
        }
    }
}
