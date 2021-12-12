package model.pieces;

import java.awt.Point;
import java.nio.file.DirectoryIteratorException;

import exceptions.*;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;

public abstract class Piece implements Movable {

	private String name;
	private Player owner;
	private Game game;
	private int posI;
	private int posJ;

	public Piece(Player p, Game g, String name) {
		this.owner = p;
		this.game = g;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPosI() {
		return posI;
	}

	public void setPosI(int i) {
		posI = i;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int j) {
		posJ = j;
	}

	public Game getGame() {
		return game;
	}

	public Player getOwner() {
		return owner;
	}

	public void attack(Piece target) {
		if (target instanceof Armored && ((Armored) target).isArmorUp()) {
			((Armored) target).setArmorUp(false);
			return;
		} else {
			if (target instanceof Hero) {
				int c = this.getOwner().getPayloadPos();
				this.getOwner().setPayloadPos(c + 1);
				this.getOwner().getDeadCharacters().add(target);
			} else {
				int c = this.getOwner().getSideKilled();
				this.getOwner().setSideKilled(c + 1);
				if ((c + 1) % 2 == 0) {
					int cp = this.getOwner().getPayloadPos();
					this.getOwner().setPayloadPos(cp + 1);
				}

			}
			int i = target.getPosI();
			int j = target.getPosJ();
			this.getGame().getCellAt(i, j).setPiece(null);
		}
		this.getGame().checkWinner();
	}

	public Cell moveLeft() {
		int j = posJ;
		int i = posI;
		j = j - 1;
		if (j < 0)
			j = 5;
		return game.getCellAt(i, j);
	}

	public Cell moveRight() {
		int j = posJ;
		int i = this.getPosI();
		j = j + 1;
		if (j > 5)
			j = 0;
		return game.getCellAt(i, j);
	}

	public Cell moveUp() {
		int j = this.getPosJ();
		int i = this.getPosI();
		i = i - 1;
		if (i < 0)
			i = 6;
		return game.getCellAt(i, j);
	}

	public Cell moveDown() {
		int j = this.getPosJ();
		int i = this.getPosI();
		i = i + 1;
		if (i > 6)
			i = 0;
		return game.getCellAt(i, j);
	}

	public Cell moveUpLeft() {
		int i = this.getPosI();
		int j = this.getPosJ();
		j = j - 1;
		i = i - 1;
		if (i < 0)
			i = 6;
		if (j < 0)
			j = 5;
		return (this.getGame().getCellAt(i, j));

	}

	public Cell moveUpRight() {
		int i = this.getPosI();
		int j = this.getPosJ();
		i = i - 1;
		j = j + 1;
		if (i < 0)
			i = 6;
		if (j > 5)
			j = 0;
		return (this.getGame().getCellAt(i, j));
	}

	public Cell moveDownRight() {
		int i = this.getPosI();
		int j = this.getPosJ();
		j = j + 1;
		i = i + 1;
		if (i > 6)
			i = 0;
		if (j > 5)
			j = 0;
		return (this.getGame().getCellAt(i, j));
	}

	public Cell moveDownLeft() {
		int i = this.getPosI();
		int j = this.getPosJ();
		j = j - 1;
		i = i + 1;
		if (i > 6)
			i = 0;
		if (j < 0)
			j = 5;
		return this.getGame().getCellAt(i, j);
	}

	public void move(Direction r) throws Exception {
		if (this.getGame().getCurrentPlayer() != this.getOwner())
			throw new WrongTurnException("IT'S NOT YOUR TURN", this);
		int i = this.getPosI();
		int j = this.getPosJ();

		switch (r) {
		case DOWN: {
			if (this.moveDown().getPiece() != null
					&& this.moveDown().getPiece().getOwner() == this.getOwner()) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if (this.moveDown().getPiece() != null) {
					attack(this.moveDown().getPiece());
					if ((this.moveDown().getPiece() == null)) {
					int a = this.getPosI() + 1;
					int b = this.getPosJ();
					if (a > 6)
						a = 0;

					moveDown().setPiece(this);
					this.setPosI(a);
					this.setPosJ(b);
					
					this.getGame().getCellAt(i, j).setPiece(null);


				}} else {
					moveDown().setPiece(this);
					int a = this.getPosI() + 1;
					int b = this.getPosJ();
					if (a > 6)
						a = 0;

					this.setPosI(a);
					this.setPosJ(b);
					
					this.getGame().getCellAt(i, j).setPiece(null);

				}
			}
			}
			break;

		case UP: {
			if (this.moveUp().getPiece() != null
					&& this.moveUp().getPiece().getOwner() == this.getOwner()) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if (this.moveUp().getPiece() != null) {
					attack(this.moveUp().getPiece());
					if ((this.moveUp().getPiece() == null)) {
						moveUp().setPiece(this);
						int a = this.getPosI() - 1;
						int b = this.getPosJ();
						if (a < 0)
							a = 6;

						this.setPosI(a);
						this.setPosJ(b);
						this.getGame().getCellAt(i, j).setPiece(null);
						

					}
				} else {
					moveUp().setPiece(this);
					int a = this.getPosI() - 1;
					int b = this.getPosJ();
					if (a < 0)
						a = 6;

					this.setPosI(a);
					this.setPosJ(b);
					this.getGame().getCellAt(i, j).setPiece(null);
				}
			}
		}
			break;
		case RIGHT: {
			if (this.moveRight().getPiece() != null
					&& this.moveRight().getPiece().getOwner() == this
							.getOwner()) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if (this.moveRight().getPiece() != null) {
					attack(this.moveRight().getPiece());
					if ((this.moveRight().getPiece() == null)) {
						moveRight().setPiece(this);
						int a = this.getPosI();
						int b = this.getPosJ() + 1;

						if (b > 5)
							b = 0;
						this.setPosI(a);
						this.setPosJ(b);
						this.getGame().getCellAt(i, j).setPiece(null);

					}
				} else {
					moveRight().setPiece(this);
					int a = this.getPosI();
					int b = this.getPosJ() + 1;

					if (b > 5)
						b = 0;
					this.setPosI(a);
					this.setPosJ(b);
					this.getGame().getCellAt(i, j).setPiece(null);
				}
			}
		}
			break;
		case LEFT: {
			if (this.moveLeft().getPiece() != null
					&& this.moveLeft().getPiece().getOwner() == this.getOwner()) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if ((this.moveLeft().getPiece() != null)) {
					attack(this.moveLeft().getPiece());
					if ((this.moveLeft().getPiece() == null)) {
						moveLeft().setPiece(this);
						int a = this.getPosI();
						int b = this.getPosJ() - 1;

						if (b < 0)
							b = 5;
						this.setPosI(a);
						this.setPosJ(b);
						this.getGame().getCellAt(i, j).setPiece(null);
					}
				} else {
					moveLeft().setPiece(this);
					int a = this.getPosI();
					int b = this.getPosJ() - 1;

					if (b < 0)
						b = 5;
					this.setPosI(a);
					this.setPosJ(b);
					this.getGame().getCellAt(i, j).setPiece(null);
				}
			}
		}
			break;
		case DOWNLEFT: {
			if (this.moveDownLeft().getPiece() != null
					&& this.moveDownLeft().getPiece().getOwner() == this
							.getOwner()) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if (this.moveDownLeft().getPiece() != null) {
					attack(this.moveDownLeft().getPiece());
					if ((this.moveDownLeft().getPiece() == null)) {
						moveDownLeft().setPiece(this);
						int a = this.getPosI() + 1;
						int b = this.getPosJ() - 1;
						if (a > 6)
							a = 0;
						if (b < 0)
							b = 5;
						this.setPosI(a);
						this.setPosJ(b);
						this.getGame().getCellAt(i, j).setPiece(null);
					}
				} else {
					moveDownLeft().setPiece(this);
					int a = this.getPosI() + 1;
					int b = this.getPosJ() - 1;
					if (a > 6)
						a = 0;
					if (b < 0)
						b = 5;
					this.setPosI(a);
					this.setPosJ(b);
					this.getGame().getCellAt(i, j).setPiece(null);
				}
			}
		}
			break;
		case DOWNRIGHT: {
			if (this.moveDownRight().getPiece() != null
					&& this.moveDownRight().getPiece().getOwner()
							.equals(this.getOwner())) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if (this.moveDownRight().getPiece() != null) {
					attack(this.moveDownRight().getPiece());
					if ((this.moveDownRight().getPiece() == null)) {
						moveDownRight().setPiece(this);
						int a = this.getPosI() + 1;
						int b = this.getPosJ() + 1;
						if (a > 6)
							a = 0;
						if (b > 5)
							b = 0;
						this.setPosI(a);
						this.setPosJ(b);
						this.getGame().getCellAt(i, j).setPiece(null);
					}
				} else {
					moveDownRight().setPiece(this);
					int a = this.getPosI() + 1;
					int b = this.getPosJ() + 1;
					if (a > 6)
						a = 0;
					if (b > 5)
						b = 0;
					this.setPosI(a);
					this.setPosJ(b);
					this.getGame().getCellAt(i, j).setPiece(null);
				}
			}
		}
			break;
		case UPLEFT: {
			if (this.moveUpLeft().getPiece() != null
					&& this.moveUpLeft().getPiece().getOwner() == this.getOwner()) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if ((this.moveUpLeft().getPiece() != null)) {
					attack(this.moveUpLeft().getPiece());
					if ((this.moveUpLeft().getPiece() == null)) {
						moveUpLeft().setPiece(this);
						int a = this.getPosI()-1 ;
						int b = this.getPosJ() - 1;

						if (b < 0)
							b = 5;
						if(a<0)
							a=6	;
						this.setPosI(a);
						this.setPosJ(b);
						this.getGame().getCellAt(i, j).setPiece(null);
					}
				} else {
					moveUpLeft().setPiece(this);
					int a = this.getPosI()-1 ;
					int b = this.getPosJ() - 1;

					if (b < 0)
						b = 5;
					if(a<0)
						a=6	;
					this.setPosI(a);
					this.setPosJ(b);
					this.getGame().getCellAt(i, j).setPiece(null);
				}
			}
		}
			break;
		case UPRIGHT: {
			if (this.moveUpRight().getPiece() != null
					&& this.moveUpRight().getPiece().getOwner() == this.getOwner()) {
				throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this,
						r);
			} else {
				if ((this.moveUpRight().getPiece() != null)) {
					attack(this.moveUpRight().getPiece());
					if ((this.moveUpRight().getPiece() == null)) {
						moveUpRight().setPiece(this);
						int a = this.getPosI()-1 ;
						int b = this.getPosJ() +1;

						if (b > 5)
							b = 0;
						if(a<0)
							a=6	;
						this.setPosI(a);
						this.setPosJ(b);
						this.getGame().getCellAt(i, j).setPiece(null);
					}
				} else {
					moveUpRight().setPiece(this);
					int a = this.getPosI()-1 ;
					int b = this.getPosJ() +1;

					if (b > 5)
						b = 0;
					if(a<0)
						a=6	;
					this.setPosI(a);
					this.setPosJ(b);
					this.getGame().getCellAt(i, j).setPiece(null);
				}
			}
		}
			break;
		}

		this.getGame().switchTurns();
	}

	// public void move(Direction r) throws Exception{
	// if (this.getGame().getCurrentPlayer() != this.getOwner())
	// throw new WrongTurnException("IT'S NOT YOUR TURN", this);
	// int x=this.getPosI();
	// int y=this.getPosJ();
	// switch (r){
	// case UP:
	// if(this.moveUp().getPiece()!=null&&this.moveUp().getPiece().getOwner().equals(this.getOwner()))
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveUp().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveUp().getPiece()==null){
	// int j=y;
	// int i=x-1;
	// if(i<0)
	// i=6;
	// this.moveUp().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	// case DOWN:
	// if(this.moveDown().getPiece()!=null&&this.moveDown().getPiece().getOwner()==this.getOwner())
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveDown().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveDown().getPiece()==null){
	// int j=y;
	// int i=x+1;
	// if(i>6)
	// i=0;
	// this.moveDown().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	// case LEFT:
	// if(this.moveLeft().getPiece()!=null&&this.moveLeft().getPiece().getOwner()==this.getOwner())
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveLeft().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveLeft().getPiece()==null){
	// int j=y-1;
	// int i=x;
	// if(j<0)
	// i=5;
	// this.moveLeft().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	//
	// case RIGHT:
	// if(this.moveRight().getPiece()!=null&&this.moveRight().getPiece().getOwner()==this.getOwner())
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveRight().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveRight().getPiece()==null){
	// int j=y+1;
	// int i=x;
	// if(j>5)
	// j=0;
	// this.moveRight().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	//
	// case UPRIGHT:
	// if(this.moveUpRight().getPiece()!=null&&this.moveUpRight().getPiece().getOwner()==this.getOwner())
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveUpRight().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveUpRight().getPiece()==null){
	// int j=y+1;
	// int i=x-1;
	// if(i<0)
	// i=6;
	// if(j>5)
	// j=0;
	// this.moveUpRight().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	// case UPLEFT:
	// if(this.moveUpLeft().getPiece()!=null&&this.moveUpLeft().getPiece().getOwner()==this.getOwner())
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveUpLeft().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveUpLeft().getPiece()==null){
	// int j=y-1;
	// int i=x-1;
	// if(i<0)
	// i=6;
	// if(j<0)
	// j=5;
	// this.moveUpLeft().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	// case DOWNRIGHT:
	// if(this.moveDownRight().getPiece()!=null&&this.moveDownRight().getPiece().getOwner()==this.getOwner())
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveDownRight().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveDownRight().getPiece()==null){
	// int j=y+1;
	// int i=x+1;
	// if(i>6)
	// i=0;
	// if(j>5)
	// j=0;
	// this.moveDownRight().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	// case DOWNLEFT:
	// if(this.moveDownLeft().getPiece()!=null&&this.moveDownLeft().getPiece().getOwner()==this.getOwner())
	// throw new OccupiedCellException("YOU CAN NOT MOVE HERE", this, r);
	// if(this.moveDownLeft().getPiece()!=null)
	// this.attack(this.moveUp().getPiece());
	// if(this.moveDownLeft().getPiece()==null){
	// int j=y-1;
	// int i=x+1;
	// if(i>6)
	// i=0;
	// if(j<0)
	// j=5;
	// this.moveDownLeft().setPiece(this);
	// this.getGame().getCellAt(x, y).setPiece(null);
	// this.setPosI(i);
	// this .setPosJ(j);}
	// break;
	// }
	// this.getGame().switchTurns();
	// }

	public static void main(String[] args) {
		Direction d = Direction.DOWN;
		Direction r = Direction.LEFT;
		System.out.println(d.equals(r));

	}

}