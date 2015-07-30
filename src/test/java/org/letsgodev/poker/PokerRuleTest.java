package org.letsgodev.poker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * User: mokkeee
 * Date: 15/07/30
 */
public class PokerRuleTest {

    @Test
    public  void ワンペアを判定できること() {
       assertThat(PokerRule.judge(new Integer[]{1, 1, 2, 3, 4}), is(PokerHand.OnePair));
    }

    @Test
    public void 役なしを判定できること() {
        assertThat(PokerRule.judge(new Integer[]{1, 2, 3, 4, 6}), is(PokerHand.NoPair));
    }

    @Test
    public void ツーペアを判定できること() {
        assertThat(PokerRule.judge(new Integer[]{1,1,2,2,3}), is(PokerHand.TwoPair));
    }

    @Test
    public void スリーカードを判定できること() {
        assertThat(PokerRule.judge(new Integer[]{1, 1, 1, 2, 3}), is(PokerHand.ThreeOfAKind));
    }

    @Test
    public void ストレートを判定できること() {
        assertThat(PokerRule.judge(new Integer[]{1, 2, 3, 4, 5}), is(PokerHand.Straight));
//        assertThat(PokerRule.judge(new Integer[]{12, 13, 11, 2, 1}), is(not(PokerHand.Straight)));
    }

    @Test
    public void _10から1のストレートを判定できること() {
        assertThat(PokerRule.judge(new Integer[]{12, 13, 11, 10, 1}), is(PokerHand.Straight));
    }
}
