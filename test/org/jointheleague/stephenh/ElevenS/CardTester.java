package org.jointheleague.stephenh.ElevenS;

import static org.junit.Assert.*;
import org.junit.Test;

public class CardTester {

	@Test
	public void testValues() {
		Card card = new Card("Five", "Clubs", 5);
		assertEquals("Clubs", card.suit());
		assertEquals("Five", card.rank());
		assertEquals(5, card.pointValue());
	}
	
	@Test
	public void testString() {
		Card card = new Card("Five", "Clubs", 5);
		assertEquals("Five of Clubs (point value = 5)", card.toString());
	}
	
	@Test
	public void testMatch() {
		Card a = new Card("Five", "Clubs", 5),
				b = new Card("Five", "Clubs", 5),
				c = new Card("Six", "Clubs", 5),
				d = new Card("Five", "Spades", 5),
				e = new Card("Five", "Clubs", 7);
		assertTrue(a.matches(b));
		assertFalse(a.matches(c));
		assertFalse(a.matches(d));
		assertFalse(a.matches(e));
	}

}
