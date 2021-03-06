package pinbowling;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import pinbowling.model.BowlingRules;
import pinbowling.model.Frame;
import pinbowling.model.PlayerThrows;
import pinbowling.model.ScoreCalculator;
import pinbowling.utils.FormatPrinter;
import pinbowling.utils.InfoMapper;

public class PinBowlingGame {
	private static final Logger logger = Logger.getLogger(PinBowlingGame.class.getName());
	private InfoMapper infoMapper;
	private ScoreCalculator calculator;
	private Frame frame;
	
	
	public PinBowlingGame(){
		infoMapper =InfoMapper.builder().build();
		calculator=ScoreCalculator.builder().build();
		frame=Frame.builder().build();
	}

	public static void main(String[] args) {
		try (Scanner ins = new Scanner(System.in)) {
			String path = "";
			while (path.equals("")) {
				System.out.println("You can find a example files in resources folder into src/main/java/resources");
				System.out.print("Enter file path of the players Scores: ");
				path = ins.next();
				String response = new PinBowlingGame().formatGameScore(path);
				System.out.println(response);

			}
			ins.close();
		}
	}

	public String formatGameScore(String path) {
		try (Scanner ins = new Scanner(System.in)) {
			
			Map<String, List<PlayerThrows>> players = infoMapper.playersInfo(path);
			Map<String, List<Frame>> mapFrames = frame.gameMapperToFrames(players);
			mapFrames = calculator.calculateScore(mapFrames);
			return new FormatPrinter().formatScore(mapFrames);
		} catch (NumberFormatException e) {
			logger.log(Level.INFO, BowlingRules.FILE_ERROR.getDescription());
		} catch (IOException e) {
			logger.log(Level.INFO, BowlingRules.INVALID_PINES_VALUE.getDescription());
		}catch (IndexOutOfBoundsException e) {
			logger.log(Level.INFO,"The number of frames are oversized for any player");
		}
		return "";

	}

}
