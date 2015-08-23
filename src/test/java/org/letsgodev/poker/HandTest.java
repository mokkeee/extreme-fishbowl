package org.letsgodev.poker;

import org.junit.Test;
import org.letsgodev.trump.Card;
import org.letsgodev.trump.Card.Rank;
import org.letsgodev.trump.Card.Suit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public class HandTest {

    static Card club_1 = new Card(Rank.Ace, Suit.Club);
    static Card club_2 = new Card(Rank.Two, Suit.Club);
    static Card club_3 = new Card(Rank.Three, Suit.Club);

    static Card heart_1 = new Card(Rank.Ace, Suit.Heart);
    static Card heart_2 = new Card(Rank.Two, Suit.Heart);
    static Card heart_3 = new Card(Rank.Three, Suit.Heart);
    static Card heart_4 = new Card(Rank.Four, Suit.Heart);
    static Card heart_5 = new Card(Rank.Five, Suit.Heart);
    static Card heart_6 = new Card(Rank.Six, Suit.Heart);

    static Card diamond_1 = new Card(Rank.Ace, Suit.Diamond);
    static Card diamond_2 = new Card(Rank.Two, Suit.Diamond);
    static Card diamond_3 = new Card(Rank.Three, Suit.Diamond);
    static Card diamond_4 = new Card(Rank.Four, Suit.Diamond);
    static Card diamond_6 = new Card(Rank.Six, Suit.Diamond);

    static Card spade_1 = new Card(Rank.Ace, Suit.Spade);
    static Card spade_10 = new Card(Rank.Ten, Suit.Spade);
    static Card spade_11 = new Card(Rank.Jack, Suit.Spade);
    static Card spade_12 = new Card(Rank.Queen, Suit.Spade);
    static Card spade_13 = new Card(Rank.King, Suit.Spade);

    @Test
    public void 役なしを判定できること() {
        Hand hand = PokerRule.newHand(club_1, club_2, heart_3, heart_4, diamond_6);
        assertThat(hand.toPokerHand(), is(PokerHand.NoPair));
    }

    @Test
    public void ワンペアを判定できること() {
        Hand hand = PokerRule.newHand(club_1, heart_1, club_2, heart_3, heart_4);
        assertThat(hand.toPokerHand(), is(PokerHand.OnePair));
    }

    @Test
    public void ツーペアを判定できること() {
        Hand hand = PokerRule.newHand(club_1, heart_1, club_2, heart_3, heart_2);
        assertThat(hand.toPokerHand(), is(PokerHand.TwoPair));
    }

    @Test
    public void スリーカードを判定できること() {
        Hand hand = PokerRule.newHand(club_1, heart_1, club_2, heart_3, diamond_1);
        assertThat(hand.toPokerHand(), is(PokerHand.ThreeOfAKind));
    }

    @Test
    public void ストレートを判定できること() {
        Hand hand = PokerRule.newHand(spade_1, diamond_2, club_3, heart_5, heart_4);
        assertThat(hand.toPokerHand(), is(PokerHand.Straight));
    }

    @Test
    public void _10から1のストレートを判定できること() {
        Hand hand = PokerRule.newHand(spade_10, spade_11, spade_12, spade_13, diamond_1);
        assertThat(hand.toPokerHand(), is(PokerHand.Straight));
    }

    @Test
    public void _11から２はストレートと判定されないこと() {
        Hand hand = PokerRule.newHand(spade_11, spade_12, spade_13, diamond_1, heart_2);
        assertThat(hand.toPokerHand(), is(not(PokerHand.Straight)));
    }

    @Test
    public void フラッシュを判定できること() {
        Hand hand = PokerRule.newHand(heart_1, heart_3, heart_4, heart_5, heart_6);
        assertThat(hand.toPokerHand(), is(PokerHand.Flush));
    }

    @Test
    public void 異なるマークのカードがある場合はフラッシュと判定されないこと() {
        Hand hand = PokerRule.newHand(heart_1, heart_3, heart_4, heart_5, diamond_6);
        assertThat(hand.toPokerHand(), is(not(PokerHand.Flush)));
    }

    @Test
    public void フルハウスを判定できること() {
        Hand hand = PokerRule.newHand(heart_1, diamond_1, spade_1, club_2, diamond_2);
        assertThat(hand.toPokerHand(), is(PokerHand.FullHouse));
    }

    @Test
    public void フォーカードを判定できること() {
        Hand hand = PokerRule.newHand(heart_1, diamond_1, club_1, diamond_2, spade_1);
        assertThat(hand.toPokerHand(), is(PokerHand.FourOfAKind));
    }

    @Test
    public void ストレートフラッシュを判定できること() {
        Hand hand = PokerRule.newHand(heart_2, heart_6, heart_3, heart_5, heart_4);
        assertThat(hand.toPokerHand(), is(PokerHand.StraightFlush));
    }

    @Test
    public void ロイヤルストレートフラッシュを判定できること() {
        Hand hand = PokerRule.newHand(spade_10, spade_13, spade_12, spade_11, spade_1);
        assertThat(hand.toPokerHand(), is(PokerHand.RoyalStraightFlush));
    }

    @Test
    public void ロイヤルストレートフラッシュはストレートフラッシュに勝つこと() {
        Hand royalStraightFlash = PokerRule.newHand(spade_10, spade_13, spade_12, spade_11, spade_1);
        Hand straightFlash = PokerRule.newHand(heart_2, heart_6, heart_3, heart_5, heart_4);

        assertThat(royalStraightFlash.compareTo(straightFlash), is(greaterThan(0)));
    }

    @Test
    public void 同じ役の場合は高いランクのカードを持っている方が勝つこと() {
        Hand noPair_13 = PokerRule.newHand(diamond_3, diamond_2, diamond_4, spade_11, spade_13);
        Hand noPair_12 = PokerRule.newHand(heart_3, club_2, heart_6, heart_5, spade_12);

        assertThat(noPair_13.compareTo(noPair_12), is(greaterThan(0)));
    }

    @Test
    public void エースとキングではエースが勝つこと() {
        Hand noPair_13 = PokerRule.newHand(diamond_4, diamond_2, club_3, spade_11, spade_13);
        Hand noPair_ace = PokerRule.newHand(heart_3, club_1, heart_2, heart_5, spade_12);

        assertThat(noPair_ace.compareTo(noPair_13), is(greaterThan(0)));
    }
}
