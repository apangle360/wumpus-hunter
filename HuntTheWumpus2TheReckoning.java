package main;

public class HuntTheWumpus2TheReckoning {
	public static void main(String[] args){
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.setCavernHasPit(gameMap.getCaverns()[2][2]);
		gameMap.setCavernHasBat(gameMap.getCaverns()[3][2]);
		gameMap.setCavernHasWumpus(gameMap.getCaverns()[2][2]);
		gameMap.setPlayerArrows(5);
		gameMap.setWumpusXY(2, 2);
		gameMap.getPlayer().setPlayerPos(1, 1);
		while (!gameMap.getGameOver())
			gameMap.playerTurn();
		System.out.println("Play again?");
	}
}
