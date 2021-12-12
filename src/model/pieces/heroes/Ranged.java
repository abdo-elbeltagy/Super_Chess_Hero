package model.pieces.heroes;

import java.awt.Point;

import exceptions.*;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Ranged extends ActivatablePowerHero {

	public Ranged(Player player, Game game, String name) {
		super(player, game, name);
	}

	public String toString() {
		return "R";
	}

//	public void move(Direction r) throws Exception {
//		super.move(r);
//	}

	public void usePower(Direction d, Piece target, Point newPos)throws Exception {
		super.usePower(d, target, newPos);
		if (d == Direction.DOWNLEFT || d == Direction.DOWNRIGHT
				|| d == Direction.UPLEFT || d == Direction.UPRIGHT) {
			throw new InvalidPowerDirectionException("YOU CANNOT POWER HERE",
					this, d);
		}
		switch (d) {
		case UP:
			for (int i = this.getPosI()-1; i >= -1; i--) {
				if (!(isvalid(i , this.getPosJ()))) {
					throw new InvalidPowerDirectionException(
							"YOU ARE NOT ALLAWED", this, d);
				} else {
					if (this.getGame().getCellAt(i, this.getPosJ()).getPiece() != null
							&& this.getGame().getCellAt(i, this.getPosJ())
									.getPiece().getOwner() != this.getOwner()) {
						this.attack(this.getGame().getCellAt(i, this.getPosJ())
								.getPiece());
						break;
					} else if (this.getGame().getCellAt(i, this.getPosJ())
							.getPiece() != null
							&& this.getGame().getCellAt(i, this.getPosJ())
									.getPiece().getOwner() == this.getOwner()) {

						throw new InvalidPowerDirectionException(
								"YOU ARE NOT ALLAWED", this, d);
					}
				}
			}
			break;
		case DOWN:
			for (int i = this.getPosI()+1; i <= 7; i++) {
				if (!(isvalid(i , this.getPosJ()))) {
					throw new InvalidPowerDirectionException(
							"YOU ARE NOT ALLAWED", this, d);
				} else {
					if (this.getGame().getCellAt(i, this.getPosJ()).getPiece() != null
							&& this.getGame().getCellAt(i, this.getPosJ())
									.getPiece().getOwner() != this.getOwner()) {
						this.attack(this.getGame().getCellAt(i, this.getPosJ())
								.getPiece());
						break;
					} else if (this.getGame().getCellAt(i, this.getPosJ())
							.getPiece() != null
							&& this.getGame().getCellAt(i, this.getPosJ())
									.getPiece().getOwner() == this.getOwner()) {

						throw new InvalidPowerDirectionException(
								"YOU ARE NOT ALLAWED", this, d);
					}
				}
			}
			break;
		case LEFT:
			for (int j = this.getPosJ()-1; j >= -1; j--) {
				if (!(isvalid(this.getPosI(), j))) {
					throw new InvalidPowerDirectionException(
							"YOU ARE NOT ALLAWED", this, d);
				} else {
					if (this.getGame().getCellAt(this.getPosI(), j).getPiece() != null
							&& this.getGame().getCellAt(this.getPosI(), j)
									.getPiece().getOwner() != this.getOwner()) {
						this.attack(this.getGame().getCellAt(this.getPosI(), j)
								.getPiece());
						break;
					} else if (this.getGame().getCellAt(this.getPosI(), j)
							.getPiece() != null
							&& this.getGame().getCellAt(this.getPosI(), j)
									.getPiece().getOwner() == this.getOwner()) {

						throw new InvalidPowerDirectionException(
								"YOU ARE NOT ALLAWED", this, d);
					}
				}
			}break;
		case RIGHT:
			for (int j = this.getPosJ()+1; j <= 6; j++) {
			if (!(isvalid(this.getPosI(), j))) {
				throw new InvalidPowerDirectionException(
						"YOU ARE NOT ALLAWED", this, d);
			} else {
				if (this.getGame().getCellAt(this.getPosI(), j).getPiece() != null
						&& this.getGame().getCellAt(this.getPosI(), j)
								.getPiece().getOwner() != this.getOwner()) {
					this.attack(this.getGame().getCellAt(this.getPosI(), j)
							.getPiece());
					break;
				} else if (this.getGame().getCellAt(this.getPosI(), j)
						.getPiece() != null
						&& this.getGame().getCellAt(this.getPosI(), j)
								.getPiece().getOwner() == this.getOwner()) {

					throw new InvalidPowerDirectionException(
							"YOU ARE NOT ALLAWED", this, d);
				}
			}
		}break;
			}
		this.getGame().switchTurns();
		this.setPowerUsed(true);

		
		}

	}

