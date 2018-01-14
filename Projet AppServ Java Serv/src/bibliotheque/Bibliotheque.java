package bibliotheque;

import java.util.ArrayList;
import java.util.List;

/**
 * G�re les documents et les utilisateurs de la biblioth�que
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
	 * Ajoute un document � la bibliotheque
	 * @param d Le document � ajouter
	 */
	public void addDocument(Document d){
		docs.add(d);
	}
	
	/**
	 * Ajoute un abonn� � la biblioth�que
	 * @param a L'abonn� � ajouter
	 */
	public void addAbonne(Abonne a){
		abonnes.add(a);
	}

	/**
	 * Donne la liste des documents contenus dans la biblioth�que
	 * @return La liste des documents
	 */
	public List<Document> getDocs() {
		return docs;
	}

	/**
	 * Donne la listes des abonn�s � la biblioth�que
	 * @return La liste des abonn�s
	 */
	public List<Abonne> getAbonnes() {
		return abonnes;
	}

	@Override
	public String toString() {
		return "La bibliothèque contient " + this.abonnes.size() + " abonnés et " + this.docs.size() + " documents.";
	}
	
	
}
