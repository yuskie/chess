package com.yuskie.chessV4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.yuskie.chessV4.chess.*;


public class RookTest {
	private Rook newRook;
	@Before
	public void setup(){
		newRook = new Rook(Utility.Color.BLACK);
	}

	@Test
	public void constructor_test() {
		assertEquals(Utility.Color.BLACK, newRook.getColor());
		assertFalse(newRook.isMoved());
	}
	
	@Test
	public void moved_boolean_change(){
		newRook.moved();
		assertTrue(newRook.isMoved());
	}
	
	@Test
	public void movement_check(){
		assertTrue(newRook.validMove("a1", "a2"));
		assertFalse(newRook.validMove("a1", "b2"));
		assertTrue(newRook.validMove("a1", "a8"));
		assertTrue(newRook.validMove("a1", "h1"));
		assertTrue(newRook.validMove("a1", "g1"));
	}
}
