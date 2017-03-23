package com.yuskie.chessV4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.yuskie.chessV4.chess.*;

public class KingTest {
	private King newKing;
	
	@Before
	public void setup(){
		newKing = new King(Utility.Color.BLACK);
	}

	@Test
	public void constructor_test() {
		assertEquals(Utility.Color.BLACK, newKing.getColor());
		assertFalse(newKing.isMoved());
	}
	
	@Test
	public void moved_boolean_change(){
		newKing.moved();
		assertTrue(newKing.isMoved());
	}
	
	@Test
	public void movement_check(){
		assertTrue(newKing.validMove("a1", "a2"));
		assertFalse(newKing.validMove("a1", "c3"));
		assertTrue(newKing.validMove("a1", "b2"));
	}
}
