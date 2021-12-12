package model.pieces.heroes;

import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.sidekicks.SideKick;

import java.awt.Point;

import exceptions.*;

public class Tech extends ActivatablePowerHero {

	public Tech(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "T";
	}

	public void move(Direction r) throws Exception {
		switch (r) {
		case UPLEFT:
		case DOWNLEFT:
		case UPRIGHT:
		case DOWNRIGHT:
			super.move(r);
			break;
		default:
			throw new UnallowedMovementException("YOU CANNOT MOVE HERE", this, r);
		}

	}

	public void usePower(Direction d, Piece target, Point newPos) throws Exception {
		super.usePower(d, target, newPos);
		// teleport
		if (target!=null && newPos != null) {
			int i = newPos.x;
			int j = newPos.y;
			if (this.getGame().getCellAt(i, j).getPiece() != null || !(this.getOwner()==target.getOwner()))
				throw new InvalidPowerTargetException("invalid target exception", this, target);
			else if(this.getGame().getCellAt(i, j).getPiece()==null ) {
				int a = target.getPosI();
				int b = target.getPosJ();
				this.getGame().getCellAt(i, j).setPiece(target);
				target.setPosI(i);
				target.setPosJ(j);
				this.getGame().getCellAt(a, b).setPiece(null);
			}
		}
		// restore
		else if (this.getOwner()==target.getOwner()) {
			
			 
				if (target instanceof Armored && ((Armored) target).isArmorUp())
					throw new InvalidPowerTargetException("invalid target exception", this, target);

				if (target instanceof Armored && (!((Armored) target).isArmorUp()))
					((Armored) target).setArmorUp(true);
				else if (!(((ActivatablePowerHero) target).isPowerUsed()))
					throw new InvalidPowerTargetException("invalid target exception", this, target);
				else if ((((ActivatablePowerHero) target).isPowerUsed()))
					((ActivatablePowerHero) target).setPowerUsed(false);
				else if(target instanceof SideKick || target instanceof Speedster)
					throw new InvalidPowerTargetException("invalid target exception", this, target);
			} 
			//hack

			else if (!(this.getOwner()==target.getOwner())) {
			
					if (target instanceof Armored && (!((Armored) target).isArmorUp()))
						throw new InvalidPowerTargetException("invalid target exception", this, target);
					else if(target instanceof Armored && ((Armored) target).isArmorUp()){
						((Armored) target).setArmorUp(false);
					}
					else if (((ActivatablePowerHero) target).isPowerUsed())
						throw new InvalidPowerTargetException("invalid target exception", this, target);
					else{if(!((ActivatablePowerHero) target).isPowerUsed()){
						((ActivatablePowerHero) target).setPowerUsed(true);
					}
					else if(target instanceof SideKick || target instanceof Speedster)
						throw new InvalidPowerTargetException("invalid target exception", this, target);
						
						
					}
				
				}
			
		
	
	this.setPowerUsed(true);
	}
}
