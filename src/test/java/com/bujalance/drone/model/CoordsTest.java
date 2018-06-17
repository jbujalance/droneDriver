package com.bujalance.drone.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoordsTest {

	@Test
	public void constructorTest() {
		// GIVEN a pair of coordinates
		int row = 2;
		int col = 3;

		// WHEN building coordinates upon this pair
		Coords coords = new Coords(row, col);

		// THEN the row and column fields are correctly initialized
		assertEquals(row, coords.getRow());
		assertEquals(col, coords.getCol());
	}

	@Test
	public void equalityTest() {
		// GIVEN a pair of coordinates to check equality on
		Coords subject = new Coords(1, 2);
		// AND pairs of equal and different coordinates
		Coords equal = new Coords(1, 2);
		Coords differentRow = new Coords(0, 2);
		Coords differentCol = new Coords(1, 0);
		Coords allDifferent = new Coords(0, 0);

		// THEN the equality is correctly evaluated
		assertTrue(subject.equals(equal));
		assertFalse(subject.equals(null));
		assertFalse(subject.equals(Integer.valueOf(1)));
		assertFalse(subject.equals(differentRow));
		assertFalse(subject.equals(differentCol));
		assertFalse(subject.equals(allDifferent));
	}
}
