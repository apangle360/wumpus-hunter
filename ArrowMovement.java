package fixtures;

import main.Player;
import main.WumpusMap;

public class ArrowMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private int playerX;
	private int playerY;
	private char direction;
	private Player player = testMap.getPlayer();
	private int arrowX;
	private int arrowY;
	
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		player.setPosX(playerX);
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
		playerY = player.getPosY();
		return testMap.getFinalArrowY();
	}
	
}
