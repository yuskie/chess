package com.yuskie.chessV4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yuskie.chessV4.chess.Bishop;
import com.yuskie.chessV4.chess.King;
import com.yuskie.chessV4.chess.Knight;
import com.yuskie.chessV4.chess.Pawn;
import com.yuskie.chessV4.chess.Piece;
import com.yuskie.chessV4.chess.Queen;
import com.yuskie.chessV4.chess.Rook;
import com.yuskie.chessV4.chess.Utility;

public class UtilityTest {

	@Test
	public void straight_movement_test() {
		assertFalse(Utility.straightMovement("a1", "b2", 5));
		assertFalse(Utility.straightMovement("g1", "i1", 5));
		assertFalse(Utility.straightMovement("a7", "a10", 5));
		assertFalse(Utility.straightMovement("a1", "a10", 5));
		assertTrue(Utility.straightMovement("c1", "g1", 8));
		assertTrue(Utility.straightMovement("d7", "a7", 3));
		assertTrue(Utility.straightMovement("d7", "c7", 3));
		assertTrue(Utility.straightMovement("d7", "e7", 3));
		assertTrue(Utility.straightMovement("d6", "d5", 3));
		assertTrue(Utility.straightMovement("e7", "d7", 3));

	}

	@Test
	public void diagonal_movement_test() {
		assertFalse(Utility.diagonalMovement("c1", "g1", 8));
		assertFalse(Utility.diagonalMovement("d7", "a7", 3));
		assertFalse(Utility.diagonalMovement("d7", "c7", 3));
		assertFalse(Utility.diagonalMovement("d7", "e7", 3));
		assertFalse(Utility.diagonalMovement("b2", "c4", 2));
		assertTrue(Utility.diagonalMovement("a1", "b2", 2));
		assertTrue(Utility.diagonalMovement("a1", "c3", 2));
		assertTrue(Utility.diagonalMovement("a1", "h8", 8));
		assertTrue(Utility.diagonalMovement("b2", "c3", 2));
		assertTrue(Utility.diagonalMovement("b2", "a1", 2));
		assertTrue(Utility.diagonalMovement("g2", "h1", 2));
		assertTrue(Utility.diagonalMovement("b5", "c6", 2));
	}

	@Test
	public void l_movement_test() {
		assertFalse(Utility.lMovement("c1", "g1"));
		assertFalse(Utility.lMovement("d7", "a7"));
		assertFalse(Utility.lMovement("d7", "c7"));
		assertFalse(Utility.lMovement("d7", "e7"));
		assertFalse(Utility.lMovement("b2", "c3"));
		assertFalse(Utility.lMovement("a1", "b2"));
		assertFalse(Utility.lMovement("g1", "i1"));
		assertFalse(Utility.lMovement("a7", "a10"));
		assertFalse(Utility.lMovement("a1", "a10"));
		assertTrue(Utility.lMovement("d4", "b5"));
		assertTrue(Utility.lMovement("d4", "b3"));
		assertTrue(Utility.lMovement("d4", "c2"));
		assertTrue(Utility.lMovement("d4", "c6"));
		assertTrue(Utility.lMovement("d4", "e2"));
		assertTrue(Utility.lMovement("d4", "e6"));
		assertTrue(Utility.lMovement("d4", "f3"));
		assertTrue(Utility.lMovement("d4", "f5"));
	}

	@Test
	public void getNextLoc_test() {
		// L movement
		assertEquals("b5", Utility.getNextLoc("d4", "b5"));
		assertEquals("b3", Utility.getNextLoc("d4", "b3"));
		assertEquals("c2", Utility.getNextLoc("d4", "c2"));
		assertEquals("c6", Utility.getNextLoc("d4", "c6"));
		assertEquals("e2", Utility.getNextLoc("d4", "e2"));
		assertEquals("e6", Utility.getNextLoc("d4", "e6"));
		assertEquals("f3", Utility.getNextLoc("d4", "f3"));
		assertEquals("f5", Utility.getNextLoc("d4", "f5"));
		// Straight movement
		assertEquals("d1", Utility.getNextLoc("c1", "g1"));
		assertEquals("c7", Utility.getNextLoc("d7", "a7"));
		assertEquals("c7", Utility.getNextLoc("d7", "c7"));
		assertEquals("e7", Utility.getNextLoc("d7", "e7"));
		// Diagonal movement
		assertEquals("b2", Utility.getNextLoc("a1", "b2"));
		assertEquals("b2", Utility.getNextLoc("a1", "c3"));
		assertEquals("b2", Utility.getNextLoc("a1", "h8"));
		assertEquals("c3", Utility.getNextLoc("b2", "c3"));
	}
	
	@Test
	public void generateAllMoves_Test() {
		Bishop bishop = new Bishop(Utility.Color.WHITE);
		King king = new King(Utility.Color.WHITE);
		Knight knight = new Knight(Utility.Color.WHITE);
		Pawn pawn = new Pawn(Utility.Color.WHITE);
		Queen queen = new Queen(Utility.Color.WHITE);
		Rook rook = new Rook(Utility.Color.WHITE);
		assertEquals(7, Utility.generateAllMoves("a1", (Piece) bishop).size());
		assertEquals(3, Utility.generateAllMoves("a1", (Piece) king).size());
		assertEquals(2, Utility.generateAllMoves("a1", (Piece) knight).size());
		assertEquals(3, Utility.generateAllMoves("a1", (Piece) pawn).size());
		assertEquals(21, Utility.generateAllMoves("a1", (Piece) queen).size());
		assertEquals(14, Utility.generateAllMoves("a1", (Piece) rook).size());
	}
}
