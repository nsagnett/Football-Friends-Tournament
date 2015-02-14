package nsapp.com.footballfriendstournament.model;

public class Match {

    public Equipe getDomicile() {
        return domicile;
    }

    public Equipe getExterieure() {
        return exterieure;
    }

    public void setDomicile(Equipe domicile) {
        this.domicile = domicile;
    }

    public void setExterieure(Equipe exterieure) {
        this.exterieure = exterieure;
    }

    private Equipe domicile;
    private Equipe exterieure;
    private boolean isFinished;

    public Match() {
        this.domicile = null;
        this.exterieure = null;
    }

    public Match(Equipe domicile, Equipe exterieure) {
        this.domicile = domicile;
        this.exterieure = exterieure;
    }

    public void scoreFinalChampionnat(int nbButsD, int nbButsE, int cartonsRougesD, int cartonsJaunesD, int cartonsRougesE, int cartonsJaunesE) {
        if (nbButsD > nbButsE) {
            domicile.victoire(nbButsD, nbButsE, cartonsRougesD, cartonsJaunesD);
            exterieure.defaite(nbButsE, nbButsD, cartonsRougesE, cartonsJaunesE);
        } else if (nbButsD < nbButsE) {
            domicile.defaite(nbButsD, nbButsE, cartonsRougesD, cartonsJaunesD);
            exterieure.victoire(nbButsE, nbButsD, cartonsRougesE, cartonsJaunesE);
        } else {
            domicile.matchNul(nbButsD, nbButsE, cartonsRougesD, cartonsJaunesD);
            exterieure.matchNul(nbButsE, nbButsD, cartonsRougesE, cartonsJaunesE);
        }
        isFinished = true;
    }

    public Equipe gagnantMatchCoupe(int nbButsD, int nbButsE) {
        return nbButsD > nbButsE ? domicile
                : nbButsE > nbButsD ? exterieure
                : null;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
