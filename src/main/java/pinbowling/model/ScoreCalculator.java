package pinbowling.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;


public class ScoreCalculator {
	
	public Map<String,List<Frame>> calculateScore(Map<String,List<Frame>> framesByPlayers){
		System.out.println("");
		framesByPlayers.keySet().parallelStream().forEach(name ->{
			List<Frame> frames=framesByPlayers.get(name);
			frames.forEach(frame -> {
				if (frames.indexOf(frame)==frames.size()-1) {
					int score=streamList(frames,frames.indexOf(frame)).mapToInt(num->num).sum();
					score=score+frames.get(frames.indexOf(frame)-1).getFinalScore();
					frame.setFinalScore(score);
				} else {
					int score=streamList(frames,frames.indexOf(frame)).mapToInt(num->(num)).sum();
					if (frame.isStrike()) {
						int index=frames.indexOf(frame)+1;
						score=score+streamList(frames,index).limit(2).mapToInt(num->num).sum();
						if (frames.get(index).isStrike() && index<frames.size()-1) {
							score=score+streamList(frames,frames.indexOf(frame)+2).collect(Collectors.toList()).get(0);
						}
					} else {
						if(score== 10) {
							score=score+streamList(frames,frames.indexOf(frame)+1).collect(Collectors.toList()).get(0);
						}
					}
					if (frames.indexOf(frame)>0) {
						score=score+frames.get(frames.indexOf(frame)-1).getFinalScore();
					}
					frame.setFinalScore(score);
				}
			});
//			frames.forEach(System.out::println);
			
		});
		
		return framesByPlayers;
	}
	
	private Stream<Integer> streamList(List<Frame> frames, int index) {
		return frames.get(index).getPointsPerBall().stream().map(num  -> NumberUtils.isCreatable(num)?Integer.parseInt(num):0);
	}

}
