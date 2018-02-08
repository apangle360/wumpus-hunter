package fixtures;

import main.Player;
import main.WumpusMap;

public class PlayerMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private int playerX;
	private int playerY;
	private char direction;
	private String movementResult = "movement";
	private Player player = testMap.getPlayer();
	
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
	
	public int posX(){
		movementResult = testMap.movePlayer(direction);
		playerX = player.getPosX();
		return playerX;
	}
	
	public int posY(){
		playerY = player.getPosY();
		return playerY;
	}
	
	public String errorMessageResult(){
		return movementResult;
	}
	
	
}
