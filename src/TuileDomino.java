import java.util.LinkedList;
import java.util.Random;

public class TuileDomino extends Tuile {
	private char[][] elementsTuiles;

	public char[][] getElementsTuiles() {
		return elementsTuiles;
	}

	public void setElementsTuiles(char[][] elementsTuiles) {
		this.elementsTuiles = elementsTuiles;
	}

	public TuileDomino() {
		this.elementsTuiles = new char[][]{{'0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
	}

	public TuileDomino(LinkedList<Character> c) {
		this.elementsTuiles = new char[][]{{' ', c.get(0), c.get(1), c.get(2), ' '}, {c.get(3), ' ', ' ', ' ', c.get(4)}, {c.get(5), ' ', ' ', ' ', c.get(6)}, {c.get(7), ' ', ' ', ' ', c.get(8)}, {' ', c.get(9), c.get(10), c.get(11), ' '}};
	}

	public TuileDomino clone() {
		TuileDomino tuileDomino = new TuileDomino();
		int i = 0;
		for (char[] row : this.elementsTuiles) {
			int j = 0;
			for (char paysage : row) {
				tuileDomino.elementsTuiles[i][j] = paysage;
				j++;
			}
			i++;
		}
		return tuileDomino;
	}
	public void remplirSac(Sac sac) {
		int nbTuiles = 50;
		Random r = new Random();
		for(int i=0; i<nbTuiles; i++) {
			LinkedList<Character> c = new LinkedList<>();
			for(int x=0; x<13; x++) {
				c.add(Character.forDigit(r.nextInt(2), 10)); //convertit int en char
			}
			sac.getSac().add(new TuileDomino(c));
		}
	}
	@Override
	int score(Terrain terrain) {
		int score = 0;
		Coordonnes coordonnes = terrain.getCoordonnesOccupied().get(terrain.getCoordonnesOccupied().size() - 1);
		int x= coordonnes.x;
		int y= coordonnes.y;
		if(terrain.isCoordinateOccupied(x+1, y)) {
			Tuile tuileDroite = terrain.getTuile(x+1, y);
			score += Character.getNumericValue(tuileDroite.getElementsTuiles()[1][0]);
			score += Character.getNumericValue(tuileDroite.getElementsTuiles()[2][0]);
			score += Character.getNumericValue(tuileDroite.getElementsTuiles()[3][0]);
		}
		if(terrain.isCoordinateOccupied(x,y+1)) {
			Tuile tuileHaut = terrain.getTuile(x, y+1);
			score += Character.getNumericValue(tuileHaut.getElementsTuiles()[4][1]);
			score += Character.getNumericValue(tuileHaut.getElementsTuiles()[4][2]);
			score += Character.getNumericValue(tuileHaut.getElementsTuiles()[4][3]);
		}
		if(terrain.isCoordinateOccupied(x-1, y)) {
			Tuile tuileGauche = terrain.getTuile(x-1, y);
			score += Character.getNumericValue(tuileGauche.getElementsTuiles()[1][4]);
			score += Character.getNumericValue(tuileGauche.getElementsTuiles()[2][4]);
			score += Character.getNumericValue(tuileGauche.getElementsTuiles()[3][4]);
		}
		if(terrain.isCoordinateOccupied(x, y-1)) {
			Tuile tuileBas = terrain.getTuile(x, y-1);
			score += Character.getNumericValue(tuileBas.getElementsTuiles()[0][1]);
			score += Character.getNumericValue(tuileBas.getElementsTuiles()[0][2]);
			score += Character.getNumericValue(tuileBas.getElementsTuiles()[0][3]);
		}
		return score;
	}
	
	
}
