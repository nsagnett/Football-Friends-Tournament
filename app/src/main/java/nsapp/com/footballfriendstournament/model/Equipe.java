package nsapp.com.footballfriendstournament.model;

public class Equipe {

    private static int uniqueID = 0;
    private int id;
    private boolean wasExempt;
    private boolean wasDomicile;
    private String nom;
    private int matches;
    private int victoires;
    private int nuls;
    private int defaites;
    private int butsPour;
    private int butsContre;
    private int cartonsJaunes;
    private int cartonsRouges;
    private int differenceButs;
    private int points;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getVictoires() {
        return victoires;
    }

    public int getNuls() {
        return nuls;
    }

    public int getDefaites() {
        return defaites;
    }

    public int getButsPour() {
        return butsPour;
    }

    public int getButsContre() {
        return butsContre;
    }

    public int getCartonsJaunes() {
        return cartonsJaunes;
    }

    public int getCartonsRouges() {
        return cartonsRouges;
    }

    public int getDifferenceButs() {
        return differenceButs;
    }

    public int getPoints() {
        return points;
    }

    public boolean wasExempt() {
        return wasExempt;
    }

    public void setWasExempt(boolean wasExempt) {
        this.wasExempt = wasExempt;
    }

    public boolean wasDomicile() {
        return wasDomicile;
    }

    public void setWasDomicile(boolean wasDomicile) {
        this.wasDomicile = wasDomicile;
    }

    public int getMatches() {
        return matches;
    }

    private void MAJ(int nbButsPour, int nbButsContre, int cartonsRouges, int cartonsJaunes) {
        this.matches++;
        this.butsPour += nbButsPour;
        this.butsContre += nbButsContre;
        this.cartonsRouges += cartonsRouges;
        this.cartonsJaunes += cartonsJaunes;
        this.differenceButs = (butsPour - butsContre);
    }

    public void victoire(int nbButsPour, int nbButsContre, int cartonsRouges, int cartonsJaunes) {
        MAJ(nbButsPour, nbButsContre, cartonsRouges, cartonsJaunes);
        victoires++;
        points += 3;
    }

    public void matchNul(int nbButsPour, int nbButsContre, int cartonsRouges, int cartonsJaunes) {
        MAJ(nbButsPour, nbButsContre, cartonsRouges, cartonsJaunes);
        nuls++;
        points++;
    }

    public void defaite(int nbButsPour, int nbButsContre, int cartonsRouges, int cartonsJaunes) {
        MAJ(nbButsPour, nbButsContre, cartonsRouges, cartonsJaunes);
        defaites++;
    }

    public Equipe(String nom) {
        uniqueID++;
        id = uniqueID;
        this.nom = nom;
        matches = 0;
        victoires = 0;
        defaites = 0;
        nuls = 0;
        butsContre = 0;
        butsPour = 0;
        cartonsJaunes = 0;
        cartonsRouges = 0;
    }

    public Equipe(String nom, int victoires, int nuls, int defaites, int butsPour, int butsContre, int cartonsJaunes, int cartonsRouges, int differenceButs, int points) {
        this(nom);
        this.nom = nom;
        this.victoires = victoires;
        this.nuls = nuls;
        this.defaites = defaites;
        this.butsPour = butsPour;
        this.butsContre = butsContre;
        this.cartonsJaunes = cartonsJaunes;
        this.cartonsRouges = cartonsRouges;
        this.differenceButs = differenceButs;
        this.points = points;
    }
}
