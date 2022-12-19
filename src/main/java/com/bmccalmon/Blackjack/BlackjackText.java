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
			int bet = 0;
			while (bet < MIN_BET || bet > MAX_BET) {
				System.out.print(p.getName() + "'s bet: ");
				bet = input.nextInt();
				p.setCurrentBet(bet);
			}
		}
	}

}
