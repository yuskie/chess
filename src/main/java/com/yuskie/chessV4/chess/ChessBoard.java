package com.yuskie.chessV4.chess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.yuskie.chessV4.chess.Utility.Color;

@Component
public class ChessBoard {
	private Map<String, Piece> boardState;

	private static final int X_BLACK_PAWN_START_LOC = 0;
	private static final int Y_BLACK_PAWN_START_LOC = 7;

	private static final int X_WHITE_PAWN_START_LOC = 0;
	private static final int Y_WHITE_PAWN_START_LOC = 2;

	private static final int X_BLACK_BACK_START_LOC = 0;
	private static final int Y_BLACK_BACK_START_LOC = 8;

	private static final int X_WHITE_BACK_START_LOC = 0;
	private static final int Y_WHITE_BACK_START_LOC = 1;

	private static final int BOARD_SIZE = 8;

	private String pawnLoc = "";
	private boolean pawnMoved = false;
	private String enPassantLoc = "";

	public ChessBoard() {
		boardState = new HashMap<String, Piece>();
		initializeBoardState();
	}

	public void setupNewGame() {
		setupPawn(Utility.Color.BLACK, X_BLACK_PAWN_START_LOC, Y_BLACK_PAWN_START_LOC);
		setupPawn(Utility.Color.WHITE, X_WHITE_PAWN_START_LOC, Y_WHITE_PAWN_START_LOC);
		setupBackRow(Utility.Color.BLACK, X_BLACK_BACK_START_LOC, Y_BLACK_BACK_START_LOC);
		setupBackRow(Utility.Color.WHITE, X_WHITE_BACK_START_LOC, Y_WHITE_BACK_START_LOC);
	}

	public boolean movePiece(Color color, String startLocation, String endLocation) {
		Piece movingPiece = boardState.get(startLocation);
		if (movingPiece == null || movingPiece.getColor() != color) {
			return false;
		} else if (isInCheck(color)) {
			return outOfCheck(startLocation, endLocation);
		} else if (checkValidMove(startLocation, endLocation, movingPiece)) {
			if (movingPiece.getClass() == Pawn.class) {
				return handlePawnExceptions(startLocation, endLocation, movingPiece);
			} else {
				movePieces(startLocation, endLocation, movingPiece);
				return true;
			}
		} else if (movingPiece.getClass() == King.class) {
			King kingPiece = (King) movingPiece;
			if (canCastle(startLocation, endLocation, kingPiece)) {
				String closestRookLoc = getCastlingRookLoc(endLocation);
				Piece castleRook = boardState.get(closestRookLoc);
				if (castleRook != null && castleRook.getClass() == Rook.class && !castleRook.isMoved()) {
					castling(startLocation, endLocation, kingPiece, closestRookLoc, castleRook);
					pawnMoved = false;
					enPassantLoc = "";
					return true;
				}
			}
		}
		return false;
	}

	private boolean handlePawnExceptions(String startLocation, String endLocation, Piece movingPiece) {
		if (enPassant(startLocation, endLocation, movingPiece)) {
			return true;
		} else {
			if (Utility.diagonalMovement(startLocation, endLocation, 1)) {
				if (boardState.get(endLocation) != null && Utility.diagonalMovement(startLocation, endLocation, 1)) {
					movePieces(startLocation, endLocation, movingPiece);
					return true;
				}
				return false;
			} else {
				pawnMoved = pawnMoved(startLocation, endLocation, movingPiece);
				movePieces(startLocation, endLocation, movingPiece);
				return true;
			}
		}
	}

