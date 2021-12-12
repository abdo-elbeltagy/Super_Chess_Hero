package model.game;

import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game {

	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;
	

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		this.assemblePieces();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public int getPayloadPosTarget() {
		return payloadPosTarget;
	}

	@Override
	public String toString() {
		String s = "";
		System.out.println("      " + getPlayer2().getName());
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null)
					System.out.print("n ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		System.out.println("    " + getPlayer1().getName());
		return s;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void assemblePieces() {
		Super s1 = new Super(player1, this, "P");
		board[5][0] = new Cell(s1);
		Armored a1 = new Armored(player1, this, "A");
		board[5][1] = new Cell(a1);
		Medic m1 = new Medic(player1, this, "M");
		board[5][2] = new Cell(m1);
		Ranged r1 = new Ranged(player1, this, "R");
		board[5][3] = new Cell(r1);
		Speedster sp1 = new Speedster(player1, this, "S");
		board[5][4] = new Cell(sp1);
		Tech t1 = new Tech(player1, this, "T");
		board[5][5] = new Cell(t1);
		Super s2 = new Super(player2, this, "P");
		board[1][5] = new Cell(s2);
		Armored a2 = new Armored(player2, this, "A");
		board[1][4] = new Cell(a2);
		Medic m2 = new Medic(player2, this, "M");
		board[1][3] = new Cell(m2);
		Ranged r2 = new Ranged(player2, this, "R");
		board[1][2] = new Cell(r2);
		Speedster sp2 = new Speedster(player2, this, "S");
		board[1][1] = new Cell(sp2);
		Tech t2 = new Tech(player2, this, "T");
		board[1][0] = new Cell(t2);
		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * 6);
			int y = (int) (Math.random() * 6);
			Cell temp = board[5][x];
			board[5][x] = board[5][y];
			board[5][y] = temp;
			temp = board[1][x];
			board[1][x] = board[1][y];
			board[1][y] = temp;

		}
		for (int i = 0; i <= 5; i++) {
			board[4][i] = new Cell(new SideKickP1(this, "S1"));
			board[2][i] = new Cell(new SideKickP2(this, "S2"));
		}
		for (int j = 0; j <= 5; j++) {
			int i0 = 0;
			int i1 = 1;
			int i2 = 2;
			int i3 = 4;
			int i4 = 5;
			int i5 = 3;
			int i6 = 6;
			this.getCellAt(i1, j).getPiece().setPosI(i1);
			this.getCellAt(i1, j).getPiece().setPosJ(j);
			this.getCellAt(i2, j).getPiece().setPosI(i2);
			this.getCellAt(i2, j).getPiece().setPosJ(j);
			this.getCellAt(i3, j).getPiece().setPosI(i3);
			this.getCellAt(i3, j).getPiece().setPosJ(j);
			this.getCellAt(i4, j).getPiece().setPosI(i4);
			this.getCellAt(i4, j).getPiece().setPosJ(j);
			board[i0][j] = new Cell(null);
			board[i5][j] = new Cell(null);
			board[i6][j] = new Cell(null);

		}
	}

	public Cell getCellAt(int i, int j) {
		return board[i][j];
	}
	

	public void switchTurns() {
		if (currentPlayer.equals(player1))
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}

	public boolean checkWinner() {
		if (currentPlayer.getPayloadPos() >= 6)
			return true;
		else
			return false;

	}

	public static void main(String[] args) throws Exception {
		Player p1 = new Player("abdo");
		Player p2 = new Player("abdo000");

		Game g = new Game(p1, p2);
		System.out.println(g);
		g.board[5][3].getPiece().move(Direction.DOWN);
	System.out.println(g);
		g.board[2][0].getPiece().move(Direction.DOWN);
		System.out.println(g);
		//g.board[0][3].getPiece().move(Direction.LEFT);
//		g.board[2][0].getPiece().move(Direction.DOWN);
//		g.board[4][1].getPiece().move(Direction.LEFT);
//		g.board[3][0].getPiece().move(Direction.RIGHT);
		System.out.println(g.board[5][3].getPiece().getOwner().getName());
		System.out.println(g.board[0][3].getPiece().getOwner().getName());

		
		System.out.println(g);

		// System.out.println(g.board[2][1].getPiece().getPosI());
		// System.out.println(g.board[6][3]);

	}

}
