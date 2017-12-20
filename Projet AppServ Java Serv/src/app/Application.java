package app;
import java.io.IOException;

import bibliotheque.Abonne;
import bibliotheque.Bibliotheque;
import documents.Livre;
import serveurs.*;

public class Application {

	public static void main(String[] args) {
		Bibliotheque Biblio = new Bibliotheque();
		// Ajout d'abonnés
		Biblio.addAbonne(new Abonne("Antoine","PAVY"));
		Biblio.addAbonne(new Abonne("Arthur","CAYET"));
		Biblio.addAbonne(new Abonne("Jacques","COUDERC"));
		Biblio.addAbonne(new Abonne("Ash","KETCHUM"));
		Biblio.addAbonne(new Abonne("Emmanuel","MACRON"));
		Biblio.addAbonne(new Abonne("Peter","PAN"));
		Biblio.addAbonne(new Abonne("Kylo","REN"));
		Biblio.addAbonne(new Abonne("Anakin","SKYWALKER"));
		
		// Ajout de livres
		Biblio.addDocument(new Livre("La Pêche pour les Nuls","Vincent LALU"));
		Biblio.addDocument(new Livre("Kaleb","Myra ELJUNDIR"));
		Biblio.addDocument(new Livre("Kaleb II","Myra ELJUNDIR"));
		Biblio.addDocument(new Livre("Kaleb III","Myra ELJUNDIR"));
		Biblio.addDocument(new Livre("La Passe-Mirroir Livre 1 : Les Fiancés de l'hiver","Christelle DABOS"));
		Biblio.addDocument(new Livre("La Passe-Mirroir Livre 2 : Les Disparus du Clairdelune","Christelle DABOS"));
		Biblio.addDocument(new Livre("La Passe-Mirroir Livre 3 : La Mémoire de Babel","Christelle DABOS"));
		Biblio.addDocument(new Livre("L'Épreuve Livre 1 : Le Labyrinthe","James DASHNER"));
		Biblio.addDocument(new Livre("L'Épreuve Livre 2 : La Terre Brûlée","James DASHNER"));
		Biblio.addDocument(new Livre("L'Épreuve Livre 3 : Le Remède Mortel","James DASHNER"));
		Biblio.addDocument(new Livre("Le Guide Non Officiel POKÉMON GO","Ivy ST-IVE"));
		Biblio.addDocument(new Livre("Pokémon La Grande Aventure 1","Hidenori KUSAKA & MATO"));
		Biblio.addDocument(new Livre("Pokémon La Grande Aventure 2","Hidenori KUSAKA & MATO"));
		Biblio.addDocument(new Livre("Pokémon La Grande Aventure 3","Hidenori KUSAKA & MATO"));
		Biblio.addDocument(new Livre("Harry Potter à l'école des sorciers","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Harry Potter et la Chambre des secrets","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Harry Potter et le Prisonier d'Azkaban","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Harry Potter et la coupe de feu","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Harry Potter et l'Ordre du phénix","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Harry Potter et le Prince de sang-mêlé","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Harry Potter et les Reliques de la Mort","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Harry Potter et l'Enfant Maudit","J.K. ROWLING"));
		Biblio.addDocument(new Livre("Les Animaux Fantastiques par Norbert Dragonneau","J.K. ROWLING"));
		Biblio.addDocument(new Livre("La Politique pour les Nuls","Philipe REINHARD"));
		Biblio.addDocument(new Livre("",""));
		Biblio.addDocument(new Livre("",""));
		Biblio.addDocument(new Livre("",""));
		Biblio.addDocument(new Livre("",""));
		Biblio.addDocument(new Livre("",""));
		Biblio.addDocument(new Livre("",""));
		
		
		
		try {
			new Thread(new ServeurReservation(2500, Biblio)).start();
			new Thread(new ServeurEmprunt(2600, Biblio)).start();
			new Thread(new ServeurRetour(2700, Biblio)).start();
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur :" + e);
		}
	}

}
