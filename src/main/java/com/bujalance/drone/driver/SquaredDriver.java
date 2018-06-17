package com.bujalance.drone.driver;

import com.bujalance.drone.model.Coords;
import com.bujalance.drone.model.Direction;
import com.bujalance.drone.model.Grid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This version of the driver follows a squared pattern to retrieve the cell Ids to be visited.
 * First, the column of adjacent cells from the original cell is built.
 * Then it iterates over each cell of the central column to build the adjacent cell rows.
 * This algorithm correctly manages the end of the grid.
 */
public class SquaredDriver extends AbstractDriver {

	public SquaredDriver(final Grid pGrid) {
		super(pGrid);
	}

	@Override
	Set<String> findCellIdsToVisit(final Coords pCoords, final int pRange) {
		Set<String> cellIdsToVisit = new HashSet<>();
		String originCellId = fGrid.getCellIdByCoordinates(pCoords).orElseThrow(() -> new IllegalArgumentException("The coordinates of the origin cell must be within the grid boundaries"));
		for (String columnCellId : getCellIdsInColumnFromCentralCell(originCellId, pRange)) {
			cellIdsToVisit.add(columnCellId);
			cellIdsToVisit.addAll(getAdjacentCellIdsInRowFromCentralCell(columnCellId, pRange));
		}
		return cellIdsToVisit;
	}

	/**
	 * Builds a column of adjacent cells from the origin cell, which is the cell at the central row of the column
	 * @param pCentralCellId the cell at the center of the column
	 * @param pRange the number of adjacency steps to return.
	 * @return the adjacent cells in the column, following directions UP and DOWN from the origin. The result includes the central cell.
	 */
	private Set<String> getCellIdsInColumnFromCentralCell(final String pCentralCellId, final int pRange) {
		Set<String> cellIdsInColumn = new HashSet<>();
		cellIdsInColumn.add(pCentralCellId);
		cellIdsInColumn.addAll(getAdjacentMultiDirectionalCellIds(pCentralCellId, pRange, new HashSet<>(Arrays.asList(Direction.UP, Direction.DOWN))));
		return cellIdsInColumn;
	}

	/**
	 * Builds a row of adjacent cells from the origin cell, which is the cell at the center of the row
	 * @param pCentralCellId the cell at the center of the row
	 * @param pRange the number of adjacency steps to return.
	 * @return the adjacent cells in the row, following directions RIGHT and LEFT from the origin. The result does not include the central cell.
	 */
	private Set<String> getAdjacentCellIdsInRowFromCentralCell(final String pCentralCellId, final int pRange) {
		Set<String> cellIdsInRow = new HashSet<>();
		cellIdsInRow.addAll(getAdjacentMultiDirectionalCellIds(pCentralCellId, pRange, new HashSet<>(Arrays.asList(Direction.RIGHT, Direction.LEFT))));
		return cellIdsInRow;
	}

	/**
	 * @param pOriginCell the origin cell from which to retrieve the adjacent cells
	 * @param pRange the number of adjacency steps to make. For example, if {@code range = 2},
	 *               then two steps of adjacency will be returned: the adjacent cell, and the adjacent of the adjacent.
	 * @param pDirections a set of directions to follow on the adjacency search
	 * @return all the adjacent cells of the origin cell following all the given directions.
	 * The origin cell is not included in the returned Ids.
	 */
	private Set<String> getAdjacentMultiDirectionalCellIds(final String pOriginCell, final int pRange, Set<Direction> pDirections) {
		Set<String> cellIds = new HashSet<>();
		for (Direction direction : pDirections) {
			cellIds.addAll(getAdjacentCellIdsInRangeForDirection(pOriginCell, pRange, direction));
		}
		return cellIds;
	}

	/**
	 * @param pOriginCellId the origin cell from which to retrieve the adjacent cells.
	 * @param pRange the number of adjacency steps to make. For example, if {@code range = 2},
	 *               then two steps of adjacency will be returned: the adjacent cell, and the adjacent of the adjacent.
	 * @param pDirection direction to follow on the adjacency search.
	 * @return the Ids of the adjacent cells in the given direction.
	 * The origin cell Id is not included in the result.
	 */
	private Set<String> getAdjacentCellIdsInRangeForDirection(final String pOriginCellId, final int pRange, final Direction pDirection) {
		Set<String> cellIds = new HashSet<>();
		String currentCellId;
		String previousCellId = pOriginCellId;
		for (int i = 0; i < pRange; i++) {
			Optional<String> adjacentCellId = fGrid.getAdjacentCellId(previousCellId, pDirection);
			if (adjacentCellId.isPresent()) {
				currentCellId = adjacentCellId.get();
				cellIds.add(currentCellId);
				previousCellId = currentCellId;
			} else {
				// Leave the loop if we arrive to the grid's limit
				break;
			}
		}
		return cellIds;
	}
}
