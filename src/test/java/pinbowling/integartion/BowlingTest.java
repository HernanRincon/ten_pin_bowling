package pinbowling.integartion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.BaseTest;
import pinbowling.PinBowlingGame;

class BowlingTest extends BaseTest {

	private static PinBowlingGame game;

	@BeforeEach
	void initInfo() {
		game = new PinBowlingGame();
	}

	@Test
	void test() {
		String response=game.formatGameScore("src/main/java/resources/Data_Game_2.txt");
		assertEquals(FORMATED_SCORE.strip(), response.strip(),"The escore formated is not the expected");
	}

}
