import java.util.SortedSet;
import java.util.TreeSet;

public class Fase {
	
	private int nroFase;
	static SortedSet <Enemy> Enemys = new TreeSet <Enemy> ();
	
	public Fase(int nroFase) {
		this.nroFase = nroFase;
	}
	
	public void adiciona(Enemy enemy) {
		Enemys.add(enemy);
	}
	
	public int getNroFase() {
		return nroFase;
	}
}
