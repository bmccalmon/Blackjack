package com.bmccalmon.Blackjack;

/**
 * Blackjack implementation
 * @author benmccalmon
 * @version 1.0
 */

public class App 
{
	
    public static void main( String[] args )
    {
    	Blackjack blackjack = new BlackjackText();
    	blackjack.round = Round.NAMING;
    	blackjack.playRound();
    }
}
