package memory;

@SuppressWarnings("serial")
public class CardAlreadyPickedException extends Exception {
	public CardAlreadyPickedException(String message) {
		super(message);
	}
}
