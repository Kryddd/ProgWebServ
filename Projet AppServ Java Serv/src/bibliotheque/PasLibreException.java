package bibliotheque;

public class PasLibreException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public PasLibreException() {
		super();
	}
	
	public PasLibreException(String err) {
		super(err);
	}
}
