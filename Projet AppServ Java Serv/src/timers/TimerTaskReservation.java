package timers;
import java.util.TimerTask;

import bibliotheque.Document;

public class TimerTaskReservation extends TimerTask{
	private Document docReserve;
	
	public TimerTaskReservation(Document doc){
		this.docReserve = doc;
	}
	
	@Override
	public void run() {
		docReserve.retour();
		System.out.println("reservation n°" + docReserve.numero() + " annulée");
	}
	
}
