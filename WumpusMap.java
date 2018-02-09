package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WumpusMap {
	private Integer numberOfCaverns = 0;
	private Cavern[][] caverns;
	private Player player;
	private Integer wumpusX;
	private Integer wumpusY;
	private Integer playerArrows;
	private Integer finalArrowX;
	private Integer finalArrowY;
	private Integer numberOfBoundaryCaverns = 0;
	private Scanner reader;
	private boolean gameIsOver = false;
	
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
		output("You enter a spoooooky cave system.");
		reader = new Scanner(System.in);
	}
	
	public void playerTurn(){
		
		for(int i = 6; i >= 0; i-- ) {
			for(int j = 0; j < 7; j++ ) {
				if (getCaverns()[i][j].getIsBoundary()){
					System.out.print(" X ");
				}else if (getPlayer().getPosX() == j && getPlayer().getPosY() == i){
					System.out.print(" O ");
				}else{
					System.out.print("   ");
				}
				
			}
			System.out.println();
		}
		
		pitIsAdjacent();
		batsAreAdjacent();
		wumpusIsAdjacent();
		output("What would you like to do? Select from options below:");
		output("Press 1 to Rest");
		output("Press 2 to Move");
		output("Press 3 to Shoot");
		boolean inputEntered = false;
		int option = 0;
		while (!inputEntered){
		try{
		reader = new Scanner(System.in);
		option = reader.nextInt();
		}catch (Exception ex){
			output("invalid input");
			continue;
		}
		if (!(option == 1 || option == 2 || option == 3)){
			output("invalid input");
			continue;
		}
		inputEntered = true;
		}
		if (option == 1)
			output("You take a breather for a bit");
		if (option == 2)
			movementDirection();
		if (option == 3)
			arrowDirection();
		wumpusTurn();
	}
	
	public void wumpusTurn(){
		if (!gameIsOver){
			moveWumpus();
			if (!playerDeathCheck()){
				playerTurn();
			} else {
				gameOver();
			}
		}
	}
	
	public void gameOver(){
		
		setGameOver(true);
	}
	
	public boolean getGameOver(){
		return gameIsOver;
	}
	
	public void setGameOver(boolean gameState){
		this.gameIsOver = gameState;
	}
	
	public void movementDirection(){
		output("Choose a direction to move:");
		output("Press N to move north");
		output("Press E to move east");
		output("Press W to move west");
		output("Press S to move south");
		boolean inputEntered = false;
		String option = "option";
		while (!inputEntered){
		try{
		reader = new Scanner(System.in);
		option = reader.next();
		}catch (Exception ex){
			output("invalid input");
			continue;
		}
		if (!(option.toUpperCase().charAt(0) == 'N' || option.toUpperCase().charAt(0) == 'E' || option.toUpperCase().charAt(0) == 'S' || option.toUpperCase().charAt(0) == 'W')){
			output("invalid input");
			continue;
		}
		inputEntered = true;
		}
		Cavern[][] cavern = getCaverns();
		movePlayer(option.charAt(0));
		do{roomCheck();}
		while (cavern[getPlayer().getPosX()][getPlayer().getPosY()].getHasBat());
		roomCheck();
		playerDeathCheck();
	}
	
	public void roomCheck() {
		arrowCheck();
		batCheck();
	}
	
	public void arrowDirection(){
		output("Choose a direction to shoot:");
		output("Press N to shoot north");
		output("Press E to shoot east");
		output("Press W to shoot west");
		output("Press S to shoot south");
		boolean inputEntered = false;
		String option = "option";
		while (!inputEntered){
		try{
		reader = new Scanner(System.in);
		option = reader.next();
		}catch (Exception ex){
			output("invalid input");
			continue;
		}
		if (!(option.toUpperCase().charAt(0) == 'N' || option.toUpperCase().charAt(0) == 'E' || option.toUpperCase().charAt(0) == 'S' || option.toUpperCase().charAt(0) == 'W')){
			output("invalid input");
			continue;
		}
		inputEntered = true;
		}
		//if (isInvalidDirection(option.charAt(0))
		
		if (playerArrows >= 1){
		playerShootArrow(option.charAt(0));
		isWumpusHitCheck(option.charAt(0));
		}else{
			output("You are out of arrows!");
		}
	}
	
	//public boolean
	
	public void output(String text){
		System.out.println(text);
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
		testmap.setPlayerArrows(4);
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
		numberOfBoundaryCaverns++;
	}
	
	public Integer getNumberOfInhabitableCaverns(){
		return numberOfCaverns - numberOfBoundaryCaverns; 
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
		String alertMessage = ("You shoot an arrow. You now have " + (getPlayerArrows()-1) + " arrows."); 
		if (playerArrows <= 0) {
			alertMessage = "You are out of arrows!";
			System.out.println(alertMessage);
			return alertMessage;
		}
		if (shotDirection == 'E') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX() + 1, getFinalArrowY())) {
				setFinalArrowXY(getFinalArrowX() +1, getFinalArrowY());
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			Integer arrowsInEndCavern = caverns[getFinalArrowX()][getFinalArrowY()].getNumberOfArrows();
			caverns[getFinalArrowX()][getFinalArrowY()].setNumberOfArrows(arrowsInEndCavern + 1);
			if (!(player.getPosX() == getFinalArrowX() && player.getPosY() == getFinalArrowY()))
				alertMessage = alertMessage + "You hear a clunk in the distance...";
			playerArrows -= 1; 
		}
		if (shotDirection == 'W') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX() - 1, getFinalArrowY())) {
				setFinalArrowXY(getFinalArrowX() - 1, getFinalArrowY());
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			Integer arrowsInEndCavern = caverns[getFinalArrowX()][getFinalArrowY()].getNumberOfArrows();
			caverns[getFinalArrowX()][getFinalArrowY()].setNumberOfArrows(arrowsInEndCavern + 1);
			if (!(player.getPosX() == getFinalArrowX() && player.getPosY() == getFinalArrowY()))
				alertMessage = alertMessage + "You hear a clunk in the distance...";
			playerArrows -= 1; 
		}
		if (shotDirection == 'N') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX(), getFinalArrowY() + 1)) {
				setFinalArrowXY(getFinalArrowX(), getFinalArrowY() + 1);
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			Integer arrowsInEndCavern = caverns[getFinalArrowX()][getFinalArrowY()].getNumberOfArrows();
			caverns[getFinalArrowX()][getFinalArrowY()].setNumberOfArrows(arrowsInEndCavern + 1);
			if (!(player.getPosX() == getFinalArrowX() && player.getPosY() == getFinalArrowY()))
				alertMessage = alertMessage + "You hear a clunk in the distance...";
			playerArrows -= 1; 
		}
		if (shotDirection == 'S') {
			setFinalArrowXY(player.getPosX(), player.getPosY());
			while(validateCavern(getFinalArrowX(), getFinalArrowY() - 1)) {
				setFinalArrowXY(getFinalArrowX(), getFinalArrowY() - 1);
			}			
			setCavernHasArrow(caverns[getFinalArrowX()][getFinalArrowY()]);
			Integer arrowsInEndCavern = caverns[getFinalArrowX()][getFinalArrowY()].getNumberOfArrows();
			caverns[getFinalArrowX()][getFinalArrowY()].setNumberOfArrows(arrowsInEndCavern + 1);
			if (!(player.getPosX() == getFinalArrowX() && player.getPosY() == getFinalArrowY()))
				alertMessage = alertMessage + "You hear a clunk in the distance...";
			playerArrows -= 1; 
		}
		System.out.println(alertMessage);
		return alertMessage;
}
	
	public void moveWumpus(){
		Random randomGenerator = new Random();
		Integer originalWumpusX = wumpusX;
		Integer originalWumpusY = wumpusY;
		
		do{
			wumpusX = originalWumpusX;
			wumpusY = originalWumpusY;
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
					output("You move east.");
				}
			else { 
				output("You cannot go East from here.");
				return ("You cannot go East from here."); 
				}
			}
			if (movementOption == 'W'){
				if(validateCavern(player.getPosX() - 1, player.getPosY())) {
					player.setPosX(player.getPosX() - 1);
					output("You move west.");
				}
			else {
				output("You cannot go West from here.");
				return ("You cannot go West from here."); 
				} 
			}
			if (movementOption == 'N'){
				if(validateCavern(player.getPosX(), player.getPosY() + 1)) {
					player.setPosY(player.getPosY() + 1);
					output("You move north.");
				}
			else {
				output("You cannot go North from here.");
				return ("You cannot go North from here.");
				}
			}
			if (movementOption == 'S'){
				if(validateCavern(player.getPosX(), player.getPosY() - 1)) {
					player.setPosY(player.getPosY() - 1);
					output("You move south.");
				}
			else {
				output("You cannot go South from here.");
				return "You cannot go South from here.";
				}  
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
		boolean batFound = false;
		for (Cavern cavern : getAdjacentCaverns()){
			if (cavern.getHasBat()){
				System.out.println("You hear the chirping of bats.");
				batFound = true;
			}
		}
		return batFound;
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
	
	public void arrowCheck() {
		
		Player player = getPlayer();
		Cavern[][] caverns = getCaverns();
		if (caverns[player.getPosX()][player.getPosY()].getHasArrow()) {
			playerArrows += caverns[getFinalArrowX()][getFinalArrowY()].getNumberOfArrows();
			output("You picked up an arrow! You now have " + playerArrows + " arrows");
			caverns[getFinalArrowX()][getFinalArrowY()].setNumberOfArrows(0);
		}
		caverns[getFinalArrowX()][getFinalArrowX()].setHasArrow(false);
	}
	
	public boolean isWumpusHitCheck(char direction) {
		Player player = getPlayer();
		if (direction == 'E') {
			if (getWumpusY() == player.getPosY() && getWumpusX() > player.getPosX()){
				System.out.println("Wumpus Hit! You have slain the monster");
				this.setGameOver(true);
				return true;
			}
		}
		if (direction == 'W') {
			if (getWumpusY() == player.getPosY() && getWumpusX() < player.getPosX()){
				System.out.println("Wumpus Hit! You have slain the monster");
				this.setGameOver(true);
				return true; 
			}
		}
		if (direction == 'N') {
			if (getWumpusX() == player.getPosX() && getWumpusY() > player.getPosY()){
				System.out.println("Wumpus Hit! You have slain the monster");
				this.setGameOver(true);
				return true; 
			}
		}
		if (direction == 'S') {
			if (getWumpusX() == player.getPosX() && getWumpusY() < player.getPosY()){
				System.out.println("Wumpus Hit! You have slain the monster");
				this.setGameOver(true);
				return true; 
			}
		}
		
		return false;
	}
	
	public void batWarp() {
		Random randomGenerator = new Random();
		Player player = this.getPlayer();
		
		do{
			int movementOptionX = (int) randomGenerator.nextInt(caverns.length - 2) + 1;
			int movementOptionY = (int) randomGenerator.nextInt(caverns[0].length - 2) + 1;
			player.setPlayerPos(movementOptionX, movementOptionY);
		} while (!validateCavern(player.getPosX(), player.getPosY()));
		getPlayer().setPlayerPos(player.getPosX(), player.getPosY());
		System.out.println("Bats fly you to an different location!");
	}
	
	public void batCheck() {
			Player player = getPlayer();
			Cavern[][] caverns = getCaverns();
			if (caverns[player.getPosX()][player.getPosY()].getHasBat()) {
				batWarp();
			}
		}
	
	public boolean playerDeathCheck() {
		Player player = getPlayer();
		Cavern[][] cavern = getCaverns();
		if(getWumpusX() == player.getPosX() && getWumpusY() == player.getPosY()) {
			System.out.println("The Wumpus has found you!");
			setGameOver(true);
			return true;
		}
		if(cavern[player.getPosX()][player.getPosY()].getHasPit()) {
			System.out.println("You walked into a pit! How unfortunate!");
			setGameOver(true);
			return true;
		}
		if(cavern[player.getPosX()][player.getPosY()].getHasArrow()) {
			System.out.println("You shot yourself!  Good aim.");
			setGameOver(true);
			return true;
		}
		return false;
}
	
}