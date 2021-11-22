package memory;

public class Player {

	private PlayerNumber pn;
	private int score = 0;
	private String name;
	
	public Player(String user, PlayerNumber pn) {
		this.pn = pn;
		this.name = user;
	}
	
	public Player(String user) {
        this.name = user;
    }
	
	public PlayerNumber getPlayerNumber() {
        return pn;
    }

    public void setPlayerNumber(PlayerNumber pn) {
        this.pn = pn;
    }

    public String getUser() {
        return name;
    }
    
    public void setScore(int score) {
    	this.score = score;
    }
    
    public void updateScore() {
    	score++;
    }
    
    public int getScore() {
    	return score;
    }
}
