package memory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.Assert;

class MemoryTest {

	private final String alex = "Alex";
	private final String leo = "Leo";

	private MemoryImplementation getMemoryAfterPick() throws StateException, PlayerNumberTakenException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
		Player user1 = playerList.get(0);
		Player user2 = playerList.get(1);
		mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		mimp.choosePlayerNumber(user2, PlayerNumber.PLAYER_TWO);

		return mimp;
	}

	// ***ChoosePlayerNumber***//

	@Test
	@DisplayName("ChoosePlayerNumberTest")
	void ChoosePlayerNumberTest() throws PlayerNumberTakenException, StateException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
        Player user2 = playerList.get(1);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		assertEquals(pn1, PlayerNumber.PLAYER_ONE);
		PlayerNumber pn2 = mimp.choosePlayerNumber(user2, PlayerNumber.PLAYER_TWO);
		assertEquals(pn2, PlayerNumber.PLAYER_TWO);
	}

	@Test
	@DisplayName("Expected = PlayerNumberTakenException")
	void ChoosePlayerNumberTakenTest() throws PlayerNumberTakenException, StateException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
        Player user2 = playerList.get(1);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		assertThrows(PlayerNumberTakenException.class, () -> {
			PlayerNumber pn2 = mimp.choosePlayerNumber(user2, PlayerNumber.PLAYER_ONE);
		});
	}
	
	@Test
	@DisplayName("Expected = StateException")
	void ChoosePlayerNumberSETest() throws PlayerNumberTakenException, StateException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		assertThrows(StateException.class, () -> {
			mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		});
	}

	// ***SetNumberOfCardPairs***//

	@Test
	@DisplayName("SetNumberOfCardPairsTest")
	void SetNumberOfCardPairsTest() throws IllegalArgumentException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		mimp.setNumberOfCardPairs(4);
		Assert.assertEquals(4, mimp.getNumberOfCardPairs());
	}

	@Test
	@DisplayName("Expected = IllegalArgumentException")
	void SetNumberOfCardPairsExceptionTest() throws IllegalArgumentException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		assertThrows(IllegalArgumentException.class, () -> {
			mimp.setNumberOfCardPairs(-1);
		});
	}

	// ***PickCard***//

	@Test
	@DisplayName("PickCardTest")
	void PickCardTest1() throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException, StateException, IllegalArgumentException, GameOverException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		mimp.setNumberOfCardPairs(2);
		mimp.pickCard(3, user1);
	}

	@Test
	@DisplayName("Expected = CardAlreadyPickedException")
	void PickCardExceptionCAPETest1()
			throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException, StateException, IllegalArgumentException, GameOverException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		mimp.setNumberOfCardPairs(2);
		mimp.pickCard(1, user1);
		assertThrows(CardAlreadyPickedException.class, () -> {
			mimp.pickCard(1, user1);
		});
	}

	@Test
	@DisplayName("Expected = IllegalArgumentException")
	void PickCardExceptionIATest1()
			throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException, StateException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		mimp.setNumberOfCardPairs(2);
		assertThrows(IllegalArgumentException.class, () -> {
			mimp.pickCard(-1, user1);
		});
	}

	@Test
	@DisplayName("Expected = KeyOutOfBoundsException")
	void PickCardExceptionKOBETest2()
			throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException, StateException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		mimp.setNumberOfCardPairs(2);
		assertThrows(KeyOutOfBoundsException.class, () -> {
			mimp.pickCard(5, user1);
		});
	}
	
	@Test
	@DisplayName("Expected = StateException")
	void PickCardExceptionSETest1()
			throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException, IllegalArgumentException, StateException, GameOverException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
        Player user2 = playerList.get(1);
		PlayerNumber pn2 = mimp.choosePlayerNumber(user2, PlayerNumber.PLAYER_TWO);
		mimp.setNumberOfCardPairs(2);
		mimp.pickCard(0, user1);
		assertThrows(StateException.class, () -> {
			mimp.pickCard(1, user2);
		});
	}

	// ***PrepareNextRound***//

	@Test
	@DisplayName("PrepareNextRoundTest1")
	void PrepareNextRoundTest1()
			throws PlayerNumberTakenException, KeyOutOfBoundsException, CardAlreadyPickedException, StateException, IllegalArgumentException, GameOverException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		mimp.setNumberOfCardPairs(1);
		assertEquals(user1.getScore(), 0);
		mimp.pickCard(0, user1);
		try {
			mimp.pickCard(1, user1);
		} catch(GameOverException e) {
			System.out.println("Soll so sein");
		}
		assertEquals(user1.getScore(), 1);
	}

	@Test
	@DisplayName("Expected = GameOverException")
	void PrepareNextRoundGOETest1()
			throws PlayerNumberTakenException, KeyOutOfBoundsException, CardAlreadyPickedException, StateException, IllegalArgumentException, GameOverException {
		MemoryImplementation mimp = new MemoryImplementation(alex, leo);
		List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
		PlayerNumber pn1 = mimp.choosePlayerNumber(user1, PlayerNumber.PLAYER_ONE);
		mimp.setNumberOfCardPairs(1);
		mimp.pickCard(0, user1);
		assertThrows(GameOverException.class, () -> {
			mimp.pickCard(1, user1);
		});
	}

}
