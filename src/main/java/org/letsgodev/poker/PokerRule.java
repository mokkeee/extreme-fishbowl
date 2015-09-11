package org.letsgodev.poker;

import org.letsgodev.trump.Card;

import java.util.Comparator;

/**
 * User: mokkeee
 * Date: 15/08/22
 */
public class PokerRule {

    // FIXME 上位のPokerGameクラスから呼び出したほうがいいかも


    static int compareTo(Hand hand1, Hand hand2) {
        // 高い役の方が勝ち
        PokerHand pokerHand1 = hand1.getPokerHand();
        PokerHand pokerHand2 = hand2.getPokerHand();
        if (pokerHand1 != pokerHand2) {
            return pokerHand1.isHigherHand(pokerHand2) ? 1 : -1;
        }

        // 役が同じ場合は強いカードを持っていた方が勝ち
        // FIXME PokerHandに寄せたほうがよい
        PokerCard[] sortedCard1 = hand1.getSortedPokerCards();
        PokerCard[] sortedCard2 = hand2.getSortedPokerCards();

        for (int i = sortedCard1.length - 1; i >= 0; i--) {
            PokerCard card1 = sortedCard1[i];
            PokerCard card2 = sortedCard2[i];
            int compareVal = card1.compareTo(card2);
            if (compareVal != 0) {
                return compareVal;
            }
        }

        return 0;
    }
}
