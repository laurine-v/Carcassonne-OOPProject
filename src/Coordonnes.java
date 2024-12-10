public class Coordonnes implements Cloneable {

    int x;
    int y;

    Coordonnes(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Coordonnes)){
            return false;
        }
        return (this.x == ((Coordonnes) obj).x && this.y == ((Coordonnes) obj).y);
    }

    @Override
    public Coordonnes clone() {
        return new Coordonnes(this.x, this.y);
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
}
