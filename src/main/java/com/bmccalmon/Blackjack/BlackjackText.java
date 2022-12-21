package com.bmccalmon.Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackText extends Blackjack {
	
	@Override
	protected void promptNames(ArrayList<Player> players) {
		Scanner input = new Scanner(System.in);
		System.out.print("How many players are playing? ");
		int numPlayers = input.nextInt();
		for (int i = 1; i <= numPlayers; i++) {
			System.out.print("Player " + i + " name: ");
			players.add(new Player(input.next()));
		}
	}

	@Override
	protected void promptBet(ArrayList<Player> players) {
		Scanner input = new Scanner(System.in);
		System.out.println("BETTING ROUND\tMinimum bet: " + MIN_BET + "\tMaximum bet: " + MAX_BET);
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if (p.getCurrentBet() != 0.0)
				continue;
			System.out.println(p.getName() + " has " + Blackjack.DOLLAR_SIGN + p.getMoney() + " in the bank.");
			double bet = -1;
			while (bet < MIN_BET || bet > MAX_BET || bet > p.getMoney()) {
				System.out.print(p.getName() + "'s bet: " + Blackjack.DOLLAR_SIGN);
				bet = input.nextDouble();
			}
			p.setCurrentBet(bet);
			p.removeMoney(bet);
		}
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
		
		Scanner input = new Scanner(System.in);
		String d = "null";
		while (!d.equals("h") && !d.equals("s")) {
			System.out.println(player.getName() + "'s turn. Do you want to hit (h) or stand (s)? ");
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
	
	private void showCards(Player player) {
		System.out.println(player.getName() + "'s total: " + player.getHand().getSum());
		for (int i = 0; i < player.getHand().size(); i++) {
			System.out.println(player.getHand().get(i).getName());
		}
	}

}
