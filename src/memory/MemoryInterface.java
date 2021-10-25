package memory;

/**
 * An Interface for the game Memory
 * 
 * @author Alexander Yurovskyy
 *
 */
public interface MemoryInterface {
	
	/**
	 * The users choose for themselves which player they want to be.
	 * @param user , the user
	 * @param pn , player number
	 * @return Player
	 * @throws PlayerNumberTakenException
	 */
	Player choosePlayerNumber(String user, PlayerNumber pn) throws PlayerNumberTakenException;
	
	/**
	 * Sets the number of card-pairs for the game
	 * @param pairs
	 * @throws IllegalArgumentException
	 */
	void setNumberOfCardPairs(int pairs) throws IllegalArgumentException; 
	
	/**
	 * Selects a card. The cards are revealed to both players.
	 * @param key , the key of the card
	 * @throws KeyOutOfBoundsException
	 * @throws CardAlreadyPickedException
	 */
	void pickCard(int key) throws KeyOutOfBoundsException, CardAlreadyPickedException;
	
	/**
	 * Checks whether the cards are matching or not.
	 * @return false , if the cards aren't matching
	 * 		   true , if else
	 */
	boolean validate() throws StateException;
	
	/**
	 * Updates the playing field and the scores of each player.
	 */
	void prepareNextRound();

}
