package org.letsgodev.poker;

import org.letsgodev.trump.Card;

/**
 * User: mokkeee
 * Date: 15/08/08
 */
public class Hand implements Comparable<Hand> {

    private Card[] cards;

    Hand(Card[] cards) {
        this.cards = cards;
    }

    public PokerHand getPokerHand() {
        return PokerHand.judgePokerHand(this);
    }

    @Override
    public int compareTo(Hand opponent) {
        return PokerRule.compareTo(this, opponent);
    }

    public Card[] getCards() {
        return cards;
    }
}
