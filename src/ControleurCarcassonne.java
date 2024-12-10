import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JLabel;

public class ControleurCarcassonne{
	Jeu jeu;
	JPanelCarcassonne jpc;
	JLabel labelTournee;
	
	static int nbRotations = 0;
	

	ControleurCarcassonne(Jeu jeu, JPanelCarcassonne jpc) {
		this.jeu = jeu;
		this.jpc = jpc;
	}
	
	
	public void piocher() {
		jpc.piochee = (TuileCarcassonne) jeu.sac.piocher();
		jpc.tuile = new JLabel(jpc.piochee.getImage());
		jpc.rack.add(jpc.tuile, BorderLayout.SOUTH);
		jpc.revalidate();
		jpc.repaint();
		jpc.piocherBouton.setEnabled(false);
		jpc.piocherBouton.setText("<html>Piocher<br>(" + jeu.sac.getSac().size()+")</html>");
	}
	
	public void tourner() {
		// Rotate the matrix
		jpc.piochee.faireRotation();

		// Rotate the icon
		JLabel label = (JLabel)jpc.rack.getComponent(0);
		Icon icon = label.getIcon();

		if (icon instanceof RotatedIcon){
			((RotatedIcon) icon).setDegrees(((RotatedIcon) icon).getDegrees()+90);
			label.setIcon(icon);
		}
		else { // first time the tile is rotated
			RotatedIcon rotated_icon = new RotatedIcon(icon, 90);
			label.setIcon(rotated_icon);
		}

		jpc.revalidate();
		jpc.repaint();
	}
	
	public void passer() {
		jpc.numJoueur = (jpc.numJoueur+1)%jeu.joueurs.size(); //recommence au joueur 0 quand a fait tous les joueurs
		jpc.tours();
	}
	
	public JLabel getTournee() {
		return this.labelTournee;
	}
	

	

}
