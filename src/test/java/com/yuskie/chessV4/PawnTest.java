package com.yuskie.chessV4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.yuskie.chessV4.chess.*;


public class PawnTest {
	private Pawn newPawn;
	
	@Before
	public void setup(){
		newPawn = new Pawn(Utility.Color.BLACK);
	}

	@Test
	public void constructor_test() {
		assertEquals(Utility.Color.BLACK, newPawn.getColor());
		assertFalse(newPawn.isMoved());
	}
	
	@Test
	public void moved_boolean_change(){
		newPawn.moved();
		assertTrue(newPawn.isMoved());
	}
	
	@Test
	public void movement_check(){
		assertTrue(newPawn.validMove("a3", "a2"));
		assertFalse(newPawn.validMove("a1", "b1"));
		assertTrue(newPawn.validMove("a3", "a1"));
		assertTrue(newPawn.validMove("c2", "c1"));
		Pawn whitePawn = new Pawn(Utility.Color.WHITE);
		assertFalse(whitePawn.validMove("a3", "a1"));
		assertFalse(whitePawn.validMove("a3", "a2"));
		assertFalse(whitePawn.validMove("a1", "b1"));
		assertFalse(whitePawn.validMove("a3", "a1"));
		assertFalse(whitePawn.validMove("c2", "c1"));
		assertTrue(whitePawn.validMove("a1", "a2"));
		assertTrue(whitePawn.validMove("a1", "a3"));
		whitePawn.moved();
		assertFalse(whitePawn.validMove("a1", "a3"));
		assertTrue(whitePawn.validMove("a1", "a2"));
		newPawn.moved();
		assertFalse(newPawn.validMove("c7", "c5"));
		assertTrue(newPawn.validMove("c7", "c6"));

	}

}
