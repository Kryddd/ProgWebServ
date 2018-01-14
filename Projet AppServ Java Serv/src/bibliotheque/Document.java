package bibliotheque;

/**
 * Documents de la biblioth�que
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public interface Document {
	
	/**
	 * Num�ro du document
	 * @return Le num�ro
	 */
	public int numero();
	
	/**
	 * Permet � un abonn� de r�server le document
	 * @param ab Abonn� qui souhaite r�server le doc
	 * @throws PasLibreException
	 */
	public void reserver(Abonne ab) throws PasLibreException;
	
	/**
	 * Permet � un abonn� d'emprunter le document
	 * @param ab Abonn� qui souhaite emprunter le doc
	 * @throws PasLibreException
	 */
	void emprunter(Abonne ab) throws PasLibreException;
	
	/**
	 * Permet de rendre le document � la biblioth�que
	 */
	public void retour();
}
