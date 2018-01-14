package bibliotheque;

import java.util.ArrayList;
import java.util.List;

/**
 * Gère les documents et les utilisateurs de la bibliothèque
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public class Bibliotheque {
	private List<Document> docs;
	private List<Abonne> abonnes;
	
	/**
	 * Constructeur de la classe Bibliotheque
	 */
	public Bibliotheque() {
		docs = new ArrayList<Document>();
		abonnes = new ArrayList<Abonne>();
	}
	
	/**
	 * Ajoute un document à la bibliotheque
	 * @param d Le document à ajouter
	 */
	public void addDocument(Document d){
		docs.add(d);
	}
	
	/**
	 * Ajoute un abonné à la bibliothèque
	 * @param a L'abonné à ajouter
	 */
	public void addAbonne(Abonne a){
		abonnes.add(a);
	}

	/**
	 * Donne la liste des documents contenus dans la bibliothèque
	 * @return La liste des documents
	 */
	public List<Document> getDocs() {
		return docs;
	}

	/**
	 * Donne la listes des abonnés à la bibliothèque
	 * @return La liste des abonnés
	 */
	public List<Abonne> getAbonnes() {
		return abonnes;
	}

	@Override
	public String toString() {
		return "La bibliothÃ¨que contient " + this.abonnes.size() + " abonnÃ©s et " + this.docs.size() + " documents.";
	}
	
	
}
