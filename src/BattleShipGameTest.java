import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;


public class BattleShipGameTest {
	
	@Test
	public void testHorizontalRotations(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][] gameBoard = testBoard.getGameBoard();
		testBoard.putBoat(0, 0, new BattleShip(false));
		assertFalse("Point should not be empty", gameBoard[0][0].isEmpty());
		assertFalse("Point should not be empty", gameBoard[0][1].isEmpty());
		assertFalse("Point should not be empty", gameBoard[0][2].isEmpty());
		assertFalse("Point should not be empty", gameBoard[0][3].isEmpty());
		assertTrue("Point should be empty", gameBoard[1][0].isEmpty());
	}
	
	@Test
	public void testVerticalRotations(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][] gameBoard = testBoard.getGameBoard();
		testBoard.putBoat(0, 0, new BattleShip(true));
		assertFalse("Point should not be empty", gameBoard[0][0].isEmpty());
		assertFalse("Point should not be empty", gameBoard[1][0].isEmpty());
		assertFalse("Point should not be empty", gameBoard[2][0].isEmpty());
		assertFalse("Point should not be empty", gameBoard[3][0].isEmpty());
		assertTrue("Point should be empty", gameBoard[0][1].isEmpty());
	}

	@Test
	public void testHasWon(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][] gameBoard = testBoard.getGameBoard();
		
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		testBoard.putBoat(0, 1, new BattleShip(false));
		testBoard.putBoat(0, 2, new Destroyer(false));
		testBoard.putBoat(0, 3, new PatrolBoat(false));
		
		testBoard.targetLocation(0, 0);
		testBoard.targetLocation(1, 0);
		testBoard.targetLocation(2, 0);
		testBoard.targetLocation(3, 0);
		testBoard.targetLocation(4, 0);
		
		testBoard.targetLocation(0, 1);
		testBoard.targetLocation(1, 1);
		testBoard.targetLocation(2, 1);
		testBoard.targetLocation(3, 1);
		
		testBoard.targetLocation(0, 2);
		testBoard.targetLocation(1, 2);
		testBoard.targetLocation(2, 2);
		
		testBoard.targetLocation(0, 3);
		testBoard.targetLocation(1, 3);
		
		assertTrue("Board should be sunk", testBoard.hasWon());
		
	}
	
	@Test
	public void testHasNotYetWon(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][] gameBoard = testBoard.getGameBoard();
		
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		testBoard.putBoat(0, 1, new BattleShip(false));
		testBoard.putBoat(0, 2, new Destroyer(false));
		testBoard.putBoat(0, 3, new PatrolBoat(false));
		
		testBoard.targetLocation(0, 0);
		testBoard.targetLocation(1, 0);
		testBoard.targetLocation(2, 0);
		testBoard.targetLocation(3, 0);
		testBoard.targetLocation(4, 0);
		
		testBoard.targetLocation(0, 1);
		testBoard.targetLocation(1, 1);
		testBoard.targetLocation(2, 1);
		testBoard.targetLocation(3, 1);
		
		testBoard.targetLocation(0, 2);
		testBoard.targetLocation(1, 2);
		testBoard.targetLocation(2, 2);
		
		testBoard.targetLocation(0, 3);
		
		assertFalse("Board should be sunk", testBoard.hasWon());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void placeShipOutOfBoundsHorizontal(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		testBoard.putBoat(6, 0, new AircraftCarrier(false));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void placeShipOutOfBoundsVertical(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		testBoard.putBoat(0, 6, new AircraftCarrier(true));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceAlreadyPlaced(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		testBoard.putBoat(0, 0, new AircraftCarrier(true));		
		testBoard.putBoat(0, 0, new Destroyer(true));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceAlreadyPlacedOneOverlapping(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		testBoard.putBoat(0, 0, new AircraftCarrier(false));		
		testBoard.putBoat(4, 0, new Destroyer(false));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHitAlreadyAttacked(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		testBoard.putBoat(0, 0, new AircraftCarrier(false));		
		testBoard.putBoat(4, 0, new Destroyer(false));
		testBoard.targetLocation(0, 0);
		testBoard.targetLocation(0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHadAttackedPoint(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		testBoard.putBoat(0, 0, new AircraftCarrier(false));		
		testBoard.putBoat(4, 0, new Destroyer(false));
	}
	
	@Test
	public void testIfEmpty(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][]gameBoard = testBoard.getGameBoard();
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		assertTrue(gameBoard[9][9].isEmpty());
	}

	@Test
	public void testshipShouldSink(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		testBoard.putBoat(0, 1, new BattleShip(false));
		testBoard.putBoat(0, 2, new Destroyer(false));
		testBoard.putBoat(0, 3, new PatrolBoat(false));
		
		testBoard.targetLocation(0, 0);
		testBoard.targetLocation(1, 0);
		testBoard.targetLocation(2, 0);
		testBoard.targetLocation(3, 0);
		testBoard.targetLocation(4, 0);
		
		testBoard.targetLocation(0, 1);
		testBoard.targetLocation(1, 1);
		testBoard.targetLocation(2, 1);
		testBoard.targetLocation(3, 1);
		
		testBoard.targetLocation(0, 2);
		testBoard.targetLocation(1, 2);
		testBoard.targetLocation(2, 2);
		
		testBoard.targetLocation(0, 3);
		testBoard.targetLocation(1, 3);
		
		Ship[] arrayShips = testBoard.getArrayShips();
		assertTrue(arrayShips[0].Destroyed());
		assertTrue(arrayShips[1].Destroyed());
		assertTrue(arrayShips[2].Destroyed());
		assertTrue(arrayShips[3].Destroyed());
	}
	
	@Test
	public void testPointsHaveSunk(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][]gameBoard = testBoard.getGameBoard();
		
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		testBoard.putBoat(0, 1, new BattleShip(false));
		testBoard.putBoat(0, 2, new Destroyer(false));
		testBoard.putBoat(0, 3, new PatrolBoat(false));
		
		testBoard.targetLocation(0, 0);
		testBoard.targetLocation(1, 0);
		testBoard.targetLocation(2, 0);
		testBoard.targetLocation(3, 0);
		testBoard.targetLocation(4, 0);
		
		testBoard.targetLocation(0, 1);
		testBoard.targetLocation(1, 1);
		testBoard.targetLocation(2, 1);
		testBoard.targetLocation(3, 1);
		
		testBoard.targetLocation(0, 2);
		testBoard.targetLocation(1, 2);
		testBoard.targetLocation(2, 2);
		
		testBoard.targetLocation(0, 3);
		testBoard.targetLocation(1, 3);
		
		testBoard.hasAShipSunk();
		testBoard.hasAShipSunk();
		testBoard.hasAShipSunk();
		testBoard.hasAShipSunk();
		
		assertTrue(gameBoard[0][0].hasShipSunk());
		assertTrue(gameBoard[0][1].hasShipSunk());
		assertTrue(gameBoard[0][2].hasShipSunk());
		assertTrue(gameBoard[0][3].hasShipSunk());
	}
	
	@Test
	public void testShipNameHasJustSunk(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][]gameBoard = testBoard.getGameBoard();
		
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		testBoard.putBoat(0, 1, new BattleShip(false));
		testBoard.putBoat(0, 2, new Destroyer(false));
		testBoard.putBoat(0, 3, new PatrolBoat(false));
		
		testBoard.targetLocation(0, 0);
		testBoard.targetLocation(1, 0);
		testBoard.targetLocation(2, 0);
		testBoard.targetLocation(3, 0);
		testBoard.targetLocation(4, 0);

		testBoard.hasAShipSunk();
		String shipName = testBoard.returnSunkShipName();
		assertEquals("Aircraft-Carrier", shipName);
	}
	
	@Test
	public void testShipNameShouldOnlyShowSunkOnce(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		Points[][]gameBoard = testBoard.getGameBoard();
		
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		testBoard.putBoat(0, 1, new BattleShip(false));
		testBoard.putBoat(0, 2, new Destroyer(false));
		testBoard.putBoat(0, 3, new PatrolBoat(false));
		
		testBoard.targetLocation(0, 0);
		testBoard.targetLocation(1, 0);
		testBoard.targetLocation(2, 0);
		testBoard.targetLocation(3, 0);
		testBoard.targetLocation(4, 0);

		testBoard.hasAShipSunk();
		assertFalse(testBoard.hasAShipSunk());
	}
	
	@Test
	public void testRandomAIGenerator(){
		Board testBoard = new Board(10, 10, 4, "test", false);
		testBoard.putBoat(0, 0, new AircraftCarrier(false));
		testBoard.putBoat(0, 1, new BattleShip(false));
		testBoard.putBoat(0, 2, new Destroyer(false));
		testBoard.putBoat(0, 3, new PatrolBoat(false));
		
		for (int i = 0; i < 100; i++){
			testBoard.ComputerAttackRandom();
		}
		assertTrue(testBoard.hasWon());
	}
	
}
	