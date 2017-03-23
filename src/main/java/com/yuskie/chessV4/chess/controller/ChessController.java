package com.yuskie.chessV4.chess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuskie.chessV4.chess.ChessBoard;


@Controller 
public class ChessController {
	
	@Autowired
	private ChessBoard chessBoard;

	@RequestMapping("/")
	public String displayGreeting() {
		return "chessBoard"; 
		
	}
	
	@MessageMapping("/mapUpdate")
	public void handleMessage() {
	}
		
		
			
}
