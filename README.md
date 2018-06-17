# Drone Driver
A drone driver exercise.

## Model
The basic objects are:
* `Coords`: The grid-coordinates of a cell
* `Cell`: The basic unit of a grid (urbanización)
* `Grid`: A cell grid
* `Direction`: Direction of translation

## Assumptions
* Integer coordinates: In real life, the cells would have a height and a length and would hold an infinite range of double coordinates.
However, these double coordinates could always be assimilated to the integer coordinates of the enclosing cell.
* The grid of cells is finite, so the cells at the limits must be correctly managed.
* String Ids: to make the cell retrieval by Id easier.

## Implementations
Two implementations of the Driver:
* `SpiralDriver`: Follows a helical pattern to find the cell Ids to visit.
Does not support the end of the grid. _See the Javadoc._
* `SquaredDriver`: Follows a square pattern to find the cell Ids to visit.
This driver correctly manages the end of the grid. _See the Javadoc._

## Results
See the test for the results:
* `SquaredDriverTest`
* `SpiralDriverTest`