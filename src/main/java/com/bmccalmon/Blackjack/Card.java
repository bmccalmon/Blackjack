package com.bmccalmon.Blackjack;

public class Card {
	
	private int value;
	private Face face;
	private Suit suit;
	
	private String name;
	
	public Card(int value, Face face, Suit suit) {
		this.value = value;
		this.face = face;
		this.suit = suit;
		calculateName();
	}
	
	public int getValue() {
		return value;
	}
	
	public Face getFace() {
		return face;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public String getName() {
		return name;
	}
	
	private void calculateName() {
		String faceName = "";
		String suitName = "";
		switch (this.face) {
			case JACK:
				faceName = "Jack";
				break;
			case QUEEN:
				faceName = "Queen";
				break;
			case KING:
				faceName = "King";
				break;
			case ACE:
				faceName = "Ace";
				break;
			case NUMBER:
				faceName = Integer.toString(value);
				break;
		}
		switch (this.suit) { 
			case CLUBS:
				suitName = "Clubs";
				break;
			case DIAMONDS:
				suitName = "Diamonds";
				break;
			case HEARTS:
				suitName = "Hearts";
				break;
			case SPADES:
				suitName = "Spades";
				break;
		}
		this.name = faceName + " of " + suitName;
	}
}
