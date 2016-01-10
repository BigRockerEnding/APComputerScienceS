package org.jointheleague.stephenh.ElevenS.test;

import static org.junit.Assert.*;
import org.jointheleague.stephenh.ElevenS.*;

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

}
