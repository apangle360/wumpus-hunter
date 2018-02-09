package fixtures;

import main.Player;
import main.WumpusMap;

public class WumpusDeath {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private char shootDirection;
	private Player player = testMap.getPlayer();
	
	
	public void setPlayerX(int playerX) {
		player.setPosX(playerX);
		testMap.setPlayerArrows(5);
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
	
	public void setDirection(char shootDirection) {
		this.shootDirection = shootDirection;
	}
	
	public boolean wumpusDeath(){
		testMap.playerShootArrow(shootDirection);
		testMap.isWumpusHitCheck(shootDirection);
		return testMap.playerDeathCheck();
	}
}
