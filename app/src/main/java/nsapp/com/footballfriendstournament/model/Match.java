package nsapp.com.footballfriendstournament.model;

public class Match {

    public Equipe getDomicile() {
        return domicile;
    }

    public Equipe getExterieure() {
        return exterieure;
    }

    private Equipe domicile;
    private Equipe exterieure;
    private boolean isFinished;

    public Match(Equipe domicile, Equipe exterieure) {
        this.domicile = domicile;
        this.exterieure = exterieure;
    }

    public void scoreFinal(int nbButsD, int nbButsE, int cartonsRougesD, int cartonsJaunesD, int cartonsRougesE, int cartonsJaunesE) {
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

    public boolean isFinished() {
        return isFinished;
    }
}
