package pinbowling.utils;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.NoArgsConstructor;
import pinbowling.model.BowlingRules;
import pinbowling.model.Frame;

/**
 * 
 * @author hernan
 *
 */
@Builder
@NoArgsConstructor
public class FormatPrinter {
	private static final String FRAME=String.format("Frame\t\t%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s","1","2","3","4","5","6","7","8","9","10");
	private static final String PIN_FALLS="Pinfalls\t%1$s%2$s%3$s%4$s%5$s%6$s%7$s%8$s%9$s%10$s";
	private static final String SCORE="Score\t\t%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s";
	private static final String STANDAR_SPACES="%-3s";
	
	public String formatScore(Map<String, List<Frame>> mapFrames) {
		StringBuilder response = new StringBuilder();
		response.append(FRAME);
		response.append("\n");
		mapFrames.keySet().stream().forEach(name -> {
			Object [] pinFallsArray= mapFrames.get(name).stream().map(map -> normalizePinFalls(map.getPointsPerBall(),map.getFrameId())).toArray(String[]::new);
			Object [] scoreArray= mapFrames.get(name).stream().map(map -> String.valueOf(map.getFinalScore())).toArray(String[]::new);
			
			response.append(name);
			response.append("\n");
			response.append(String.format(PIN_FALLS, pinFallsArray));
			response.append("\n");
			response.append(String.format(SCORE, scoreArray));
			response.append("\n");
		});
		return response.toString();
	}
	
	private String normalizePinFalls(List<String> pinFalls, int frameId){
		StringBuilder collect=  new StringBuilder();
		if (pinFalls.size()<=1) {
			pinFalls.add(0, " ");
		}
		pinFalls.forEach(val->{
			if (val.equals(BowlingRules.STRIKE.getRule())) {
				collect.append(String.format(STANDAR_SPACES, "X"));
			} else {
				if (pinFalls.indexOf(val)==1&&frameId<9) {
					collect.append(String.format(STANDAR_SPACES, "/"));
				} else {
					collect.append(String.format(STANDAR_SPACES, val));
				}
				
			}
			
		});
		return collect.toString();
	}

}
