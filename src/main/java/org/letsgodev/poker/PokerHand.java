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
        public boolean matches(Card[] cards) {
            return isAllSameSuit(cards) && isAceHighStraightRanks(cards);
        }
    },
    StraightFlush {
        @Override
        public boolean matches(Card[] cards) {
            return isAllSameSuit(cards) && isSequentialRank(cards);
        }
    },
    FourOfAKind {
        @Override
        public boolean matches(Card[] cards) {
            return hasFourOfAKind(cards);
        }
    },
    FullHouse {
        @Override
        public boolean matches(Card[] cards) {
            return hasThreeOfAKind(cards) && getPairCount(cards) == 1;
        }
    },
    Flush {
        @Override
        public boolean matches(Card[] cards) {
            return isAllSameSuit(cards);
        }
    },
    Straight {
        @Override
        public boolean matches(Card[] cards) {
            return isSequentialRank(cards) || isAceHighStraightRanks(cards);
        }
    },
    ThreeOfAKind {
        @Override
        public boolean matches(Card[] cards) {
            return hasThreeOfAKind(cards);
        }
    },
    TwoPair {
        @Override
        public boolean matches(Card[] cards) {
            return getPairCount(cards) == 2;
        }
    },
    OnePair {
        @Override
        public boolean matches(Card[] cards) {
            return getPairCount(cards) == 1;
        }
    },
    NoPair {
        @Override
        public boolean matches(Card[] cards) {
            return true;
        }
    };

    public static PokerHand judgePokerHand(Hand hand) {
        Card[] cards = hand.getCards();
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

    abstract boolean matches(Card[] cards);

    private static boolean hasFourOfAKind(Card[] cards) {
        return getCountsPerRank(cards).contains(4);
    }

    private static boolean isAllSameSuit(Card[] cards) {
        return Arrays.stream(cards).allMatch(card -> card.suit == cards[0].suit);
    }

    private static boolean isAceHighStraightRanks(Card[] cards) {
        final Integer[] aceHighStraightRanks = {1, 10, 11, 12, 13};
        return Arrays.equals(getCardsRankBySortedValue(cards), aceHighStraightRanks);
    }

    private static boolean isSequentialRank(Card[] cards) {
        Integer[] ranks = getCardsRankBySortedValue(cards);

        final Integer minNumber = ranks[0];
        final Integer[] sequentialRanks = {minNumber, minNumber + 1, minNumber + 2, minNumber + 3, minNumber + 4};

        return Arrays.equals(ranks, sequentialRanks);
    }

    private static Integer[] getCardsRankBySortedValue(Card[] cards) {
        Integer[] ranks = Arrays.stream(cards).map(card -> card.rank.value).sorted().toArray(Integer[]::new);
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

    public static Card[] sortCards(Card[] cards) {
        return Arrays.stream(cards).sorted(PokerRule.cardComparator).toArray(Card[]::new);
    }
}
