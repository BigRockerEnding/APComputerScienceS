package org.jointheleague.stephenh.ElevenS;

import static org.junit.Assert.*;

import org.jointheleague.stephenh.ElevenS.Deck;
import org.junit.Test;

public class DeckTester {
	
	@Test
	public void testValuesDealAndEmpty() {
		Deck empty = new Deck(new String[]{"0"}, new String[]{"0"}, new int[]{0});
		assertFalse(empty.isEmpty());
		empty.deal();
		assertTrue(empty.isEmpty());
		Deck full = new Deck(new String[]{"Five", "Six"}, new String[]{"Clubs", "Spades"}, new int[]{5, 6});
		assertFalse(full.isEmpty());
		Card findA = new Card("Five", "Spades", 5);
		Card findB = new Card("Random", "Something", 0);
		boolean foundA = false;
		boolean foundB = false;
		for (Card c = full.deal(); !full.isEmpty(); c = full.deal()) {
			if (c.matches(findA)) foundA = true;
			if (c.matches(findB)) foundB = true;
		}
		assertTrue(foundA);
		assertFalse(foundB);
	}
	
	@Test
	public void testException() {
		try {
			Deck a = new Deck(null, new String[]{"0"}, new int[0]);
			fail("Object must throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			
		} catch (Exception e) {
			fail("Object must throw IllegalArgumentException");
		}
	}
}
