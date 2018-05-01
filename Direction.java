package game;

import javafx.geometry.Point2D;
public enum Direction {
	NORTH(-1, 0),
	SOUTH(+1, 0),
	WEST(0, -1),
	EAST(0, +1),
	NORTHWEST(-1, -1),
	SOUTHEAST(+1, +1),
	SOUTHWEST(+1, -1),
	NORTHEAST(-1, +1);
	private int rowstep;
	private int colstep;
	private Direction(int rowstep, int colstep) {
		this.rowstep = rowstep;
		this.colstep = colstep;
	}
	public Point2D next(Point2D cur_point) {
		return new Point2D(cur_point.getX() + rowstep, cur_point.getY() + colstep);
	}
}