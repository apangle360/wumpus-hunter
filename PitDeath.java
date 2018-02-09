package fixtures;

import main.Player;
import main.WumpusMap;

public class PitDeath {
	private WumpusMap wumpusMap = new WumpusMap(5,5);
	private WumpusMap testMap = wumpusMap.generateWumpusTestMap();
	private char direction;
	private Player player = testMap.getPlayer();
	
	public void setPlayerX(int playerX) {
		player.setPosX(playerX);
	}

	public void setPlayerY(int playerY) {
		player.setPosY(playerY);
	} 
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public void posX(Integer extra){
		
	}
	
public void posY(Integer extra){
		
	}
	public String status(){
		testMap.setWumpusXY(3, 3);
		testMap.movePlayer(direction);
		if(testMap.playerDeathCheck()) return "You died";
		return "nope";
	}
	
}