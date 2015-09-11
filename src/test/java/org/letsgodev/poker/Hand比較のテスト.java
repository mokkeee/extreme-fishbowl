package org.letsgodev.poker;

import org.junit.Test;
import org.letsgodev.trump.Card;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import static org.letsgodev.poker.Cards.*;

/**
 * User: mokkeee
 * Date: 15/09/11
 */
public class Hand比較のテスト {


    @Test
    public void カードをランク順に並べ変えられること() {
        Hand hand = Hand.newHand(diamond_1, diamond_2, spade_13, spade_12, diamond_4);

        Card[] sut = hand.getSortedPokerCards();
        final Card[] sortedCards = new Card[]{diamond_2, diamond_4, spade_12, spade_13, diamond_1};
        assertThat(sut, is(sortedCards));
    }

    @Test
    public void 同じ役の場合は高いランクのカードを持っている方が勝つこと() {
        Hand noPair_13 = Hand.newHand(diamond_3, diamond_2, diamond_4, spade_11, spade_13);
        Hand noPair_12 = Hand.newHand(heart_3, club_2, heart_6, heart_5, spade_12);

        assertThat(noPair_13.compareTo(noPair_12), is(greaterThan(0)));
    }

    @Test
    public void エースとキングではエースが勝つこと() {
        Hand noPair_13 = Hand.newHand(diamond_4, diamond_2, club_3, spade_11, spade_13);
        Hand noPair_ace = Hand.newHand(heart_3, club_1, heart_2, heart_5, spade_12);

        assertThat(noPair_ace.compareTo(noPair_13), is(greaterThan(0)));
    }
}
