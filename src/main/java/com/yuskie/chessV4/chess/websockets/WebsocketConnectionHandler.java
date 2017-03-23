package com.yuskie.chessV4.chess.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.yuskie.chessV4.chess.ChessBoard;


@Component
public class WebsocketConnectionHandler {
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private ChessBoard chessBoard;
	
	private int usersConnected = 0;
	
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