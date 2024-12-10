import java.util.LinkedList;
import java.util.Random;  

public class Sac {
    private final LinkedList<Tuile> sac = new LinkedList<>();
    public LinkedList<Tuile> getSac() {
        return sac;
    }

	public Sac(char nomDuJeu){
		if(nomDuJeu == 'd'){
			(new TuileDomino()).remplirSac(this);
		}
		if(nomDuJeu == 'c'){
			(new TuileCarcassonne()).remplirSac(this);
		}
	}


	public boolean drawable() {
		return this.sac.size()!=0;
	}
    //pour piocher unr tuile de manière aléatoire
    public Tuile piocher() {
		if( this.drawable()) {
			Random r = new Random();
			int indice = r.nextInt(this.sac.size());
			Tuile drawn = this.sac.get(indice);
			//enlève la tuile piochée du sac :
			this.sac.remove(indice);
			return drawn;
		}else return null;
    }


}