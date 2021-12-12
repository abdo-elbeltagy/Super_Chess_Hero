package model.pieces.sidekicks;

import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public class SideKickP2 extends SideKick {
	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name);
	}
	public void attack(Piece target) {
          super.attack(target);
	}
	
	public void move(Direction r) throws Exception {
		switch(r)
		{
		case DOWN:
		case DOWNRIGHT:
		case LEFT:
		case DOWNLEFT:	
		case RIGHT:super.move(r);break;
		default:throw new UnallowedMovementException("YOU CANNOT MOVE HERE",this,r);
		}
		
	}
}
