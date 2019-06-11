package Best_Time_to_Buy_and_Sell_Stock_II;

/**
 * @program: LeetCode
 * @Description: 122. 买卖股票的最佳时机 II dp空间优化解法
 * @Author: SOYANGA
 * @Create: 2019-06-29 01:56
 * @Version 1.0
 */
public class Best_Time_to_Buy_and_Sell_Stock_II {
    class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
            }
            return dp_i_0;
        }
    }
}
