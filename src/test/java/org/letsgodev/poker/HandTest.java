package org.letsgodev.poker;

import org.junit.Test;

import org.letsgodev.poker.Card.Suit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public class HandTest {
    Card club_1 = new Card(1, Suit.Club);
    Card club_2 = new Card(2, Suit.Club);
    Card club_3 = new Card(3, Suit.Club);

    Card heart_1 = new Card(1, Suit.Heart);
    Card heart_2 = new Card(2, Suit.Heart);
    Card heart_3 = new Card(3, Suit.Heart);
    Card heart_4 = new Card(4, Suit.Heart);
    Card heart_5 = new Card(5, Suit.Heart);
    Card heart_6 = new Card(6, Suit.Heart);

    Card diamond_1 = new Card(1, Suit.Diamond);
    Card diamond_2 = new Card(2, Suit.Diamond);
    Card diamond_6 = new Card(6, Suit.Diamond);

    Card spade_1 = new Card(1, Suit.Spade);
    Card spade_10 = new Card(10, Suit.Spade);
    Card spade_11 = new Card(11, Suit.Spade);
    Card spade_12 = new Card(12, Suit.Spade);
    Card spade_13 = new Card(13, Suit.Spade);

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
//        assertThat(PokerRule.judge(new Integer[]{12, 13, 11, 2, 1}), is(not(PokerHand.Straight)));
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
}
