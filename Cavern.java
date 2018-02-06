package main;

public class Cavern {
	private boolean hasBat;
	private boolean hasArrow;
	private boolean hasPit;
	private boolean isBoundary = false;
	
	public Cavern(){
		
	}
	
	public boolean getHasBat(){
		return hasBat;
	}
	
	public void setHasBat(boolean hasBat){
		this.hasBat = hasBat;
	}
	
	public boolean getHasArrow(){
		return hasArrow;
	}
	
	public void setHasArrow(boolean hasArrow){
		this.hasArrow = hasArrow;
	}
	
	public boolean getHasPit(){
		return hasPit;
	}
	
	public void setHasPit(boolean hasPit){
		this.hasPit = hasPit;
	}
	
	public boolean getIsBoundary(){
		return isBoundary;
	}
	
	public void setIsBoundary(boolean isBoundary){
		this.isBoundary = isBoundary;
	}
}
