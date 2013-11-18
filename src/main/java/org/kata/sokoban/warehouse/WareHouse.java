package org.kata.sokoban.warehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WareHouse {

	private final BoardElement[][] map;
	public final Player player;
	private List<Box> boxes;

	public WareHouse(BoardBuilder boardBuilder, Player player, Box... boxes) {
		this.map = boardBuilder.build();
		validate(player);
		validate(boxes);
		this.player = player;
		this.boxes = Arrays.asList(boxes);
	}

	public WareHouse(BoardBuilder boardBuilder, Player player) {
		this.map = boardBuilder.build();
		validate(player);
		this.player = player;
		this.boxes = new ArrayList<Box>();
	}

	void addBox(Box box) {
		this.boxes.add(box);
	}

	public List<Box> getBoxes() {
		return boxes;
	}

	public void movePlayer(PositionSwitcher movement) {
		Position newPlayerPos = movement.change(player.getPosition());
		Box box = boxIn(newPlayerPos);
		if (canMoveBoxAt(movement, box)) {
			box.move(movement);
			player.move(movement);

		} else if (isEmpty(newPlayerPos)) {
			player.move(movement);
		}
	}

	private boolean canMoveBoxAt(PositionSwitcher movement, Box box) {
		if (box != null) {
			Position newBoxPos = movement.change(box.getPosition());
			return isEmpty(newBoxPos);
		}
		return false;
	}

	private Box boxIn(Position pos) {
		if (boxes != null) { 
			for (Box box : boxes) {
				if (box.isIn(pos.x, pos.y)) {
					return box;
				}
			}
		}
		return null;
	}

	private void validate(Movable... element) {
		for (int i = 0; i < element.length; i++) {
			if (!isEmpty(element[i].getPosition())) {
				throw new IllegalArgumentException("No puedes colocar elementos en casillas que no estén vacías");
			}
		}
	}

	private boolean isEmpty(Position position) {
		return map[position.x][position.y] == BoardElement.empty && (boxIn(position) == null);
	}
}
