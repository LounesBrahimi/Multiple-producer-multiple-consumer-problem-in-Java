public class MainProjet {

	public static void main(String args[]) throws InterruptedException {
		int nombreProducteurs = 5;
		int nombreConsommateurs = 5;
		Thread[] producteurs =  new Thread [nombreProducteurs];
		Thread[] consommateurs =  new Thread [nombreConsommateurs];
	
		Producteur producteur1 = new Producteur("Pomme", 5);
		Producteur producteur2 = new Producteur("Abricot", 8);
		Producteur producteur3 = new Producteur("Ananas", 4);
		Producteur producteur4 = new Producteur("Kiwi", 6);
		Producteur producteur5 = new Producteur("Cerise", 10);
		
		Tapis.capacite += producteur1.getCibleDeProduction()+
			producteur2.getCibleDeProduction()+producteur3.getCibleDeProduction()+
				producteur4.getCibleDeProduction()+producteur5.getCibleDeProduction(); 
	
		Consommateur.compteur = Tapis.capacite;
		
		producteurs[0] = new Thread(producteur1, "P1");
		producteurs[1] = new Thread(producteur2, "P2");
		producteurs[2] = new Thread(producteur3, "P3");
		producteurs[3] = new Thread(producteur4, "P4");
		producteurs[4] = new Thread(producteur5, "P5");
	
		for(int i=0; i<nombreConsommateurs; i++) {
			consommateurs[i] = new Thread(new Consommateur(i+1), "C"+i);
		}
	
		for(int i=0; i<nombreConsommateurs; i++) {
			producteurs[i].start();
		}
	
		for(int i=0; i<nombreConsommateurs; i++) {
			consommateurs[i].start();
		}
	
		for(int i=0; i<nombreConsommateurs; i++) {
			consommateurs[i].join();
		}

		for(int i=0; i<nombreConsommateurs; i++) {
			producteurs[i].join();
		}
		System.out.println("Compteur : "+Consommateur.compteur);
		System.out.println("_____________Fin_____________");
	}
}