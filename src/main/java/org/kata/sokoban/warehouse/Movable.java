package org.kata.sokoban.warehouse;

public class Movable {

	private Position position;

	public Movable(Position initialPosition) {
		this.position = initialPosition;
	}

	Movable move(PositionSwitcher switcher) {
		this.position = switcher.change(position);
		return this;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	public boolean isIn(int x, int y) {
		return position.x == x && position.y == y;
	}
	
	public boolean isIn(Position position) {
		return isIn(position.x, position.y);
	}
}
