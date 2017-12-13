package bibliotheque;

public interface Document {
	public int numero();
	public void reserver(Abonne ab) throws PasLibreException;
	void emprunter(Abonne ab) throws PasLibreException;
	public void retour();
}
