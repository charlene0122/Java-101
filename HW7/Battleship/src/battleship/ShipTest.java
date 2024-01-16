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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		//case 1
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//case 2
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		//case 3
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		//case 4
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
		//case 5
		ship = new  EmptySea();
		assertEquals(1, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		//case 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(0, battleship.getBowRow());
		
		//case 2 cruiser
		Ship cruiser = new Cruiser();
		row = 6;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(6, cruiser.getBowRow());

		//case 3 submarine
		Ship submarine = new Submarine();
		row = 1;
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(1, submarine.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		// Case 1 battleship
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(4, battleship.getBowColumn());

		// Case 2 cruiser
		Ship cruiser = new Cruiser();
		row = 6;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(6, cruiser.getBowColumn());
		cruiser.setBowColumn(9);
		assertEquals(9, cruiser.getBowColumn());
		
		// Case 3 submarine
		Ship submarine = new Submarine();
		row = 1;
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(7, submarine.getBowColumn());
	}
		

	@Test
	void testGetHit() {
		// Case 1 Battleship
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);

		// Case 2 Cruiser
		ship = new Cruiser();
		hits = new boolean[]{false, false, false};
		assertArrayEquals(hits, ship.getHit());
		
		int row = 6;
		int column = 6;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(6, 6));
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		
		// Case 3 Destroyer
		ship = new Destroyer();
		hits = new boolean[]{true, true};
		
		row = 9;
		column = 9;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(ocean.shootAt(9, 8));
		assertArrayEquals(hits, ship.getHit());
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertTrue(ship.isSunk());

	}

	@Test
	void testGetShipType() {
		// Case 1
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		// Case 2
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		// Case 3
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		// Case 4
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		
		// Case 5
		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		// Case 1 battleship
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		// Case 2 cruiser
		Ship cruiser = new Cruiser();
		row = 6;
		column = 6;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(cruiser.isHorizontal());
		
		// Case 3 submarine
		Ship submarine = new Submarine();
		row = 1;
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarine.isHorizontal());

	}
	
	@Test
	void testSetBowRow() {
		
		// Case 1 battleship
		Ship battleship = new Battleship();
		int row = 0;
		battleship.setBowRow(row);
		assertEquals(0, battleship.getBowRow());
		
		// Case 2 cruiser
		Ship cruiser = new Cruiser();
		row = 6;
		cruiser.setBowRow(row);
		assertEquals(6, cruiser.getBowRow());

		// Case 3 submarine
		Ship submarine = new Submarine();
		row = 1;
		submarine.setBowRow(row);
		assertEquals(1, submarine.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		// Case 1 battleship
		Ship battleship = new Battleship();
		int column = 4;
		battleship.setBowColumn(column);
		assertEquals(4, battleship.getBowColumn());

		// Case 2 cruiser
		Ship cruiser = new Cruiser();
		column = 6;
		cruiser.setBowRow(column);
		assertEquals(6, cruiser.getBowRow());

		// Case 3 submarine
		Ship submarine = new Submarine();
		column = 7;
		submarine.setBowRow(column);
		assertEquals(7, submarine.getBowRow());
	}

	@Test
	void testSetHorizontal() {
		// Case 1 battleship
		Ship battleship = new Battleship();
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());

		// Case 2 cruiser
		Ship cruiser = new Cruiser();
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());

		// Case 3 submarine
		Ship submarine = new Submarine();
		horizontal = false;
		submarine.setHorizontal(horizontal);
		assertFalse(submarine.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		// test when other ships are not in the ocean
		// Case 1: top row
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		// Case 2: right lower corner
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		// Case 3: boundary limit
		Ship cruiser1 = new Cruiser();
		row = 8;
		column = 1;
		ok = cruiser1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Illegal to place ship here.");

		// Case 4: boundary limit
		Ship cruiser2 = new Cruiser();
		row = 0;
		column = 2;
		horizontal = false;
		ok = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Illegal to place ship here.");
		
		// Case 5: boundary limit
		Ship cruiser3 = new Cruiser();
		row = 10;
		column = 4;
		horizontal = false;
		ok = cruiser3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Illegal to place ship here.");

	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
	
		//test third ship
		Ship destoryer1 = new Destroyer();
		row = 5;
		column = 5;
		horizontal = false;
		boolean ok3 = destoryer1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "OK to place ship here.");
		destoryer1.placeShipAt(row, column, horizontal, ocean);
		
		//test forth ship
		Ship destoryer2 = new Destroyer();
		row = 0;
		column = 3;
		horizontal = true;
		boolean ok4 = destoryer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship vertically adjacent above.");

		// Cases 1: adjacent check -- horizontal
		Ship cruiser1 = new Cruiser();
		row = 5;
		column = 4;
		horizontal = true;
		boolean ok5 = cruiser1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5, "Not OK to place ship horizontally adjacent below.");

		// Cases 2: adjacent check -- vertical
		Ship cruiser2 = new Cruiser();
		row = 3;
		column = 5;
		horizontal = false;
		boolean ok6 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok6, "Not OK to place ship vertically adjacent below.");
		
		// Cases 3: adjacent check -- diagonal
		Ship cruiser3 = new Cruiser();
		row = 3;
		column = 4;
		horizontal = true;
		boolean ok7 = cruiser3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok7, "Not OK to place ship diagonally adjacent below.");
		
	}

	@Test
	void testPlaceShipAt() {
		
		// Case 1: empty ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(0, battleship.getBowRow());
		assertEquals(4, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);

		// Case 2
		Ship cruiser = new Cruiser();
		row = 6;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(6, cruiser.getBowRow());
		assertEquals(6, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[6][7].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[6][5]);

		// Case 3
		Ship destroyer = new Destroyer();
		row = 1;
		column = 7;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(1, destroyer.getBowRow());
		assertEquals(7, destroyer.getBowColumn());
		assertFalse(destroyer.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[1][6].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[1][7]);
	}

	@Test
	void testShootAt() {

		// Case 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());

		// Case 2
		Ship cruiser = new Cruiser();
		row = 6;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(cruiser.shootAt(6, 6));
		boolean[] hitArray1 = {true, false, false};
		assertArrayEquals(hitArray1, cruiser.getHit());
		

		// Case 3
		Ship destroyer = new Destroyer();
		row = 1;
		column = 7;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(destroyer.shootAt(0, 7));
		boolean[] hitArray2 = {false, true};
		assertArrayEquals(hitArray2, destroyer.getHit());
	

		// Case 4
		Ship submarine = new Submarine();
		row = 7;
		column = 3;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.shootAt(7, 4));
		assertTrue(submarine.shootAt(7, 3));
		assertFalse(submarine.shootAt(7, 3));
		boolean[] hitArray3 = {true};
		assertArrayEquals(hitArray3, submarine.getHit());
		assertTrue(submarine.isSunk());
	}
	
	@Test
	void testIsSunk() {

		// Case 1
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
		
		// Case 2
		Ship destroyer = new Destroyer();
		row = 6;
		column = 6;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(destroyer.shootAt(6, 6));
		assertTrue(destroyer.shootAt(6, 5));
		assertTrue(destroyer.isSunk());
		
		
		// Case 3
		Ship battleship = new Battleship();
		row = 1;
		column = 7;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertFalse(battleship.isSunk());
		assertFalse(battleship.shootAt(0, 9));
		assertTrue(battleship.shootAt(1, 6));
		assertFalse(battleship.isSunk());
	}

	@Test
	void testToString() {
		// Case 1
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		assertTrue(battleship.shootAt(8, 1));
		assertEquals("x", battleship.toString());
		assertTrue(battleship.shootAt(7, 1));
		assertEquals("x", battleship.toString());
		assertTrue(battleship.shootAt(6, 1));
		assertEquals("s", battleship.toString());
		
		// Case 2
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());
		
		row = 1;
		column = 7;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(submarine.shootAt(1, 7));
		assertEquals("s", submarine.toString());

		// Case 3
		Ship empty = new EmptySea();
		assertEquals("-", empty.toString());

	}

}
