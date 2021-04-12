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
	private static Logger logger = Logger.getLogger(PinBowlingGame.class.getName());

	// "/home/hernan/projects/proyectos_github/ten_pin_bowling/src/main/java/resources/Data_Game.txt"
	public static void main(String[] args) {
		try (Scanner ins = new Scanner(System.in)) {
			String path = "";
			while (path.equals("")) {
				System.out.print("Enter file path of the players Scores: ");
				path = ins.next();
				String response=formatGameScore(path);
				System.out.println(response);
	
			}
//			System.out.println("path value:" + path);
			ins.close();
		}
	}

	public static String formatGameScore(String path) {
		try (Scanner ins = new Scanner(System.in)) {
			Map<String, List<PlayerThrows>> players = InfoMapper.playersInfo(path);
			Map<String, List<Frame>> mapFrames=Frame.gameMapperToFrames(players);
			mapFrames=ScoreCalculator.calculateScore(mapFrames);
			return new FormatPrinter().formatScore(mapFrames);
		} catch (NumberFormatException e) {
			logger.log(Level.INFO, BowlingRules.FILE_ERROR.getDescription());
		} catch (IOException e) {
			logger.log(Level.INFO, BowlingRules.INVALID_PINES_VALUE.getDescription());
		}
		return "";

		}

}
