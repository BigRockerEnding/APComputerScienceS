package org.jointheleague.stephenh.ElevenS;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * The Deck class represents a shuffled deck of cards. It provides several
 * operations including initialize, shuffle, deal, and check if empty.
 */
public class Deck {

	/**
	 * cards contains all the cards in the deck.
	 */
	private List<Card> cards;

	/**
	 * size is the number of not-yet-dealt cards. Cards are dealt from the top
	 * (highest index) down. The next card to be dealt is at size - 1.
	 */
	private int size;

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits, and produces
	 * one of the corresponding card.
	 * 
	 * @param ranks
	 *            is an array containing all of the card ranks.
	 * @param suits
	 *            is an array containing all of the card suits.
	 * @param values
	 *            is an array containing all of the card point values.
	 */
	public Deck(String[] ranks, String[] suits, int[] values) {
		if (ranks == null || ranks.length <= 0 || suits == null || suits.length <= 0 || values == null
				|| values.length <= 0 || ranks.length != values.length)
			throw new IllegalArgumentException("ranks, suits, and values can't be empty, and ranks must equal values");
		cards = new ArrayList<Card>();
		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				cards.add(new Card(ranks[i], suit, values[i]));
			}
		}
		shuffle();
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 * 
	 * @return true if this deck is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 * 
	 * @return the number of undealt cards in this deck.
	 */
	public int size() {
		return size;
	}

	/**
	 * Randomly permute the given collection of cards and reset the size to
	 * represent the entire deck.
	 */
	public void shuffle() {
		Card[] shuffled = new Card[cards.size()];
		for (int i = 0; i < cards.size(); i++) {
			shuffled[i] = cards.get(i);
		}
		for (int i = shuffled.length - 1; i > 0; i--) {
			int r = new Random().nextInt(i + 1);
			Card temp = shuffled[i];
			shuffled[i] = shuffled[r];
			shuffled[r] = temp;
		}
		cards.clear();
		for (Card card : shuffled) {
			cards.add(card);
		}
		this.size = cards.size();
	}

	/**
	 * Deals a card from this deck.
	 * 
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if (this.isEmpty()) return null;
		size--;
		return cards.get(size);
	}

	/**
	 * Generates and returns a string representation of this deck.
	 * 
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";
		if (size == 0) rtn = rtn + "none\n";
		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		if (cards.size() <= size) rtn = rtn + "none\n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}
