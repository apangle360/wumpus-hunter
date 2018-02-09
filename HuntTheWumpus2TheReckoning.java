package main;

import java.util.Scanner;

public class HuntTheWumpus2TheReckoning {
	public static void main(String[] args){
		boolean restart = false;
		
		do{
		System.out.println("WUMPUS HUNTER 2:");
		System.out.println("THE RECKONING");
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.setCavernHasPit(gameMap.getCaverns()[2][2]);
		gameMap.setCavernHasBat(gameMap.getCaverns()[3][2]);
		gameMap.setCavernHasWumpus(gameMap.getCaverns()[2][2]);
		gameMap.setPlayerArrows(5);
		gameMap.setWumpusXY(2, 2);
		gameMap.getPlayer().setPlayerPos(1, 1);
		for(int i = 0; i < 5; i++ ) {
			gameMap.setBoundaryCavern(0, i);
			gameMap.setBoundaryCavern(i, 0);
			gameMap.setBoundaryCavern(i, 4);
			gameMap.setBoundaryCavern(4, i);
		}
		
		while (!gameMap.getGameOver())
			gameMap.playerTurn();
		System.out.println("Play again?");
		Scanner reader = new Scanner(System.in);
		System.out.println("Press Y to Play Again.");
		String input = reader.next();
		if (input.charAt(0) == 'Y'){
			restart = false;
		}else{
			System.out.println("Thanks for playing");
		restart = true;
		}
		}while(!restart);
	}
}
