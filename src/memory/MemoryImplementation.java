package memory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The implementation of the game Memory
 * 
 * @author Alexander Yurovskyy
 * @version 2.0
 */
public class MemoryImplementation implements MemoryInterface {

	private Player user1;
	private Player user2;
	private int turn;
	private int cardPairs;
	private int pickOne;
	private int pickTwo;
	private List<Character> totalCards = new LinkedList<Character>();
	private List<Player> playerList = new LinkedList<>();

	public MemoryImplementation(String user1, String user2) {
		this.pickOne = -1;
		this.user1 = new Player(user1);
		this.user2 = new Player(user2);
		playerList.add(this.user1);
		playerList.add(this.user2);
		turn = 1;
	}

	public List<Player> getPlayer() {
		return playerList;
	}

	@Override
	public PlayerNumber choosePlayerNumber(Player p, PlayerNumber pn)
			throws PlayerNumberTakenException, StateException {
		if (p.getPlayerNumber() != null) {
			throw new StateException(p.getUser() + " hat bereits" + p.getUser() + " gewaehlt!");
		} else if (p == user1 && user2.getPlayerNumber() == pn || p == user2 && user1.getPlayerNumber() == pn) {
			throw new PlayerNumberTakenException(pn);
		}
		p.setPlayerNumber(pn);
		return pn;
	}

	@Override
	public void setNumberOfCardPairs(int pairs) throws IllegalArgumentException {
		System.out.println("Please enter the number of card pairs you would like to play with.");
		if (pairs > 0) {
			this.cardPairs = pairs;
		} else {
			throw new IllegalArgumentException("There must be at least one pair!");
		}
		char value = 'a';
		for (int i = 1; i <= cardPairs * 2; i++) {
			totalCards.add(value);
			if (i % 2 == 0) {
				value++;
			}
		}
		Collections.shuffle(totalCards);
	}

	@Override
	public int getNumberOfCardPairs() {
		return cardPairs;
	}

	@Override
	public void prepareNextRound(Player p) throws GameOverException {
		if (totalCards.get(pickOne) == totalCards.get(pickTwo)) {
			p.updateScore();
			totalCards.set(pickOne, '?');
			totalCards.set(pickTwo, '?');
		}
		if (playerList.get(0).getScore() + playerList.get(1).getScore() == cardPairs) {
			if(playerList.get(0).getScore() == playerList.get(1).getScore()) {
				System.out.println("Draw!");
				throw new GameOverException("Game Over");
			}
			else if (playerList.get(0).getScore() > playerList.get(1).getScore()) {
				System.out.println("Player one wins!");
				throw new GameOverException("Game Over");
			} else {
				System.out.println("Player two wins!");
				throw new GameOverException("Game Over");
			}
		}

		pickOne = -1;
		pickTwo = -1;
		turn++;
	}

	@Override
	public void pickCard(int key, Player p)
			throws StateException, KeyOutOfBoundsException, CardAlreadyPickedException, IllegalArgumentException, GameOverException {
		if(turn % 2 == 1 && p.getPlayerNumber() != PlayerNumber.PLAYER_ONE || turn % 2 == 0 && p.getPlayerNumber() != PlayerNumber.PLAYER_TWO) {
			throw new StateException("It is not the turn of " + p.getPlayerNumber() + " !");
		} else if (key < 0) {
			throw new IllegalArgumentException("This key does not correspond with any legal key!");
		} else if (key > totalCards.size()) {
			throw new KeyOutOfBoundsException("This key does not correspond with any legal key!");
		} else if (pickOne == -1) {
			pickOne = key;
		} else {
			if (pickOne == key) {
				throw new CardAlreadyPickedException("This card has already been picked!");
			}
			pickTwo = key;
			prepareNextRound(p);
		}
	}

	public List<Character> getTotalCards() {
		return totalCards;
	}
}
