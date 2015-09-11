package org.letsgodev.poker;

import java.util.*;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public enum PokerHand {
    RoyalStraightFlush {
        @Override
        boolean matches(PokerCard[] cards) {
            return isAllSameSuit(cards) && isAceHighStraightRanks(cards);
        }
    },
    StraightFlush {
        @Override
        boolean matches(PokerCard[] cards) {
            return isAllSameSuit(cards) && isSequentialRank(cards);
        }
    },
    FourOfAKind {
        @Override
        boolean matches(PokerCard[] cards) {
            return hasFourOfAKind(cards);
        }
    },
    FullHouse {
        @Override
        boolean matches(PokerCard[] cards) {
            return hasThreeOfAKind(cards) && getPairCount(cards) == 1;
        }
    },
    Flush {
        @Override
        boolean matches(PokerCard[] cards) {
            return isAllSameSuit(cards);
        }
    },
    Straight {
        @Override
        boolean matches(PokerCard[] cards) {
            return isSequentialRank(cards) || isAceHighStraightRanks(cards);
        }
    },
    ThreeOfAKind {
        @Override
        boolean matches(PokerCard[] cards) {
            return hasThreeOfAKind(cards);
        }
    },
    TwoPair {
        @Override
        boolean matches(PokerCard[] cards) {
            return getPairCount(cards) == 2;
        }
    },
    OnePair {
        @Override
        boolean matches(PokerCard[] cards) {
            return getPairCount(cards) == 1;
        }
    },
    NoPair {
        @Override
        boolean matches(PokerCard[] cards) {
            return true;
        }
    };

    public static PokerHand judgePokerHand(Hand hand) {
        PokerCard[] cards = hand.getCards();
        for (PokerHand pokerHand : PokerHand.values()) {
            if (pokerHand.matches(cards)) {
                return pokerHand;
            }
        }

        return PokerHand.NoPair;
    }

    public boolean isHigherHand(PokerHand hand) {
        return this.ordinal() < hand.ordinal();
    }

    abstract boolean matches(PokerCard[] cards);

    private static boolean hasFourOfAKind(PokerCard[] cards) {
        return getCountsPerRank(cards).contains(4);
    }

    private static boolean isAllSameSuit(PokerCard[] cards) {
        return Arrays.stream(cards).allMatch(card -> card.suit == cards[0].suit);
    }

    private static boolean isAceHighStraightRanks(PokerCard[] cards) {
        final Integer[] aceHighStraightRanks = {1, 10, 11, 12, 13};
        return Arrays.equals(getPokerCardsRankBySortedValue(cards), aceHighStraightRanks);
    }

    private static boolean isSequentialRank(PokerCard[] cards) {
        Integer[] ranks = getPokerCardsRankBySortedValue(cards);

        final Integer minNumber = ranks[0];
        final Integer[] sequentialRanks = {minNumber, minNumber + 1, minNumber + 2, minNumber + 3, minNumber + 4};

        return Arrays.equals(ranks, sequentialRanks);
    }

    private static Integer[] getPokerCardsRankBySortedValue(PokerCard[] cards) {
        return Arrays.stream(cards).map(card -> card.rank.value).sorted().toArray(Integer[]::new);
    }

    private static boolean hasThreeOfAKind(PokerCard[] cards) {
        return getCountsPerRank(cards).contains(3);
    }

    private static int getPairCount(PokerCard[] cards) {
        Collection<Integer> counts = getCountsPerRank(cards);
        return (int) counts.stream().filter(cnt -> cnt == 2).count();
    }

    private static Collection<Integer> getCountsPerRank(PokerCard[] cards) {
        Map<PokerCard.Rank, Integer> cardCounts = new HashMap<>();
        for (PokerCard c : cards) {
            Integer count = cardCounts.getOrDefault(c.rank, 0);
            cardCounts.put(c.rank, ++count);
        }
        return cardCounts.values();
    }
}
