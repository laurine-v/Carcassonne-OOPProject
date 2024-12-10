import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class View extends JFrame{
	CardLayout cardlayout = new CardLayout();
	JPanel container = new JPanel(cardlayout);
	
	JPanel menuPanel = new JPanel();
	JPanel parametresPanel = new JPanel();
	
	JButton ExitButton;
	JButton DominoButton = new JButton("Dominos");
	JButton CarcassonneButton = new JButton("Carcassonne");

	JLabel titre = new JLabel("Choisir le nombre de joueurs");
	JButton ValiderButton = new JButton("Valider");
	Integer[] choixJoueurs = {1, 2, 3, 4, 5, 6, 7, 8};
	JComboBox<Integer> nbJoueursButton = new JComboBox<>(choixJoueurs);
	int nbJoueurs;
	ArrayList<Boolean> listeJoueurs = new ArrayList<>(Arrays.asList(false, true));
	JPanel checkBoxPanel = new JPanel();
	
	JPanelDominos dominoPanel;
	JPanelCarcassonne carcassonnePanel;
	
	
	public View() {
		setSize(1840, 1020);

		//gestion des différents JPanels:
		container.add(menuPanel, "Menu");
		
		container.add(parametresPanel, "Paramètres");
		cardlayout.show(container, "Paramètres");
		

		//affichage du panel paramètres (choix du nb et type de joueurs):
		parametresPanel.setLayout(null);
		parametresPanel.setBackground(new Color(228, 226, 203));
		checkBoxPanel.setBackground(new Color(228, 226, 203));
		checkBoxPanel.setBounds(852, 240, 200, 239);
		checkBoxPanel.setPreferredSize(new Dimension(200, 200));
		parametresPanel.add(checkBoxPanel);
		titre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		titre.setBounds(834, 26, 250, 77);
		parametresPanel.add(titre);

		ValiderButton.addActionListener(e -> cardlayout.show(container, "Menu"));
		ValiderButton.setBounds(873, 669, 169, 77);
		parametresPanel.add(ValiderButton);
		nbJoueursButton.setBounds(917, 131, 54, 34);
		
		nbJoueursButton.setSelectedItem(2);
		nbJoueurs = (int) nbJoueursButton.getSelectedItem();

		//appelle la fonction qui affiche les checkboxes pour choisir le type de joueur:
		joueurIA();
		nbJoueursButton.addActionListener(e -> joueurIA());

		//appelle la fonction qui crée les panels jeu en fonction des paramètres passés (par défaut, 2 joueurs dont 1 IA)
		creerPanels();
		
		parametresPanel.add(nbJoueursButton);

		//affichage du panel menu (choix du jeu):
		menuPanel.setBackground(new Color(200, 200, 200));
		getContentPane().add(container, BorderLayout.CENTER);
		menuPanel.setLayout(null);

		ExitButton = new JButton("Exit");
		ExitButton.addActionListener(e -> System.exit(EXIT_ON_CLOSE));
		ExitButton.setBounds(10, 11, 62, 41);
		menuPanel.add(ExitButton);
		
		DominoButton.setFont(new Font("Engravers MT", Font.PLAIN, 10));
		DominoButton.addActionListener(e -> cardlayout.show(container, "Dominos"));
		DominoButton.setBounds(828, 257, 150, 59);
		menuPanel.add(DominoButton);
		
		CarcassonneButton.setFont(new Font("Engravers MT", Font.PLAIN, 10));
		CarcassonneButton.addActionListener(e -> cardlayout.show(container, "Carcassonne"));
		CarcassonneButton.setBounds(828, 348, 150, 59);
		menuPanel.add(CarcassonneButton);
	}
	//création des panels jeu en fonction des paramètres passés:
	public void creerPanels() {
		dominoPanel = new JPanelDominos(nbJoueurs, listeJoueurs);
		carcassonnePanel = new JPanelCarcassonne(nbJoueurs, listeJoueurs);
		container.add(dominoPanel, "Dominos");
		container.add(carcassonnePanel, "Carcassonne");
	}
	
	public void joueurIA() {
		checkBoxPanel.removeAll(); //pour qu'il n'y ait pas des checkboxes en double
		nbJoueurs = (int) nbJoueursButton.getSelectedItem();
		for(int i=0; i<nbJoueurs; i++) {
			//pour chaque joueur, on crée une checkbox pour savoir si c'est une IA ou pas
			JCheckBox isIA = new JCheckBox("Joueur " + i + " est une IA ?", false);
			final int j = i; //doit être final pour l'utiliser dans la classe interne suivante
			listeJoueurs.add(i, false);
			isIA.addItemListener(e -> {
				listeJoueurs.set(j, isIA.isSelected());
				//On recrée un jeu avec les paramètres mis à jour
				creerPanels();
			});
			checkBoxPanel.add(isIA);

		}
		revalidate();
		repaint();
		
	
	}
}
