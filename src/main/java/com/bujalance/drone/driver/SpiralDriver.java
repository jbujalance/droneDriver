package com.bujalance.drone.driver;

import com.bujalance.drone.model.Coords;
import com.bujalance.drone.model.Direction;
import com.bujalance.drone.model.Grid;

import java.util.HashSet;
import java.util.Set;

/**
 * This version of the driver follows an helical pattern to retrieve the cell Ids.
 * Starting from the origin cell, the pattern is the following:
 * <p>
 *     UP - RIGHT - DOWN - LEFT - UP - RIGHT
 * </p>
 * The loop finish at the first cell (the upper cell from the origin), and then fills up the following circle.
 * <br>
 * <b>/!\ ATTENTION</b> This driver does not manage the end of the grid.
 */
public class SpiralDriver extends AbstractDriver {

	public SpiralDriver(final Grid pGrid) {
		super(pGrid);
	}

	/**
	 * Gets all the cell Ids present in the circle surrounding the origin cell.
	 * @param pOriginCellId the origin cell
	 * @param pCircle an integer representing the distance (in tiles) from the origin cell to the surrounding cells.
	 *                For a value of 1, the returned cell Ids will be the ones touching the origin cell.
	 *                For a value of 2, the returned cell Ids will be the ones surrounding the origin cell and at a distance of 1 tile from the origin cell.
	 * @return the cell Ids surrounding the given origin cell and the given circle distance.
	 */
	private Set<String> getCellIdsInCircle(final String pOriginCellId, final int pCircle) {
		Set<String> cellIds = new HashSet<>();
		String currentCellId = pOriginCellId;
		String previousCellId = currentCellId;
		for (int i = 0; i < pCircle; i++) {
			currentCellId = getAdjacentCellId(previousCellId, Direction.UP);
			previousCellId = currentCellId;
		}
		cellIds.add(currentCellId);

		previousCellId = currentCellId;
		for (int i = 0; i < pCircle; i++) {
			currentCellId = getAdjacentCellId(previousCellId, Direction.RIGHT);
			cellIds.add(currentCellId);
			previousCellId = currentCellId;
		}

		previousCellId = currentCellId;
		for (int i = 0; i < 2 * pCircle; i++) {
			currentCellId = getAdjacentCellId(previousCellId, Direction.DOWN);
			cellIds.add(currentCellId);
			previousCellId = currentCellId;
		}

		previousCellId = currentCellId;
		for (int i = 0; i < 2 * pCircle; i++) {
			currentCellId = getAdjacentCellId(previousCellId, Direction.LEFT);
			cellIds.add(currentCellId);
			previousCellId = currentCellId;
		}

		previousCellId = currentCellId;
		for (int i = 0; i < 2 * pCircle; i++) {
			currentCellId = getAdjacentCellId(previousCellId, Direction.UP);
			cellIds.add(currentCellId);
			previousCellId = currentCellId;
		}

		previousCellId = currentCellId;
		for (int i = 0; i < pCircle - 1; i++) {
			currentCellId = getAdjacentCellId(previousCellId, Direction.RIGHT);
			cellIds.add(currentCellId);
			previousCellId = currentCellId;
		}

		return cellIds;
	}

	/**
	 * @param pOriginCellId the origin cell
	 * @param pDirection the direction of adjacency
	 * @return the adjacent cell Id to the origin cell following the given direction, if it belongs to the grid.
	 * Otherwise, if the adjacent cell is not within the grid boundaries, an exception is thrown
	 * @throws UnsupportedOperationException if the adjacent cell is out of the grid boundaries
	 */
	private String getAdjacentCellId(final String pOriginCellId, final Direction pDirection) {
		return fGrid.getAdjacentCellId(pOriginCellId, pDirection).orElseThrow(() -> new UnsupportedOperationException("This algorithm is rubbish and does not support the end of the grid."));
	}

	@Override
	Set<String> findCellIdsToVisit(final Coords pOriginCoords, final int pRange) {
		Set<String> cellIds = new HashSet<>();
		String originCellId = fGrid.getCellIdByCoordinates(pOriginCoords).orElseThrow(() -> new IllegalArgumentException("The coordinates of the origin cell must be within the grid boundaries"));
		for (int i = 0; i <= pRange; i++) {
			cellIds.addAll(getCellIdsInCircle(originCellId, i));
		}
		return cellIds;
	}
}
