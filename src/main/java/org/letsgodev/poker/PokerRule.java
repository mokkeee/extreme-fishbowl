package org.letsgodev.poker;

import org.letsgodev.trump.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * User: mokkeee
 * Date: 15/08/22
 */
public class PokerRule {

    public static Hand newHand(Card... cards) {
        return new Hand(cards);
    }

    public static int compareTo(Hand hand1, Hand hand2) {
        // 高い役の方が勝ち
        PokerHand pokerHand1 = hand1.toPokerHand();
        PokerHand pokerHand2 = hand2.toPokerHand();
        if (pokerHand1 != pokerHand2) {
            return pokerHand1.getHandRank() < pokerHand2.getHandRank() ? 1 : -1;
        }

        // 役が同じ場合は強いカードを持っていた方が勝ち
        // TODO 強いカードが同じ場合、次のカードで比較する処理未実装
        Card highCard1 = pokerHand1.getHighCard(hand1.getCards());
        Card highCard2 = pokerHand2.getHighCard(hand2.getCards());

        return cardComparator.compare(highCard1, highCard2);
    }

    static PokerHand judgePokerHand(Hand hand) {
        Card[] cards = hand.getCards();
        for (PokerHand pokerHand : PokerHand.values()) {
            if (pokerHand.isMeet(cards)) {
                return pokerHand;
            }
        }

        return PokerHand.NoPair;
    }

    static Comparator<? super Card> cardComparator = (o1, o2) -> {
        // Aceが一番強い
        if (o1.rank == Card.Rank.Ace) {
            return o2.rank != Card.Rank.Ace ? 1 : 0;
        } else if (o2.rank == Card.Rank.Ace) {
            return -1;
        }

        // Acd以外のカードは数字の順に強い
        int rank1 = o1.rank.value;
        int rank2 = o2.rank.value;
        return rank1 - rank2;
    };
}
