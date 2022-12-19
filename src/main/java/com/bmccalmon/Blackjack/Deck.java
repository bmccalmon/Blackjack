package com.bmccalmon.Blackjack;

import java.util.ArrayList;

public class Deck extends ArrayStack<Card> {
	
	public Deck() {}
	
	public Deck(boolean standard52) {
		if (standard52)
			resetDeck();
	}
	
	public void shuffle() {
		/*
		 1. Pop all cards into an Arraylist
		 2. Use shuffle algorithm
		 3. Push each card back into the stack
		 */
		ArrayList<Card> toShuffle = new ArrayList<Card>();
		while (!this.isEmpty())
			toShuffle.add(this.pop());
		for (int i = toShuffle.size()-1; i > 0; i--) {
			int roll = (int)(Math.random() * i);
			if (roll == i)
				continue;
			// swap roll and i
			Card temp = toShuffle.get(i);
			toShuffle.set(i, toShuffle.get(roll));
			toShuffle.set(roll, temp);
		}
		for (int i = 0; i < toShuffle.size(); i++)
			this.push(toShuffle.get(i));
	}
	
	private void resetDeck() {
		// TODO: Maybe have resetDeck() in the BlackJack class
		// Clears deck and adds the standard 52 cards
		while (!this.isEmpty()) {
			this.pop();
		}
		// 2
		this.push(new Card(2, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(2, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(2, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(2, Face.NUMBER, Suit.SPADES));
		// 3
		this.push(new Card(3, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(3, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(3, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(3, Face.NUMBER, Suit.SPADES));
		// 4
		this.push(new Card(4, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(4, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(4, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(4, Face.NUMBER, Suit.SPADES));
		// 5
		this.push(new Card(5, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(5, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(5, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(5, Face.NUMBER, Suit.SPADES));
		// 6
		this.push(new Card(6, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(6, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(6, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(6, Face.NUMBER, Suit.SPADES));
		// 7
		this.push(new Card(7, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(7, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(7, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(7, Face.NUMBER, Suit.SPADES));
		// 8
		this.push(new Card(8, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(8, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(8, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(8, Face.NUMBER, Suit.SPADES));
		// 9
		this.push(new Card(9, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(9, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(9, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(9, Face.NUMBER, Suit.SPADES));
		// 10
		this.push(new Card(10, Face.NUMBER, Suit.CLUBS));
		this.push(new Card(10, Face.NUMBER, Suit.DIAMONDS));
		this.push(new Card(10, Face.NUMBER, Suit.HEARTS));
		this.push(new Card(10, Face.NUMBER, Suit.SPADES));
		// Jack
		this.push(new Card(10, Face.JACK, Suit.CLUBS));
		this.push(new Card(10, Face.JACK, Suit.DIAMONDS));
		this.push(new Card(10, Face.JACK, Suit.HEARTS));
		this.push(new Card(10, Face.JACK, Suit.SPADES));
		// Queen
		this.push(new Card(10, Face.QUEEN, Suit.CLUBS));
		this.push(new Card(10, Face.QUEEN, Suit.DIAMONDS));
		this.push(new Card(10, Face.QUEEN, Suit.HEARTS));
		this.push(new Card(10, Face.QUEEN, Suit.SPADES));
		// King
		this.push(new Card(10, Face.KING, Suit.CLUBS));
		this.push(new Card(10, Face.KING, Suit.DIAMONDS));
		this.push(new Card(10, Face.KING, Suit.HEARTS));
		this.push(new Card(10, Face.KING, Suit.SPADES));
		// Ace
		this.push(new Card(11, Face.ACE, Suit.CLUBS));
		this.push(new Card(11, Face.ACE, Suit.DIAMONDS));
		this.push(new Card(11, Face.ACE, Suit.HEARTS));
		this.push(new Card(11, Face.ACE, Suit.SPADES));
	}
}
