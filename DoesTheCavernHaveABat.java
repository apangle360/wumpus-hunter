package fixtures;

import main.WumpusMap;

public class DoesTheCavernHaveABat {
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
	
	public boolean cavernHasBat(){
		return testMap.getCaverns()[cavernX][cavernY].getHasBat();
	}
}
