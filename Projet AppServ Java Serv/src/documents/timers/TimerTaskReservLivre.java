package documents.timers;
import java.util.TimerTask;

import documents.Livre;

/**
 * TimerTask de la r�servation du livre
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public class TimerTaskReservLivre extends TimerTask{
	private Livre livreReserv;
	
	/**
	 * Constructeur de la classe TimerTaskReservLivre
	 * @param livre Le livre r�serv�
	 */
	public TimerTaskReservLivre(Livre livre){
		this.livreReserv = livre;
	}
	
	@Override
	public void run() {
		livreReserv.supprReservation();
		System.out.println("reservation n�" + livreReserv.numero() + " annul�e");
	}
	
}
