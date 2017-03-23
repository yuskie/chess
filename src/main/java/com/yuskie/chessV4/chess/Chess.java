package com.yuskie.chessV4.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yuskie.chessV4.chess.Utility.Color;

public class Chess extends GameBase {
	private List<Player> players;
	private ChessBoard newChessBoard;
	private Scanner scanner;

	public Chess() {
		super(2);
		newChessBoard = new ChessBoard();
		newChessBoard.setupNewGame();
		players = new ArrayList<Player>();
	}

	@Override
	protected void setup(int numberOfPlayers) {
		players.add(new Player(Color.WHITE));
		players.add(new Player(Color.BLACK));
		scanner = new Scanner(System.in);
	}

	@Override
	protected void takeTurn(int player) {
		newChessBoard.print();
		Player currentPlayer = players.get(player - 1);
		if (!newChessBoard.isCheckMate(currentPlayer.getColor())) {
			boolean moved = false;
			while (!moved) {
				System.out.print("What piece would you like to move? ");
				String pieceMoving = scanner.nextLine().toLowerCase();
				System.out.print("Where would you like to move it? ");
				String location = scanner.nextLine().toLowerCase();
				moved = newChessBoard.movePiece(currentPlayer.getColor(), pieceMoving, location);
				if (!moved) {
					System.out.println("Invalid move");
				}
				if(newChessBoard.pawnPromotion()){
					boolean pawnPromoted = false;
					while(!pawnPromoted){
						System.out.print("What would you like to promote to? (KN, B, Q, R)");
						String pieceType = scanner.nextLine().toLowerCase();
						pawnPromoted = newChessBoard.promotePawn(pieceType);
					}
				}
			}
		}else{
			players.remove(player-1);
		}
	}

	@Override
	protected boolean isGameOver() {
		return getActivePlayers().size() == 1;
	}

	@Override
	protected void finishGame() {
		scanner.close();
		Player winner = getActivePlayers().get(0);
		System.out.println("Congratz! Player " + winner.getColor() + " is the winner!!!");
	}

	private List<Player> getActivePlayers() {
		List<Player> activePlayers = new ArrayList<Player>();
		for (Player p : players) {
			if (newChessBoard.isCheckMate(p.getColor())) {
				activePlayers.add(p);
			}
		}
		return activePlayers;
	}
}