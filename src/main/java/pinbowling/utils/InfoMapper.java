package pinbowling.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;

import lombok.Builder;
import lombok.NoArgsConstructor;
import pinbowling.model.PlayerInfo;
import pinbowling.model.PlayerThrows;


@Builder
@NoArgsConstructor
public class InfoMapper {

	public Map<String, List<PlayerThrows>> playersInfo(String filePath)
			throws IOException, NumberFormatException {
		Map<String, List<PlayerThrows>> players = new HashMap<String, List<PlayerThrows>>();
		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			List<String> lines = stream.collect(Collectors.toList());
			players = lines.stream().map(line -> mapLineToPlayerThrow(line, lines.indexOf(line)))
					.collect(Collectors.groupingBy(PlayerThrows::getPlayerName, Collectors.toList()));
		}
		return players;
	}

	private PlayerThrows mapLineToPlayerThrow(String line, Integer id) throws NumberFormatException {
		String[] values = line.split(" ");

		PlayerThrows newPlayer = PlayerThrows.builder()
				.player(PlayerInfo.builder().playerName(values[0]).dateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)).build())
				.throwId(id + 1).build();
		if (NumberUtils.isCreatable(values[1])) {
			newPlayer.setFallenPines(values[1]);
			newPlayer.setValid(true);
		} else {
			newPlayer.setFallenPines(values[1].toUpperCase());
			newPlayer.setValid(false);
		}
		return newPlayer;

	}
	
}
