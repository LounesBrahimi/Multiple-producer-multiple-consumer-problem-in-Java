/*
 * Class implementant le thread consommateur
 * 
 * @author  BRAHIMI Lounes
 */
public class Consommateur implements Runnable{

	static int compteur;
	private int identifiant;
		
	public Consommateur(int identifiant) {
		this.identifiant = identifiant;
	}
	
	/*
	 * Methode executant le thread
	 */
	@Override
	public void run() {
		while(compteur > 0) {
			try {
				consommer();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Methode utilisee par le run() pour plus de lisibilite
	 */
	public void consommer() throws InterruptedException {
		synchronized (Tapis.fileDePaquets) {
			while (Tapis.estVide()) {
				System.out.println("C"+this.identifiant+" en attente : file #Vide#");
		        (Tapis.fileDePaquets).wait();
		    }
			ETIQUETTE: if (Consommateur.getCompteur() <= 0) {
				Tapis.fileDePaquets.notify();
				break ETIQUETTE;
			}
			else {
			Thread.sleep((long)(Math.random() * 250));
		    System.out.println("C "+this.identifiant+" mange "+ (Tapis.defiler()).getNom());
		    Tapis.fileDePaquets.notify();
		    compteur--; 
			}
		}
	}

	/*
	 * Methode indiquant si le compteur a atteint 0
	 */
	public static boolean consommationEnCours() {
		return compteur > 0;
	}
	
	public static int getCompteur() {
		return compteur;
	}

	public static void setCompteur(int compteur) {
		Consommateur.compteur = compteur;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
}