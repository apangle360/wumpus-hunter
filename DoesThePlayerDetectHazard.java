package fixtures;

import main.Cavern;
import main.WumpusMap;

public class DoesThePlayerDetectHazard {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private Integer playerX;
	private Integer playerY;
	
	public void setPlayerX(Integer playerX){
		this.playerX = playerX;
		testMap.getPlayer().setPosX(playerX);
	}
	
	public void setPlayerY(Integer playerY){
		this.playerY = playerY;
		testMap.getPlayer().setPosY(playerY);
	}
	
	public void setWumpusX(Integer wumpusX){
		testMap.setWumpusX(wumpusX);
	}
	
	public void setWumpusY(Integer wumpusY){
		testMap.setWumpusY(wumpusY);
	}
	
	public boolean playerHearsWind(){
		return testMap.pitIsAdjacent();
	}
	
	public boolean playerSmellsWumpus(){
		return testMap.wumpusIsAdjacent();
	}
	
	public boolean playerHearsBats(){
		return testMap.batsAreAdjacent();
	}
}
