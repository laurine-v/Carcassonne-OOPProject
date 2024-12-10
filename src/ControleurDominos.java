import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControleurDominos{

	Jeu jeu;
	JPanelDominos jpd;
	
	ControleurDominos(Jeu jeu, JPanelDominos jpd){
		this.jeu = jeu;
		this.jpd = jpd;
	}
	
	
	public void piocher() {
		jpd.piochee = jeu.sac.piocher();
		jpd.tuile = new JLabel(jpd.afficherTuile(jpd.piochee));
		jpd.rack.add(jpd.tuile, BorderLayout.SOUTH);
		jpd.revalidate();
		jpd.repaint();
		jpd.piocherBouton.setEnabled(false);
		jpd.piocherBouton.setText("<html>Piocher<br>(" + jeu.sac.getSac().size()+")</html>");
	}
	
	public void tourner() {
		jpd.piochee.faireRotation();
		jpd.tuile.setText(jpd.afficherTuile(jpd.piochee));
		jpd.revalidate();
		jpd.repaint();
	}
	
	public void passer() {
		jpd.numJoueur = (jpd.numJoueur+1)%jeu.joueurs.size(); //recommence au joueur 0 quand a fait tous les joueurs
		jpd.tours();
	}
	
}

