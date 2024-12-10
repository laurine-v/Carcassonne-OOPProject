import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JPanelCarcassonne extends JPanel{

	Jeu jeu;
	//TODO: faire un jpanel jeu abstrait (ou plutôt interface?) dont héritent jpanelDominos et JPanelCarcassonne,
	//avec les boutons tourner, piocher, passer. Utiliser le même contrôleur pour les 2 ?
	
	//structure vue-contrôleur 
	ControleurCarcassonne cc;
	
	JButton piocherBouton = new JButton("Piocher");
	JButton tournerBouton = new JButton("Tourner");
	JButton passerBouton = new JButton("Passer");
	JTextArea nbToursArea = new JTextArea();
	
	
	
	JPanel menu = new JPanel();
	JPanel rack = new JPanel();
	JPanel terrainPanel = new JPanel();

	TuileCarcassonne piochee;
	
	JLabel tuile;
	
	JLabel centralLabel = new JLabel();

	int numJoueur = 0;
	
	int nb_rows = 10;
	int nb_cols = 20;
	
	
	TuileCarcassonne centrale;
	int nb_tour = 1;
	
	public JPanelCarcassonne(int nbJoueurs, ArrayList<Boolean> listeJoueurs) {

		//System.out.println("dominos "+nbJoueurs + ""+listeJoueurs);
		
		jeu = new Jeu('c', nbJoueurs, listeJoueurs);
		cc = new ControleurCarcassonne(jeu, this);
		
		//Gestion de l'affichage:
		centrale = (TuileCarcassonne) jeu.sac.piocher();
		
		setLayout(new BorderLayout());
		menu.setPreferredSize(new Dimension(200, 100));
		menu.setBackground(new Color(0, 0, 0));
		add(menu, BorderLayout.SOUTH);
		menu.setLayout(null);
		
		piocherBouton.setPreferredSize(new Dimension(250, 100));
		piocherBouton.setBounds(10, 30, 101, 59);
		tournerBouton.setPreferredSize(new Dimension(250, 100));
		tournerBouton.setBounds(120, 30, 101, 59);
		passerBouton.setPreferredSize(new Dimension(250, 100));
		passerBouton.setBounds(230, 30, 101, 59);
		
		nbToursArea.setPreferredSize(new Dimension(250, 100));
		nbToursArea.setBounds(550, 30, 100, 59);
		
		
		menu.add(piocherBouton);
		menu.add(tournerBouton);
		menu.add(passerBouton);
		menu.add(nbToursArea);
		rack.setBounds(700, 0, 1980-227, 100);
		menu.add(rack);
		rack.setBackground(new Color(192, 192, 192));
		rack.setPreferredSize(new Dimension(50, 50));
		

		nbToursArea.setText("tour" + nb_tour);
		
		
		//Gestion des boutons:
		piocherBouton.addActionListener(( event) -> {cc.piocher();});
		
		tournerBouton.addActionListener(( event) -> {cc.tourner();});
		
		passerBouton.addActionListener(( event) -> {cc.passer();});

		
		//initialisation du terrain
		this.initialiserTerrain();
		
		nbToursArea.setText(" tour" + nb_tour + "\n " + jeu.joueurs.get(numJoueur).getNom());
				
	}
	
	public void initialiserTerrain() {

		piocherBouton.setEnabled(true);
		rack.removeAll();
		
		add(terrainPanel, BorderLayout.CENTER);
		terrainPanel.setLayout(new GridLayout(nb_rows, nb_cols));
		
		for (int row = 0; row < nb_rows; row++) {
	         for (int col = 0; col < nb_cols; col++) {
	        	 
	            if(row == nb_rows/2 && col == nb_cols/2) {   //tuile de départ au centre
	            	
	            	centralLabel.setIcon(centrale.getImage());
	            	//centralLabel.setText(afficherTuile(centrale));	            	
	            	terrainPanel.add(centralLabel);
	            	jeu.terrain.poser(centrale,0,0);
	            }
	            else {
	            	final int x = col;  //doit être final pour pouvoir l'utiliser dans la calsse interne de mouseclicked
	            	final int y = row;
	        
		            JLabel zoneTuile = new JLabel();
		            
		            zoneTuile.setPreferredSize(new Dimension(15, 15));

		            terrainPanel.add(zoneTuile);	            
		            
		            
		            zoneTuile.addMouseListener(new MouseAdapter() {
		            	@Override
		                public void mouseClicked(MouseEvent event) {
		            		if(piochee.posable(piochee, convertX(x,nb_cols), convertY(y, nb_rows), jeu.terrain)) { //TODO: convertir les coordonnées
		            			jeu.terrain.poser(piochee, convertX(x,nb_cols), convertY(y, nb_rows));
		            			zoneTuile.setIcon(((JLabel)rack.getComponent(0)).getIcon());
			    				repaint();
			    				jeu.joueurs.get(numJoueur).setScore(piochee.score(jeu.terrain));
			    				numJoueur = (numJoueur+1)%jeu.joueurs.size();
			    				tours();
			    				//pour qu'on ne puisse plus cliquer sur une tuile posée :
			    				zoneTuile.removeMouseListener(zoneTuile.getMouseListeners()[0]);

		            		}
		            		else {
		            			zoneTuile.setText("!");
		            		}
		            		
		                }
	            });    	           
	            }
	         }
	      }
		//On appelle tours ici au cas où le 1er joueur est une IA
		tours();
	}
	
	public void tours() {
		String score = "";
		if(jeu.sac.drawable()) {		
			
			if(!jeu.joueurs.get(numJoueur).isIA()) {
				nbToursArea.setText(" tour" + nb_tour + "\n " + jeu.joueurs.get(numJoueur).getNom());
				for(Joueur joueur: jeu.joueurs) {
					score += " score du " + joueur.getNom() + " : " + joueur.getScore() + "\n";
				}
				jeu.terrain.updateTableau();
				piocherBouton.setEnabled(true);
				rack.removeAll();
				repaint();
				
			}
			
			else {				
				piocherBouton.setText("<html>Piocher<br>(" + jeu.sac.getSac().size()+")</html>");
				TuileCarcassonne piochee = (TuileCarcassonne) jeu.sac.piocher();
				Point p = piochee.IAgraphique(piochee,jeu.terrain, nb_cols, nb_rows);
				if(p != null){
					if((p.y * nb_cols + p.x) >=0 && ((p.y * nb_cols + p.x)) < terrainPanel.getComponentCount()) {							
						jeu.joueurs.get(numJoueur).setScore(piochee.score(jeu.terrain));
						JLabel labelTuile = ((JLabel) terrainPanel.getComponent(p.y * nb_cols + p.x));
						labelTuile.setIcon(piochee.getImage());
						//pour qu'on ne puisse plus cliquer sur une tuile posée :
						labelTuile.removeMouseListener(labelTuile.getMouseListeners()[0]);
					}
				}
				for(Joueur joueur: jeu.joueurs) {
					score += " score du " + joueur.getNom() + " : " + joueur.getScore() + "\n";
				}
				numJoueur = (numJoueur+1)%jeu.joueurs.size();
				tours();
			}
			if(numJoueur == jeu.joueurs.size()-1) { //quand tous les joueurs ont joué
				nb_tour++;
			}
		}
		else { //fin du jeu
			//On affiche les scores:
			for(Joueur joueur: jeu.joueurs) {
				score += " score du " + joueur.getNom() + " : " + joueur.getScore() + "\n";
			}
			
			Joueur gagnant = jeu.joueurs.get(0);
			for(Joueur j: jeu.joueurs) {
				if(j.getScore() > gagnant.getScore()) {
					gagnant = j;
				}
			}
			JLabel victoire = new JLabel("Victoire de " + gagnant.getNom() + " !");
			victoire.setFont(new Font("Arial", Font.BOLD, 40));
			rack.add(victoire);
			piocherBouton.setText("<html>Piocher<br>(" + jeu.sac.getSac().size()+")</html>");
			piocherBouton.setEnabled(false);
			tournerBouton.setEnabled(false);
			passerBouton.setEnabled(false);
			repaint();
		}
		
			
	}
	
	public int convertX(int x, int taille) {
    	return x-taille/2;
    }
   
	 public int convertY(int y, int taille) {	 
	    return -(y-taille/2);
	 }
	 
//	 public String afficherTuile(Tuile t) {
//		 return "<html><div style='display: flex; justify-content: center; align-items: center; width: 30px; height: 50px; border: 1px solid black;'>"
//		 		+ "<pre>" + t.toString() + "</pre></div></html>";
//	 }
	
	
}


