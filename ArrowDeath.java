package fixtures;

import main.Player;
import main.WumpusMap;

public class ArrowDeath {
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
	
	public void setShootDirection(char shootDirection) {
		this.shootDirection = shootDirection;
	}
	
	public String status(){
		testMap.playerShootArrow(shootDirection);
		if(testMap.playerDeathCheck()) return "You died";
		return "nope";
	}
	
}