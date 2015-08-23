package org.letsgodev.poker;

import org.letsgodev.trump.Card;

import java.util.*;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public enum PokerHand {
    RoyalStraightFlush {
        @Override
        public boolean isMeet(Card[] cards) {
            return isAllSameSuit(cards) && isAceHighStraightRanks(cards);
        }
    },
    StraightFlush {
        @Override
        public boolean isMeet(Card[] cards) {
            return isAllSameSuit(cards) && isSequentialRank(cards);
        }
    },
    FourOfAKind {
        @Override
        public boolean isMeet(Card[] cards) {
            return hasFourOfAKind(cards);
        }
    },
    FullHouse {
        @Override
        public boolean isMeet(Card[] cards) {
            return hasThreeOfAKind(cards) && getPairCount(cards) == 1;
        }
    },
    Flush {
        @Override
        public boolean isMeet(Card[] cards) {
            return isAllSameSuit(cards);
        }
    },
    Straight {
        @Override
        public boolean isMeet(Card[] cards) {
            return isSequentialRank(cards) || isAceHighStraightRanks(cards);
        }
    },
    ThreeOfAKind {
        @Override
        public boolean isMeet(Card[] cards) {
            return hasThreeOfAKind(cards);
        }
    },
    TwoPair {
        @Override
        public boolean isMeet(Card[] cards) {
            return getPairCount(cards) == 2;
        }
    },
    OnePair {
        @Override
        public boolean isMeet(Card[] cards) {
            return getPairCount(cards) == 1;
        }
    },
    NoPair {
        @Override
        public boolean isMeet(Card[] cards) {
            return true;
        }
    };

    public abstract boolean isMeet(Card[] cards);

    public Integer getHandRank() {
        return ordinal();
    }

    private static boolean hasFourOfAKind(Card[] cards) {
        return getCountsPerRank(cards).contains(4);
    }

    private static boolean isAllSameSuit(Card[] cards) {
        return Arrays.stream(cards).allMatch(card -> card.suit == cards[0].suit);
    }

    private static boolean isAceHighStraightRanks(Card[] cards) {
        final Integer[] aceHighStraightRanks = {1, 10, 11, 12, 13};
        return Arrays.equals(getSequentialRanks(cards), aceHighStraightRanks);
    }

    private static boolean isSequentialRank(Card[] cards) {
        Integer[] ranks = getSequentialRanks(cards);

        final Integer minNumber = ranks[0];
        final Integer[] sequentialRanks = {minNumber, minNumber + 1, minNumber + 2, minNumber + 3, minNumber + 4};

        return Arrays.equals(ranks, sequentialRanks);
    }

    private static Integer[] getSequentialRanks(Card[] cards) {
        Integer[] ranks = Arrays.stream(cards).map(card -> card.rank.value).toArray(Integer[]::new);
        Arrays.sort(ranks);
        return ranks;
    }

    private static boolean hasThreeOfAKind(Card[] cards) {
        return getCountsPerRank(cards).contains(3);
    }

    private static int getPairCount(Card[] cards) {
        Collection<Integer> counts = getCountsPerRank(cards);
        return (int) counts.stream().filter(cnt -> cnt == 2).count();
    }

    private static Collection<Integer> getCountsPerRank(Card[] cards) {
        Map<Card.Rank, Integer> cardCounts = new HashMap<>();
        for (Card c : cards) {
            Integer count = cardCounts.getOrDefault(c.rank, 0);
            cardCounts.put(c.rank, ++count);
        }
        return cardCounts.values();
    }

    public Card getHighCard(Card[] cards) {
        return Arrays.stream(cards).max(PokerRule.cardComparator).get();
    }
}
