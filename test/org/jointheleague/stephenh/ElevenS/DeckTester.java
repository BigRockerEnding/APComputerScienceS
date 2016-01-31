package org.jointheleague.stephenh.ElevenS;

import static org.junit.Assert.*;
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
		assertEquals(4, full.size());
		Card findA = new Card("Five", "Spades", 5);
		Card findB = new Card("Random", "Something", 0);
		boolean foundA = false;
		boolean foundB = false;
		while (!full.isEmpty()) {
			Card c = full.deal();
			if (c.matches(findA)) foundA = true;
			if (c.matches(findB)) foundB = true;
		}
		assertTrue(foundA);
		assertFalse(foundB);
	}
	
	@Test
	public void testException() {
		try {
			new Deck(null, new String[]{"0"}, new int[0]);
			fail("Object must throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			
		} catch (Exception e) {
			fail("Object must throw IllegalArgumentException");
		}
	}
	
	@Test
	public void manualTest() {
		Deck deck = new Deck(new String[]{"Five", "Six"}, new String[]{"Clubs", "Spades"}, new int[]{5, 6});
		//System.out.println(deck);
		while (!deck.isEmpty()) {
			deck.deal();
			//System.out.println(deck);
		}
	}
	
	@Test
	public void testShuffle() {
		Deck deck = new Deck(new String[]{"One", "Two"}, new String[]{"Hearts"}, new int[]{1, 2});
		deck.deal();
		deck.deal();
		assertTrue(deck.isEmpty());
		assertNull(deck.deal());
		deck.shuffle();
		assertFalse(deck.isEmpty());
		System.out.println(deck);
		assertNotNull(deck.deal());
		System.out.println(deck);
	}
}
