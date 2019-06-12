package Best_Time_to_Buy_and_Sell_Stock_II;

/**
 * @program: LeetCode
 * @Description: 122. 买卖股票的最佳时机 II dp经典解法
 * @Author: SOYANGA
 * @Create: 2019-06-29 01:56
 * @Version 1.0
 */
public class Best_Time_to_Buy_and_Sell_Stock_II2 {
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length == 0) {
                return 0;
            }
            int n = prices.length;
            int[][] mp = new int [n][2];
            mp[0][0] = 0;
            mp[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                mp[i][0] = Math.max(mp[i-1][0], mp[i-1][1] + prices[i]);
                mp[i][1] = Math.max(mp[i-1][1], mp[i][0] - prices[i]);
            }
            return mp[n-1][0];
        }
    }
}
