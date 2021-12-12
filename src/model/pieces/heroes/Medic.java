package model.pieces.heroes;

import java.awt.Point;

import org.junit.internal.ExactComparisonCriteria;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import exceptions.*;

public class Medic extends ActivatablePowerHero {

	public Medic(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "M";
	}

	public void move(Direction r) throws Exception {
		switch (r) {
		case UP:
		case DOWN:
		case LEFT:
		case RIGHT:
			super.move(r);
			break;
		default:
			throw new UnallowedMovementException("YOU CAN NOT MOVE HERE", this, r);
		}
	}

	public void usePower(Direction d, Piece target, Point newPos) throws Exception {
		super.usePower(d, target, newPos);
		
		if (target.getOwner() != this.getOwner())
			throw new InvalidPowerTargetException("THIS IS NOT YOUR PIECE", this, target);
		
		int i = 0;
//		while (true) {
//
//			if (i > this.getOwner().getDeadCharacters().size())
//				throw new InvalidPowerTargetException("THE PIECE IS ALREADY EXIST", this, target);
//			if (target.equals(this.getOwner().getDeadCharacters().get(i))) {
//				this.getOwner().getDeadCharacters().remove(i);
//				break;
//
//			}
//			i++;
//		}
		
		if(!(this.getOwner().getDeadCharacters().contains(target)))
				throw new InvalidPowerTargetException("THE PIECE IS ALREADY EXIST", this, target);
			
		int x = this.getPosI();
		int y = this.getPosJ();
		switch (d) {
		case UP:
			if (this.moveUp().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveUp().setPiece(target);
			    if(x==0)
			    {
			    	x=6;
			    	 target.setPosI(x);
					 target.setPosJ(y);
			    }
			    else{
			     target.setPosI(x-1);
			     target.setPosJ(y);
			    }
			    this.getOwner().getDeadCharacters().remove(target);
                break;
		case DOWN:
			if (this.moveDown().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveDown().setPiece(target);
			    if(x==6)
			    {
			    	x=0;
			    	target.setPosI(x);
					target.setPosJ(y);
			    }
			    else{
			    target.setPosI(x+1);
			    target.setPosJ(y);
			    }
			    this.getOwner().getDeadCharacters().remove(target);
                break;
		case LEFT:
			if (this.moveLeft().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveLeft().setPiece(target);
			    if(y==0)
			    {
			    	y=5;
			    	target.setPosI(x);
				    target.setPosJ(y);
			    }
			    else{
			    target.setPosI(x);
			    target.setPosJ(y-1);
			    }
			    this.getOwner().getDeadCharacters().remove(target);
                break;
		case RIGHT:
			if (this.moveRight().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveRight().setPiece(target);
			    if(y==0)
			    {
			    	y=5;
			    	target.setPosI(x);
				    target.setPosJ(y);
			    }
			    else
			    {
			    target.setPosI(x);
			    target.setPosJ(y+1);
			    }
			    this.getOwner().getDeadCharacters().remove(target);
                break;

			
		case UPLEFT:
			if (this.moveUpLeft().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveUpLeft().setPiece(target);
                if(x==0 && y==0)
                {
                	x=6;
                	y=5;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(x==0)
                {
                	x=6;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(y==0)
                {
                	y=5;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else {
                	target.setPosI(x-1);
                	target.setPosJ(y-1);
                }
			    this.getOwner().getDeadCharacters().remove(target);
                break;
		case UPRIGHT:
			if (this.moveUpRight().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveUpRight().setPiece(target);
			    if(x==0 && y==5)
                {
                	x=6;
                	y=0;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(x==0)
                {
                	x=6;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(y==5)
                {
                	y=0;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else {
                	target.setPosI(x-1);
                	target.setPosJ(y+1);
                }
			    this.getOwner().getDeadCharacters().remove(target);
                break;
		case DOWNLEFT:
			if (this.moveDownLeft().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveDownLeft().setPiece(target);
			    if(x==6 && y==0)
                {
                	x=0;
                	y=5;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(x==6)
                {
                	x=0;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(y==0)
                {
                	y=5;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else {
                	target.setPosI(x+1);
                	target.setPosJ(y-1);
                }
			    this.getOwner().getDeadCharacters().remove(target);
                break;

		case DOWNRIGHT:
			if (this.moveDownRight().getPiece() != null)
				throw new InvalidPowerTargetException("the cell is occupied", this, target);
			    this.moveDownRight().setPiece(target);
			    if(x==6 && y==5)
                {
                	x=0;
                	y=0;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(x==6)
                {
                	x=0;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else if(y==5)
                {
                	y=0;
                	target.setPosI(x);
                	target.setPosJ(y);
                }
                else {
                	target.setPosI(x+1);
                	target.setPosJ(y+1);
                }
			    this.getOwner().getDeadCharacters().remove(target);
                break;

		}
		if(target instanceof Armored)
			((Armored) target).setArmorUp(true);
		if(target instanceof ActivatablePowerHero)
			((ActivatablePowerHero) target).setPowerUsed(false);
		this.getGame().switchTurns();
		this.setPowerUsed(true);
	}

}
