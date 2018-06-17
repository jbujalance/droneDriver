package com.bujalance.drone.model;

import java.util.function.UnaryOperator;

/**
 * A class representing a direction of translation for a given coordinates.
 * Each direction applies a one-unit translation following the direction to the given coordinates.
 */
public enum Direction implements UnaryOperator<Coords> {
	UP {
		@Override
		public Coords apply(final Coords pCoords) {
			return new Coords(pCoords.getRow() - 1, pCoords.getCol());
		}
	},
	DOWN {
		@Override
		public Coords apply(final Coords pCoords) {
			return new Coords(pCoords.getRow() + 1, pCoords.getCol());
		}
	},
	LEFT {
		@Override
		public Coords apply(final Coords pCoords) {
			return new Coords(pCoords.getRow(), pCoords.getCol() - 1);
		}
	},
	RIGHT {
		@Override
		public Coords apply(final Coords pCoords) {
			return new Coords(pCoords.getRow(), pCoords.getCol() + 1);
		}
	}
}
