package org.letsgodev.poker;

import java.util.*;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public class PokerRule {
    public static PokerHand judge(Integer[] ranks) {
        // ストレート:全て連続した数字となってること
        if ( isSequentialRank(ranks) ) {
            return PokerHand.Straight;
        }
        // ストレート：数字が1,10,11,12,13のセットであること
        if ( isAceHighStraightRanks(ranks) ) {
            return PokerHand.Straight;
        }

        // スリーカード：同じ数字のカードが3枚ある
        if ( hasThreeOfAKind(ranks) ) {
            return PokerHand.ThreeOfAKind;
        }

        int pairCount = getPairCount(ranks);
        // ツーペア：同じ数字のカード2枚が2組ある
        if (pairCount == 2) {
            return PokerHand.TwoPair;
        }
        // ワンペア：同じ数字のカード2枚が１組ある
        else if (pairCount == 1) {
            return  PokerHand.OnePair;
        }
        // ノーペア：上記全ての条件に一致しない
        return PokerHand.NoPair;
    }

    private static boolean isAceHighStraightRanks(Integer[] ranks) {
        final Integer[] aceHighStraightRanks = new Integer[]{1, 10, 11, 12, 13};
        return Arrays.equals(ranks, aceHighStraightRanks);
    }

    private static boolean isSequentialRank(Integer[] ranks) {
        Arrays.sort(ranks);

        Integer minNumber = ranks[0];
        final Integer[] sequentialRanks = new Integer[]{minNumber, minNumber+1, minNumber+2, minNumber+3, minNumber+4};

        return Arrays.equals(ranks, sequentialRanks);
    }

    private static boolean hasThreeOfAKind(Integer[] ranks) {
        return getCountsPerRank(ranks).contains(3);
    }

    private static int getPairCount(Integer[] ranks) {
        Collection<Integer> counts = getCountsPerRank(ranks);
        int pairCount = 0;
        for (Integer count :counts ) {
            if ( count == 2 ) {
                pairCount++;
            }
        }
        return pairCount;
    }

    private static Collection<Integer> getCountsPerRank(Integer[] ranks) {
        Map<Integer, Integer> cardCounts = new HashMap<>();
        for ( Integer i : ranks ) {
            Integer count  = cardCounts.getOrDefault(i, 0);
            cardCounts.put(i, ++count);
        }
        return cardCounts.values();
    }


}
