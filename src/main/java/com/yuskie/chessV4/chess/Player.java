package com.yuskie.chessV4.chess;

import com.yuskie.chessV4.chess.Utility.Color;

public class Player {
	private Color color;
	
	public Player(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}
	
}