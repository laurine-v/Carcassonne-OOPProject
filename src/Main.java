import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Tuile tuileDomino = new TuileCarcassonne();
        tuileDomino.setElementsTuiles(new char[][]{{'5', '*', '*','*', '*'},{'=','=','=','=','='},{'=','=','=','=','='},{'=','=','=','=','='},{'=','=','=','=','='}});

        tuileDomino.getElementsTuiles()[0][0] = '5';
        tuileDomino.getElementsTuiles()[1][0] = '3';
        tuileDomino.getElementsTuiles()[4][1] = '3';
        tuileDomino.getElementsTuiles()[4][4] = '3';
        tuileDomino.getElementsTuiles()[1][1] = '=';
        tuileDomino.getElementsTuiles()[2][2] = '=';
        tuileDomino.getElementsTuiles()[3][3] = '=';

        Tuile tuileDomino2 = new TuileCarcassonne();
        tuileDomino2.setElementsTuiles(new char[][]{{'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'5', '*', '*', '*', '*'}});

        Tuile tuileDomino3 = new TuileCarcassonne();
        tuileDomino3.setElementsTuiles(new char[][]{{'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'*', '+', '+', '+', '+'}});

        Tuile tuileDomino4 = new TuileCarcassonne();
        tuileDomino4.setElementsTuiles(new char[][]{{'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+'}});

        tuileDomino.printTuile();
        tuileDomino2.printTuile();

        System.out.println(tuileDomino.Collable(tuileDomino2,'h'));
        System.out.println(tuileDomino.Collable(tuileDomino2,'b'));
        System.out.println(tuileDomino.Collable(tuileDomino2,'g'));
        System.out.println(tuileDomino.Collable(tuileDomino2,'d'));
        System.out.println();
        System.out.println(tuileDomino2.Collable(tuileDomino,'h'));
        System.out.println(tuileDomino2.Collable(tuileDomino,'b'));
        System.out.println(tuileDomino2.Collable(tuileDomino,'g'));
        System.out.println(tuileDomino2.Collable(tuileDomino,'d'));

        Tuile tuileCarcassonne = new TuileCarcassonne();
        tuileCarcassonne.getElementsTuiles()[0][0] = 'a';
        tuileCarcassonne.getElementsTuiles()[1][0] = 'b';
        tuileCarcassonne.getElementsTuiles()[4][4] = 'c';

        Tuile tuileCarcassonne2 = new TuileCarcassonne();
        tuileCarcassonne2.getElementsTuiles()[4][0] = 'a';

        tuileCarcassonne.printTuile();
        tuileCarcassonne2.printTuile();
        System.out.println(tuileCarcassonne.Collable(tuileCarcassonne2,'h'));
        System.out.println(tuileCarcassonne.Collable(tuileCarcassonne2,'b'));
        System.out.println(tuileCarcassonne.Collable(tuileCarcassonne2,'g'));
        System.out.println(tuileCarcassonne.Collable(tuileCarcassonne2,'d'));

        Coordonnes coordonnes = new Coordonnes(0,0);
        Coordonnes coordonnes1 = new Coordonnes(1,0);
        ArrayList<Coordonnes> coordonnesArrayList= new ArrayList<>();
        coordonnesArrayList.add(coordonnes);
        coordonnesArrayList.add(coordonnes1);
        System.out.println(coordonnesArrayList);
        System.out.println(coordonnesArrayList.get(0));
        System.out.println(coordonnesArrayList.contains(new Coordonnes(1,0)));
        System.out.println(coordonnesArrayList.lastIndexOf(new Coordonnes(1,0)));

        Terrain terrain = new Terrain();
        terrain.poser(tuileDomino,0,0);
        terrain.poser(tuileDomino,3,0);
        terrain.poser(tuileDomino,-1,0);
        terrain.poser(tuileDomino,0,-1);
        terrain.poser(tuileDomino,0,1);
        terrain.poser(tuileDomino,1,0);
        terrain.poser(tuileDomino2,0,1);
        terrain.poser(tuileDomino3,1,1);
        for (int i = 0;i <10;i++){
            terrain.poser(tuileDomino4,2+i,1);
        }
        for (int i = 0;i <5;i++){
            terrain.poser(tuileDomino4,5,i+1);
        }

        for (int i = 0;i >-5;i--){
            terrain.poser(tuileDomino4,5,i+1);
        }

        for (int i = 0;i >-4;i--){
            terrain.poser(tuileDomino4,2+i,2);
        }
        
        //Test du sac pour les dominos
        Sac s = new Sac('d');
        s.piocher().printTuile();
        s.piocher().printTuile();
        s.piocher().printTuile();
        s.piocher().printTuile();
        s.piocher().printTuile();

        //Test du sac pour Carcassonne
        Sac sCarcassonne = new Sac('c');
        sCarcassonne.piocher().printTuile();
        sCarcassonne.piocher().printTuile();
        sCarcassonne.piocher().printTuile();
        sCarcassonne.piocher().printTuile();
        sCarcassonne.piocher().printTuile();
    }
}