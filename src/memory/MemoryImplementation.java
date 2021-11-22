package memory;

import java.util.Collections;
import java.util.LinkedList;

/**
 * The implementation of the game Memory
 * 
 * @author Alexander Yurovskyy
 * @version 1.0
 */
public class MemoryImplementation implements MemoryInterface {

	private int cardPairs;
	private int pickOne;
	private int pickTwo;
	private LinkedList<Character> totalCards = new LinkedList<Character>();

	public MemoryImplementation() {
		this.pickOne = -1;
	}

	@Override
	public Player choosePlayerNumber(String user, PlayerNumber pn) throws PlayerNumberTakenException {
		System.out.println("Payer One, please enter your username.");
		Player p = new Player(user, pn);
		return p;
	}

	/*
	 * @Override public void setNumberOfCardPairs(int pairs) throws
	 * IllegalArgumentException { Scanner sc = new Scanner(System.in); try { pairs =
	 * sc.nextInt(); if(pairs > 0) { this.cardPairs = pairs; } }
	 * catch(IllegalArgumentException e) {
	 * System.out.println("There must be at least one pair!"); } }
	 */

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
	public boolean validate() {	
		return totalCards.get(pickOne) == totalCards.get(pickTwo);
	}

	/*
	 * Aendert die Indexe von Karten, die raus sind. 
	 * Updated den Score.
	 * Beendet das Spiel, wenn alle Karten weg sind.
	 * Ernennt den Gewinner.
	 */
	@Override
	public void prepareNextRound(Player p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pickCard(int key , Player p)
			throws KeyOutOfBoundsException, CardAlreadyPickedException, IllegalArgumentException {
		if (key < 0) {
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

	public LinkedList<Character> getTotalCards() {
		return totalCards;
	}
}
