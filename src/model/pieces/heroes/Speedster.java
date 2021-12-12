package model.pieces.heroes;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Speedster extends NonActivatablePowerHero {

	public Speedster(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "S";
	}

	public void move(Direction r) throws Exception {
		int i;
		int j;
		int x;
		int y;
		switch (r) {
		case UP:
			j = this.getPosJ();
			i = this.getPosI();
			
			 x = i - 2;
			if (x == -1)
				x = 6;
			if (x == -2)
				x = 5;
			if (this.getGame().getCellAt(x, j).getPiece() != null) {
				if (this.getGame().getCellAt(x, j).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(x, j).getPiece());
			}

			if (this.getGame().getCellAt(x, j).getPiece() == null) {
				this.getGame().getCellAt(x, j).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosI(x);

			}

			break;
		case DOWN:
			j = this.getPosJ();
			i = this.getPosI();
			
			 x = i + 2;
			if (x == 7)
				x = 0;
			if (x == 8)
				x = 1;
			if (this.getGame().getCellAt(x, j).getPiece() != null) {
				if (this.getGame().getCellAt(x, j).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(x, j).getPiece());
			}

			if (this.getGame().getCellAt(x, j).getPiece() == null) {
				this.getGame().getCellAt(x, j).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosI(x);

			}
			break;
		case LEFT:
			
			j = this.getPosJ();
			i = this.getPosI();
			 y = j - 2;
			if (y == -1)
				y = 5;
			if (y == -2)
				y = 4;
			if (this.getGame().getCellAt(i, y).getPiece() != null) {
				if (this.getGame().getCellAt(i, y).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(i, y).getPiece());
			}

			if (this.getGame().getCellAt(i, y).getPiece() == null) {
				this.getGame().getCellAt(i, y).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosJ(y);

			}
			
			break;
		case RIGHT:
			j = this.getPosJ();
			i = this.getPosI();
			 y = j + 2;
			if (y == 6)
				y = 0;
			if (y == 7)
				y = 1;
			if (this.getGame().getCellAt(i, y).getPiece() != null) {
				if (this.getGame().getCellAt(i, y).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(i, y).getPiece());
			}

			if (this.getGame().getCellAt(i, y).getPiece() == null) {
				this.getGame().getCellAt(i, y).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosJ(y);

			}
			break;
		case UPRIGHT:
			j = this.getPosJ();
			i = this.getPosI();
			 y = j + 2;
			 x = i-2;
			if (y == 6)
				y = 0;
			if (y == 7)
				y = 1;
			if(x==-1)
				x=6;
			if(x==-2)
				x=5;
			if (this.getGame().getCellAt(x, y).getPiece() != null) {
				if (this.getGame().getCellAt(x, y).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(x, y).getPiece());
			}

			if (this.getGame().getCellAt(x, y).getPiece() == null) {
				this.getGame().getCellAt(x, y).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosJ(y);
				this.setPosI(x);

			}
			break;
		case UPLEFT:
			j = this.getPosJ();
			i = this.getPosI();
			 y = j - 2;
			 x = i-2;
			if (y == -1)
				y = 5;
			if (y ==-2)
				y = 4;
			if(x==-1)
				x=6;
			if(x==-2)
				x=5;
			if (this.getGame().getCellAt(x, y).getPiece() != null) {
				if (this.getGame().getCellAt(x, y).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(x, y).getPiece());
			}

			if (this.getGame().getCellAt(x, y).getPiece() == null) {
				this.getGame().getCellAt(x, y).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosJ(y);
				this.setPosI(x);

			}
			break;
		case DOWNRIGHT:
			j = this.getPosJ();
			i = this.getPosI();
			 y = j + 2;
			 x = i+2;
			if (y == 6)
				y =0;
			if (y ==7)
				y = 1;
			if(x==7)
				x=0;
			if(x==8)
				x=1;
			if (this.getGame().getCellAt(x, y).getPiece() != null) {
				if (this.getGame().getCellAt(x, y).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(x, y).getPiece());
			}

			if (this.getGame().getCellAt(x, y).getPiece() == null) {
				this.getGame().getCellAt(x, y).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosJ(y);
				this.setPosI(x);

			}
			break;
		case DOWNLEFT:
			j = this.getPosJ();
			i = this.getPosI();
			 y = j - 2;
			 x = i+2;
			if (y == -1)
				y = 5;
			if (y ==-2)
				y = 4;
			if(x==7)
				x=0;
			if(x==8)
				x=1;
			if (this.getGame().getCellAt(x, y).getPiece() != null) {
				if (this.getGame().getCellAt(x, y).getPiece().getOwner() == this
						.getOwner())
					throw new OccupiedCellException("YOU CAN NOT MOVE HERE",
							this, r);
				else
					this.attack(this.getGame().getCellAt(x, y).getPiece());
			}

			if (this.getGame().getCellAt(x, y).getPiece() == null) {
				this.getGame().getCellAt(x, y).setPiece(this);
				this.getGame().getCellAt(i, j).setPiece(null);
				this.setPosJ(y);
				this.setPosI(x);

			}
			break;
		}
		 this.getGame().switchTurns();
	}
}
