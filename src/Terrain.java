import java.util.ArrayList;
public class Terrain {
    private final ArrayList<Tuile> tuilesPoses = new ArrayList<>();
    private final ArrayList<Coordonnes> coordonnesOccupied = new ArrayList<>();
    private final ArrayList<Coordonnes> coordonnesDisponibles = new ArrayList<>();
    int minX,minY,maxX,maxY,tailleX,tailleY;
    String[][] tab = new String[(tailleY+1)*5][];
    public Terrain(){
        coordonnesDisponibles.add(new Coordonnes(0,0));
    }
    public boolean isCoordinateAvailable(int x, int y){
        Coordonnes coordinate = new Coordonnes(x,y);
        return coordonnesDisponibles.contains(coordinate);
    }
    public boolean isCoordinateOccupied(int x, int y){
        Coordonnes coordinate = new Coordonnes(x,y);
        return coordonnesOccupied.contains(coordinate);
    }
    Tuile getTuile(int x, int y){
        Coordonnes coordinate = new Coordonnes(x,y);
        return tuilesPoses.get(coordonnesOccupied.lastIndexOf(coordinate)).clone();
    }
    public void poser(Tuile tuile, int x, int y ){
        Coordonnes coordinate = new Coordonnes(x,y);
        System.out.println("-------------------");
        System.out.println(coordinate);

        if(coordonnesOccupied.size()==0||this.getTuile(0,0).posable(tuile,x,y,this)){
            tuilesPoses.add(tuile);
            coordonnesOccupied.add(coordinate);
            updateCoordonnesDisponibles(x,y);
            updateCoordinateAffichage(x,y);
            updateTableau();
            System.out.println("Coordonnées occupées " + this.coordonnesOccupied);
            System.out.println("Coordonnées disponibles " + this.coordonnesDisponibles);
            //TODO score
        }else{
            System.out.println("On ne peut pas poser la tuile");
        }
    }
    private void updateCoordonnesDisponibles(int x, int y){
        coordonnesDisponibles.remove(new Coordonnes(x,y));
        updateOneCoordonneDisponible(x-1,y);
        updateOneCoordonneDisponible(x+1,y);
        updateOneCoordonneDisponible(x,y-1);
        updateOneCoordonneDisponible(x,y+1);
    }
    private void updateOneCoordonneDisponible(int x, int y){
        if(!isCoordinateOccupied(x,y)& !isCoordinateAvailable(x , y)){
            coordonnesDisponibles.add(new Coordonnes(x,y));
        }
    }
    private void updateCoordinateAffichage(int x, int y){
        minX= Math.min(x, minX);
        minY= Math.min(y, minY);
        maxX= Math.max(x, maxX);
        maxY= Math.max(y, maxY);
        tailleX=maxX-minX +2;
        tailleY=maxY-minY +2;
        System.out.println("Coordonnées affichage " + tailleX + "," + tailleY);
    }
    //TODO Simplifier void updateTableau (Trop long)
    public void updateTableau(){
        Tuile[][] tuilesPosesAffichage = new Tuile[tailleY+1][tailleX+1];
        int i=0;
        // On remplit la liste avec les tuiles posées de haut en bas de gauche à droite
        for (Coordonnes coordonnes: coordonnesOccupied) {
            Tuile tuile = tuilesPoses.get(i);
            System.out.println(coordonnes  + " et " +  (tailleY-(coordonnes.y + Math.abs(minY) + 1)) + "," + (coordonnes.x+ Math.abs(minX) +1 ));
            tuilesPosesAffichage[tailleY-(coordonnes.y + Math.abs(minY) + 1)][(coordonnes.x+Math.abs(minX) +1) ] = tuile;
            i++;
        }
        //On place des tuiles vides si pas de tuiles
        for(int x = 0; x < tailleY+1; x++) {
            for(int y = 0; y < tailleX+1 ; y++) {
                if (tuilesPosesAffichage[x][y]==null){
                    tuilesPosesAffichage[x][y] = new TuileCarcassonne();
                }
            }
        }
        //On concatène les tuiles de chaque rangée
        String[][] lignesLignes = new String[tailleY+1][tailleX+1];
        int tailleMargeGauche=Math.max(String.valueOf(minY-1).length(),String.valueOf(maxY+1).length());
        i = 0;
        for (Tuile[] tuiles : tuilesPosesAffichage) {
            String margeGauche = "";
            for (int j = 0; j < tailleMargeGauche-String.valueOf(maxY-i+1).length(); j++) {
                margeGauche += " ";
            }
            margeGauche += maxY-i+1 + "|";
            String[] lignes = new String[]{margeGauche,margeGauche,margeGauche,margeGauche,margeGauche};
            for (Tuile tuile : tuiles) {
                lignes = Tuile.concatenateString(lignes, tuile.getElementsTuiles());
            }
            lignesLignes[i] = lignes;
            i++;
        }
        // On affiche chaque ligne
        String separatorHorizontal = " ";
        for (int m = 0; m < tailleMargeGauche; m++) {
            separatorHorizontal += " ";
        }
        for (int m = 0; m < (tailleX+1)*6; m++) {
            separatorHorizontal += "_";
        }
        tab = new String[(tailleY+1)*5][];
        i=0;
        for (String[] lignes : lignesLignes) {
            for (String ligne : lignes) {
                System.out.println(ligne);
                if(i%5==4){
                System.out.println(separatorHorizontal);
            }
                String[] tabulation = ligne.split("");
                tab[i] = tabulation;
                i++;
            }
        }
        String coordinateDown = " ";
        for (int m = 0; m < tailleMargeGauche; m++) {
            coordinateDown += " ";
        }
        int k = minX-2;
        for (int m = 0; m < tailleX+1; m++) {
                k++;
                coordinateDown += k;
                for (int j = 0; j < 6-String.valueOf(k).length(); j++) {
                    coordinateDown += " ";
            }
        }
        System.out.println(coordinateDown);
    }

    public ArrayList<Coordonnes> getCoordonnesDisponibles() {
        ArrayList<Coordonnes> clone = new ArrayList<>();
        for (Coordonnes coordonate: coordonnesDisponibles
             ) {
            clone.add(coordonate.clone());
        }
        return clone;
    }

    public ArrayList<Coordonnes> getCoordonnesOccupied() {
        ArrayList<Coordonnes> clone = new ArrayList<>();
        for (Coordonnes coordonate: coordonnesOccupied
        ) {
            clone.add(coordonate.clone());
        }
        return clone;
    }
    
    
}
