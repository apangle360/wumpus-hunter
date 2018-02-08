package fixtures;

import main.Cavern;
import main.WumpusMap;

public class DoesThePlayerDetectHazardWind {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private Integer cavernX;
	private Integer cavernY;
	
	public void setCavernX(Integer cavernX){
		this.cavernX = cavernX;
	}
	
	public void setCavernY(Integer cavernY){
		this.cavernY = cavernY;
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
