package Test;

import static org.junit.Assert.*;
import main.Cavern;
import main.Player;
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
	public void testPlayerShootArrow_E_startAtSpecificSpot() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(1, 3);
		testMap.playerShootArrow('E');
		Cavern[][] testCaverns= testMap.getCaverns();
		System.out.println(testMap.getFinalArrowY());
		assertTrue(testCaverns[3][3].getHasArrow());
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
		testMap.arrowCheck();
		assertEquals(6, 0 + testMap.getPlayerArrows());	
	}
	
	@Test
	public void testWumpusHit_N() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 2);
		testMap.setWumpusXY(2, 3);
		assertTrue(testMap.isWumpusHitCheck('N'));
	}
	@Test
	public void testWumpusHit_S() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 2);
		testMap.setWumpusXY(2, 1);
		assertTrue(testMap.isWumpusHitCheck('S'));
	}
	@Test
	public void testWumpusHit_E() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 2);
		testMap.setWumpusXY(3, 2);
		assertTrue(testMap.isWumpusHitCheck('E'));
	}
	@Test
	public void testWumpusHit_W() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 2);
		testMap.setWumpusXY(1, 2);
		assertTrue(testMap.isWumpusHitCheck('W'));
	}
	
	@Test
	public void testBatWarpMovementIsRandom(){
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(2, 2);
		Integer pos1Count = 0;
		Integer pos2Count = 0;
		Integer pos3Count = 0;
		Integer pos4Count = 0;
		Integer pos5Count = 0;
		Integer pos6Count = 0;
		Integer pos7Count = 0;
		Integer pos8Count = 0;
		Integer pos9Count = 0;
		for (int i = 0; i < 9000; i++){
			testMap.batWarp();
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 1)
				pos1Count++;
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 2)
				pos2Count++;
			if (testMap.getPlayer().getPosX() == 1 && testMap.getPlayer().getPosY() == 3)
				pos3Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 1)
				pos4Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 2)
				pos5Count++;
			if (testMap.getPlayer().getPosX() == 2 && testMap.getPlayer().getPosY() == 3)
				pos6Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 1)
				pos7Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 2)
				pos8Count++;
			if (testMap.getPlayer().getPosX() == 3 && testMap.getPlayer().getPosY() == 3)
				pos9Count++;
			testMap.getPlayer().setPlayerPos(2, 2);
		}
		assertEquals(true, pos1Count > 750);
		assertEquals(true, pos2Count > 750);
		assertEquals(true, pos3Count > 750);
		assertEquals(true, pos4Count > 750);
		assertEquals(true, pos5Count > 750);
		assertEquals(true, pos6Count > 750);
		assertEquals(true, pos7Count > 750);
		assertEquals(true, pos8Count > 750);
		assertEquals(true, pos9Count > 750);
	}
	
	@Test
	public void testDeathCheck_Wumpus() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(3, 3);
		testMap.setWumpusXY(3, 3);
		assertTrue(testMap.playerDeathCheck());
	}
	
	@Test
	public void testDeathCheck_Pit() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		Cavern[][] caverns = testMap.getCaverns();
		caverns[1][3].setHasPit(true);
		testMap.getPlayer().setPlayerPos(1, 3);
		assertTrue(testMap.playerDeathCheck());
	}
	
	@Test
	public void testDeathCheck_Arrow() {
		WumpusMap gameMap = new WumpusMap(5,5);
		WumpusMap testMap = gameMap.generateWumpusTestMap();
		testMap.getPlayer().setPlayerPos(1, 2);
		assertTrue(testMap.playerDeathCheck());
	}
}
