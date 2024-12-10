import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
public class Jeu {
    final LinkedList<Joueur> joueurs = new LinkedList<>();
    final Sac sac;
    final Terrain terrain;
    public Jeu(char nomDuJeu, int nombreDeJoueurs, ArrayList<Boolean> joueursAreIA){
        //Initialisation des joueurs
        for (int n = 0; n < nombreDeJoueurs ; n++) {
            joueurs.add(new Joueur("Joueur "+ n, joueursAreIA.get(n)));
        }
        //Initialisation terrain
        this.terrain = new Terrain();
        //initialisation du sac
        this.sac = new Sac(nomDuJeu);
//        this.terrain.poser(this.sac.piocher(),0,0);
//        try {partie();}catch (InterruptedException e){
//            System.out.println("InterruptedException");
//        }
    }
    public void partie() throws InterruptedException {
        int tour = 0;
        while (sac.drawable()){
            tour ++;
            System.out.println("-------------------Tour " + tour + "-------------------");
            for (Joueur joueur: joueurs
            ) {
                System.out.println(joueur.getNom() + " score: " + joueur.getScore());
                // Pioche une tuile
                if(sac.drawable() & joueur.isPlaying() ){
                    System.out.println(sac.getSac().size());
                    joueur.addMain(sac.piocher());
                    joueur.printMain();
                    if(!joueur.isIA()){
                        if(modeTexte(joueur)){
                            joueur.setScore(terrain.getTuile(0,0).score(terrain));
                        };
                        joueur.setMain(null);
                    }else {
                        if(terrain.getTuile(0,0).intelligenceArtificielle(joueur.getMain(),terrain)){
                            joueur.setScore(terrain.getTuile(0,0).score(terrain));
                        };
                        //Thread.sleep(2000);
                    }

                }
                System.out.println(joueur.getNom() + " score: " + joueur.getScore());


            }

        }

    }
    public boolean modeTexte(Joueur joueur) {
        boolean tuilePosee = false;
        boolean played = false;
        while (!played && joueur.isPlaying()) {
            System.out.println("Faites une action");
            Scanner scanResponse = new Scanner(System.in);
            String response = scanResponse.next();
            //Rotation
            if (response.equalsIgnoreCase("retourne")
                    | response.equalsIgnoreCase("r")
                    | response.equalsIgnoreCase("rotation")) {
                joueur.getMain().faireRotation();
                joueur.getMain().printTuile();
            } //ABANDON
            else if (response.equalsIgnoreCase("a")
                    | response.equalsIgnoreCase("abandonne")
                    | response.equalsIgnoreCase("give up")) {
                joueur.setAbandonne(true);
            } //POSER
            else if (response.equalsIgnoreCase("poser")
                    | response.equalsIgnoreCase("p")) {
                System.out.println("entrer x");
                scanResponse = new Scanner(System.in);
                if (scanResponse.hasNextInt()) {
                    int x = scanResponse.nextInt();
                    System.out.println("entrer y");
                    scanResponse = new Scanner(System.in);
                    int y = scanResponse.nextInt();
                    if( terrain.getTuile(0,0).posable(joueur.getMain(),x,y,terrain)){
                        terrain.poser(joueur.getMain(),x,y);
                        played = true;
                    }else {
                        System.out.println("Impossible de poser la tuile");
                }
                }
            } //SKIP
            else if (response.equalsIgnoreCase("s")
                    | response.equalsIgnoreCase("passer")
                    | response.equalsIgnoreCase("skip")) {
                played = true;
            }
        }
        return tuilePosee;
    }
}
