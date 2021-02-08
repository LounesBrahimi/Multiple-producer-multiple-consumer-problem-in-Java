import java.util.LinkedList;
import java.util.Queue;
/*
 * Class representant le tapie qui contient les produits
 * 
 * @author  BRAHIMI Lounes
 */
public class Tapis {

	static Queue<Paquet> fileDePaquets = new LinkedList<>();
	static int capacite = 0;
	
	public Tapis() {
	}
	
	/*
	 * Methode permettant d'enfiler une valeur dans la file
	 */
	public static void enfiler(Paquet nouveauPaquet) {
		if (!estPlein()) {
			fileDePaquets.add(nouveauPaquet);
		}
	}
	
	/*
	 * Methode permettant de defiler la valeur en tete de la file
	 */
	public static Paquet defiler() {
		if (!estVide()) {
			Paquet paquet = fileDePaquets.remove();
			return paquet;
		}
		return null;
	}
	
	/*
	 * Methode indiquant si la file est pleine
	 */
	public static boolean estPlein() {
		return fileDePaquets.size() == capacite;
	}
	
	/*
	 * Methode indiquant si la file est vide
	 */
	public static boolean estVide() {
		return (fileDePaquets.size() == 0);
	}
	
	public Queue<Paquet> getFileDePaquets() {
		return fileDePaquets;
	}

	public int getCapacite() {
		return capacite;
	}
}