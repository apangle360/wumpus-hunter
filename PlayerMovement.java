package fixtures;

import main.WumpusMap;

public class PlayerMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private int playerX;
	private int playerY;
	private char direction;
	private String movementResult = "movement";
	
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		testMap.setPlayerX(playerX);
	}

	public void setPlayerY(int playerY) {
		testMap.setPlayerY(playerY);
	} 
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public int posX(){
		movementResult = testMap.movePlayer(direction);
		playerX = testMap.getPlayerX();
		return playerX;
	}
	
	public int posY(){
		playerY = testMap.getPlayerY();
		return playerY;
	}
	
	public String errorMessageResult(){
		return movementResult;
	}
	
	
}
