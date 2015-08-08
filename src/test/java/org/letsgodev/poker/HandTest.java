package org.letsgodev.poker;

import org.junit.Test;

import org.letsgodev.poker.Card.Suit;
import org.letsgodev.poker.Card.Rank;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public class HandTest {
    Card club_1 = new Card(Rank.Ace, Suit.Club);
    Card club_2 = new Card(Rank.Two, Suit.Club);
    Card club_3 = new Card(Rank.Three, Suit.Club);

    Card heart_1 = new Card(Rank.Ace, Suit.Heart);
    Card heart_2 = new Card(Rank.Two, Suit.Heart);
    Card heart_3 = new Card(Rank.Three, Suit.Heart);
    Card heart_4 = new Card(Rank.Four, Suit.Heart);
    Card heart_5 = new Card(Rank.Five, Suit.Heart);
    Card heart_6 = new Card(Rank.Six, Suit.Heart);

    Card diamond_1 = new Card(Rank.Ace, Suit.Diamond);
    Card diamond_2 = new Card(Rank.Two, Suit.Diamond);
    Card diamond_6 = new Card(Rank.Six, Suit.Diamond);

    Card spade_1 = new Card(Rank.Ace, Suit.Spade);
    Card spade_10 = new Card(Rank.Ten, Suit.Spade);
    Card spade_11 = new Card(Rank.Jack, Suit.Spade);
    Card spade_12 = new Card(Rank.Queen, Suit.Spade);
    Card spade_13 = new Card(Rank.King, Suit.Spade);

    @Test
    public void 役なしを判定できること() {
        Hand hand = Hand.newHand(club_1, club_2, heart_3, heart_4, diamond_6);
        assertThat(hand.getPokerHand(), is(PokerHand.NoPair));
    }

    @Test
    public void ワンペアを判定できること() {
        Hand hand = Hand.newHand(club_1, heart_1, club_2, heart_3, heart_4);
        assertThat(hand.getPokerHand(), is(PokerHand.OnePair));
    }

    @Test
    public void ツーペアを判定できること() {
        Hand hand = Hand.newHand(club_1, heart_1, club_2, heart_3, heart_2);
        assertThat(hand.getPokerHand(), is(PokerHand.TwoPair));
    }

    @Test
    public void スリーカードを判定できること() {
        Hand hand = Hand.newHand(club_1, heart_1, club_2, heart_3, diamond_1);
        assertThat(hand.getPokerHand(), is(PokerHand.ThreeOfAKind));
    }

    @Test
    public void ストレートを判定できること() {
        Hand hand = Hand.newHand(spade_1, diamond_2, club_3, heart_5, heart_4);
        assertThat(hand.getPokerHand(), is(PokerHand.Straight));
    }

    @Test
    public void _10から1のストレートを判定できること() {
        Hand hand = Hand.newHand(spade_10, spade_11, spade_12, spade_13, diamond_1);
        assertThat(hand.getPokerHand(), is(PokerHand.Straight));
    }

    @Test
    public void _11から２はストレートと判定されないこと() {
        Hand hand = Hand.newHand(spade_11, spade_12, spade_13, diamond_1, heart_2);
        assertThat(hand.getPokerHand(), is(not(PokerHand.Straight)));
    }

    @Test
    public void フラッシュを判定できること() {
        Hand hand = Hand.newHand(heart_1, heart_3, heart_4, heart_5, heart_6);
        assertThat(hand.getPokerHand(), is(PokerHand.Flush));
    }

    @Test
    public void 異なるマークのカードがある場合はフラッシュと判定されないこと() {
        Hand hand = Hand.newHand(heart_1, heart_3, heart_4, heart_5, diamond_6);
        assertThat(hand.getPokerHand(), is(not(PokerHand.Flush)));
    }

    @Test
    public void フルハウスを判定できること() {
        Hand hand = Hand.newHand(heart_1, diamond_1, spade_1, club_2, diamond_2);
        assertThat(hand.getPokerHand(), is(PokerHand.FullHouse));
    }

    @Test
    public void フォーカードを判定できること() {
        Hand hand = Hand.newHand(heart_1, diamond_1, club_1, diamond_2, spade_1);
        assertThat(hand.getPokerHand(), is(PokerHand.FourOfAKind));
    }

    @Test
    public void ストレートフラッシュを判定できること() {
        Hand hand = Hand.newHand(heart_2, heart_6, heart_3, heart_5, heart_4);
        assertThat(hand.getPokerHand(), is(PokerHand.StraightFlush));
    }

    @Test
    public void ロイヤルストレートフラッシュを判定できること() {
        Hand hand = Hand.newHand(spade_10, spade_13, spade_12, spade_11, spade_1);
        assertThat(hand.getPokerHand(), is(PokerHand.RoyalStraightFlush));
    }
}
