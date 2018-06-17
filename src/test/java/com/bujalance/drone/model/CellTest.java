package com.bujalance.drone.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CellTest {

	@Test
	public void testIdGeneration() {
		// GIVEN a pair of coordinates
		int row = 1;
		int col = 2;

		// WHEN building a cell upon these coordinates
		Cell cell = new Cell(new Coords(row, col));

		// THEN its id is correctly generated
		assertEquals(Cell.ID_PREFIX + row + Cell.ID_COORDS_SEPARATOR + col, cell.getId());
	}
}
