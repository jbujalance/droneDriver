package com.bujalance.drone.model;

/**
 * Class representing a land cell in a grid.
 */
public class Cell {

	/** X coordinate of the cell in the grid. */
	private Integer fCoordX;
	/** Y coordinate of the cell in the grid. */
	private Integer fCoordY;
	/** The cell Id. */
	private String fId;

	/**
	 * Automatic-id constructor.
	 * The Id of the cell will be 'Cell_x,y' where x is the X coordinate of the cell and y the Y coordinate.
	 *
	 * @param pCoordX X coordinate of the cell
	 * @param pCoordY Y coordinate of the cell
	 */
	public Cell(final Integer pCoordX, final Integer pCoordY) {
		this(pCoordX, pCoordY, coordinatesToId(pCoordX, pCoordY));
	}

	/**
	 * Full constructor.
	 *
	 * @param pCoordX X coordinate of the cell
	 * @param pCoordY Y coordinate of the cell
	 * @param pId     the cell Id
	 */
	public Cell(final Integer pCoordX, final Integer pCoordY, final String pId) {
		fCoordX = pCoordX;
		fCoordY = pCoordY;
		fId = pId;
	}

	/**
	 * @param pCoordX X coordinate
	 * @param pCoordY Y coordinate
	 * @return an Id generated from the coordinates of this form: 'Cell_x,y' where x is the X coordinate of the cell and y the Y coordinate.
	 */
	private static String coordinatesToId(final Integer pCoordX, final Integer pCoordY) {
		return "Cell_" + pCoordX + "," + pCoordY;
	}

	public Integer getCoordX() {
		return fCoordX;
	}

	public void setCoordX(final Integer pCoordX) {
		fCoordX = pCoordX;
	}

	public Integer getCoordY() {
		return fCoordY;
	}

	public void setCoordY(final Integer pCoordY) {
		fCoordY = pCoordY;
	}

	public String getId() {
		return fId;
	}

	public void setId(final String pId) {
		fId = pId;
	}
}
