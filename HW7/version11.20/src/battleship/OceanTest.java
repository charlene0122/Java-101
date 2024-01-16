/**
 * CIT 5910 HW7: Battleship
 * Implementing a simple version of the Battleship game.
 * 
 * @author Di Yang & Zhifei Wu
 * Penn ID: 74113922 30406975
 * This java file is solely completed by the author mentioned above, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		// test case #1: create a destroyer and see if seas are correctly occupied
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		assertTrue(ocean.isOccupied(0, 5));
		assertFalse(ocean.isOccupied(2, 5));
		assertFalse(ocean.isOccupied(1, 4));
		
		// test case #2: create a submarine and see if seas are correctly occupied
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(0, 0));
		assertFalse(ocean.isOccupied(0, 1));
		assertFalse(ocean.isOccupied(1, 1));
		
		// test case #3: create a battleship and see if seas are correctly occupied
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(9, 9));
		assertTrue(ocean.isOccupied(9, 8));
		assertTrue(ocean.isOccupied(9, 7));
		assertTrue(ocean.isOccupied(9, 6));
		assertFalse(ocean.isOccupied(9, 5));
		assertFalse(ocean.isOccupied(8, 9));
		assertFalse(ocean.isOccupied(7, 9));
		
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		// test case #1: create a destroyer, shoot it until it is sunk
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		// one spot is shot, should not be sunk
		assertTrue(ocean.shootAt(1, 5));
		assertTrue(ocean.shootAt(1, 5));
		assertEquals(3, ocean.getShotsFired());
		assertEquals(2, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertFalse(destroyer.isSunk());
		
		// both spot are shot, should be sunk
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(4, ocean.getShotsFired());
		assertEquals(3, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// ship sunk, additional shot should increase shots fired but not hit count
		assertFalse(ocean.shootAt(0, 5));
		assertFalse(ocean.shootAt(1, 5));
		assertEquals(6, ocean.getShotsFired());
		assertEquals(3, ocean.getHitCount());
		
		// test case #2: create a submarine, shoot it until it is sunk
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		
		assertFalse(ocean.shootAt(1, 1));		// shoot at wrong place, should not sunk
		assertTrue(ocean.shootAt(0, 0));		// shoot at right place, should sunk now
		assertFalse(ocean.shootAt(0, 0)); 		// ship sunk, should return false
		assertEquals(9, ocean.getShotsFired());	// 3 more shots
		assertEquals(4, ocean.getHitCount());	// but only 1 hit the ship
		assertEquals(2, ocean.getShipsSunk());
		assertTrue(submarine.isSunk());
		
		// test case #3: create a battleship, shoot it until it is sunk
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(8, 9));		// shoot at wrong place, return false
		assertTrue(ocean.shootAt(9, 9));	
		assertTrue(ocean.shootAt(9, 9));		// shoot at same place but ship is afloat, return true
		assertTrue(ocean.shootAt(9, 8));	
		assertTrue(ocean.shootAt(9, 7));
		assertFalse(battleship.isSunk());		// has not sunk
		assertTrue(ocean.shootAt(9, 6));		// should sunk after this shot
		assertTrue(battleship.isSunk());
		assertFalse(ocean.shootAt(9, 8)); 		// ship sunk, should return false
		assertEquals(16, ocean.getShotsFired());	// 7 more shots
		assertEquals(9, ocean.getHitCount());	// 5 hit the ship
		assertEquals(3, ocean.getShipsSunk());	
		
	}

	@Test
	void testGetShotsFired() {
		
		// test case #1: shoot at empty place, see if shotsFired increase correctly
		// should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		// test case #2: shoot down a destroyer, count number of shots
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());	// 2 more shots, should increase by 2
		
		// keep shooting after it sunk, should also increase shot
		assertFalse(ocean.shootAt(1, 5));
		assertFalse(ocean.shootAt(0, 5)); 
		assertEquals(8, ocean.getShotsFired());	// 2 more shots, should increase by 2
		
		// test case #3, shoot down submarine, count number of shots
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		
		// keep shooting after it sunk, should also increase shot
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(10, ocean.getShotsFired());	// 2 more shots, should increase by 2
		
		
	}

	@Test
	void testGetHitCount() {
		
		// test case #1: shoot a destroyer, count number of hits
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		// shoot at the same place, when ship is afloat, number of hit should increase
		assertTrue(ocean.shootAt(1, 5));
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(3, ocean.getHitCount());
		
		// test case #2, shoot down submarine, count number of hits
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		// shoot down the submarine
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(4, ocean.getHitCount());
		
		// when the ship is sunk, additional shoot at it should not increase hit count
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(4, ocean.getHitCount());
		
		// test case #3: shoot down a battleship, count number of hits
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		// shoot down the battleship
		assertTrue(ocean.shootAt(9, 9));	
		assertTrue(ocean.shootAt(9, 9));		// shoot at same place but ship is afloat, return true
		assertTrue(ocean.shootAt(9, 8));	
		assertTrue(ocean.shootAt(9, 7));
		assertTrue(ocean.shootAt(9, 6));		// should sunk after this shot
		assertTrue(battleship.isSunk());
		assertEquals(9, ocean.getHitCount());
		
		// when the ship is sunk, additional shoot at it should not increase hit count
		assertFalse(ocean.shootAt(9, 9));	
		assertFalse(ocean.shootAt(9, 9));		
		assertFalse(ocean.shootAt(9, 8));	
		assertFalse(ocean.shootAt(9, 7));
		assertFalse(ocean.shootAt(9, 6));		
		assertEquals(9, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		// test case #1: shoot a destroyer, count number of ships sunk
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(1, ocean.getShipsSunk());
		
		// test case #2, shoot down submarine, count number of ships sunk
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		// shoot down the submarine
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(2, ocean.getShipsSunk());

		// test case #3: shoot down a battleship, count number of ships sunk
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		// shoot down the battleship
		assertTrue(ocean.shootAt(9, 9));	
		assertTrue(ocean.shootAt(9, 9));		// shoot at same place but ship is afloat, return true
		assertTrue(ocean.shootAt(9, 8));	
		assertTrue(ocean.shootAt(9, 7));
		assertFalse(battleship.isSunk());
		assertEquals(2, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(9, 6));		// should sunk after this shot
		assertTrue(battleship.isSunk());
		assertEquals(3, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		// test case #1: place a new destroyer
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals("destroyer", shipArray[1][5].getShipType());
		assertEquals("destroyer", shipArray[0][5].getShipType());
		assertEquals("empty", shipArray[0][6].getShipType());
		assertEquals("empty", shipArray[1][4].getShipType());
		
		// test case #2: place a submarine
		Ship submarine = new Submarine();
		row = 8;
		column = 6;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals("submarine", shipArray[8][6].getShipType());
		assertEquals("empty", shipArray[8][5].getShipType());
		assertEquals("empty", shipArray[8][7].getShipType());
		
		// test case #3: place a battleship
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("battleship", shipArray[9][8].getShipType());
		assertEquals("battleship", shipArray[9][7].getShipType());
		assertEquals("battleship", shipArray[9][6].getShipType());
		assertEquals("battleship", shipArray[9][9].getShipType());
		assertEquals("empty", shipArray[9][5].getShipType());
		assertEquals("empty", shipArray[8][9].getShipType());
		
	}

}
