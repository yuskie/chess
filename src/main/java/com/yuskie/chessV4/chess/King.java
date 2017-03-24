package com.yuskie.chessV4.chess;

import com.yuskie.chessV4.chess.Utility.Color;
import static com.yuskie.chessV4.chess.Utility.*;

public class King implements Piece {
	private Color color;
	private static final int MAX_NORMAL_MOVEMENT = 1;
	private boolean moved;
	private String pieceString;
	
	public King(Color color) {
		this.color = color;
		this.moved = false;
		this.pieceString = print();
	}
	
	public boolean validMove(String startLocation, String endLocation) {
		return diagonalMovement(startLocation, endLocation, MAX_NORMAL_MOVEMENT) || straightMovement(startLocation, endLocation, MAX_NORMAL_MOVEMENT);
	}

	public String print() {
		String color = "";
		if (this.color == Color.BLACK) {
			color = "b";
		} else if (this.color == Color.WHITE) {
			color = "w";
		}
		return color + "-K";
	}

	public Color getColor() {
		return this.color;
	}

	public boolean isMoved() {
		return moved;
	}

	public void moved() {
		this.moved = true;
	}
	
	public String getPieceString() {
		return pieceString;
	}
}
