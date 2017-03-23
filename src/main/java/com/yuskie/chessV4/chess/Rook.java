package com.yuskie.chessV4.chess;

import com.yuskie.chessV4.chess.Utility.Color;
import static com.yuskie.chessV4.chess.Utility.*;

import java.util.List;

public class Rook implements Piece {
	private Color color;
	private boolean moved;
	
	public Rook(Color color) {
		this.color = color;
		this.moved = false;
	}

	public boolean validMove(String startLocation, String endLocation) {
		return straightMovement(startLocation, endLocation, BOARD_SIZE);
	}

	public String print() {
		String color = "";
		if (this.color == Color.BLACK) {
			color = "b";
		} else if (this.color == Color.WHITE) {
			color = "w";
		}
		return color + "-R";
	}
	
	public List<String> possibleMovements(String location){
		
		return null;
	}

	public Color getColor() {
		return this.color;
	}

	public void moved() {
		this.moved = true;
	}

	public boolean isMoved() {
		return this.moved;
	}
}
