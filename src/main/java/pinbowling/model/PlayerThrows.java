package pinbowling.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerThrows {
	private int throwId;
	private PlayerInfo player;
	private String fallenPines ;
	private boolean isValid;
	
	public static void validateShots(List<PlayerThrows> playerGame) {
		
	}
	
	public String getPlayerName() {
		return player.getPlayerName();
	}

}
