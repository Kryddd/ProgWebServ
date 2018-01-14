package app;
import java.io.IOException;

import bibliotheque.Abonne;
import bibliotheque.Bibliotheque;
import documents.Livre;
import serveurs.*;

public class Application {

	public static void main(String[] args) {
		Bibliotheque biblio = new Bibliotheque();
		// Ajout d'abonn�s
		biblio.addAbonne(new Abonne("Antoine","PAVY", "antoine.pavy@etu.parisdescartes.fr"));
		biblio.addAbonne(new Abonne("Arthur","CAYET", "arthur.cayet@etu.parisdescartes.fr"));
		biblio.addAbonne(new Abonne("Jacques","COUDERC", "jaccouderc@gmail.com"));
		biblio.addAbonne(new Abonne("Pierre","COUDERC", "pierre.couderc.portable@gmail.com"));
		biblio.addAbonne(new Abonne("Jean-Fran�ois","Brette", "jean-francois.brette@parisdescartes.fr"));
		
		// Ajout de livres
		biblio.addDocument(new Livre("La Peche pour les Nuls","Vincent LALU"));
		biblio.addDocument(new Livre("Kaleb","Myra ELJUNDIR"));
		biblio.addDocument(new Livre("Kaleb II","Myra ELJUNDIR"));
		biblio.addDocument(new Livre("Kaleb III","Myra ELJUNDIR"));
		biblio.addDocument(new Livre("La Passe-Mirroir Livre 1 : Les Fianc�s de l'hiver","Christelle DABOS"));
		biblio.addDocument(new Livre("La Passe-Mirroir Livre 2 : Les Disparus du Clairdelune","Christelle DABOS"));
		biblio.addDocument(new Livre("La Passe-Mirroir Livre 3 : La M�moire de Babel","Christelle DABOS"));
		biblio.addDocument(new Livre("L'�preuve Livre 1 : Le Labyrinthe","James DASHNER"));
		biblio.addDocument(new Livre("L'�preuve Livre 2 : La Terre Br�l�e","James DASHNER"));
		biblio.addDocument(new Livre("L'�preuve Livre 3 : Le Rem�de Mortel","James DASHNER"));
		biblio.addDocument(new Livre("Le Guide Non Officiel POKÉMON GO","Ivy ST-IVE"));
		biblio.addDocument(new Livre("Pok�mon La Grande Aventure 1","Hidenori KUSAKA & MATO"));
		biblio.addDocument(new Livre("Pok�mon La Grande Aventure 2","Hidenori KUSAKA & MATO"));
		biblio.addDocument(new Livre("Pok�mon La Grande Aventure 3","Hidenori KUSAKA & MATO"));
		biblio.addDocument(new Livre("Harry Potter à l'�cole des sorciers","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et la Chambre des secrets","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et le Prisonier d'Azkaban","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et la coupe de feu","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et l'Ordre du ph�nix","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et le Prince de sang-m�l�","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et les Reliques de la Mort","J.K. ROWLING"));
		biblio.addDocument(new Livre("Harry Potter et l'Enfant Maudit","J.K. ROWLING"));
		biblio.addDocument(new Livre("Les Animaux Fantastiques par Norbert Dragonneau","J.K. ROWLING"));
		biblio.addDocument(new Livre("La Politique pour les Nuls","Philipe REINHARD"));
		biblio.addDocument(new Livre("L'Humour pour les Nuls","Gordon ZOLA"));
		biblio.addDocument(new Livre("Alice au pays des merveilles","Walt DISNEY"));
		biblio.addDocument(new Livre("Comment tuer son p�re","OEdipe"));
		biblio.addDocument(new Livre("La Force pour les Nuls","Maitre YODA"));
		biblio.addDocument(new Livre("Le Guide de la Survie Extrême","Bear GRYLLS"));
		biblio.addDocument(new Livre("Belle La Coccinelle","Antoon KRINGS"));
		
		System.out.println(biblio.toString());
		
		try {
			new Thread(new Serveur(2500, biblio)).start();
			System.out.println("Serveur reservation demarr� sur le port 2500");
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur : " + e);
		}
		
		try {
			new Thread(new Serveur(2600, biblio)).start();
			System.out.println("Serveur emprunt demarr� sur le port 2600");
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur : " + e);
		}
		
		try {
			new Thread(new Serveur(2700, biblio)).start();
			System.out.println("Serveur retour demarr� sur le port 2700");
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur : " + e);
		}
	}

}
