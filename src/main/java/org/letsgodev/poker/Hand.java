package org.letsgodev.poker;

import org.letsgodev.trump.Card;

import java.util.Arrays;

/**
 * User: mokkeee
 * Date: 15/08/08
 */
public class Hand implements Comparable<Hand> {

    private PokerCard[] cards;

    public static Hand newHand(Card... cards) {
        return new Hand(cards);
    }

    Hand(Card[] cards) {
        this.cards = Arrays.stream(cards).map(PokerCard::new).toArray(PokerCard[]::new);
    }

    public PokerHand getPokerHand() {
        return PokerHand.judgePokerHand(this);
    }

    @Override
    public int compareTo(Hand opponent) {
        if (opponent == null) return 1;
        return PokerRule.compareTo(this, opponent);
    }

    PokerCard[] getCards() {
        return cards;
    }

    PokerCard[] getSortedPokerCards() {
        return Arrays.stream(this.cards).sorted().toArray(PokerCard[]::new);
    }
}
