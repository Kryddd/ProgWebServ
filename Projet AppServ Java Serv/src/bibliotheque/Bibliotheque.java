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
	
	public void addDocument(Document d){
		docs.add(d);
	}
	
	public void addAbonne(Abonne a){
		abonnes.add(a);
	}

	public List<Document> getDocs() {
		return docs;
	}

	public List<Abonne> getAbonnes() {
		return abonnes;
	}
	
	
}
