package com.bujalance.drone.model;

/**
 * Class representing a land cell in a grid.
 */
public class Cell {

	/** Cell coordinates */
	private Coords fCoords;
	/** The cell Id. */
	private String fId;

	public final static String ID_PREFIX = "Cell_";
	public final static String ID_COORDS_SEPARATOR = ",";

	public Cell(final Coords pCoords) {
		fCoords = pCoords;
		fId = coordinatesToId(pCoords);
	}

	/**
	 * @param pCoords cell coordinates
	 * @return an Id generated from the coordinates of this form: 'Cell_x,y' where x is the row of the cell in the grid and y the column.
	 */
	private static String coordinatesToId(final Coords pCoords) {
		return ID_PREFIX + pCoords.getRow() + ID_COORDS_SEPARATOR + pCoords.getCol();
	}

	public Coords getCoords() {
		return fCoords;
	}

	public String getId() {
		return fId;
	}
}
