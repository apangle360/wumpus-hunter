package fixtures;

import main.Player;
import main.WumpusMap;

public class PlayerWumpusDeath {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private char playerDirection;
	private Player player = testMap.getPlayer();
	
	public void setPlayerX(int playerX) {
		player.setPosX(playerX);
	}

	public void setPlayerY(int playerY) {
		player.setPosY(playerY);
	} 
	
	public void setWumpusX(int wumpusX) {
		testMap.setWumpusX(wumpusX);
	}

	public void setWumpusY(int wumpusY) {
		testMap.setWumpusY(wumpusY);
	}
	
	public void setPlayerDirection(char playerDirection) {
		this.playerDirection = playerDirection;
	}
	
	public boolean death(){
		testMap.movePlayer(playerDirection);
		return testMap.playerDeathCheck();
	}

}
