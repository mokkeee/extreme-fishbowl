package org.letsgodev.poker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import static org.letsgodev.poker.Cards.*;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public class 役判定のテスト {

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
        Hand hand = Hand.newHand(spade_1, diamond_3, club_2, heart_5, heart_4);
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

    @Test
    public void ロイヤルストレートフラッシュはストレートフラッシュに勝つこと() {
        Hand royalStraightFlash = Hand.newHand(spade_10, spade_13, spade_12, spade_11, spade_1);
        Hand straightFlash = Hand.newHand(heart_2, heart_6, heart_3, heart_5, heart_4);

        assertThat(royalStraightFlash.compareTo(straightFlash), is(greaterThan(0)));
    }
}