	private boolean outOfCheck(String startLocation, String endLocation) {
		Piece startPiece = boardState.get(startLocation);
		Piece endPiece = boardState.get(endLocation);
		while (checkValidMove(startLocation, endLocation, startPiece)) {
			movePieces(startLocation, endLocation, startPiece);
			if (isInCheck(startPiece.getColor())) {
				boardState.put(endLocation, endPiece);
				boardState.put(startLocation, startPiece);
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean enPassant(String startLocation, String endLocation, Piece movingPiece) {
		if (Utility.diagonalMovement(startLocation, endLocation, 1) && endLocation.equals(enPassantLoc) && pawnMoved) {
			boardState.put(endLocation, movingPiece);
			boardState.put(startLocation, null);
			boardState.put(pawnLoc, null);
			enPassantLoc = "";
			pawnMoved = false;
			pawnLoc = "";
			return true;
		}
		return false;
	}

	public boolean isCheckMate(Color color) {
		String kingLoc = locateKing(color);
		Piece king = boardState.get(kingLoc);
		List<String> kingMoves = Utility.generateAllMoves(kingLoc, king);
		boolean kingCanMove = false;
		for (String move : kingMoves) {
			if (!notBlockingPath(kingLoc, move)) {
				continue;
			}
			kingCanMove = true;
			Piece kingMoveLocPiece = boardState.get(move);
			boardState.put(move, king);
			if (!isInCheck(color)) {
				boardState.put(kingLoc, king);
				boardState.put(move, kingMoveLocPiece);
				return false;
			}
			boardState.put(kingLoc, king);
			boardState.put(move, kingMoveLocPiece);

			Set<String> locations = boardState.keySet();
			for (String loc : locations) {
				if (boardState.get(loc) != null && boardState.get(loc).getColor() == color
						&& boardState.get(loc) != king) {
					List<String> pieceMoves = Utility.generateAllMoves(loc, boardState.get(loc));
					if (pieceMoves.contains(move) && notBlockingPath(loc, move)) {
						Piece tempMovePiece = boardState.get(move);
						Piece movingPiece = boardState.get(loc);
						boardState.put(move, movingPiece);
						if(isInCheck(color)){
							boardState.put(loc, movingPiece);
							boardState.put(move, tempMovePiece);
							continue;
						}
						boardState.put(loc, movingPiece);
						boardState.put(move, tempMovePiece);
						return false;
					}
				}
				if (boardState.get(loc) != null && boardState.get(loc).getColor() != color && boardState.get(loc).getClass()!= King.class) {
					List<String> pieceMoves = Utility.generateAllMoves(loc, boardState.get(loc));
					if (pieceMoves.contains(move) && canPieceBeRemoved(color, king, locations, loc)) {
						return false;
					}
				}

			}
		}
		if (!kingCanMove) {
			return false;
		}
		return true;
	}

	private boolean canPieceBeRemoved(Color color, Piece king, Set<String> locations, String loc) {
		for (String sameColorLoc : locations) {
			if (boardState.get(sameColorLoc) != null && boardState.get(sameColorLoc).getColor() == color
					&& boardState.get(sameColorLoc) != king) {
				List<String> sameColorMoves = Utility.generateAllMoves(sameColorLoc, boardState.get(sameColorLoc));
				if (sameColorMoves.contains(loc)) {
					return true;
				}
			}
		}
		return false;
	}

	public void print() {
		String border = createBorder();
		System.out.println(border);
		for (int i = BOARD_SIZE; i > 0; i--) {
			for (int j = 0; j < Utility.XVALUES.length; j++) {
				System.out.print("|");
				if (boardState.get(Utility.XVALUES[j] + i) == null) {
					System.out.printf("%5s", "");
				} else {
					System.out.printf("%5s", boardState.get(Utility.XVALUES[j] + i).print());
				}
			}
			System.out.print("|");
			System.out.println();
			System.out.println(border);
		}
	}

	public Map<String, Piece> getBoardState() {
		return boardState;
	}

	private boolean pawnMoved(String startLocation, String endLocation, Piece movingPiece) {
		if (movingPiece.getClass() == Pawn.class && Math
				.abs(Integer.parseInt(endLocation.substring(1)) - Integer.parseInt(startLocation.substring(1))) == 2) {
			enPassantLoc = Utility.getNextLoc(startLocation, endLocation);
			pawnLoc = endLocation;
			return true;
		}
		enPassantLoc = null;
		return false;
	}

	public boolean pawnPromotion() {
		Set<String> keys = boardState.keySet();
		for (String key : keys) {
			Piece piece = boardState.get(key);
			if (checkIfPawnIsAtEnd(key, piece)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkIfPawnIsAtEnd(String key, Piece piece) {
		return piece != null && piece.getClass() == Pawn.class
				&& (Integer.parseInt(key.substring(1)) == 1 || Integer.parseInt(key.substring(1)) == 8);
	}

	public boolean promotePawn(String pieceType) {
		String pawnLocation = "";
		Color color = null;
		Set<String> keys = boardState.keySet();
		for (String key : keys) {
			Piece piece = boardState.get(key);
			if (checkIfPawnIsAtEnd(key, piece)) {
				pawnLocation = key;
				color = piece.getColor();
				break;
			}
		}
		if (pieceType.equals("kn")) {
			boardState.put(pawnLocation, new Knight(color));
			return true;
		} else if (pieceType.equals("b")) {
			boardState.put(pawnLocation, new Bishop(color));
			return true;
		} else if (pieceType.equals("q")) {
			boardState.put(pawnLocation, new Queen(color));
			return true;
		} else if (pieceType.equals("r")) {
			boardState.put(pawnLocation, new Rook(color));
			return true;
		} else {
			System.out.println("Input invalid. Try again");
			return false;
		}
	}

	private boolean canCastle(String startLocation, String endLocation, King kingPiece) {
		return Utility.castlingMovement(startLocation, endLocation, kingPiece.isMoved())
				&& notBlockingPath(startLocation, endLocation);
	}

	private boolean checkValidMove(String startLocation, String endLocation, Piece movingPiece) {
		return movingPiece.validMove(startLocation, endLocation) && notBlockingPath(startLocation, endLocation);
	}

	private void movePieces(String startLocation, String endLocation, Piece movingPiece) {
		boardState.put(endLocation, boardState.get(startLocation));
		boardState.put(startLocation, null);
		movingPiece.moved();
	}

	private void castling(String startLocation, String endLocation, King kingPiece, String closestRookLoc,
			Piece castleRook) {
		String rookEndLoc = getRookEndLoc(closestRookLoc);
		boardState.put(endLocation, kingPiece);
		boardState.put(rookEndLoc, castleRook);
		boardState.put(startLocation, null);
		boardState.put(closestRookLoc, null);
	}

	private String locateKing(Color color) {
		Set<String> keys = boardState.keySet();
		for (String key : keys) {
			if (boardState.get(key) != null && boardState.get(key).getClass() == King.class
					&& boardState.get(key).getColor() == color) {
				return key;
			}
		}
		// Shouldn't happen
		return null;
	}

	private boolean isInCheck(Color color) {
		String kingLoc = locateKing(color);
		Set<String> locations = boardState.keySet();
		for (String loc : locations) {
			if (boardState.get(loc) != null && boardState.get(loc).getColor() != color) {
				List<String> moves = Utility.generateAllMoves(loc, boardState.get(loc));
				if (moves.contains(kingLoc) && notBlockingPath(loc, kingLoc)) {
					return true;
				}
			}
		}
		return false;
	}

	private String getRookEndLoc(String closestRookLoc) {
		String rookEndLoc = "";
		if (closestRookLoc.substring(0, 1).equals(Utility.XVALUES[0])) {
			rookEndLoc = Utility.XVALUES[3].concat(closestRookLoc.substring(1));
		} else {
			rookEndLoc = Utility.XVALUES[5].concat(closestRookLoc.substring(1));
		}
		return rookEndLoc;
	}

	private String getCastlingRookLoc(String endLocation) {
		String rookLoc = endLocation.substring(1);
		String result;
		if (endLocation.substring(0, 1) != null && endLocation.substring(0, 1).equals("g")) {
			int hLocation = 7;
			result = Utility.XVALUES[hLocation].concat(rookLoc);
		} else {
			int aLocation = 0;
			result = Utility.XVALUES[aLocation].concat(rookLoc);
		}
		return result;
	}

	private String createBorder() {
		String result = "";
		for (int i = 0; i < 49; i++) {
			result += "-";
		}
		return result;
	}

	private boolean notBlockingPath(String startLocation, String endLocation) {
		Piece endLocPiece = boardState.get(endLocation);
		Piece startPiece = boardState.get(startLocation);
		if (endLocPiece != null && endLocPiece.getColor() == startPiece.getColor()) {
			return false;
		}
		String nextString = startLocation;
		while (!Utility.getNextLoc(nextString, endLocation).equals(endLocation)) {
			nextString = Utility.getNextLoc(nextString, endLocation);
			if (boardState.get(nextString) != null) {
				return false;
			}
		}
		return true;
	}

	private void initializeBoardState() {
		for (int i = 0; i < Utility.XVALUES.length; i++) {
			for (int j = 1; j <= BOARD_SIZE; j++) {
				boardState.put(Utility.XVALUES[i] + j, null);
			}
		}
	}

	private void setupPawn(Color color, int xValueLoc, int yLocation) {
		for (int i = 0; i < Utility.XVALUES.length; i++) {
			Pawn temp = new Pawn(color);
			boardState.put(Utility.XVALUES[xValueLoc] + yLocation, temp);
			xValueLoc++;
		}
	}

	private void setupBackRow(Color color, int xValueLoc, int yLocation) {
		Piece[] backRow = { new Rook(color), new Knight(color), new Bishop(color), new Queen(color), new King(color),
				new Bishop(color), new Knight(color), new Rook(color) };
		for (int i = 0; i < Utility.XVALUES.length; i++) {
			boardState.put(Utility.XVALUES[xValueLoc] + yLocation, backRow[i]);
			xValueLoc++;
		}
	}
}