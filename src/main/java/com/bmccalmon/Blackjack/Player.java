package com.bmccalmon.Blackjack;

public class Player {
	
	private Hand playerHand;
	private int money;
	private int currentBet;
	private int wins;
	private String name;
	
	public Player(String name) {
		playerHand = new Hand();
		this.name = name;
	}
	
	public Hand getHand() {
		return playerHand;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getCurrentBet() {
		return currentBet;
	}
	
	public void setCurrentBet(int bet) {
		this.currentBet = bet;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void addWin() {
		wins++;
	}
	
}
