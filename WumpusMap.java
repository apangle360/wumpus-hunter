package WumpusHunter;

public class WumpusMap {
	private Integer numberOfCaverns = 0;
	private Cavern[][] caverns;
	private Integer playerX;
	private Integer playerY;
	private Integer WumpusX;
	private Integer WumpusY; 
	
	public WumpusMap(Integer xSize, Integer ySize){
		this.playerX = 0;
		this.playerY = 0;
		numberOfCaverns = 0;
		caverns = new Cavern[xSize][ySize];
		for (int xIndex = 0; xIndex < xSize; xIndex++){
			for (int yIndex = 0; yIndex < ySize; yIndex++){
				caverns[xIndex][yIndex] = new Cavern();
				numberOfCaverns++;
			}
		}
		
	}
	
	public void playerMoves(char direction) {
		if (direction == 'N') {
			setPlayerY(playerY + 1);
		}
		if (direction == 'S') {
			setPlayerY(playerY - 1);		
				}
		if (direction == 'E') {
			setPlayerX(playerX - 1);
		}
		if (direction == 'W') {
			setPlayerX(playerX + 1);
		}
	}
	
	public Integer getNumberOfCaverns(){
		return numberOfCaverns;
	}
	
	public Cavern[][] getCaverns(){
		return caverns;
	}
	
	public void setBoundaryCavern(int boundaryX, int boundaryY){
		caverns[boundaryX][boundaryY].setIsBoundary(true);
	}
	
	public void setCavernHasPit(Cavern cavern){
		cavern.setHasPit(true);
	}
	
	public void setCavernHasBat(Cavern cavern){
		cavern.setHasBat(true);
	}
	
	public void setCavernHasArrow(Cavern cavern){
		cavern.setHasArrow(true);
	}

	public Integer getPlayerX() {
		return playerX;
	}

	public void setPlayerX(Integer playerX) {
		this.playerX = playerX;
	}

	public Integer getPlayerY() {
		return playerY;
	}

	public void setPlayerY(Integer playerY) {
		this.playerY = playerY;
	}
	
}