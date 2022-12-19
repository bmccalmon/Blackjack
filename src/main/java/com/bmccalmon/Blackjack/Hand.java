package com.bmccalmon.Blackjack;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	private int sum;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public int getSum() {
		return sum;
	}
	
	public void add(Card card) {
		hand.add(card);
		sum += card.getValue();
	}
	
	public boolean remove(String name) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getName().equals(name)) {
				sum -= hand.get(i).getValue();
				hand.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return hand.size()+1;
	}
	
	public Card get(int i) {
		i = i - 1;
		return hand.get(i);
	}

}
