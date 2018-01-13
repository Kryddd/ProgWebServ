package timers;
import java.util.TimerTask;

import documents.Livre;

public class TimerTaskReservLivre extends TimerTask{
	private Livre docReserve;
	
	public TimerTaskReservLivre(Livre doc){
		this.docReserve = doc;
	}
	
	@Override
	public void run() {
		docReserve.supprReservation();
		System.out.println("reservation n°" + docReserve.numero() + " annulée");
	}
	
}
