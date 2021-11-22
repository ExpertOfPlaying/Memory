package memory;

public class Player {

	private PlayerNumber pn;
	private int score = 0;
	private String user;
	
	public Player(String user, PlayerNumber pn) {
		this.pn = pn;
		this.user = user;
	}
	
	public Player(String user) {
        this.user = user;
    }
	
	public PlayerNumber getPlayerNumber() {
        return pn;
    }

    public void setPlayerNumber(PlayerNumber pn) {
        this.pn = pn;
    }

    public String getUser() {
        return user;
    }
}
