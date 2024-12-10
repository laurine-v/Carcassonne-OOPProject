import java.awt.*;

public class LanceurGraphique {
	
	public static void main(String[] args) {
		EventQueue.invokeLater( () -> {
			View vue = new View();
			vue.setVisible(true);
		});

	}
}
