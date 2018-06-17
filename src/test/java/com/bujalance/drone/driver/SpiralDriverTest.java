package com.bujalance.drone.driver;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import com.bujalance.drone.model.Coords;
import com.bujalance.drone.model.Grid;

import org.junit.Test;

import java.util.Set;

public class SpiralDriverTest {

	@Test(expected = IllegalArgumentException.class)
	public void getCellIdsToVisitWithInvalidRange() {
		// GIVEN a driver over a grid
		IDriver squaredDriver = new SquaredDriver(new Grid(2, 2));

		// WHEN retrieving the cell ids to visit with an invalid range
		squaredDriver.getCellIdsToVisit(new Coords(1, 1), -1);

		// THEN an exception is thrown
	}

	@Test
	public void getCellIdsToVisitOnZeroRange() {
		// GIVEN a driver over a Grid
		IDriver spiralDriver = new SpiralDriver(new Grid(5, 5));

		// WHEN retrieving the ids to visit for the central cell on range 0
		Set<String> cellIdsToVisit = spiralDriver.getCellIdsToVisit(new Coords(2, 2), 0);

		// THEN the resulting set only contains the central cell
		assertEquals(1, cellIdsToVisit.size());
		assertThat(cellIdsToVisit, contains("Cell_2,2"));
	}

	@Test
	public void getCellIdsToVisitOnFirstRange() {
		// GIVEN a driver over a Grid
		IDriver spiralDriver = new SpiralDriver(new Grid(5, 5));

		// WHEN retrieving the ids to visit for the central cell on range 1
		Set<String> cellIdsToVisit = spiralDriver.getCellIdsToVisit(new Coords(2, 2), 1);

		// THEN the resulting set contains all the Ids to visit
		assertEquals(9, cellIdsToVisit.size());
		assertThat(cellIdsToVisit, hasItems(
				"Cell_1,1", "Cell_1,2", "Cell_1,3",
				"Cell_2,1", "Cell_2,2", "Cell_2,3",
				"Cell_3,1", "Cell_3,2", "Cell_3,3"));
	}

	@Test
	public void getCellIdsToVisitOnSecondRange() {
		// GIVEN a driver over a Grid
		IDriver spiralDriver = new SpiralDriver(new Grid(5, 5));

		// WHEN retrieving the ids to visit for the central cell on range 2
		Set<String> cellIdsToVisit = spiralDriver.getCellIdsToVisit(new Coords(2, 2), 2);

		// THEN the resulting set contains all the Ids to visit
		assertEquals(25, cellIdsToVisit.size());
		assertThat(cellIdsToVisit, hasItems(
				"Cell_0,0", "Cell_0,1", "Cell_0,2", "Cell_0,3", "Cell_0,4",
				"Cell_1,0", "Cell_1,1", "Cell_1,2", "Cell_1,3", "Cell_1,4",
				"Cell_2,0", "Cell_2,1", "Cell_2,2", "Cell_2,3", "Cell_2,4",
				"Cell_3,0", "Cell_3,1", "Cell_3,2", "Cell_3,3", "Cell_3,4",
				"Cell_4,0", "Cell_4,1", "Cell_4,2", "Cell_4,3", "Cell_4,4"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getCellIdsToVisitOnLimitBounds() {
		// GIVEN a driver over a Grid
		IDriver spiralDriver = new SpiralDriver(new Grid(5, 5));

		// WHEN retrieving the ids to visit for an origin cell close enough to the grid limits
		spiralDriver.getCellIdsToVisit(new Coords(4, 2), 1);

		// THEN an exception is thrown as this algorithm does not manages the end of the grid
	}

	@Test(expected = IllegalArgumentException.class)
	public void getCellIdsToVisitForOriginCellOutOfBounds() {
		// GIVEN a driver over a Grid
		IDriver spiralDriver = new SpiralDriver(new Grid(2, 2));

		// WHEN retrieving the Ids to visit for a cell that is not in the grid
		spiralDriver.getCellIdsToVisit(new Coords(3, 3), 1);

		// THEN an exception is thrown
	}

}
