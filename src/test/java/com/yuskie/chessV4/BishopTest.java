package com.yuskie.chessV4;

import static org.junit.Assert.*;

import com.yuskie.chessV4.chess.*;

import org.junit.Before;
import org.junit.Test;


public class BishopTest {

	private Bishop newBishop;

	@Before
	public void setup() {
		newBishop = new Bishop(Utility.Color.BLACK);
	}

	@Test
	public void constructor_test() {
		assertEquals(Utility.Color.BLACK, newBishop.getColor());
		assertFalse(newBishop.isMoved());
	}

	@Test
	public void moved_boolean_change() {
		newBishop.moved();
		assertTrue(newBishop.isMoved());
	}

	@Test
	public void movement_check() {
		assertTrue(newBishop.validMove("a1", "b2"));
		assertFalse(newBishop.validMove("a1", "b1"));
		assertTrue(newBishop.validMove("a1", "c3"));
	}
}
