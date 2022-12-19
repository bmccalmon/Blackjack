package com.bmccalmon.Blackjack;

import java.util.ArrayList;

/*
 * Objective: Each player attempts to beat the dealer by getting a count as close to 21 as possible, without going over 21.
 */

public abstract class Blackjack {
	
	public static final int MIN_BET = 2;
	public static final int MAX_BET = 500;
	
	private ArrayList<Player> players;
	private Player dealer;
	private Deck deck;
	private Round round;
	
	private int dealerFaceUpValue;
	
	public Blackjack() {
		dealer = new Player("Dealer");
		deck = new Deck(true);
		players = new ArrayList<Player>();
		round = Round.NAMING;
	}
	
	public Round getRound() {
		return round;
	}
	
	public void playRound() {
		if (round == Round.NAMING) {
			promptNames(players);
			round = Round.BETTING;
		}
		if (round == Round.BETTING) {
			promptBet(players);
			round = Round.DEALING;
		}
		if (round == Round.DEALING) {
			deck.shuffle();
			// Round 1 of dealing, each player gets a card
			for (int i = 0; i < players.size(); i++) {
				hit(players.get(i));
			}
			// Dealer gets a card face up
			dealer.getHand().add(deck.pop());
			dealerFaceUpValue = dealer.getHand().getSum();
			// Round 2 of dealing, each player gets a second card
			for (int i = 0; i < players.size(); i++) {
				hit(players.get(i));
			}
			// Dealer gets a card face down
			dealer.getHand().add(deck.pop());
		}
	}
	
	private void hit(Player player) {
		player.getHand().add(deck.pop());
	}
	
	private boolean isBust(Hand hand) {
		return hand.getSum() > 21;
	}
	
	private boolean isNatural(Hand hand) {
		return hand.getSum() == 21;
	}
	
	private boolean tiedDealer(Player player) {
		return player.getHand().getSum() == dealer.getHand().getSum();
	}
	
	private boolean beatDealer(Player player) {
		return player.getHand().getSum() > dealer.getHand().getSum();
	}
	
	protected abstract void promptNames(ArrayList<Player> players);
	protected abstract void promptBet(ArrayList<Player> players);
	
}
