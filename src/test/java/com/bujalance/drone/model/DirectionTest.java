package com.bujalance.drone.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DirectionTest {

	private static int fRow = 2;
	private static int fCol = 2;

	@Parameters(name = "Translation following direction: {0}")
	public static Collection<Object[]> testParameters() {
		return Arrays.asList(new Object[][] {
				{Direction.UP, new Coords(fRow - 1, fCol)},
				{Direction.DOWN, new Coords(fRow + 1, fCol)},
				{Direction.RIGHT, new Coords(fRow, fCol + 1)},
				{Direction.LEFT, new Coords(fRow, fCol - 1)}
		});
	}

	private Direction fInputDirection;
	private Coords fExpectedCoordinates;

	public DirectionTest(final Direction pDirection, final Coords pCoords) {
		fInputDirection = pDirection;
		fExpectedCoordinates = pCoords;
	}

	@Test
	public void testTranslation() {
		// GIVEN an origin coordinates
		Coords origin = new Coords(fRow, fCol);

		// WHEN applying the translation to these coordinates
		Coords translated = fInputDirection.apply(origin);

		// THEN the coordinates are correctly translated
		assertEquals(fExpectedCoordinates, translated);
	}
}
