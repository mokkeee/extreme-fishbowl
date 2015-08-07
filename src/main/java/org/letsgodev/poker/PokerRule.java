package org.letsgodev.poker;

import java.util.*;

import static org.letsgodev.poker.PokerHand.getCountsPerRank;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public class PokerRule {
    public static PokerHand judge(Card[] cards) {
        for ( PokerHand pokerHand : PokerHand.values() ) {
            if (pokerHand.isThisHand(cards)) {
                return pokerHand;
            }
        }

        return PokerHand.NoPair;
    }
}
