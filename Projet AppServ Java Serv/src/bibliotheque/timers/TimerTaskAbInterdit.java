package bibliotheque.timers;

import java.util.TimerTask;

import bibliotheque.Abonne;

public class TimerTaskAbInterdit extends TimerTask {
	
	private Abonne abInterdit;

	public TimerTaskAbInterdit(Abonne abonne) {
		this.abInterdit = abonne;
	}

	@Override
	public void run() {
		abInterdit.annuleInterdit();

	}

}
