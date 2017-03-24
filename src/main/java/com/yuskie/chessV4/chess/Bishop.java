package com.yuskie.chessV4.chess;

import static com.yuskie.chessV4.chess.Utility.BOARD_SIZE;
import static com.yuskie.chessV4.chess.Utility.diagonalMovement;

import com.yuskie.chessV4.chess.Utility.Color;

public class Bishop implements Piece {
	private Color color;
	private boolean moved;
	private String pieceString;

	public Bishop(Color color) {
		this.color = color;
		this.moved = false;
		this.pieceString = print();
	}
	
	public boolean validMove(String startLocation, String endLocation) {
		return diagonalMovement(startLocation, endLocation, BOARD_SIZE);
	}

	public String print() {
		String color = "";
		if (this.color == Color.BLACK) {
			color = "b";
		} else if (this.color == Color.WHITE) {
			color = "w";
		}
		return color + "-B";
	}

	public Color getColor() {
		return this.color;
	}

	public void moved() {
		this.moved = true;
	}
	
	public boolean isMoved(){
		return this.moved;
	}

	public String getPieceString() {
		return pieceString;
	}
}
