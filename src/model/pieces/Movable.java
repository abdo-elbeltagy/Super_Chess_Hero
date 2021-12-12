package model.pieces;

import exceptions.UnallowedMovementException;
import model.game.Direction;

public interface Movable {
	public void move(Direction r)throws Throwable;
	
}
