package org.letsgodev.trump;

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

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }
}
