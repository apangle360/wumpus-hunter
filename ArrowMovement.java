package fixtures;

import main.Cavern;
import main.WumpusMap;

public class ArrowMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private Integer playerX;
	private Integer playerY;
	private char direction;
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	public void setPlayerX(Integer playerX) {
		this.playerX = playerX;
	}
	public void setPlayerY(Integer playerY) {
		this.playerY = playerY;
	}
	public int arrowX(){
		testMap.playerShootArrow(direction);
		int arrowX = testMap.getFinalArrowX();
		return arrowX;
	}
	
	public int arrowY(){
		int arrowY = testMap.getFinalArrowY();
		return arrowY;
	}
	

}
