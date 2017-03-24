package com.yuskie.chessV4.chess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuskie.chessV4.chess.ChessBoard;
import com.yuskie.chessV4.chess.WebChessModel;
import com.yuskie.chessV4.chess.WebMovement;


@Controller 
public class ChessController {
	
	@Autowired
	private ChessBoard chessBoard;

	@RequestMapping("/")
	public String displayGreeting() {
		return "chessBoard"; 
		
	}
	
	@MessageMapping("/chessUpdate")
	public WebChessModel handleMessage(WebMovement update) {
		boolean moveable = chessBoard.movePiece(update.getColor(), update.getStartLoc(), update.getEndLoc());
		WebChessModel chessModel = new WebChessModel();
		chessModel.setChessModel(chessBoard);
		chessModel.setColor(update.getColor());
		chessModel.setCheckMate(chessBoard);
		chessModel.setMoved(moveable);
		return chessModel;
	}
		
		
			
}
