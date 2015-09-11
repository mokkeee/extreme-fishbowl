package org.letsgodev.poker;

import org.letsgodev.trump.Card;
import org.letsgodev.trump.Card.Rank;
import org.letsgodev.trump.Card.Suit;

/**
 * User: mokkeee
 * Date: 15/09/11
 */
public class Cards {
    static final Card club_1 = new Card(Rank.Ace, Suit.Club);
    static final Card club_2 = new Card(Rank.Two, Suit.Club);
    static final Card club_3 = new Card(Rank.Three, Suit.Club);

    static final Card heart_1 = new Card(Rank.Ace, Suit.Heart);
    static final Card heart_2 = new Card(Rank.Two, Suit.Heart);
    static final Card heart_3 = new Card(Rank.Three, Suit.Heart);
    static final Card heart_4 = new Card(Rank.Four, Suit.Heart);
    static final Card heart_5 = new Card(Rank.Five, Suit.Heart);
    static final Card heart_6 = new Card(Rank.Six, Suit.Heart);

    static final Card diamond_1 = new Card(Rank.Ace, Suit.Diamond);
    static final Card diamond_2 = new Card(Rank.Two, Suit.Diamond);
    static final Card diamond_3 = new Card(Rank.Three, Suit.Diamond);
    static final Card diamond_4 = new Card(Rank.Four, Suit.Diamond);
    static final Card diamond_6 = new Card(Rank.Six, Suit.Diamond);

    static final Card spade_1 = new Card(Rank.Ace, Suit.Spade);
    static final Card spade_10 = new Card(Rank.Ten, Suit.Spade);
    static final Card spade_11 = new Card(Rank.Jack, Suit.Spade);
    static final Card spade_12 = new Card(Rank.Queen, Suit.Spade);
    static final Card spade_13 = new Card(Rank.King, Suit.Spade);
}
