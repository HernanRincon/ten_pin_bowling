package pinbowling.utils;

import java.util.List;
import java.util.Map;

import pinbowling.model.BowlingRules;
import pinbowling.model.Frame;

/**
 * 
 * @author hernan
 *
 */
public class FormatPrinter {
	private String frame=String.format("Frame\t\t%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s","1","2","3","4","5","6","7","8","9","10");
	private String pinFalls="Pinfalls\t%1$s%2$s%3$s%4$s%5$s%6$s%7$s%8$s%9$s%10$s";
	private String score="Score\t\t%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s%-6s";
	private String standarSpaces="%-3s";
	
	@SuppressWarnings("all")
	public String formatScore(Map<String, List<Frame>> mapFrames) {
		StringBuilder response = new StringBuilder();
		response.append(frame);
		response.append("\n");
//		System.out.println("prueba:"+frame);
		mapFrames.keySet().stream().forEach(name -> {
			String [] pinFallsArray= mapFrames.get(name).stream().map(map -> normalizePinFalls(map.getPointsPerBall(),map.getFrameId())).toArray(String[]::new);
			String [] scoreArray= mapFrames.get(name).stream().map(map -> String.valueOf(map.getFinalScore())).toArray(String[]::new);
			
//			System.out.println("prueba:"+name);
			response.append(name);
			response.append("\n");
//			System.out.println("prueba:"+String.format(pinFalls, pinFallsArray));
			response.append(String.format(pinFalls, pinFallsArray));
			response.append("\n");
//			System.out.println("prueba:"+String.format(score, scoreArray));
			response.append(String.format(score, scoreArray));
			response.append("\n");
		});
		
		
//		System.out.println("prueba:"+String.format(pinFalls, framesId));
		return response.toString();
	}
	
	private String normalizePinFalls(List<String> pinFalls, int frameId){
		StringBuilder collect=  new StringBuilder();
		if (pinFalls.size()<=1) {
			pinFalls.add(0, " ");
		}
		pinFalls.forEach(val->{
			if (val.equals(BowlingRules.STRIKE.getRule())) {
				collect.append(String.format(standarSpaces, "X"));
			} else {
				if (pinFalls.indexOf(val)==1&&frameId<9) {
					collect.append(String.format(standarSpaces, "/"));
				} else {
					collect.append(String.format(standarSpaces, val));
				}
				
			}
			
		});
		return collect.toString();
	}

}
