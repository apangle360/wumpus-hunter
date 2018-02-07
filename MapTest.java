package Test;

import static org.junit.Assert.*;
import main.WumpusMap;

import org.junit.Test;

public class MapTest {

	@Test
	public void testMapNotNull() {
		WumpusMap map = new WumpusMap(1,0);
		assertNotNull(map);
	}
	
	@Test
	public void testMakeMapOfTwoCaverns(){
		WumpusMap smallestMap = new WumpusMap(1,2);
		assertEquals(0, 2 - smallestMap.getNumberOfCaverns());
	}
	
	@Test
	public void testBoundaryIdentification(){
		WumpusMap boundedMap = new WumpusMap(5,5);
		assertEquals(0, 25 - boundedMap.getNumberOfCaverns());
	}
	@Test
	public void testPlayerLocation() {
		WumpusMap playerLocalTestMap = new WumpusMap(5,5);
		playerLocalTestMap[4][2].setPlayerLocation;
		assertTrue(playerLocalTestMap[4][2]);
	}
		
}
