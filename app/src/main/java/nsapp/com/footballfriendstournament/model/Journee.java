package nsapp.com.footballfriendstournament.model;

import android.content.Context;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;

public class Journee extends ArrayList<Match> {

    private static int uniqueID = 0;

    public Journee(Context context, ArrayList<Equipe> equipes) {
        uniqueID++;
        if (equipes.size() % 2 != 0) {
            int index = 0;
            Equipe equipe1 = null;
            for (Equipe e : equipes) {
                if (e.wasExempt()) {
                    e.setWasExempt(false);
                    equipe1 = e;
                    equipes.remove(e);
                    break;
                }
            }

            if (equipe1 == null) {
                equipe1 = equipes.remove(index);
            }

            while (equipes.size() != 1) {
                Equipe equipe2 = equipes.get(index);
                String idMatch = String.format(context.getString(R.string.id_match_format), equipe1.getId(), equipe2.getId());
                String idMatchReverse = String.format(context.getString(R.string.id_match_format), equipe2.getId(), equipe1.getId());
                if (!Championnat.idsMatches.contains(idMatch) && !Championnat.idsMatches.contains(idMatchReverse)) {
                    Championnat.idsMatches.add(idMatch);
                    equipes.remove(index);
                    if (equipe1.wasDomicile()) {
                        add(new Match(equipe2, equipe1));
                        equipe2.setWasDomicile(true);
                        equipe1.setWasDomicile(false);
                    } else {
                        add(new Match(equipe1, equipe2));
                        equipe1.setWasDomicile(true);
                        equipe2.setWasDomicile(false);
                    }
                    index = 0;
                } else {
                    index++;
                }
            }
            if (!equipes.isEmpty()) {
                equipes.get(0).setWasExempt(true);
            }
        } else if (uniqueID < equipes.size()) {
            int indexEquipeDomicile = 0;
            int indexEquipeExterieure = 1;
            while (!equipes.isEmpty()) {
                Equipe equipe1 = equipes.get(indexEquipeDomicile);
                Equipe equipe2 = equipes.get(indexEquipeExterieure);
                String idMatch = String.format(context.getString(R.string.id_match_format), equipe1.getId(), equipe2.getId());
                String idMatchReverse = String.format(context.getString(R.string.id_match_format), equipe2.getId(), equipe1.getId());
                if (equipe1.getId() != equipe2.getId() &&
                        !Championnat.idsMatches.contains(idMatch) &&
                        !Championnat.idsMatches.contains(idMatchReverse)) {
                    Championnat.idsMatches.add(idMatch);
                    equipes.remove(equipe1);
                    equipes.remove(equipe2);
                    if (equipe1.wasDomicile()) {
                        add(new Match(equipe2, equipe1));
                        equipe1.setWasDomicile(false);
                        equipe2.setWasDomicile(true);
                    } else {
                        add(new Match(equipe1, equipe2));
                        equipe1.setWasDomicile(true);
                        equipe2.setWasDomicile(false);
                    }
                    indexEquipeDomicile = 0;
                    indexEquipeExterieure = 1;
                } else if (indexEquipeExterieure == equipes.size() - 1) {
                    indexEquipeDomicile++;
                    indexEquipeExterieure = 1;
                } else {
                    indexEquipeExterieure++;
                }
            }
        }
    }

    public Journee(ArrayList<Match> matches) {
        super(matches);
        uniqueID++;
    }
}
