package bibliotheque;

import java.util.ArrayList;
import java.util.List;

public class Abonne {
	private static int cptAbonne = 0;
	private int numero;
	private String prenom;
	private String nom;
	private List<Integer> emprunt;
	private List<Integer> reservation;
	private static final int NB_MAX_RES_EMPR = 5;
	
	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public Abonne(String prenom, String nom) {
		this.numero = cptAbonne++;
		this.prenom = prenom;
		this.nom = nom;
		this.emprunt = new ArrayList<Integer>();
		this.reservation = new ArrayList<Integer>();		
	}
	
	public int numero() {
		return this.numero;
	}
	
	public List<Integer> getReservation() {
		return reservation;
	}
	
	public int addReservation(int numeroLivre) {
		if (this.reservation.size() < NB_MAX_RES_EMPR) {
			this.reservation.add(numeroLivre);
			return 0;
		}else {
			return -1;
		}
	}
	
	public void delReservation(int numeroLivre) {
		for (int i = 0; i < this.reservation.size(); i++) {
			if (numeroLivre == this.reservation.get(i)) {
				this.reservation.remove(i);
			}
		}
	}
	
	public List<Integer> getEmprunt() {
		return emprunt;
	}

	public int addEmprunt(int numeroLivre) {
		if (this.emprunt.size() < NB_MAX_RES_EMPR) {
			this.emprunt.add(numeroLivre);
			return 0;
		}else {
			return -1;
		}
	}	
	
	public void delEmprunt(int numeroLivre) {
		for (int i = 0; i < this.emprunt.size(); i++) {
			if (numeroLivre == this.emprunt.get(i)) {
				this.emprunt.remove(i);
			}			
		}
	}
}
