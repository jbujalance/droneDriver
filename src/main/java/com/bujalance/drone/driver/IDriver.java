package com.bujalance.drone.driver;

import com.bujalance.drone.model.Coords;
import com.bujalance.drone.model.Grid;

import java.util.Set;

/**
 * The driver is the entry point of the API, from which to retrieve the set of cell Ids to be visited by the drone.
 */
public interface IDriver {

	/**
	 *
	 * @param pCoords the coordinates of the central cell whose neighbours are to be visited.
	 * @param pRange the range of cells surrounding the central cell that have to be visited.
	 * @return the set of unique cell Ids to be visited by the drone.
	 */
	Set<String> getCellIdsToVisit(final Coords pCoords, final int pRange);
}
