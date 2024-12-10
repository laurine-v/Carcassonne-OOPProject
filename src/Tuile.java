import java.awt.Point;
import java.util.Arrays;

import javax.swing.Icon;

public abstract class Tuile{

    public abstract char[][] getElementsTuiles();
    public abstract void setElementsTuiles(char[][] elementsTuiles);
    public boolean Collable(Tuile tuile, char cote) {
        Tuile tuileCarcassonne =  tuile;
        if(cote == 'h'){
            return collableSimple(this,tuileCarcassonne,4);
        }
        if(cote == 'b'){
            return collableSimple(this,tuileCarcassonne,0);

        }
        tuileCarcassonne = voirRotation(tuileCarcassonne);
        Tuile thisTuile = voirRotation(this);
        if(cote == 'g'){
            return collableSimple(thisTuile,tuileCarcassonne,4);

        }
        if(cote == 'd'){
            return collableSimple(thisTuile,tuileCarcassonne,0);

        }
        return false;
    }
    private boolean collableSimple(Tuile thisTuile,Tuile tuileCarcassonne ,int integer){
        int i = 0;
        for (char paysage : thisTuile.getElementsTuiles()[integer]) {
            if (paysage != (tuileCarcassonne.getElementsTuiles()[4-integer][i])) {
                return false;
            }
            i++;
        }
        return true;
    }
    @Override
    public abstract Tuile clone();
    public void printTuile() {
        for (char[] elementsTuile : this.getElementsTuiles() ){
            System.out.println(Arrays.toString(elementsTuile));
        }
        System.out.println();

    }
    public void faireRotation() {
        final int M = this.getElementsTuiles().length;
        final int N = this.getElementsTuiles()[0].length;
        char[][] ret = new char[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M-1-r] = this.getElementsTuiles()[r][c];
            }
        }
        this.setElementsTuiles(ret);
    }
    public static Tuile voirRotation(Tuile tuileCarcassonne) {
        Tuile tuileClone = tuileCarcassonne.clone();
        tuileClone.faireRotation();
        return tuileClone;
    }
    public static String [] concatenateString(String[] strings, char[][] tuile) {
        String[] stringsResult = new String[5];
        for (int i = 0; i < 5; i++) {
            stringsResult[i] = strings[i] + String.valueOf(tuile[i]) + "|";
        }
        return stringsResult;
    }
    @Override
    public String toString() {
        String string ="";
        for(char[] chars: this.getElementsTuiles()) {
            //System.out.println(string);
            string += String.valueOf(chars) + "\n";
        }
        return string;
    }
    public boolean posable(Tuile tuile, int x, int y, Terrain terrain){
        return (terrain.isCoordinateAvailable(x,y)
                & checkPosableVoisin(tuile,x-1,y,'d' , terrain )
                & checkPosableVoisin(tuile,x+1,y,'g' , terrain )
                & checkPosableVoisin(tuile,x,y-1,'h' , terrain )
                & checkPosableVoisin(tuile,x,y+1,'b' , terrain ))  ;
    }
    private boolean checkPosableVoisin(Tuile tuile, int x, int y, char cote ,Terrain terrain  ){
        if(terrain.isCoordinateOccupied(x,y)){
            return tuile.Collable(terrain.getTuile(x,y), cote);
        }
        return true;
    }
    public abstract void remplirSac(Sac sac);

    boolean intelligenceArtificielle(Tuile tuile, Terrain terrain){
        for (Coordonnes coordonnes: terrain.getCoordonnesDisponibles()
             ) {
            if(this.posable(tuile,coordonnes.x,coordonnes.y,terrain)){
                terrain.poser(tuile,coordonnes.x,coordonnes.y);
                return true;
            };
        }
        return false;
    };
    
    //pour récupérer les coordonnées ou la tuile est posée pour pouvoir l'afficher
    Point IAgraphique(Tuile tuile, Terrain terrain, int nb_cols, int nb_rows){
        for (Coordonnes coordonnes: terrain.getCoordonnesDisponibles()
             ) {
            if(this.posable(tuile,coordonnes.x,coordonnes.y,terrain)){
                terrain.poser(tuile,coordonnes.x,coordonnes.y);
                return new Point(coordonnes.x+nb_cols/2, (-coordonnes.y+nb_rows/2));
            };
        }
        return null;
    };

    abstract int score(Terrain terrain);
    
  
}


