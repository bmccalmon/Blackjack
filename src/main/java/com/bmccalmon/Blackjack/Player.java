package com.bmccalmon.Blackjack;

public class Player {
	
	public static final double STARTING_BALANCE = 50.0;
	
	private Hand playerHand;
	private double money;
	private double currentBet;
	private int wins;
	private String name;
	
	public Player(String name) {
		playerHand = new Hand();
		this.name = name;
		this.money = STARTING_BALANCE;
	}
	
	public Hand getHand() {
		return playerHand;
	}
	
	public String getName() {
		return name;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public void addMoney(double money) {
		this.money += money;
	}
	
	public void removeMoney(double money) {
		this.money -= money;
	}
	
	public double getCurrentBet() {
		return currentBet;
	}
	
	public void setCurrentBet(double bet) {
		this.currentBet = bet;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void addWin() {
		wins++;
	}
	
}
