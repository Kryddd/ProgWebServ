package documents.timers;

import java.util.TimerTask;

import documents.Livre;

public class TimerTaskEmpruntLivre extends TimerTask {
	
	private Livre livreEmprunt;

	public TimerTaskEmpruntLivre(Livre livre) {
		this.livreEmprunt = livre;
	}

	@Override
	public void run() {
		livreEmprunt.setEnRetard();
		System.out.println("Emprunt " + livreEmprunt.numero() + " en retard");
	}

}
