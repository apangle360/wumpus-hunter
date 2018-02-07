package main;

public class WumpusMap {
	private Integer numberOfCaverns = 0;
	private Cavern[][] caverns;
	
	public WumpusMap(Integer xSize, Integer ySize){
		caverns = new Cavern[xSize][ySize];
		for (int xIndex = 0; xIndex < xSize; xIndex++){
			for (int yIndex = 0; yIndex < ySize; yIndex++){
				caverns[xIndex][yIndex] = new Cavern();
				numberOfCaverns++;
			}
		}
	}
	
	public Integer getNumberOfCaverns(){
		return numberOfCaverns;
	}
}
