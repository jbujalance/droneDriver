package com.bujalance.drone.model;

import static org.junit.Assert.*;
import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import org.junit.Test;

import java.util.Optional;

public class GridTest {

	@Test
	public void getCellIdByCoordinatesForCellInBounds() {
		// GIVEN a Grid
		Grid grid = new Grid(3, 3);

		// WHEN retrieving a cell Id by its coordinates
		Optional<String> cellId = grid.getCellIdByCoordinates(new Coords(1, 2));

		// THEN the cell Id is correctly retrieved
		assertThat(cellId, isPresentAndIs("Cell_1,2"));
	}

	@Test
	public void getCellIdByCoordinatesForCellOutOfBounds() {
		// GIVEN a Grid
		Grid grid = new Grid(3, 3);

		// WHEN retrieving a cell Id with out of bounds coordinates
		Optional<String> cellId = grid.getCellIdByCoordinates(new Coords(4, 4));

		// THEN no Id is returned
		assertThat(cellId, isEmpty());
	}

	@Test
	public void correctlyRetrieveAdjacentCells() {
		// GIVEN a Grid
		Grid grid = new Grid(3, 3);

		// WHEN retrieving all the adjacent cells of the central cell
		String centralCellId = "Cell_1,1";
		Optional<String> upAdjacent = grid.getAdjacentCellId(centralCellId, Direction.UP);
		Optional<String> downAdjacent = grid.getAdjacentCellId(centralCellId, Direction.DOWN);
		Optional<String> rightAdjacent = grid.getAdjacentCellId(centralCellId, Direction.RIGHT);
		Optional<String> leftAdjacent = grid.getAdjacentCellId(centralCellId, Direction.LEFT);

		// THEN the returned Ids are those of the adjacent cells
		assertThat(upAdjacent, isPresentAndIs("Cell_0,1"));
		assertThat(downAdjacent, isPresentAndIs("Cell_2,1"));
		assertThat(rightAdjacent, isPresentAndIs("Cell_1,2"));
		assertThat(leftAdjacent, isPresentAndIs("Cell_1,0"));
	}
}
