package fixtures;

import main.WumpusMap;

public class PlayerArrows {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private String arrowShotResult = "You shoot an arrow.";
	
	private Integer startingArrows = 0;
	private String action = "shoot";
	
	public void setStartingArrows(Integer startingArrows){
		this.startingArrows = startingArrows;
		testMap.setPlayerArrows(startingArrows);;
	}
	
	public void setAction(String action){
		this.action = action;
	}
	
	public Integer resultArrowNumber(){
		arrowShotResult = testMap.playerShootArrow('N');
		return testMap.getPlayerArrows();
	}
	
	public String arrowMessage(){
		return arrowShotResult;
	}
}
