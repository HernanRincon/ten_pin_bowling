package pinbowling.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.BaseTest;
import pinbowling.model.Frame;
import pinbowling.model.ScoreCalculator;



class ScoreCalculatorTest extends BaseTest{
	
	private static ScoreCalculator calculator;
	
	@BeforeEach
	void initInfo() {
		calculator= new ScoreCalculator();
		
	}

	@Test
	void test() {
		Map<String,List<Frame>> players=calculator.calculateScore(scoreList);
		assertEquals(300, players.get("hernan").get(9).getFinalScore(),"The ScoreCalculator has problems to calculate score");
	}

}
