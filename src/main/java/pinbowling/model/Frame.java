package pinbowling.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Frame {
	private int frameId;
	private List<String> pointsPerBall;
	private Integer finalScore;
	private PlayerInfo player;
	private boolean isStrike;

	public static void validateGame(List<PlayerThrows> gamePerPlayer) {
	}

	/**
	 * This method map the throws per player to frames 
	 * @param players
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static Map<String, List<Frame>> gameMapperToFrames(Map<String, List<PlayerThrows>> players)
			throws IOException, NumberFormatException {
		Map<String, List<Frame>> game = new HashMap<String, List<Frame>>();
		players.keySet().parallelStream().forEach(name -> {
			List<Frame> frames = new ArrayList<Frame>();
			int indexThrow = 0;
			for (int i = 0; i < 10; i++) {
				List<PlayerThrows> pThrows = players.get(name);
				PlayerThrows playerThrows = pThrows.get(indexThrow);
				Frame frame = Frame.builder().frameId(i).player(playerThrows.getPlayer()).build();
				if(i==9) {
					List<String> poinst = pThrows.stream().map(value -> value.getFallenPines())
							.collect(Collectors.toList()).subList(indexThrow, pThrows.size());
					frame.setPointsPerBall(poinst);
					indexThrow = indexThrow + pThrows.size();
					if(poinst.stream().allMatch(m-> m.equals(BowlingRules.STRIKE.getRule()))) {
						frame.setStrike(true);
					}
				}else {
					if (playerThrows.isValid() && playerThrows.getFallenPines().equals(BowlingRules.STRIKE.getRule())) {
						List<String> poinst = pThrows.stream().map(value -> value.getFallenPines())
								.collect(Collectors.toList()).subList(indexThrow, indexThrow + 1);
						frame.setPointsPerBall(poinst);
						frame.setStrike(true);
						indexThrow = indexThrow + 1;
					} else {
						List<String> poinst = pThrows.stream().map(value -> value.getFallenPines())
								.collect(Collectors.toList()).subList(indexThrow, indexThrow + 2);
						frame.setPointsPerBall(poinst);
						indexThrow = indexThrow + 2;
					}
				}
				
				frames.add(frame);
			}
//			frames.forEach(System.out::println);
			game.put(name, frames);
		});

		return game;
	}

}
