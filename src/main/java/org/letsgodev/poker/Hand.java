package org.letsgodev.poker;

/**
 * User: mokkeee
 * Date: 15/08/08
 */
public class Hand {

    private Card[] cards;

    public PokerHand getPokerHand() {
        return pokerHand;
    }

    private PokerHand pokerHand;

    private Hand(Card[] cards) {
        this.cards = cards;
    }

    public static Hand newHand(Card... cards) {
        Hand hand = new Hand(cards);
        hand.pokerHand = judgeHand(cards);

        return hand;
    }

    public static PokerHand judgeHand(Card[] cards) {
        for (PokerHand pokerHand : PokerHand.values()) {
            if (pokerHand.isThisHand(cards)) {
                return pokerHand;
            }
        }

        return PokerHand.NoPair;
    }
}
