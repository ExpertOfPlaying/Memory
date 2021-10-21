package memory;

public class Player {

	private PlayerNumber pn;
	private int score = 0;
	private String user;
	
	public Player(PlayerNumber pn, String user) {
		this.pn = pn;
		this.user = user;
	}
}
