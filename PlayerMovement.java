package fixtures;

import main.WumpusMap;

public class PlayerMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private int playerX;
	private int playerY;
	private char direction;
	
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public int posX(){
		testMap.movePlayer(direction);
		playerX = testMap.getPlayerX();
		return playerX;
	}
	
	public int posY(){
		testMap.movePlayer(direction);
		playerY = testMap.getPlayerY();
		return playerY;
	}
	
}
