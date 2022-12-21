package com.bmccalmon.Blackjack;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public int getSum() {
		int sum = 0;
		for (int i = 0; i < this.size(); i++) {
			sum += this.get(i).getValue();
		}
		return sum;
	}
	
	public void add(Card card) {
		hand.add(card);
	}
	
	public void reset() {
		hand.clear();
	}
	
	public boolean remove(String name) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getName().equals(name)) {
				hand.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return hand.size();
	}
	
	public Card get(int i) {
		return hand.get(i);
	}

}
