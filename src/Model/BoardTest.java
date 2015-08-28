package Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {
	
	@Test
	public void testDrawBishops() {
		Board b=new Board(0);
		assertEquals(true, b.testDraw());
	}
	
	@Test
	public void testDrawKnights() {
		Board b=new Board(1);
		assertEquals(true, b.testDraw());
	}
	
	@Test
	public void testDraw() {
		Board b=new Board(2);
		assertEquals(true, b.testDraw());
	}
	
	@Test
	public void testCheckmate() {
		Board b=new Board(4);
		assertEquals(true, b.testCheckmate());
	}
	
	@Test
	public void testMovePawn() {
		Board b=new Board(3);
		assertEquals(true, b.testMovePawn('w', 1, 0, 2, 0, true));
		assertEquals(true, b.testMovePawn('w', 1, 0, 3, 0, true));
		assertEquals(true, b.testMovePawn('w', 1, 0, 2, 1, true));
	}
	
	@Test
	public void testMoveRook() {
		Board b=new Board(3);
		assertEquals(true, b.testMoveRook('b', 5, 5, 6, 5, true));
		assertEquals(true, b.testMoveRook('b', 5, 5, 5, 7, true));
		assertEquals(true, b.testMoveRook('b', 5, 5, 4, 5, true));
	}
	
	@Test
	public void testMoveKnight() {
		Board b=new Board(3);
		assertEquals(true, b.testMoveKnight('b', 4, 2, 5, 4, true));
		assertEquals(true, b.testMoveKnight('b', 4, 2, 6, 3, true));
		assertEquals(true, b.testMoveKnight('b', 4, 2, 2, 1, true));
	}

	@Test
	public void testMoveBishop() {
		Board b=new Board(3);
		assertEquals(true, b.testMoveBishop('b', 5, 3, 7, 5, true));
		assertEquals(true, b.testMoveBishop('b', 5, 3, 3, 5, true));
		assertEquals(false, b.testMoveBishop('b', 5, 3, 1, 7, true));
	}
	
	@Test
	public void testMoveKing() {
		Board b=new Board(3);
		assertEquals(true, b.testMoveKing('b', 5, 1, 5, 2, true));
		assertEquals(true, b.testMoveKing('b', 5, 1, 5, 0, true));
		assertEquals(true, b.testMoveKing('b', 5, 1, 4, 1, true));
	}

}
