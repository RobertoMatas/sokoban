package org.kata.sokoban.warehouse;

public enum Movement implements PositionSwitcher {

	right {

		public Position change(final Position oldPosition) {
			return new Position(oldPosition.x, oldPosition.y + 1);
		}

	},
	left {

		public Position change(final Position oldPosition) {
			return new Position(oldPosition.x, oldPosition.y - 1);
		}

	},
	down {

		public Position change(final Position oldPosition) {
			return new Position(oldPosition.x + 1, oldPosition.y);
		}

	},
	up {

		public Position change(final Position oldPosition) {
			return new Position(oldPosition.x - 1, oldPosition.y);
		}

	};
}
