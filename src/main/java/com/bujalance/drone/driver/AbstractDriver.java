package com.bujalance.drone.driver;

import com.bujalance.drone.model.Coords;
import com.bujalance.drone.model.Grid;

import java.util.Set;

public abstract class AbstractDriver implements IDriver {

	protected Grid fGrid;

	/**
	 * Builds a driver upon the given grid.
	 * @param pGrid the cell grid to be analyzed by the driver.
	 */
	public AbstractDriver(final Grid pGrid) {
		fGrid = pGrid;
	}

	@Override
	public Set<String> getCellIdsToVisit(final Coords pCoords, final int pRange) {
		assertRangeValidity(pRange);
		return findCellIdsToVisit(pCoords, pRange);
	}

	private void assertRangeValidity(final int pRange) {
		if (pRange < 0) {
			throw new IllegalArgumentException("The range must be an integer greater or equal than zero. The passed range was: " + pRange);
		}
	}

	abstract Set<String> findCellIdsToVisit(final Coords pCoords, final int pRange);
}
