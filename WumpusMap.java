package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WumpusMap {
	private Integer numberOfCaverns = 0;
	private Cavern[][] caverns;
	private Integer playerX;
	private Integer playerY;
	private Integer wumpusX;
	private Integer wumpusY;
	private Integer playerArrows;
	private Integer finalArrowX;
	private Integer finalArrowY;
	
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
	
	public static WumpusMap generateWumpusTestMap() {
		WumpusMap testmap = new WumpusMap(5,5);
		for(int i = 0; i < 5; i++ ) {
			testmap.setBoundaryCavern(0, i);
			testmap.setBoundaryCavern(i, 0);
			testmap.setBoundaryCavern(i, 4);
			testmap.setBoundaryCavern(4, i);
		}
		testmap.setCavernHasPit(testmap.caverns[2][2]);
		testmap.setCavernHasBat(testmap.caverns[3][2]);
		testmap.setCavernHasArrow(testmap.caverns[1][2]);
		testmap.setCavernHasWumpus(testmap.caverns[2][2]);
		testmap.setPlayerArrows(5);
		testmap.setWumpusXY(2, 2);
		testmap.setPlayerXY(1, 1);
		return testmap; 
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
	
	public void setCavernHasWumpus(Cavern cavern){
		cavern.setHasWumpus(true);
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
	
	public Integer getPlayerArrows() {
		return playerArrows;
	}

	public void setPlayerArrows(Integer playerArrows) {
		this.playerArrows = playerArrows;
	}

	public Integer getFinalArrowX() {
		return finalArrowX;
	}

	public void setFinalArrowX(Integer finalArrowX) {
		this.finalArrowX = finalArrowX;
	}

	public Integer getFinalArrowY() {
		return finalArrowY;
	}

	public void setFinalArrowY(Integer finalArrowY) {
		this.finalArrowY = finalArrowY;
	}
	
	public void setFinalArrowXY(Integer finalArrowX, Integer finalArrowY) {
		setFinalArrowX(finalArrowX);
		setFinalArrowY(finalArrowY);
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
	
	public String movePlayer(char movementOption){
			if (movementOption == 'E'){
				if(validateCavern(playerX + 1, playerY)) {
					this.playerX += 1;
				}
			else return ("You cannot go East from here."); 
			}
			if (movementOption == 'W'){
				if(validateCavern(playerX - 1, playerY)) {
					this.playerX -= 1;
				}
			else return ("You cannot go West from here.");  
			}
			if (movementOption == 'N'){
				if(validateCavern(playerX, playerY + 1)) {
					this.playerY += 1;
				}
			else return ("You cannot go North from here."); 
			}
			if (movementOption == 'S'){
				if(validateCavern(playerX, playerY - 1)) {
					this.playerY -= 1;
				}
			else return "You cannot go South from here.";  
			}
			if (movementOption == 'R'){
				  
			}
			return "Movement successful";
	}
	
	public String playerShootArrow(char shotDirection) {
		String alertMessage = "Error message not assigned!"; 
		if (playerArrows <= 0) {
			alertMessage = "Out of arrows!";
			return alertMessage;
		}
		if (shotDirection == 'E') {
			setFinalArrowXY(getPlayerX(), getPlayerY());
			while(validateCavern(getFinalArrowX() + 1, getFinalArrowY())) {
				setFinalArrowXY(getFinalArrowX() +1, getFinalArrowY());
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		if (shotDirection == 'W') {
			setFinalArrowXY(getPlayerX(), getPlayerY());
			while(validateCavern(getFinalArrowX() - 1, getFinalArrowY())) {
				setFinalArrowXY(getFinalArrowX() - 1, getFinalArrowY());
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		if (shotDirection == 'N') {
			setFinalArrowXY(getPlayerX(), getPlayerY());
			while(validateCavern(getFinalArrowX(), getFinalArrowY() + 1)) {
				setFinalArrowXY(getFinalArrowX(), getFinalArrowY() + 1);
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		if (shotDirection == 'S') {
			setFinalArrowXY(getPlayerX(), getPlayerY());
			while(validateCavern(getFinalArrowX(), getFinalArrowY() - 1)) {
				setFinalArrowXY(getFinalArrowX(), getFinalArrowY() - 1);
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		
		return alertMessage;
	}
	
	private boolean validateCavern(Integer xPosition, Integer yPosition){
		return !(caverns[xPosition][yPosition].getIsBoundary());
	}
	
	public boolean wumpusIsAdjacent(){
		if (Math.abs(wumpusX - playerX) == 1 && Math.abs(wumpusY - playerY) == 0){
			System.out.println("You smell the Wumpus.");
			return true;
		}
		if (Math.abs(wumpusX - playerX) == 0 && Math.abs(wumpusY - playerY) == 1){
			System.out.println("You smell the Wumpus.");
			return true;
		}
		return false;
	}
	
	public boolean batsAreAdjacent(){
		for (Cavern cavern : getAdjacentCaverns()){
			if (cavern.getHasBat()){
				System.out.println("You hear the chirping of bats.");
				return true;
			}
		}
		return false;
	}
	
	public boolean pitIsAdjacent(){
		for (Cavern cavern : getAdjacentCaverns()){
			if (cavern.getHasPit()){
				System.out.println("You hear wind.");
				return true;
			}
		}
		return false;
	}
	
	private List<Cavern> getAdjacentCaverns(){
		List<Cavern> adjacentCaverns = new ArrayList<Cavern>();
		adjacentCaverns.add(getCaverns()[playerX+1][playerY]);
		adjacentCaverns.add(getCaverns()[playerX-1][playerY]);
		adjacentCaverns.add(getCaverns()[playerX][playerY+1]);
		adjacentCaverns.add(getCaverns()[playerX][playerY-1]);
		return adjacentCaverns;
	}
}