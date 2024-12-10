public class Joueur {
    private boolean IA;
    private Tuile main = null;
    private final String nom;
    private int score;

    private boolean abandonne;

    public Joueur(String nom, boolean IA){
        this.nom = nom;
        this.IA = IA;

    }

    public boolean isPlaying() {
        return !abandonne;
    }

    public void setAbandonne(boolean abandonne) {
        this.abandonne = abandonne;
    }

    public boolean isIA() {
        return IA;
    }
    public void setIA(boolean IA) {
        this.IA = IA;
    }
    public Tuile getMain() {
        return main;
    }
    public void addMain(Tuile tuile) {
        this.main = tuile;
    }

    public void setMain(Tuile main) {
        this.main = main;
    }

    public void printMain(){
        this.main.printTuile();

    }
    public String getNom() {
        return nom;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int points) {
        this.score += points;
    }
}
