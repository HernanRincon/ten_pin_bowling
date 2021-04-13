package common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import pinbowling.model.Frame;
import pinbowling.model.PlayerInfo;

public class BaseTest {
	
	protected static Map<String, List<Frame>> scoreList = initScore() ;
	protected static final String FORMATED_SCORE="Frame		1     2     3     4     5     6     7     8     9     10    \n"
			+ "Carl\n"
			+ "Pinfalls	   X     X     X     X     X     X     X     X     X  X  X  X  \n"
			+ "Score		30    60    90    120   150   180   210   240   270   300  \n";
	
	protected static Map<String, List<Frame>> initScore(){
		Map<String, List<Frame>> players= new HashMap<String, List<Frame>>();
		List<Frame> frames = new ArrayList<Frame>();
		IntStream.range(0, 9).forEach(i -> {
			frames.add(Frame.builder().frameId(i).pointsPerBall(Arrays.asList("10"))
					.player(PlayerInfo.builder().playerName("hernan").dateTime(LocalDateTime.now()).build()).isStrike(true)
					.build());
		});
		frames.add(Frame.builder().frameId(9).pointsPerBall(Arrays.asList("10", "10", "10"))
				.player(PlayerInfo.builder().playerName("hernan").dateTime(LocalDateTime.now()).build()).isStrike(true)
				.build());
	
		players.put("hernan", frames);
		return players;
	}

}
