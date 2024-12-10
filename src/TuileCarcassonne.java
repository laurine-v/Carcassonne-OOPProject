import javax.swing.Icon;
import javax.swing.ImageIcon;

public class TuileCarcassonne extends Tuile{
	private ImageIcon image;
	private char[][] elementsTuiles;
	private char[][] pions = new char[5][5];
	public TuileCarcassonne(){
		this.elementsTuiles=  new char[][]{{'0','0','0','0','0'},{'0','0','0','0','0'},{'0','0','0','0','0'},{'0','0','0','0','0'},{'0','0','0','0','0'}};
	}
	public TuileCarcassonne(char[]c) {
		this.elementsTuiles=  new char[][]{{'p', c[0], c[1],c[2],'p'},{c[3],c[4],c[5],c[6],c[7]},{c[8],c[9],c[10],c[11],c[12]}, {c[13],c[14],c[15],c[16],c[17]},{'p',c[18],c[19],c[20],'p'}};
	}
	public char[][] getElementsTuiles() {
		return elementsTuiles;
	}
	public  void setElementsTuiles(char[][] elementsTuiles) {
		this.elementsTuiles = elementsTuiles;
	}
	public char[][] getPions() {
		return pions;
	}
	public void setPions(char[][] pions) {
		this.pions = pions;
	}
	public TuileCarcassonne clone() {
		TuileCarcassonne tuileCarcassonne = new TuileCarcassonne();
		int i = 0;
		for (char[] row: this.elementsTuiles) {
			int j = 0;
			for (char paysage: row) {
				tuileCarcassonne.elementsTuiles[i][j]=paysage;
				j++;
			}
			i++;
		}
		return tuileCarcassonne;
	}
	public void remplirSac(Sac sac) {
		// Nombre d’occurences pour chacune des 24 tuiles
		int[] nb_of_tiles = {9, 3, 2, 1, 1, 3, 3, 8, 4, 5, 2, 3, 4, 2, 3, 2, 4, 3, 1, 2, 1, 1, 2, 3};

		// Chaque ligne correspond à une tuile
		// Les propriétés de chaque tuile sont représenté par des charactère, qui sont ensuite utilisés pour en faire
		// un tableau représentant la tuile physique
		char[][] tiles_feature = {
		{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'c', 'c', 'c', 'p', 'p', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'c', 'c', 'c', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'c', 'c', 'c', 'q', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'p', 'p', 'p'},
		{'q', 'q', 'q', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'c', 'c', 'c', 'q', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'p', 'p', 'p', 'p', 'p', 'c', 'c', '*', 'c', 'c', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'p', 'c', 'p', 'p', 'p', 'c', 'p', 'p', 'p', 'p', 'c', 'p', 'p', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'c', 'c', '*', 'c', 'c', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
		{'q', 'q', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p'},
		{'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'p', 'p', 'p'},
		{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'a', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
		{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'a', 'p', 'p', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
		{'p', 'p', 'p', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'p', 'p', 'p'},
		{'q', 'q', 'q', 'p', 'p', 'p', 'p', 'p', 'c', 'c', 'c', 'c', 'c', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
		{'q', 'q', 'q', 'p', 'p', 'p', 'p', 'p', 'c', 'c', 'c', 'p', 'p', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'p', 'p', 'p', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'p', 'p', 'p'},
		{'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q'},
		{'p', 'c', 'p', 'p', 'p', 'c', 'p', 'p', 'c', 'c', '*', 'c', 'c', 'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
		{'q', 'q', 'q', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
		{'p', 'p', 'p', 'q', 'p', 'p', 'p', 'q', 'q', 'p', 'p', 'p', 'q', 'q', 'p', 'p', 'p', 'q', 'p', 'p', 'p'}};


		for(int i=0; i<nb_of_tiles.length; i++) {
			TuileCarcassonne tile = new TuileCarcassonne(tiles_feature[i]);
			tile.image = new ImageIcon("src/tiles_images/" + (i + 1) + ".png");
			for(int j=0; j<nb_of_tiles[i]; j++){
				sac.getSac().add(tile);}
		}

	}

	public Icon getImage() {
		return this.image;
	}

	@Override
	int score( Terrain terrain) {
		return 0;
	}
}
