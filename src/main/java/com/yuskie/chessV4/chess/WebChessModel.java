package com.yuskie.chessV4.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebChessModel {
	private Map<String, String> chessModel;

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
			result.put(key, chessBoardState.get(key).print());
		}
		return result;
	}
}
