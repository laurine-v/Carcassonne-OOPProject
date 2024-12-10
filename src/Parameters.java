import java.util.ArrayList;
import java.util.Scanner;

public class Parameters {

	private char nomDuJeu;
	private int nombreDeJoueurs;

	private final ArrayList<Boolean> joueursAreIA = new ArrayList<>();

	private char modeDeJeu;

	public Parameters(){
		choixDuJeu();
		choixDuNombreDeJoueurs();
		choixDuTypeDesJoueurs();
		choixDuModeJeu();
	}

	public char getNomDuJeu() {
		return nomDuJeu;
	}

	public int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}
	public ArrayList<Boolean> getJoueursAreIA() {
		return joueursAreIA;
	}

	public char getModeDeJeu() {
		return modeDeJeu;
	}

	//TODO Réfléchir a comment implémenter les sauvegardes

	public void choixDuJeu(){
		boolean choisi = false;
		while (!choisi) {
			System.out.println("A quel jeu voulez vous jouer ?");
			Scanner scanResponse = new Scanner(System.in);
			String response = scanResponse.next();
			if(response.equalsIgnoreCase("Carcassonne") | response.equalsIgnoreCase("c")){
				this.nomDuJeu = 'c';
				choisi = true;
			}else if(response.equalsIgnoreCase("domino") | response.equalsIgnoreCase("d")){
				this.nomDuJeu = 'd';
				choisi = true;
			}
		}
	}
	public void choixDuNombreDeJoueurs(){
		while (true) {
			System.out.println("Combien de joueurs voulez vous ?");
			Scanner scanResponse = new Scanner(System.in);
			if (scanResponse.hasNextInt()) {
				nombreDeJoueurs = scanResponse.nextInt();
				break;
			}
		}
	}
	public void choixDuTypeDesJoueurs(){
		for (int i = 0; i < nombreDeJoueurs; i++) {
			boolean choisi = false;
			while (!choisi) {
				System.out.println("Le joueur " + i +" est une IA  ?");
				Scanner scanResponse = new Scanner(System.in);
				String response = scanResponse.next();
				if(response.equalsIgnoreCase("Oui") | response.equalsIgnoreCase("o") | response.equalsIgnoreCase("true")){
					joueursAreIA.add(true);
					choisi = true;
				}else if(response.equalsIgnoreCase("Non") | response.equalsIgnoreCase("n") | response.equalsIgnoreCase("0")){
					joueursAreIA.add(false);
					choisi = true;
				}
				//System.out.println(joueursAreIA);

			}
		}
	}

	public void choixDuModeJeu(){
		boolean choisi = false;
		while (!choisi) {
			System.out.println("Quel mode de jeu voulez vous lancer ?");
			Scanner scanResponse = new Scanner(System.in);
			String response = scanResponse.next();
			if(response.equalsIgnoreCase("texte") | response.equalsIgnoreCase("t")){
				this.modeDeJeu = 't';
				choisi = true;
			}else if(response.equalsIgnoreCase("graphique") | response.equalsIgnoreCase("g")){
				this.modeDeJeu = 'g';
				choisi = true;
			}
		}
	}

}

