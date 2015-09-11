package org.letsgodev.poker;

import org.letsgodev.trump.Card;

/**
 * User: mokkeee
 * Date: 15/09/11
 */
class PokerCard extends Card implements Comparable<PokerCard> {

    public PokerCard(Card card) {
        super(card.rank, card.suit);
    }

    @Override
    public int compareTo(PokerCard o) {
        if (o == null) return 1;
        // Aceが一番強い
        if (this.rank == Rank.Ace) {
            return o.rank != Rank.Ace ? 1 : 0;
        } else if (o.rank == Rank.Ace) {
            return -1;
        }

        // Acd以外のカードは数字の順に強い
        final int rank1 = this.rank.value;
        final int rank2 = o.rank.value;
        return rank1 - rank2;
    }
}
