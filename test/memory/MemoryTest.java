package memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.Assert;

class MemoryTest {

	//***ChoosePlayerNumber***//
	
	@Test
	@DisplayName("ChoosePlayerNumberTest")
	void ChoosePlayerNumberTest() throws PlayerNumberTakenException {
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
	}
	
	@Test
	@DisplayName("Expected = PlayerNumberTakenException")
	void ChoosePlayerNumberTakenTest() throws PlayerNumberTakenException {
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		assertThrows(PlayerNumberTakenException.class, () -> {
			Player p2 = mint.choosePlayerNumber("Leonid", PlayerNumber.PLAYER_ONE);
		});
	}
	
	//***SetNumberOfCardPairs***//
	
	@Test
	@DisplayName("SetNumberOfCardPairsTest")
	void SetNumberOfCardPairsTest() throws IllegalArgumentException {
		MemoryInterface mint = new MemoryImplementation();
		mint.setNumberOfCardPairs(4);
		Assert.assertEquals(4, mint.getNumberOfCardPairs());
	}

	@Test
	@DisplayName("Expected = IllegalArgumentException")
	void SetNumberOfCardPairsExceptionTest() throws IllegalArgumentException{
		MemoryInterface mint = new MemoryImplementation();
		assertThrows(IllegalArgumentException.class, () -> {
			mint.setNumberOfCardPairs(-1);
		});
	}
	
	//***PickCard***//

	@Test
	@DisplayName("PickCardTest")
	void PickCardTest1() throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		mint.setNumberOfCardPairs(2);
		mint.pickCard(3, p1);
	}
	
	@Test
	@DisplayName("Expected = CardAlreadyPickedException")
	void PickCardExceptionCAPETest1() throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		mint.setNumberOfCardPairs(2);
		mint.pickCard(1, p1);
		assertThrows(CardAlreadyPickedException.class, () -> {
			mint.pickCard(1, p1);
		});
	}
	
	@Test
	@DisplayName("Expected = IllegalArgumentException")
	void PickCardExceptionIATest1() throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		mint.setNumberOfCardPairs(2);
		assertThrows(IllegalArgumentException.class, () -> {
			mint.pickCard(-1, p1);
		});
	}
	
	/*@Test
	@DisplayName("Expected = KeyOutOfBoundsException")
	void PickCardExceptionKOBETest1() throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		mint.setNumberOfCardPairs(2);
		assertThrows(IllegalArgumentException.class, () -> {
			mint.pickCard(2, p1);
		});
	}*/

	@Test
	@DisplayName("Expected = KeyOutOfBoundsException")
	void PickCardExceptionKOBETest2() throws KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		mint.setNumberOfCardPairs(2);
		assertThrows(KeyOutOfBoundsException.class, () -> {
			mint.pickCard(5, p1);
		});
	}
	
	//***Validate***//

	@Test
	@DisplayName("ValidateTest1")
	void ValidateTest1() throws StateException, KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryImplementation mimp = new MemoryImplementation();
		Player p1 = mimp.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		mimp.setNumberOfCardPairs(2);
		for(int i = 1; i < mimp.getTotalCards().size(); i++) {
			if(mimp.getTotalCards().get(0) != mimp.getTotalCards().get(i)) {
				mimp.pickCard(0, p1);
				mimp.pickCard(i, p1);
				assertFalse(mimp.validate());
				return;
			}
		}
	}
	
	@Test
	@DisplayName("ValidateTest2")
	void ValidateTest2() throws StateException, KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		mint.setNumberOfCardPairs(1);
		mint.pickCard(0, p1);
		mint.pickCard(1, p1);
		assertTrue(mint.validate());
	}
	
	@Test
	@DisplayName("Expected = StateException")
	void ValidateSETest1() throws StateException, KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		Player p2 = mint.choosePlayerNumber("Leonid", PlayerNumber.PLAYER_TWO);
		mint.setNumberOfCardPairs(2);
		mint.pickCard(1, p1);
		assertThrows(StateException.class, () -> {
			mint.pickCard(3, p2);
		});
	}
	
	@Test
	@DisplayName("Expected = StateException")
	void ValidateSETest2() throws StateException, KeyOutOfBoundsException, CardAlreadyPickedException, PlayerNumberTakenException{
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		Player p2 = mint.choosePlayerNumber("Leonid", PlayerNumber.PLAYER_TWO);
		mint.setNumberOfCardPairs(2);
		mint.pickCard(2, p2);
		assertThrows(StateException.class, () -> {
			mint.pickCard(4, p1);
		});
	}
	
	//***PrepareNextRound***//

	@Test
	@DisplayName("PrepareNextRoundTest1")
	void PrepareNextRoundTest1() throws PlayerNumberTakenException, KeyOutOfBoundsException, CardAlreadyPickedException, StateException {
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		Player p2 = mint.choosePlayerNumber("Leonid", PlayerNumber.PLAYER_TWO);
		mint.setNumberOfCardPairs(2);
		mint.pickCard(2, p1);
		mint.pickCard(4, p1);
		mint.validate();
		mint.prepareNextRound(p1);
	}
	
	@Test
	@DisplayName("Finale PrepareNextRoundTest2")
	void PrepareNextRoundTest2() throws PlayerNumberTakenException, KeyOutOfBoundsException, CardAlreadyPickedException, StateException {
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		Player p2 = mint.choosePlayerNumber("Leonid", PlayerNumber.PLAYER_TWO);
		mint.setNumberOfCardPairs(1);
		mint.pickCard(1, p1);
		mint.pickCard(2, p1);
		mint.validate();
		mint.prepareNextRound(p1);
	}
	
	/*@Test
	@DisplayName("PrepareNextRoundTest3")
	void PrepareNextRoundTest3() throws PlayerNumberTakenException, KeyOutOfBoundsException, CardAlreadyPickedException, StateException {
		MemoryInterface mint = new MemoryImplementation();
		Player p1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		Player p2 = mint.choosePlayerNumber("Leonid", PlayerNumber.PLAYER_TWO);
		
	}*/
}
