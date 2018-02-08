package fixtures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.Player;
import main.WumpusMap;

public class PlayerMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private int playerX;
	private int playerY;
	private int warpPosX;
	private int warpPosY;
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
	
	public void setWarpPosX(int warpPosX) {
		this.warpPosX = warpPosX;
	}
	public void setWarpPosY(int warpPosY) {
		this.warpPosY = warpPosY;
	}
	public String errorMessageResult(){
		return movementResult;
	}
	
	public int timesVisited() {
		Integer count = 0;
		Integer pos1Count = 0;
		Integer pos2Count = 0;
		Integer pos3Count = 0;
		Integer pos4Count = 0;
		Integer pos5Count = 0;
		Integer pos6Count = 0;
		Integer pos7Count = 0;
		Integer pos8Count = 0;
		Integer pos9Count = 0;
		Integer pos10Count = 0;
		Integer pos11Count = 0;
		Integer pos12Count = 0;
		Integer pos13Count = 0;
		Integer pos14Count = 0;
		Integer pos15Count = 0;
		Integer pos16Count = 0;
		Integer pos17Count = 0;
		Integer pos18Count = 0;
		Integer pos19Count = 0;
		Integer pos20Count = 0;
		Integer pos21Count = 0;
		Integer pos22Count = 0;
		Integer pos23Count = 0;
		Integer pos24Count = 0;
		Integer pos25Count = 0;
		for (int i = 0; i < 9000; i++){
			testMap.batWarp();
			if (testMap.getPlayer().getPosX() == 0 && testMap.getPlayer().getPosY() == 0)
				pos1Count++;
			if (testMap.getPlayer().getPosX() == 0 && testMap.getPlayer().getPosY() == 1)
				pos2Count++;
			if (testMap.getPlayer().getPosX() == 0 && testMap.getPlayer().getPosY() == 2)
				pos3Count++;
			if (testMap.getPlayer().getPosX() == 0 && testMap.getPlayer().getPosY() == 3)
				pos4Count++;
			if (testMap.getPlayer().getPosX() == 0 && testMap.getPlayer().getPosY() == 4)
				pos5Count++;
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 0)
				pos6Count++;
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 1)
				pos7Count++;
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 2)
				pos8Count++;
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 3)
				pos9Count++;
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 4)
				pos10Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 0)
				pos11Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 1)
				pos12Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 2)
				pos13Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 3)
				pos14Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 4)
				pos15Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 0)
				pos16Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 1)
				pos17Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 2)
				pos18Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 3)
				pos19Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 4)
				pos20Count++;
			if (testMap.getPlayer().getPosX() == 4 && testMap.getPlayer().getPosY() == 0)
				pos21Count++;
			if (testMap.getPlayer().getPosX() == 4 && testMap.getPlayer().getPosY() == 1)
				pos22Count++;
			if (testMap.getPlayer().getPosX() == 4 && testMap.getPlayer().getPosY() == 2)
				pos23Count++;
			if (testMap.getPlayer().getPosX() == 4 && testMap.getPlayer().getPosY() == 3)
				pos24Count++;
			if (testMap.getPlayer().getPosX() == 4 && testMap.getPlayer().getPosY() == 4)
				pos25Count++;
		}
		if (warpPosX == 0 && warpPosY == 0)
			count = pos1Count;
		if (warpPosX == 0 && warpPosY == 1)
			count = pos2Count;
		if (warpPosX == 0 && warpPosY == 2)
			count = pos3Count;
		if (warpPosX == 0 && warpPosY == 3)
			count = pos4Count;
		if (warpPosX == 0 && warpPosY == 4)
			count = pos5Count;
		if (warpPosX == 1 && warpPosY == 0)
			count = pos6Count;
		if (warpPosX == 1 && warpPosY == 1)
			count = pos7Count;
		if (warpPosX == 1 && warpPosY == 2)
			count = pos8Count;
		if (warpPosX == 1 && warpPosY == 3)
			count = pos9Count;
		if (warpPosX == 1 && warpPosY == 4)
			count = pos10Count;
		if (warpPosX == 2 && warpPosY == 0)
			count = pos11Count;
		if (warpPosX == 2 && warpPosY == 1)
			count = pos12Count;
		if (warpPosX == 2 && warpPosY == 2)
			count = pos13Count;
		if (warpPosX == 2 && warpPosY == 3)
			count = pos14Count;
		if (warpPosX == 2 && warpPosY == 4)
			count = pos15Count;
		if (warpPosX == 3 && warpPosY == 0)
			count = pos16Count;
		if (warpPosX == 3 && warpPosY == 1)
			count = pos17Count;
		if (warpPosX == 3 && warpPosY == 2)
			count = pos18Count;
		if (warpPosX == 3 && warpPosY == 3)
			count = pos19Count;
		if (warpPosX == 3 && warpPosY == 4)
			count = pos20Count;
		if (warpPosX == 4 && warpPosY == 0)
			count = pos21Count;
		if (warpPosX == 4 && warpPosY == 1)
			count = pos22Count;
		if (warpPosX == 4 && warpPosY == 2)
			count = pos23Count;
		if (warpPosX == 4 && warpPosY == 3)
			count = pos24Count;
		if (warpPosX == 4 && warpPosY == 4)
			count = pos25Count;
		return count;
	}
	
	
}