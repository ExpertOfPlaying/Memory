package memory;

@SuppressWarnings("serial")
public class PlayerNumberTakenException extends Exception {

	public PlayerNumberTakenException(PlayerNumber pn) {
		super("Spielernummer " + pn + " ist bereits vergeben!");
	}
}
