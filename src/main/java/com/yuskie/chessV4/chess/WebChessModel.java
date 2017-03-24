package com.yuskie.chessV4.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yuskie.chessV4.chess.Utility.Color;

public class WebChessModel {
	private Map<String, String> chessModel;
	private Color color;
	private boolean checkMate;
	private boolean moved = false;

	
	public void setColor(int i){
		if(i==1){
			this.color = Utility.Color.WHITE;
		}else if(i==2){
			this.color = Utility.Color.BLACK;
		}else{
			this.color = null;
		}
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}

	public Map<String, String> getChessModel() {
		return chessModel;
	}

	public void setChessModel(ChessBoard chessBoard) {
		this.chessModel = simplify(chessBoard);
	}
	private Map<String, String> simplify(ChessBoard chessBoard){
		Map<String, String> result = new HashMap<>();
		Map<String, Piece> chessBoardState = chessBoard.getBoardState();
		Set<String> keys = chessBoardState.keySet();
		for(String key: keys){
			if(chessBoardState.get(key)!=null){
				result.put(key, chessBoardState.get(key).print());
			}else{
				result.put(key, null);
			}
		}
		return result;
	}

	public boolean isCheckMate() {
		return checkMate;
	}

	public void setCheckMate(ChessBoard chessBoard) {
		this.checkMate = chessBoard.isCheckMate(color);
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
