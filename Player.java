package main;

public class Player {
	private Integer playerX;
	private Integer playerY;
	
	public Player(Integer playerX, Integer playerY){
		this.playerX = playerX;
		this.playerY = playerY;
	}
	
	public void setPlayerPos(Integer playerX, Integer playerY){
		this.playerX = playerX;
		this.playerY = playerY;
	}
	
	public Integer getPosX(){
		return this.playerX;
	}
	
	public Integer getPosY(){
		return this.playerY;
	}
	
	public void setPosX(Integer posX){
		this.playerX = posX;
	}
	
	public void setPosY(Integer posY){
		this.playerY = posY;
	}

}
