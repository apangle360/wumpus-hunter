package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WumpusMap {
	private Integer numberOfCaverns = 0;
	private Cavern[][] caverns;
	private Player player;
	private Integer wumpusX;
	private Integer wumpusY;
	private Integer playerArrows;
	private Integer finalArrowX;
	private Integer finalArrowY;
	
	public WumpusMap(Integer xSize, Integer ySize){
		this.player = new Player(1, 1);
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
		testmap.getPlayer().setPlayerPos(1, 1);
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
	
	public Player getPlayer(){
		return this.player;
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
	
	public String playerShootArrow(char shotDirection) {
		String alertMessage = "Error message not assigned!"; 
		if (playerArrows <= 0) {
			alertMessage = "Out of arrows!";
			return alertMessage;
		}
		if (shotDirection == 'E') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX() + 1, getFinalArrowY())) {
				setFinalArrowXY(getFinalArrowX() +1, getFinalArrowY());
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		if (shotDirection == 'W') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX() - 1, getFinalArrowY())) {
				setFinalArrowXY(getFinalArrowX() - 1, getFinalArrowY());
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		if (shotDirection == 'N') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX(), getFinalArrowY() + 1)) {
				setFinalArrowXY(getFinalArrowX(), getFinalArrowY() + 1);
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		if (shotDirection == 'S') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX(), getFinalArrowY() - 1)) {
				setFinalArrowXY(getFinalArrowX(), getFinalArrowY() - 1);
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			playerArrows -= 1; 
		}
		
		return alertMessage;
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
				if(validateCavern(player.getPosX() + 1, player.getPosY())) {
					player.setPosX(player.getPosX() + 1);
				}
			else return ("You cannot go East from here."); 
			}
			if (movementOption == 'W'){
				if(validateCavern(player.getPosX() - 1, player.getPosY())) {
					player.setPosX(player.getPosX() - 1);
				}
			else return ("You cannot go West from here.");  
			}
			if (movementOption == 'N'){
				if(validateCavern(player.getPosX(), player.getPosY() + 1)) {
					player.setPosY(player.getPosY() + 1);
				}
			else return ("You cannot go North from here."); 
			}
			if (movementOption == 'S'){
				if(validateCavern(player.getPosX(), player.getPosY() - 1)) {
					player.setPosY(player.getPosY() - 1);
				}
			else return "You cannot go South from here.";  
			}
			if (movementOption == 'R'){
				  
			}
			return "Movement successful";
	}
	
	private boolean validateCavern(Integer xPosition, Integer yPosition){
		return !(caverns[xPosition][yPosition].getIsBoundary());
	}

	
	public boolean wumpusIsAdjacent(){
		if (Math.abs(wumpusX - player.getPosX()) == 1 && Math.abs(wumpusY - player.getPosY()) == 0){
			System.out.println("You smell the Wumpus.");
			return true;
		}
		if (Math.abs(wumpusX - player.getPosX()) == 0 && Math.abs(wumpusY - player.getPosY()) == 1){
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
		adjacentCaverns.add(getCaverns()[player.getPosX()+1][player.getPosY()]);
		adjacentCaverns.add(getCaverns()[player.getPosX()-1][player.getPosY()]);
		adjacentCaverns.add(getCaverns()[player.getPosX()][player.getPosY()+1]);
		adjacentCaverns.add(getCaverns()[player.getPosX()][player.getPosY()-1]);
		return adjacentCaverns;
	}
	
	public void arrowCheck(Integer xPosition, Integer yPosition) {
		Player player = getPlayer();
		Cavern[][] caverns = getCaverns();
		if (caverns[player.getPosX()][player.getPosY()].getHasArrow()) {
			playerArrows += 1;
		}
		caverns[player.getPosX()][player.getPosY()].setHasArrow(false);
	}
	
	public boolean isWumpusHitCheck(char direction) {
		Player player = getPlayer();
		if (direction == 'E') {
			if (getWumpusY() == player.getPosY() && getWumpusX() > player.getPosX())
				System.out.println("Wumpus Hit! You have slain the monster");
				return true;
		}
		if (direction == 'W') {
			if (getWumpusY() == player.getPosY() && getWumpusX() < player.getPosX())
				System.out.println("Wumpus Hit! You have slain the monster");
				return true; 
		}
		if (direction == 'N') {
			if (getWumpusX() == player.getPosX() && getWumpusY() > player.getPosY())
				System.out.println("Wumpus Hit! You have slain the monster");
				return true; 
		}
		if (direction == 'S') {
			if (getWumpusX() == player.getPosX() && getWumpusY() < player.getPosY())
				System.out.println("Wumpus Hit! You have slain the monster");
				return true; 
		}
		System.out.println("You hear a dull thud in the distance...");
		return false;
	}
	
	public void batWarp() {
		Random randomGenerator = new Random();
		Player player = this.getPlayer();
		
		do{
			int movementOptionX = (int) randomGenerator.nextInt(3) + 1;
			int movementOptionY = (int) randomGenerator.nextInt(3) + 1;
			player.setPlayerPos(movementOptionX, movementOptionY);
		} while (!validateCavern(player.getPosX(), player.getPosY()));
		getPlayer().setPlayerPos(player.getPosX(), player.getPosY());
		System.out.println("Bats fly you to an different location!");
	}
	
	public boolean playerDeathCheck() {
		Player player = getPlayer();
		Cavern[][] cavern = getCaverns();
		if(getWumpusX() == player.getPosX() && getWumpusY() == player.getPosY()) {
			System.out.println("The Wumpus has found you!");
			return true;
		}
		if(cavern[player.getPosX()][player.getPosY()].getHasPit()) {
			System.out.println("You walked into a pit! How unfortunate!");
			return true;
		}
		if(cavern[player.getPosX()][player.getPosY()].getHasArrow()) {
			System.out.println("You shot yourself!  Good aim.");
			return true;
		}
		return false;
	}
	
	
}