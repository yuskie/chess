package com.yuskie.chessV4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.yuskie.chessV4.chess.*;

public class QueenTest {
	private Queen newQueen;

	@Before
	public void setup() {
		newQueen = new Queen(Utility.Color.BLACK);
	}

	@Test
	public void constructor_test() {
		assertEquals(Utility.Color.BLACK, newQueen.getColor());
		assertFalse(newQueen.isMoved());
	}

	@Test
	public void moved_boolean_change() {
		newQueen.moved();
		assertTrue(newQueen.isMoved());
	}

	@Test
	public void movement_check() {
		assertTrue(newQueen.validMove("a1", "a2"));
		assertFalse(newQueen.validMove("a1", "c2"));
		assertTrue(newQueen.validMove("a1", "a8"));
		assertTrue(newQueen.validMove("a1", "h1"));
		assertTrue(newQueen.validMove("a1", "g1"));
		assertTrue(newQueen.validMove("a1", "h8"));
	}
}
