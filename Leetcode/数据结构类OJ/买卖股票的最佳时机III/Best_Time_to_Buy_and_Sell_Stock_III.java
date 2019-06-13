package Best_Time_to_Buy_and_Sell_Stock_III;

/**
 * @program: LeetCode
 * @Description: 123. 买卖股票的最佳时机 III k = 2 dp经典解法
 * @Author: SOYANGA
 * @Create: 2019-06-29 02:06
 * @Version 1.0
 */
public class Best_Time_to_Buy_and_Sell_Stock_III {
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length == 0) {
                return 0;
            }
            int maxK = 2;
            int n = prices.length;
            int [][][]mp = new int[n][maxK+1][2];
            for(int i = 0;i < n; i++) {
                for(int k = maxK; k > 0;k--) {
                    if(i-1 == -1) {
                        mp[i][k][0] = 0;
                        mp[i][k][1] = -prices[i];
                        continue;
                    }
                    mp[i][k][0] = Math.max(mp[i-1][k][0], mp[i-1][k][1] + prices[i]);
                    mp[i][k][1] = Math.max(mp[i-1][k][1], mp[i-1][k-1][0] - prices[i]);
                }
            }
            return mp[n-1][maxK][0];
        }
    }
}
