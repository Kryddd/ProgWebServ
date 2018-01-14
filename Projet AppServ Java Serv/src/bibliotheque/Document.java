package bibliotheque;

/**
 * Documents de la bibliothèque
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public interface Document {
	
	/**
	 * Numéro du document
	 * @return Le numéro
	 */
	public int numero();
	
	/**
	 * Permet à un abonné de réserver le document
	 * @param ab Abonné qui souhaite réserver le doc
	 * @throws PasLibreException
	 */
	public void reserver(Abonne ab) throws PasLibreException;
	
	/**
	 * Permet à un abonné d'emprunter le document
	 * @param ab Abonné qui souhaite emprunter le doc
	 * @throws PasLibreException
	 */
	void emprunter(Abonne ab) throws PasLibreException;
	
	/**
	 * Permet de rendre le document à la bibliothèque
	 */
	public void retour();
}
