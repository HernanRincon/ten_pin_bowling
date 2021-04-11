package pinbowling;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pinbowling.model.Frame;
import pinbowling.model.PlayerThrows;
import pinbowling.model.ScoreCalculator;
import pinbowling.utils.InfoMapper;

public class PinBowlingGame {

	// "/home/hernan/projects/proyectos_github/ten_pin_bowling/src/main/java/resources/Data_Game.txt"
	public static void main(String[] args) {
		try (Scanner ins = new Scanner(System.in)) {
			String path = "";
			while (path.equals("")) {
				System.out.print("Enter file path of the players Scores: ");
				path = ins.next();
				path ="/home/hernan/projects/proyectos_github/ten_pin_bowling/src/main/java/resources/Data_Game.txt";
				formatGameScore(path);
			}
			System.out.println("path value:" + path);
			ins.close();
		}
	}

	public static String formatGameScore(String path) {
		try (Scanner ins = new Scanner(System.in)) {
			Map<String, List<PlayerThrows>> players = InfoMapper.playersInfo(path);
			players.keySet().stream().forEach(System.out::println);
			Map<String, List<Frame>> mapFrames=Frame.gameMapperToFrames(players);
			ScoreCalculator.calculateScore(mapFrames);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
