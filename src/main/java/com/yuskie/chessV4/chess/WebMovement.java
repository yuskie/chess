package com.yuskie.chessV4.chess;

import com.yuskie.chessV4.chess.Utility.Color;

public class WebMovement {
	private Color color;
	private String startLoc;
	private String endLoc;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getStartLoc() {
		return startLoc;
	}
	public void setStartLoc(String startLoc) {
		this.startLoc = startLoc;
	}
	public String getEndLoc() {
		return endLoc;
	}
	public void setEndLoc(String endLoc) {
		this.endLoc = endLoc;
	}
}
