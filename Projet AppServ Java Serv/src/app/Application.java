package app;
import java.io.IOException;

import bibliotheque.Abonne;
import bibliotheque.Bibliotheque;
import documents.Livre;
import serveurs.*;

public class Application {

	public static void main(String[] args) {
		Bibliotheque biblio = new Bibliotheque();
		// Ajout d'abonnés
		biblio.addAbonne(new Abonne("Antoine","PAVY", "antoine.pavy@etu.parisdescartes.fr"));
		biblio.addAbonne(new Abonne("Arthur","CAYET", "arthur.cayet@etu.parisdescartes.fr"));
		biblio.addAbonne(new Abonne("Jacques","COUDERC", "jaccouderc@gmail.com"));
		biblio.addAbonne(new Abonne("Pierre","COUDERC", "pierre.couderc.portable@gmail.com"));
		biblio.addAbonne(new Abonne("Jean-François","Brette", "jean-francois.brette@parisdescartes.fr"));
		
		// Ajout de livres
		biblio.addDocument(new Livre("La Peche pour les Nuls","Vincent LALU"));
		biblio.addDocument(new Livre("Kaleb","Myra ELJUNDIR"));
		biblio.addDocument(new Livre("Kaleb II","Myra ELJUNDIR"));
		biblio.addDocument(new Livre("Kaleb III","Myra ELJUNDIR"));
		biblio.addDocument(new Livre("La Passe-Mirroir Livre 1 : Les Fiancï¿½s de l'hiver","Christelle DABOS"));
		biblio.addDocument(new Livre("La Passe-Mirroir Livre 2 : Les Disparus du Clairdelune","Christelle DABOS"));
		biblio.addDocument(new Livre("La Passe-Mirroir Livre 3 : La Mï¿½moire de Babel","Christelle DABOS"));
		biblio.addDocument(new Livre("L'épreuve Livre 1 : Le Labyrinthe","James DASHNER"));
		biblio.addDocument(new Livre("L'épreuve Livre 2 : La Terre Brûlée","James DASHNER"));
		biblio.addDocument(new Livre("L'épreuve Livre 3 : Le Remède Mortel","James DASHNER"));
		biblio.addDocument(new Livre("Le Guide Non Officiel POKÃ‰MON GO","Ivy ST-IVE"));
		biblio.addDocument(new Livre("Pokémon La Grande Aventure 1","Hidenori KUSAKA & MATO"));
		biblio.addDocument(new Livre("Pokémon La Grande Aventure 2","Hidenori KUSAKA & MATO"));
		biblio.addDocument(new Livre("Pokémon La Grande Aventure 3","Hidenori KUSAKA & MATO"));
		biblio.addDocument(new Livre("Harry Potter Ã  l'école des sorciers","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et la Chambre des secrets","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et le Prisonier d'Azkaban","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et la coupe de feu","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et l'Ordre du phénix","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et le Prince de sang-mélé","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et les Reliques de la Mort","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et l'Enfant Maudit","J.K. ROWLING"));
		biblio.addDocument(new Livre("Les Animaux Fantastiques par Norbert Dragonneau","J.K. ROWLING"));
		biblio.addDocument(new Livre("La Politique pour les Nuls","Philipe REINHARD"));
		biblio.addDocument(new Livre("L'Humour pour les Nuls","Gordon ZOLA"));
		biblio.addDocument(new Livre("Alice au pays des merveilles","Walt DISNEY"));
		biblio.addDocument(new Livre("Comment tuer son père","OEdipe"));
		biblio.addDocument(new Livre("La Force pour les Nuls","Maitre YODA"));
		biblio.addDocument(new Livre("Le Guide de la Survie ExtrÃªme","Bear GRYLLS"));
		biblio.addDocument(new Livre("Belle La Coccinelle","Antoon KRINGS"));
		
		System.out.println(biblio.toString());
		
		try {
			new Thread(new Serveur(2500, biblio)).start();
			System.out.println("Serveur reservation demarré sur le port 2500");
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur : " + e);
		}
		
		try {
			new Thread(new Serveur(2600, biblio)).start();
			System.out.println("Serveur emprunt demarré sur le port 2600");
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur : " + e);
		}
		
		try {
			new Thread(new Serveur(2700, biblio)).start();
			System.out.println("Serveur retour demarré sur le port 2700");
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur : " + e);
		}
	}

}
