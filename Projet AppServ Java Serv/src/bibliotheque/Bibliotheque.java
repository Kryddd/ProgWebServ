package bibliotheque;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
	private List<Document> docs;
	private List<Abonne> abonnes;
	
	public Bibliotheque() {
		docs = new ArrayList<Document>();
		abonnes = new ArrayList<Abonne>();
	}
}
