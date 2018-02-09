package fixtures;

import main.WumpusMap;

public class WumpusMovement {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private Integer wumpusX;
	private Integer wumpusY;
	private Integer expectedFinalX;
	private Integer expectedFinalY;
	
	public void setWumpusX(Integer wumpusX){
		testMap.setWumpusX(wumpusX);
	}
	
	public void setWumpusY(Integer wumpusY){
		testMap.setWumpusY(wumpusY);
	}
	
	public void setExpectedFinalX(Integer expectedFinalX){
		this.expectedFinalX = expectedFinalX;
	}
	
	public void setExpectedFinalY(Integer expectedFinalY){
		this.expectedFinalY = expectedFinalY;
	}
	
	public boolean timesVisited(){
		Integer originalX = testMap.getWumpusX();
		Integer originalY = testMap.getWumpusY();
		Integer southCount = 0;
		Integer northCount = 0;
		Integer westCount = 0;
		Integer eastCount = 0;
		Integer restCount = 0;
		for (int i = 0; i < 1000; i++){
			testMap.moveWumpus();
			if (testMap.getWumpusX() == (originalX-1) && testMap.getWumpusY() == originalY)
				westCount++;
			if (testMap.getWumpusX() == (originalX+1) && testMap.getWumpusY() == originalY)
				eastCount++;
			if (testMap.getWumpusX() == originalX && testMap.getWumpusY() == (originalY-1))
				southCount++;
			if (testMap.getWumpusX() == originalX && testMap.getWumpusY() == (originalY-1))
				northCount++;
			if (testMap.getWumpusX() == originalX && testMap.getWumpusY() == originalY)
				restCount++;
			testMap.setWumpusX(originalX);
			testMap.setWumpusY(originalY);
		}
		if (expectedFinalX == originalX && expectedFinalY == originalY){
			return (restCount > 150 && restCount < 250);
		}
		if (expectedFinalX == originalX && expectedFinalY == (originalY+1)){
			return (northCount > 150  && northCount < 250);
		}
		if (expectedFinalX == originalX && expectedFinalY == (originalY-1)){
			return (southCount > 150  && southCount < 250);
		}
		if (expectedFinalX == (originalX-1) && expectedFinalY == originalY){
			return (westCount > 150  && westCount < 250);
		}
		if (expectedFinalX == (originalX+1) && expectedFinalY == originalY){
			return (eastCount > 150  && eastCount < 250);
		}
			
		return false;
	}
	
	
	
}
