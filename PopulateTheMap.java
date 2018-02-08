package fixtures;

import main.Cavern;
import main.WumpusMap;

public class PopulateTheMap {
	private WumpusMap wumpusMap;
	private Integer totalNumberOfCaverns = 0;
	private Integer boundaryCaverns = 0;
	private Integer cavernSideSize;
	
	public void setTotalNumberOfCaverns(Integer totalNumberOfCaverns){
		this.totalNumberOfCaverns = totalNumberOfCaverns;
		cavernSideSize = (int) Math.sqrt(totalNumberOfCaverns);
		wumpusMap = new WumpusMap(cavernSideSize, cavernSideSize);
	}
	
	public void setBoundaryCaverns(Integer boundaryCaverns){
		this.boundaryCaverns = boundaryCaverns;
		Integer createdBoundaryCaverns = 0;
		for (int i = 0; i < cavernSideSize; i++){
			for (int j = 0; j < cavernSideSize; j++){
				if (createdBoundaryCaverns < boundaryCaverns){
					wumpusMap.setBoundaryCavern(i, j);
					createdBoundaryCaverns++;
				}
			}
		}
	}
	
	public Integer numberOfCaverns(){
		return wumpusMap.getNumberOfInhabitableCaverns();
	}
}
