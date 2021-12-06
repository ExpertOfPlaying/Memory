package memory;

@SuppressWarnings("serial")
public class PlayerNumberTakenException extends Exception {

	public PlayerNumberTakenException(PlayerNumber pn) {
		super("Playernumber " + pn + " has already been chosen!");
	}
}
