package model.pieces.heroes;

import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

import java.awt.*;

import exceptions.InvalidPowerDirectionException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;

public abstract class ActivatablePowerHero extends Hero {

	private boolean powerUsed = false;
	private int i;
	private int j;

	public ActivatablePowerHero(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void usePower(Direction d, Piece target, Point newPos) throws Exception {
		
		if(!(this.getOwner()==this.getGame().getCurrentPlayer()))
			throw new WrongTurnException("THIS IS NOT YOUR TURN",this);
		if (this.isPowerUsed()) {
			throw new PowerAlreadyUsedException("POWER IS ALREADY USED", this);
			
		}
		this.setPowerUsed(true);
	}

	public boolean isPowerUsed() {
		return powerUsed;
	}

	public void setPowerUsed(boolean powerUsed) {
		this.powerUsed = powerUsed;
	}

	public boolean isvalid(int i, int j) {
		return (i >= 0 && i <= 6 && j >= 0 && j <= 5);

	}

}
