package com.bujalance.drone.model;

/**
 * A class representing a couple of Integer coordinates.
 */
public class Coords {

	/** The row index in the grid. */
	private int fRow;
	/** The column index in the grid. */
	private int fCol;

	public Coords(final int pRow, final int pCol) {
		fRow = pRow;
		fCol = pCol;
	}

	public int getRow() {
		return fRow;
	}

	public void setRow(final int pRow) {
		fRow = pRow;
	}

	public int getCol() {
		return fCol;
	}

	public void setCol(final int pCol) {
		fCol = pCol;
	}

	@Override
	public boolean equals(final Object pOther) {
		if (pOther == null) {
			return false;
		}
		if (!Coords.class.isAssignableFrom(pOther.getClass())) {
			return false;
		}
		Coords otherCoords = (Coords) pOther;
		return fRow == otherCoords.getRow() && fCol == otherCoords.getCol();
	}
}
