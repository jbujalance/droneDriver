package com.bujalance.drone.model;

import java.util.Arrays;
import java.util.Optional;

/**
 * Class representing a {@link Cell} matrix.
 */
public class Grid {

	private Cell[][] fCellMatrix;

	/**
	 * Builds a cell matrix with the given number of rows and columns.
	 * @param pRows number of rows in the grid
	 * @param pColumns number of columns in the grid
	 */
	public Grid(final int pRows, final int pColumns) {
		this(generateCellMatrix(pRows, pColumns));
	}

	public Grid(final Cell[][] pCellMatrix) {
		fCellMatrix = pCellMatrix;
	}

	/**
	 * Creates a matrix of the given size and populates it with cells with auto-generated Ids.
	 * @param pRows the number of rows in the grid.
	 * @param pColumns the number of columns in the grid.
	 * @return a new cell-populated matrix
	 */
	private static Cell[][] generateCellMatrix(final int pRows, final int pColumns) {
		Cell[][] matrix = new Cell[pRows][pColumns];
		for (int row = 0; row < pRows; row++) {
			for (int column = 0; column < pColumns; column++) {
				matrix[row][column] = new Cell(new Coords(row, column));
			}
		}
		return matrix;
	}

	/**
	 * @param pId the Id of the cell to retrieve
	 * @return the cell corresponding to the given Id
	 */
	private Cell getCellById(final String pId) {
		Integer[] coords = Arrays.stream(pId.replace(Cell.ID_PREFIX, "").split(Cell.ID_COORDS_SEPARATOR))
				.map(Integer::parseInt)
				.toArray(Integer[]::new);
		return fCellMatrix[coords[0]][coords[1]];
	}

	/**
	 * {@code obtenerIdentificadorUrbanización}
	 * @param pCoords the coordinates of the cell whose Id is to be returned
	 * @return the id of the cell corresponding to the given coordinates
	 * If the given coordinates are out of the bounds of the grid, an empty optional is returned.
	 */
	public Optional<String> getCellIdByCoordinates(final Coords pCoords) {
		boolean rowIsInBounds = 0 <= pCoords.getRow() && pCoords.getRow() < fCellMatrix.length;
		boolean columnIsInBounds = 0 <=pCoords.getCol() && pCoords.getCol() < fCellMatrix[0].length;
		if (!rowIsInBounds || !columnIsInBounds) {
			return Optional.empty();
		}
		return Optional.of(fCellMatrix[pCoords.getRow()][pCoords.getCol()].getId());
	}

	/**
	 * {@code obtenerAdyacente}
	 * @param pCellId the origin cell Id
	 * @param pDirection the direction of adjacency
	 * @return the adjacent cell Id to the origin cell following the given direction.
	 * If the adjacent cell is out of the bounds of the grid, an empty optional is returned.
	 */
	public Optional<String> getAdjacentCellId(final String pCellId, final Direction pDirection) {
		return getCellIdByCoordinates(pDirection.apply(getCellById(pCellId).getCoords()));
	}
}
