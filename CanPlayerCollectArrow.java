package fixtures;

import main.WumpusMap;

public class CanPlayerCollectArrow {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private Integer playerX;
	private Integer playerY;
	private Integer endCavernX;
	private Integer endCavernY;
	private char direction;
	
	public void setPlayerX(Integer playerX){
		this.playerX = playerX;
		testMap.getPlayer().setPosX(playerX);
	}
	
	public void setPlayerY(Integer playerY){
		this.playerY = playerY;
		testMap.getPlayer().setPosY(playerY);
	}
	
	public void setDirection(char direction){
		this.direction = direction;
		if (direction == 'N'){
			endCavernX = playerX;
			endCavernY = playerY+1;
		}
		if (direction == 'E'){
			endCavernX = playerX+1;
			endCavernY = playerY;
		}
		if (direction == 'W'){
			endCavernX = playerX-1;
			endCavernY = playerY;
		}
		if (direction == 'S'){
			endCavernX = playerX;
			endCavernY = playerY-1;
		}
	}
	
	public boolean cavernHasArrow(){
		boolean hasArrow = testMap.getCaverns()[endCavernX][endCavernY].getHasArrow();
		movePlayer(direction);
		return hasArrow;
	}
	
	private void movePlayer(char direction){
		testMap.movePlayer(direction);
		playerX = testMap.getPlayer().getPosX();
		playerY = testMap.getPlayer().getPosY();
		testMap.arrowCheck(endCavernX, endCavernY);
	}
	
	public Integer arrowCount(){
		return testMap.getPlayerArrows();
	}
	
	public boolean resultCavernHasArrows(){
		return testMap.getCaverns()[endCavernX][endCavernY].getHasArrow();
	}
	
}
