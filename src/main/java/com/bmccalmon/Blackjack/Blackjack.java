package com.bmccalmon.Blackjack;

import java.util.ArrayList;

/*
 * Objective: Each player attempts to beat the dealer by getting a count as close to 21 as possible, without going over 21.
 */

public abstract class Blackjack {
	
	public static final int MIN_BET = 2;
	public static final int MAX_BET = 500;
	public static final int MAX_PLAYERS = 5;
	public static final char DOLLAR_SIGN = '$';
	
	public Round round;
	
	private ArrayList<Player> players;
	private Player dealer;
	private Deck deck;
	
	protected int dealerFaceUpValue;
	
	public Blackjack() {
		dealer = new Player("Dealer");
		players = new ArrayList<Player>();
		round = Round.NAMING;
	}
	
	public Round getRound() {
		return round;
	}
	
	public void playRound() {
		if (round == Round.NAMING) {
			int numPlayers = promptNumberPlayers();
			for (int i = 1; i <= numPlayers; i++) {
				String name = promptPlayerName(i);
				players.add(new Player(name));
			}
			round = Round.BETTING;
			playRound();
		}
		else if (round == Round.BETTING) {
			notifyBetting();
			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				if (p.getCurrentBet() != 0.0)
					continue;
				double bet = promptBet(p);
				p.setCurrentBet(bet);
				p.removeMoney(bet);
			}
			round = Round.DEALING;
			playRound();
		}
		else if (round == Round.DEALING) {
			deck = new Deck(true);
			deck.shuffle();
			// Round 1 of dealing, each player gets a card
			for (int i = 0; i < players.size(); i++) {
				hit(players.get(i));
			}
			// Dealer gets a card face up
			hit(dealer);
			dealerFaceUpValue = dealer.getHand().getSum();
			// Round 2 of dealing, each player gets a second card
			for (int i = 0; i < players.size(); i++) {
				hit(players.get(i));
				fixAces(players.get(i).getHand());
			}
			// Dealer gets a card face down
			hit(dealer);
			fixAces(dealer.getHand());
			// If dealer has a natural, all players lose except those who have naturals
			if (isNatural(dealer.getHand())) {
				notifyDealerNatural();
				for (int i = 0; i < players.size(); i++) {
					Player p = players.get(i);
					if (!isNatural(p.getHand())) {
						p.setCurrentBet(0.0);
						p.getHand().reset();
						dealer.getHand().reset();
					}
				}
				round = Round.BETTING;
				playRound();
			}
			// Players with naturals get 1.5x their bet if dealer does not
			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				if (isNatural(p.getHand())) {
					p.addWin();
					double won = p.getCurrentBet() * 1.5;
					p.addMoney(won);
					p.setCurrentBet(0.0);
					p.getHand().reset();
					notifyBlackjack(p, won);
				}
			}
			round = Round.PLAYING;
			playRound();
		}
		else if (round == Round.PLAYING) {
			// Players with bets != 0.0 decide whether or not to hit until they are satisfied
			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				if (p.getCurrentBet() == 0.0)
					continue;
				while (!isBust(p.getHand())) {
					Decision d = promptDecision(p, dealer);
					if (d == Decision.STAND)
						break;
					Card c = hit(p);
					fixAces(p.getHand());
					notifyHit(c,p);
				}
				if (isBust(p.getHand()))
					notifyBust(p);
			}
			round = Round.DEALER_PLAYING;
			playRound();
		}
		else if (round == Round.DEALER_PLAYING) {
			notifyDealerPlaying();
			notifyDealerUnknownFlip(dealer);
			while (dealer.getHand().getSum() < 17) {
				Card c = hit(dealer);
				fixAces(dealer.getHand());
				notifyHit(c,dealer);
			}
			notifyDealerDone(dealer);
			round = Round.SETTLEMENT;
			playRound();
		}
		else if (round == Round.SETTLEMENT) {
			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				if (isBust(p.getHand())) {
					p.setCurrentBet(0.0);
				} else {
					boolean beatDealer = beatDealer(p);
					boolean tiedDealer = tiedDealer(p);
					if (isBust(dealer.getHand()) || beatDealer) {
						double won = p.getCurrentBet() * 2;
						notifyBeatDealer(p, p.getHand().getSum(), won);
						p.addMoney(won);
						p.setCurrentBet(0.0);
					} else if (tiedDealer) {
						notifyTiedDealer(p, p.getHand().getSum(), p.getCurrentBet());
					} else {
						p.setCurrentBet(0.0);
					}
				}
			  p.getHand().reset();
			}
			dealer.getHand().reset();
			round = Round.BETTING;
			playRound();
		}
	}
	
	private Card hit(Player player) {
		Card c = deck.pop();
		player.getHand().add(c);
		return c;
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
	
	private boolean fixAces(Hand hand) {
		// iterate through entire hand and change each ace if sum > 21
		boolean hadAce = false;
		for (int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if (c.getFace() == Face.ACE && hand.getSum() > 21) {
				c.setValue(1);
				hadAce = true;
			}
		}
		return hadAce;
	}
	
	protected abstract int promptNumberPlayers();
	protected abstract String promptPlayerName(int n);
	protected abstract void notifyBetting();
	protected abstract double promptBet(Player player);
	protected abstract void notifyBlackjack(Player player, double amtWon);
	protected abstract void notifyDealerNatural();
	protected abstract void notifyBust(Player player);
	protected abstract Decision promptDecision(Player player, Player dealer);
	protected abstract void notifyHit(Card c, Player p);
	protected abstract void notifyDealerPlaying();
	protected abstract void notifyDealerUnknownFlip(Player dealer);
	protected abstract void notifyDealerDone(Player dealer);
	protected abstract void notifyBeatDealer(Player player, int sum, double money);
	protected abstract void notifyTiedDealer(Player player, int sum, double pushed);
	
}
