package org.letsgodev.poker;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public enum PokerHand {
    FullHouse {
        @Override
        public boolean isThisHand(Card[] cards) {
            return hasThreeOfAKind(cards) && getPairCount(cards) == 1;
        }
    },
    Flush {
        @Override
        public boolean isThisHand(Card[] cards) {
            return hasSameSuit(cards);
        }
    },
    Straight {
        @Override
        public boolean isThisHand(Card[] cards) {
            if (isSequentialRank(cards)) {
                return true;
            }
            if (isAceHighStraightRanks(cards)) {
                return true;
            }
            return false;
        }
    },
    ThreeOfAKind {
        @Override
        public boolean isThisHand(Card[] cards) {
            return hasThreeOfAKind(cards);
        }
    },
    TwoPair {
        @Override
        public boolean isThisHand(Card[] cards) {
            return getPairCount(cards) == 2;
        }
    },
    OnePair {
        @Override
        public boolean isThisHand(Card[] cards) {
            return getPairCount(cards) == 1;
        }
    },
    NoPair {
        @Override
        public boolean isThisHand(Card[] cards) {
            return true;
        }
    };

    public abstract boolean isThisHand(Card[] cards);

    private static boolean hasSameSuit(Card[] cards) {
        return Arrays.stream(cards).allMatch(card -> card.suit == cards[0].suit);
    }

    private static boolean isAceHighStraightRanks(Card[] cards) {
        final Integer[] aceHighStraightRanks = new Integer[]{1, 10, 11, 12, 13};
        return Arrays.equals(getSequentialRanks(cards), aceHighStraightRanks);
    }

    private static boolean isSequentialRank(Card[] cards) {
        Integer[] ranks = getSequentialRanks(cards);

        final Integer minNumber = ranks[0];
        final Integer[] sequentialRanks = new Integer[]{minNumber, minNumber + 1, minNumber + 2, minNumber + 3, minNumber + 4};

        return Arrays.equals(ranks, sequentialRanks);
    }

    private static Integer[] getSequentialRanks(Card[] cards) {
        Integer[] ranks = Arrays.stream(cards).map(card -> card.rank).toArray(len -> new Integer[len]);
        Arrays.sort(ranks);
        return ranks;
    }

    private static boolean hasThreeOfAKind(Card[] cards) {
        return getCountsPerRank(cards).contains(3);
    }

    protected static int getPairCount(Card[] cards) {
        Collection<Integer> counts = getCountsPerRank(cards);
        return (int) counts.stream().filter(cnt -> cnt == 2).count();
    }

    protected static Collection<Integer> getCountsPerRank(Card[] cards) {
        Map<Integer, Integer> cardCounts = new HashMap<>();
        for (Card c : cards) {
            Integer count = cardCounts.getOrDefault(c.rank, 0);
            cardCounts.put(c.rank, ++count);
        }
        return cardCounts.values();
    }
}
