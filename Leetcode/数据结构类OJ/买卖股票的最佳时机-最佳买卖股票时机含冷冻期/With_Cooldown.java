package Best_Time_to_Buy_and_Sell_Stock_with_Cooldown;

/**
 * @program: LeetCode
 * @Description:  309. 最佳买卖股票时机含冷冻期 dp空间优化解法（基于II）
 * @Author: SOYANGA
 * @Create: 2019-06-29 02:13
 * @Version 1.0
 */
public class With_Cooldown {
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length == 0) {
                return 0;
            }
            int n = prices.length;
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            int dp_pre_0 = 0; // 代表 dp[i-2][0]
            for (int i = 0; i < n; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
                //第i天是冷冻期只有一种情况，第i-1天持有股票且卖出
                dp_pre_0 = temp;
            }
            return dp_i_0;
        }
    }
}

/*
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。

 */
