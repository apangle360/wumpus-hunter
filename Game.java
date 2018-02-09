package main;

public class Game {
	
	WumpusMap gameMap = new WumpusMap(5,5);
	public void game() {
		playerTurn();
		wumpusTurn();
	}
	
	public void playerTurn() {
		System.out.println("What will you do?");
		playerAction();
	}
	
	public void wumpusTurn() {
		gameMap.moveWumpus();
		gameMap.playerDeathCheck();
	}
	
	public void actionArrow() {
		gameMap.playerShootArrow(shotDirection);
		gameMap.isWumpusHitCheck(direction);
	}
	public void actionMove() {
		Cavern[][] cavern = gameMap.getCaverns();
		gameMap.movePlayer(movementOption);
		do{roomCheck();}
		while (cavern[gameMap.getPlayer().getPosX()][gameMap.getPlayer().getPosY()].getHasBat());
		roomCheck();
		gameMap.playerDeathCheck();
			
	}
	public void actionRest() {
		System.out.println("You rest in the cavern you are in.");
	}
	public void adjacentCavernCheck() {
		
	}
	
	public void roomCheck() {
		gameMap.arrowCheck();
		gameMap.batCheck();
	}

}
