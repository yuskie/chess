package com.yuskie.chessV4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.yuskie.chessV4.chess.*;

public class KnightTest {
	private Knight newKnight;
	
	@Before
	public void setup(){
		newKnight = new Knight(Utility.Color.BLACK);
	}

	@Test
	public void constructor_test() {
		assertEquals(Utility.Color.BLACK, newKnight.getColor());
		assertFalse(newKnight.isMoved());
	}
	
	@Test
	public void moved_boolean_change(){
		newKnight.moved();
		assertTrue(newKnight.isMoved());
	}
	
	@Test
	public void movement_check(){
		assertTrue(newKnight.validMove("a1", "c2"));
		assertFalse(newKnight.validMove("a1", "b1"));
		assertTrue(newKnight.validMove("a1", "b3"));
	}
}
