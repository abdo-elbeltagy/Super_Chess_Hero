package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}
    public void attack (Piece target)
    {
    	int x=target.getPosI();
    	int y=target.getPosJ();
    	//super.attack(target);
    	int i =this.getPosI();
    	int j =this.getPosJ();
    	
		if(target instanceof Hero)
		{
			if(target instanceof Armored && ((Armored) target).isArmorUp())
			{
				//Armored a =new Armored(this.getOwner(), this.getGame(),"A");
				((Armored) target).setArmorUp(false);
				return;
				//a.setPosI(i);
				//a.setPosJ(j);
			}
			if(target instanceof Armored && !((Armored) target).isArmorUp()){
				Armored a =new Armored(this.getOwner(), this.getGame(),"A");
				
				//((Armored) target).setArmorUp(false);
				a.getGame().getCellAt(i, j).setPiece(a);
				
			}
			
			if(target instanceof Medic)
			{
				Medic a =new Medic(this.getOwner(), this.getGame(),"M");
				a.getGame().getCellAt(i, j).setPiece(a);;
				
			}
			if(target instanceof Ranged)
			{
				Ranged a =new Ranged(this.getOwner(), this.getGame(),"R");
				a.getGame().getCellAt(i, j).setPiece(a);;
				
			}
			if(target instanceof Speedster)
			{
				
				Speedster a =new Speedster(this.getOwner(), this.getGame(),"S");
				a.getGame().getCellAt(i, j).setPiece(a);;
				
			}
			if(target instanceof Super)
			{
				
				Super a =new Super(this.getOwner(), this.getGame(),"P");
				a.getGame().getCellAt(i, j).setPiece(a);;
				
			}
			if(target instanceof Tech)
			{
				
				Tech a =new Tech(this.getOwner(), this.getGame(),"T");
				a.getGame().getCellAt(i, j).setPiece(a);;
				
			}
			
		}
		super.attack(target);
    }
	@Override
	public String toString() {
		return "K";
	}
	public void move (Direction r) throws Exception
	{
		
		super .move(r);
	}
}
