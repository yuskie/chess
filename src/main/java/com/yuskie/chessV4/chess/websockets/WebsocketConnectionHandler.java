package com.yuskie.chessV4.chess.websockets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.yuskie.chessV4.chess.ChessBoard;
import com.yuskie.chessV4.chess.Piece;
import com.yuskie.chessV4.chess.WebChessModel;


@Component
public class WebsocketConnectionHandler {
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private ChessBoard chessBoard;
	
	private int usersConnected = 0;
	
	@Autowired
	public void setup(){
		chessBoard.setupNewGame();
	}
	
	@EventListener
	public void onConnectEvent(SessionSubscribeEvent event) {
		usersConnected++;
       	template.convertAndSend("/topic/mapUpdate", chessBoard);
    }
	
	@EventListener
	public void onDisconnectEvent(SessionDisconnectEvent event) {
		if(--usersConnected == 0) {
			chessBoard.setupNewGame();
		}
	}
	
}