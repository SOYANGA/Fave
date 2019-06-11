package Best_Time_to_Buy_and_Sell_Stock_I;

/**
 * @program: LeetCode
 * @Description: 121. Best Time to Buy and Sell Stock 动态规划dp
 * @Author: SOYANGA
 * @Create: 2019-06-04 00:05
 * @Version 1.0
 */
public class BestTimeToBuyAndSellStock_I {
    class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                // dp[i][1] = max(dp[i-1][1], -prices[i])
                dp_i_1 = Math.max(dp_i_1, -prices[i]);
            }
            return dp_i_0;
        }
    }
}
/*
base case：
dp[-1][k][0] = dp[i][0][0] = 0
dp[-1][k][1] = dp[i][0][1] = -infinity

状态转移方程：
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

 */