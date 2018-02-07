package Test;

import static org.junit.Assert.*;
import main.WumpusMap;

import org.junit.Test;

public class MapTest {

	@Test
	public void testMapNotNull() {
		WumpusMap map = new WumpusMap(1,1);
		assertNotNull(map);
	}
	
	@Test
	public void testMakeMapOfTwoCaverns(){
		WumpusMap smallestMap = new WumpusMap(1,1);
		assertEquals(0, 1 - smallestMap.getNumberOfCaverns());
	}
	
	@Test
	public void testTopRowOfBoundariesIdentified(){
		WumpusMap boundedMap = new WumpusMap(5,5);
		assertEquals(0, 25 - boundedMap.getNumberOfCaverns());
		for (int i = 0; i < 5; i++)
			boundedMap.setBoundaryCavern(i, 4);
		for (int xIndex = 0; xIndex < 5; xIndex++){
			for (int yIndex = 0; yIndex < 5; yIndex++){
				if (yIndex == 4){
					assertEquals(true, boundedMap.getCaverns()[xIndex][yIndex].getIsBoundary());
				} else{
					assertEquals(false, boundedMap.getCaverns()[xIndex][yIndex].getIsBoundary());
				}
			}
		}
	}
	
	@Test
	public void testWumpusMovementIsRandom(){
		WumpusMap boundedMap = new WumpusMap(5,5);
		boundedMap.setWumpusX(2);
		boundedMap.setWumpusY(2);
		Integer southCount = 0;
		Integer northCount = 0;
		Integer westCount = 0;
		Integer eastCount = 0;
		Integer restCount = 0;
		for (int i = 0; i < 1000; i++){
			boundedMap.moveWumpus();
			if (boundedMap.getWumpusX() == 1 && boundedMap.getWumpusY() == 2)
				westCount++;
			if (boundedMap.getWumpusX() == 3 && boundedMap.getWumpusY() == 2)
				eastCount++;
			if (boundedMap.getWumpusX() == 2 && boundedMap.getWumpusY() == 1)
				southCount++;
			if (boundedMap.getWumpusX() == 2 && boundedMap.getWumpusY() == 3)
				northCount++;
			if (boundedMap.getWumpusX() == 2 && boundedMap.getWumpusY() == 2)
				restCount++;
			boundedMap.setWumpusX(2);
			boundedMap.setWumpusY(2);
		}
	}
	
	
}
