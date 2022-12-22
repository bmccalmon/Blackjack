package com.bmccalmon.Blackjack;

import java.util.Scanner;

public class BlackjackText extends Blackjack {
	
	@Override
	protected int promptNumberPlayers() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int numPlayers = -1;
		while (numPlayers < 1 || numPlayers > Blackjack.MAX_PLAYERS) {
			System.out.print("How many players are playing? ");
			numPlayers = input.nextInt();
		}
		return numPlayers;
	}
	
	@Override
	protected String promptPlayerName(int n) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Player " + n + " name: ");
		String name = input.next();
		return name;
	}
	
	@Override
	protected void notifyBetting() {
		System.out.println("BETTING ROUND\tMinimum bet: " + Blackjack.DOLLAR_SIGN + MIN_BET + "\tMaximum bet: " + Blackjack.DOLLAR_SIGN + MAX_BET);
	}
	
	@Override
	protected double promptBet(Player player) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println(player.getName() + " has " + Blackjack.DOLLAR_SIGN + player.getMoney() + " in the bank.");
		double bet = -1;
		while (bet < MIN_BET || bet > MAX_BET || bet > player.getMoney()) {
			System.out.print(player.getName() + "'s bet: " + Blackjack.DOLLAR_SIGN);
			bet = input.nextDouble();
		}
		return bet;
	}

	@Override
	protected void notifyBlackjack(Player player, double amtWon) {
		System.out.println(player.getName() + " won " + Blackjack.DOLLAR_SIGN + amtWon + " by drawing a natural!");
	}

	@Override
	protected void notifyDealerNatural() {
		System.out.println("The Dealer drew a natural. Any player who also has a natural has their bet pushed. All others have their bets collected.");
	}

	@Override
	protected Decision promptDecision(Player player, Player dealer) {
		System.out.println("Dealer's total: " + dealerFaceUpValue);
		System.out.println(dealer.getHand().get(0).getName() + "\nUnknown");
		showCards(player);
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String d = "null";
		while (!d.equals("h") && !d.equals("s")) {
			System.out.print(player.getName() + "'s turn. Do you want to hit (h) or stand (s)? ");
			d = input.next();
		}
		if (d.equals("h")) {
			System.out.println(player.getName() + " decided to hit.");
			return Decision.HIT;
		}
		System.out.println(player.getName() + " decided to stand.");
		return Decision.STAND;
	}

	@Override
	protected void notifyBust(Player player) {
		System.out.println(player.getName() + " busted with " + player.getHand().getSum() + ".");
	}

	@Override
	protected void notifyHit(Card c, Player p) {
		System.out.println(p.getName() + " drew a " + c.getName() + ".");
	}

	@Override
	protected void notifyDealerPlaying() {
		System.out.println("Playing ended. Dealer is now playing...");
	}
	
	@Override
	protected void notifyDealerUnknownFlip(Player dealer) {
		System.out.println("Flipping over the Dealer's unknown card...");
		showCards(dealer);
	}
	
	@Override
	protected void notifyDealerDone(Player dealer) {
		System.out.println("The Dealer is done drawing.");
		showCards(dealer);
	}
	
	@Override
	protected void notifyBeatDealer(Player player, int sum, double money) {
		System.out.println(player.getName() + " beat the dealer with " + sum + " and won " + Blackjack.DOLLAR_SIGN + money +"!");
	}
	
	@Override
	protected void notifyTiedDealer(Player player, int sum, double pushed) {
		System.out.println(player.getName() + " tied the dealer with " + sum + ". Their " + Blackjack.DOLLAR_SIGN + pushed + " bet gets pushed.");
	}
	
	private void showCards(Player player) {
		System.out.println(player.getName() + "'s total: " + player.getHand().getSum());
		for (int i = 0; i < player.getHand().size(); i++) {
			System.out.println(player.getHand().get(i).getName());
		}
	}

}
