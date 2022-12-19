package com.bmccalmon.Blackjack;

/**
 * Blackjack implementation
 * @author benmccalmon
 * @version 0.0.1-SNAPSHOT
 */

public class App 
{
    public static void main( String[] args )
    {
    	Blackjack blackjack = new BlackjackText();
    	blackjack.playRound();
    }
}
