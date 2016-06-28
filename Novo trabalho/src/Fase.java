import java.util.ArrayList;

public class Fase {
	
	private int nroFase;
	static ArrayList<Enemy> Enemys;
	
	public Fase(int nroFase) {
		this.nroFase = nroFase;
	}
	
	public static void add(Enemy enemy) {
		Enemys.add(enemy);
	}
}
