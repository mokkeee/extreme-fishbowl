package org.letsgodev.poker;

/**
 * User: mokkeee
 * Date: 15/08/07
 */
public class Card {

    public final Suit suit;
    public final Rank rank;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public enum Suit {
        Diamond, Heart, Spade, Club,
    }

    public enum Rank {
        Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6),
        Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(13);

        public final Integer value;

        Rank(Integer rankValue) {
            this.value = rankValue;
        }
    }
}
