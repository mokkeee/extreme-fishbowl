package org.letsgodev.poker;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: mokkeee
 * Date: 15/08/27
 */
@RunWith(Enclosed.class)
public class CheckTest {
    public static class テスト {
        @Test
        public void テスト() {

            assertThat(1, is(1));
        }
    }
}
