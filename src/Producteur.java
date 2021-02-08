/*
 * Class implementant le thread producteur
 * 
 * @author  BRAHIMI Lounes
 */
public class Producteur implements Runnable{

	private String nomDeProduit;
	private int cibleDeProduction;
	
	public Producteur(String nomDeProduit, int cibleDeProduction) {
		this.nomDeProduit = nomDeProduit;
		this.cibleDeProduction = cibleDeProduction;
	}
	
	/*
	 * Methode executant le thread
	 */
	@Override
	public void run() {
		while (Consommateur.consommationEnCours()) {
			for(int i=0; i<this.cibleDeProduction; i++) {
				try {
					produire(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Methode utilisee par le run() pour plus de lisibilite
	 */
	public void produire(int indice) throws InterruptedException {
		synchronized (Tapis.fileDePaquets) {
			
		       while (Tapis.estPlein()) {
		             System.out.println(this.nomDeProduit+" en attente : file #Pleine#");
		             (Tapis.fileDePaquets).wait();
		         }
		       ETIQUETTE: if (!Consommateur.consommationEnCours()) {
					Tapis.fileDePaquets.notify();
					break ETIQUETTE;
				}
		       else {
		       Paquet nouveauPaquet = new Paquet(this.nomDeProduit + " " + (1+indice)); 
		       System.out.println("Production : " + nouveauPaquet.getNom());
		       
		       Tapis.fileDePaquets.add(nouveauPaquet);
		         Thread.sleep((long)(Math.random() * 250));
		         (Tapis.fileDePaquets).notify();
		       }
		     }
	}
	
	public String getNomDeProduit() {
		return nomDeProduit;
	}

	public void setNomDeProduit(String nomDeProduit) {
		this.nomDeProduit = nomDeProduit;
	}

	public int getCibleDeProduction() {
		return cibleDeProduction;
	}

	public void setCibleDeProduction(int cibleDeProduction) {
		this.cibleDeProduction = cibleDeProduction;
	}
}