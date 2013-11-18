package org.kata.sokoban.warehouse;

import static org.junit.Assert.assertTrue;
import static org.kata.sokoban.warehouse.BoardElement.empty;
import static org.kata.sokoban.warehouse.BoardElement.wall;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.runner.RunWith;

import com.googlecode.zohhak.api.Coercion;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class WareHouseTest {

	WareHouse wareHouse;

	BoardBuilder boardBuilder;

	private Player player;

	@Before
	public void setUp() throws Exception {
		boardBuilder = mock(BoardBuilder.class);
		when(boardBuilder.build()).thenReturn(simpleBoard());
	}

	@TestWith({ 
		"2-2, right, 2-3", 
		"2-2, down, 3-2", 
		"2-2, left, 2-1", 
		"2-2, up, 1-2" 
	})
	public void movePlayerToAEmptyCellInAllDirections(Position playerPosition, Movement move, Position expectedPlayerPos) {
		player = new Player(playerPosition);
		wareHouse = new WareHouse(boardBuilder, player);

		wareHouse.movePlayer(move);

		assertTrue(player.isIn(expectedPlayerPos));
	}

	@TestWith({ 
		"1-3, right, 1-3", 
		"3-1, down, 3-1",
		"1-1, left, 1-1", 
		"1-1, up, 1-1" 
	})
	public void movePlayerToAWallCellNothingChanges(Position playerPosition, Movement move, Position expectedPlayerPos) {
		player = new Player(playerPosition);
		wareHouse = new WareHouse(boardBuilder, player);

		wareHouse.movePlayer(move);

		assertTrue(player.isIn(expectedPlayerPos));
	}

	@TestWith({ 
		"2-1, 2-2, right, 2-2, 2-3", 
		"2-3, 2-2, left, 2-2, 2-1", 
		"3-2, 2-2, up, 2-2, 1-2", 
		"1-2, 2-2, down, 2-2, 3-2" 
	})
	public void movePlayerPushingABox(Position playerPosition, Position boxPosition, Movement move, Position expectedPlayerPos, Position expectedBoxPos) {
		player = new Player(playerPosition);
		Box box = new Box(boxPosition);
		wareHouse = new WareHouse(boardBuilder, player, box);

		wareHouse.movePlayer(move);

		assertTrue(player.isIn(expectedPlayerPos));
		assertTrue(box.isIn(expectedBoxPos));
	}

	private BoardElement[][] simpleBoard() {
		BoardElement[][] board = { //
		{ wall, wall, wall, wall, wall }, //
				{ wall, empty, empty, empty, wall }, //
				{ wall, empty, empty, empty, wall },//
				{ wall, empty, empty, empty, wall },//
				{ wall, wall, wall, wall, wall } }; //
		return board;
	}

	@Coercion
	public Position makePosition(String strPos) {
		String[] split = strPos.split("-");
		return new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
	}

}
