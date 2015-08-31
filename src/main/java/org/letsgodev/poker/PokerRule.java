package org.letsgodev.poker;

import org.letsgodev.trump.Card;

import java.util.Comparator;

/**
 * User: mokkeee
 * Date: 15/08/22
 */
public class PokerRule {

    // FIXME 上位のPokerGameクラスから呼び出したほうがいいかも

    public static Hand newHand(Card... cards) {
        return new Hand(cards);
    }

    static int compareTo(Hand hand1, Hand hand2) {
        // 高い役の方が勝ち
        PokerHand pokerHand1 = hand1.getPokerHand();
        PokerHand pokerHand2 = hand2.getPokerHand();
        if (pokerHand1 != pokerHand2) {
            return pokerHand1.isHigherHand(pokerHand2) ? 1 : -1;
        }

        // 役が同じ場合は強いカードを持っていた方が勝ち
        // FIXME PokerHandに寄せたほうがよい
        Card[] sortedCard1 = pokerHand1.sortCards(hand1.getCards());
        Card[] sortedCard2 = pokerHand2.sortCards(hand2.getCards());

        for (int i = 0; i < sortedCard1.length; i++) {
            Card card1 = sortedCard1[i];
            Card card2 = sortedCard2[i];
            if (card1 != card2) {
                return cardComparator.compare(card1, card2) * (-1);
            }
        }

        return 0;
    }


    // FIXME Cardをラップするクラスで持ったほうがよいかも
    static Comparator<? super Card> cardComparator = (o1, o2) -> {
        // Aceが一番強い
        if (o1.rank == Card.Rank.Ace) {
            return o2.rank != Card.Rank.Ace ? -1 : 0;
        } else if (o2.rank == Card.Rank.Ace) {
            return 1;
        }

        // Acd以外のカードは数字の順に強い
        int rank1 = o1.rank.value;
        int rank2 = o2.rank.value;
        return rank2 - rank1;
    };
}
