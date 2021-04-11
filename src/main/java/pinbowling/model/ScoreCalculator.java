package pinbowling.model;

import java.util.List;
import java.util.Map;

public class ScoreCalculator {
	
	public static Map<String,List<Frame>> calculateScore(Map<String,List<Frame>> framesByPlayers){
		framesByPlayers.keySet().parallelStream().forEach(pl ->{
			
		});
		
		return framesByPlayers;
	}

}
