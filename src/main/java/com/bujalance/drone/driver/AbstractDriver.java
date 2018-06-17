package com.bujalance.drone.driver;

import com.bujalance.drone.model.Grid;

public abstract class AbstractDriver implements IDriver {

	protected Grid fGrid;

	/**
	 * Builds a driver upon the given grid.
	 * @param pGrid the cell grid to be analyzed by the driver.
	 */
	public AbstractDriver(final Grid pGrid) {
		fGrid = pGrid;
	}
}
