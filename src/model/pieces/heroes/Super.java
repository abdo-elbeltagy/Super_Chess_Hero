package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Super extends ActivatablePowerHero {

	public Super(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "P";
	}

	public void move(Direction r) throws Exception {
		switch (r) {
		case UP  :
		case DOWN :
		case LEFT:
		case RIGHT:
			super.move(r);
			break;
		default:
			throw new UnallowedMovementException("YOU CAN NOT MOVE HERE", this,
					r);
		}
	}

	public void usePower(Direction d, Piece target, Point newPos)
			throws Exception {
		super.usePower(d, target, newPos);
		if (d == Direction.DOWNLEFT || d == Direction.DOWNRIGHT
				|| d == Direction.UPLEFT || d == Direction.UPRIGHT) {
			throw new InvalidPowerDirectionException("YOU CANNOT POWER HERE",
					this, d);
		}

		switch (d) {
		case UP: {
			int i = this.getPosI() - 1;
			int j = this.getPosI() - 2;

			if (!(isvalid(i, this.getPosJ())))
				throw new InvalidPowerDirectionException("Invalid direction",
						this, d);
			if (this.getGame().getCellAt(i, this.getPosJ()).getPiece() != null
					&& (!(this.getGame().getCellAt(i, this.getPosJ())
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(i, this.getPosJ())
						.getPiece());
			if (this.isvalid(j, this.getPosJ())
					&& this.getGame().getCellAt(j, this.getPosJ()).getPiece() != null
					&& (!(this.getGame().getCellAt(j, this.getPosJ())
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(j, this.getPosJ())
						.getPiece());

		}
			break;
		case DOWN: {
			int i = this.getPosI() + 1;
			int j = this.getPosI() + 2;
			if (!(isvalid(i, this.getPosJ())))
				throw new InvalidPowerDirectionException("in valid direction",
						this, d);
			if (this.getGame().getCellAt(i, this.getPosJ()).getPiece() != null
					&& (!(this.getGame().getCellAt(i, this.getPosJ())
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(i, this.getPosJ())
						.getPiece());
			if (this.isvalid(j, this.getPosJ())
					&& this.getGame().getCellAt(j, this.getPosJ()).getPiece() != null
					&& (!(this.getGame().getCellAt(j, this.getPosJ())
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(j, this.getPosJ())
						.getPiece());

		}
			break;
		case LEFT: {
			int j = this.getPosJ() - 1;
			int i = this.getPosJ() - 2;
			if (!(isvalid(this.getPosI(), j)))
				throw new InvalidPowerDirectionException("in valid direction",
						this, d);
			if (this.getGame().getCellAt(this.getPosI(), j).getPiece() != null
					&& (!(this.getGame().getCellAt(this.getPosI(), j)
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(this.getPosI(), j)
						.getPiece());
			if (this.isvalid(this.getPosI(), i)
					&& this.getGame().getCellAt(this.getPosI(), i).getPiece() != null
					&& (!(this.getGame().getCellAt(this.getPosI(), i)
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(this.getPosI(), i)
						.getPiece());
		}
			break;
		case RIGHT: {
			int j = this.getPosJ() + 1;
			int i = this.getPosJ() + 2;
			if (!(isvalid(this.getPosI(), j)))
				throw new InvalidPowerDirectionException("in valid direction",
						this, d);
			if (this.getGame().getCellAt(this.getPosI(), j).getPiece() != null
					&& (!(this.getGame().getCellAt(this.getPosI(), j)
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(this.getPosI(), j)
						.getPiece());
			if (this.isvalid(this.getPosI(), i)
					&& this.getGame().getCellAt(this.getPosI(), i).getPiece() != null
					&& (!(this.getGame().getCellAt(this.getPosI(), i)
							.getPiece().getOwner() == this.getOwner())))
				this.attack(this.getGame().getCellAt(this.getPosI(), i)
						.getPiece());
		}
		}
		this.setPowerUsed(true);
		this.getGame().switchTurns();

	}
}
