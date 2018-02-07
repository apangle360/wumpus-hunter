package WumpusHunter;

import java.util.Random;

public class WumpusMap {
	private Integer numberOfCaverns = 0;
	private Cavern[][] caverns;
	private Integer playerX;
	private Integer playerY;
	private Integer wumpusX;
	private Integer wumpusY;
	
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
	
	public void setWumpusX(Integer wumpusX){
		this.wumpusX = wumpusX;
	}
	
	public void setWumpusY(Integer wumpusY){
		this.wumpusY = wumpusY;
	}
	
	public void setWumpusXY(Integer wumpusX, Integer wumpusY) {
		setWumpusY(wumpusY);
		setWumpusX(wumpusX);
	}
	
	public Integer getWumpusX(){
		return wumpusX;
	}
	
	public Integer getWumpusY(){
		return wumpusY;
	}
	
	public Integer getPlayerX(){
		return playerX;
	}
	
	public Integer getPlayerY(){
		return playerY;
	}
	
	public void setPlayerX(Integer playerX){
		this.playerX = playerX;
	}
	
	public void setPlayerY(Integer playerY){
		this.playerY = playerY;
	}
	
	public void setPlayerXY(Integer playerX, Integer playerY) {
		setPlayerY(playerY);
		setPlayerX(playerX);
	}
	
	public void moveWumpus(){
		Random randomGenerator = new Random();
		do{
			Integer movementOption = (int) randomGenerator.nextInt(5);
			if (movementOption == 1)
				wumpusX = wumpusX + 1;
			if (movementOption == 2)
				wumpusX = wumpusX - 1;
			if (movementOption == 3)
				wumpusY = wumpusY + 1;
			if (movementOption == 4)
				wumpusY = wumpusY - 1;
		} while (!validateCavern(wumpusX, wumpusY));
	}
	
	public void movePlayer(char movementOption){
			if (movementOption == 'E')
				if(validateCavern(playerX + 1, playerY)) {
					playerX = playerX + 1;
				}
				//else throw error 
			if (movementOption == 'W')
				if(validateCavern(playerX - 1, playerY)) {
					playerX = playerX - 1;
				}
			//else throw error 

			if (movementOption == 'N')
				if(validateCavern(playerX, playerY + 1)) {
					playerY = playerY + 1;
				}
			//else throw error 

			if (movementOption == 'S')
				if(validateCavern(playerX, playerY - 1)) {
					playerY = playerY - 1;
				}
			//else throw error 

	}
	
	private boolean validateCavern(Integer xPosition, Integer yPosition){
		return !(caverns[xPosition][yPosition].getIsBoundary());
	}
	
}