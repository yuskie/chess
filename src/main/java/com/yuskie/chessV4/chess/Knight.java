package com.yuskie.chessV4.chess;

import com.yuskie.chessV4.chess.Utility.Color;
import static com.yuskie.chessV4.chess.Utility.*;

public class Knight implements Piece {
	private Color color;
	private boolean moved;
	
	public Knight(Color color) {
		this.color = color;
		this.moved = false;
	}
	
	public boolean validMove(String startLocation, String endLocation) {
		return lMovement(startLocation, endLocation);
	}

	public String print() {
		String color = "";
		if (this.color == Color.BLACK) {
			color = "b";
		} else if (this.color == Color.WHITE) {
			color = "w";
		}
		return color + "-KN";
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
}
