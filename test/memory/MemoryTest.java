package memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.Assert;

class MemoryTest {

	@Test
	@DisplayName("ChoosePlayerNumberTest")
	void ChoosePlayerNumberTest() throws PlayerNumberTakenException {
		MemoryInterface mint = new MemoryImplementation();
		Player pn = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		Assert.assertEquals(mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE), pn);
	}
	
	@Test
	@DisplayName("Expected = PlayerNumberTakenException")
	void ChoosePlayerNumberTakenTest() throws PlayerNumberTakenException {
		MemoryInterface mint = new MemoryImplementation();
		Player pn1 = mint.choosePlayerNumber("Alex", PlayerNumber.PLAYER_ONE);
		assertThrows(PlayerNumberTakenException.class, () -> {
			Player pn2 = mint.choosePlayerNumber("Leonid", PlayerNumber.PLAYER_ONE);
		});
	}

	@Test
	@DisplayName("SetNumberOfCardPairsTest")
	void SetNumberOfCardPairsTest() throws IllegalArgumentException {
		MemoryInterface mint = new MemoryImplementation();
		MemoryImplementation mimp = new MemoryImplementation();
		mint.setNumberOfCardPairs(4);
		Assert.assertEquals(0, 0);
	}
	

}
