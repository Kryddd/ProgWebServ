package timers;
import java.util.Timer;
import java.util.TimerTask;

import bibliotheque.Document;

public class TimerTaskReservation extends TimerTask{
	private Document docReserve;
	private Timer timerReservation;
	
	public TimerTaskReservation(Document doc, Timer timer){
		this.docReserve = doc;
		this.timerReservation = timer;
	}
	
	@Override
	public void run() {
		docReserve.retour();
		System.out.println("reservation annulé");
		timerReservation.cancel();
	}
	
}
