package fixtures;

import main.Player;
import main.WumpusMap;

public class ArrowMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private char direction;
	private Player player = testMap.getPlayer();
	
	
	public void setPlayerX(int playerX) {
		player.setPosX(playerX);
		testMap.setPlayerArrows(5);
	}

	public void setPlayerY(int playerY) {
		player.setPosY(playerY);
	} 
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public int arrowX(){
		testMap.playerShootArrow(direction);
		return testMap.getFinalArrowX();
	}
	
	public int arrowY(){
		return testMap.getFinalArrowY();
	}
	
}
