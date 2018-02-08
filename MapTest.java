package Test;

import static org.junit.Assert.*;
import main.Cavern;
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
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.setWumpusX(2);
		gameMap.setWumpusY(2);
		Integer southCount = 0;
		Integer northCount = 0;
		Integer westCount = 0;
		Integer eastCount = 0;
		Integer restCount = 0;
		for (int i = 0; i < 5000; i++){
			gameMap.moveWumpus();
			if (gameMap.getWumpusX() == 1 && gameMap.getWumpusY() == 2)
				westCount++;
			if (gameMap.getWumpusX() == 3 && gameMap.getWumpusY() == 2)
				eastCount++;
			if (gameMap.getWumpusX() == 2 && gameMap.getWumpusY() == 1)
				southCount++;
			if (gameMap.getWumpusX() == 2 && gameMap.getWumpusY() == 3)
				northCount++;
			if (gameMap.getWumpusX() == 2 && gameMap.getWumpusY() == 2)
				restCount++;
			gameMap.setWumpusX(2);
			gameMap.setWumpusY(2);
		}
		assertEquals(true, southCount > 750);
		assertEquals(true, northCount > 750);
		assertEquals(true, eastCount > 750);
		assertEquals(true, westCount > 750);
		assertEquals(true, restCount > 750);
	}
	
	@Test
	public void testWumpusCannotMoveToBoundary(){
		WumpusMap boundedMap = new WumpusMap(5,5);
		boundedMap.setWumpusX(2);
		boundedMap.setWumpusY(2);
		boundedMap.setBoundaryCavern(3, 2);
		Integer eastCount = 0;
		for (int i = 0; i < 5000; i++){
			boundedMap.moveWumpus();
			if (boundedMap.getWumpusX() == 3 && boundedMap.getWumpusY() == 2)
				eastCount++;
			boundedMap.setWumpusX(2);
			boundedMap.setWumpusY(2);
		}
		assertEquals(0, eastCount - 0);
	}
	
	
	@Test
	public void testPlayerMove_N() {
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.getPlayer().setPlayerPos(2, 1);
		gameMap.movePlayer('N');
		assertEquals(2, 0 + gameMap.getPlayer().getPosY());
	}
	
	@Test
	public void testPlayerMove_S() {
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.getPlayer().setPlayerPos(2, 2);
		gameMap.movePlayer('S');
		assertEquals(1, 0 + gameMap.getPlayer().getPosY());
	}
	
	@Test
	public void testPlayerMove_W() {
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.getPlayer().setPlayerPos(2, 2);
		gameMap.movePlayer('W');
		assertEquals(1, 0 + gameMap.getPlayer().getPosX());
	}
	
	@Test
	public void testPlayerMove_E() {
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.getPlayer().setPlayerPos(2, 2);
		gameMap.movePlayer('E');
		assertEquals(3, 0 + gameMap.getPlayer().getPosX());
	}
	
	@Test
	public void testPlayerDoesNotMoveIntoBoundary() {
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.getPlayer().setPlayerPos(2, 2);
		gameMap.setBoundaryCavern(3, 2);
		gameMap.movePlayer('E');
		assertEquals(2, 0 + gameMap.getPlayer().getPosX());
	}
	
	@Test
	public void testHazardsDetected(){
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.getPlayer().setPlayerPos(2, 2);
		gameMap.setWumpusXY(1, 2);
		Cavern[][] caverns = gameMap.getCaverns();
		gameMap.setCavernHasBat(caverns[3][2]);
		gameMap.setCavernHasPit(caverns[2][3]);
		assertEquals(true, gameMap.wumpusIsAdjacent());
		assertEquals(true, gameMap.batsAreAdjacent());
		assertEquals(true, gameMap.pitIsAdjacent());
	}
	
	@Test
	public void testNoFalseHazardsDetected(){
		WumpusMap gameMap = new WumpusMap(5,5);
		gameMap.getPlayer().setPlayerPos(2, 2);
		gameMap.setWumpusXY(3, 3);
		Cavern[][] caverns = gameMap.getCaverns();
		gameMap.setCavernHasBat(caverns[1][1]);
		gameMap.setCavernHasPit(caverns[1][3]);
		assertEquals(false, gameMap.wumpusIsAdjacent());
		assertEquals(false, gameMap.batsAreAdjacent());
		assertEquals(false, gameMap.pitIsAdjacent());
	}
	
	@Test
	public void testPlayerShootArrow_N() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 1);
		testMap.playerShootArrow('N');
		Cavern[][] testCaverns= testMap.getCaverns();
		assertTrue(testCaverns[2][3].getHasArrow());
	}
	@Test
	public void testPlayerShootArrow_S() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 1);
		testMap.playerShootArrow('S');
		Cavern[][] testCaverns= testMap.getCaverns();
		assertTrue(testCaverns[2][1].getHasArrow());
	}
	@Test
	public void testPlayerShootArrow_E() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 1);
		testMap.playerShootArrow('E');
		Cavern[][] testCaverns= testMap.getCaverns();
		assertTrue(testCaverns[3][1].getHasArrow());
	}
	@Test
	public void testPlayerShootArrow_W() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 1);
		testMap.playerShootArrow('W');
		Cavern[][] testCaverns= testMap.getCaverns();
		assertTrue(testCaverns[1][1].getHasArrow());
	}
	@Test
	public void testArrowCheck() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(1, 2);
		testMap.arrowCheck(testMap.getPlayer().getPosX(), testMap.getPlayer().getPosY());
		assertEquals(6, 0 + testMap.getPlayerArrows());	
	}
}