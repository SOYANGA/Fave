package Best_Time_to_Buy_and_Sell_Stock_III;

/**
 * @program: LeetCode
 * @Description: 123. 买卖股票的最佳时机 III k = 2 dp空间优化解法
 * @Author: SOYANGA
 * @Create: 2019-06-29 02:06
 * @Version 1.0
 */
public class Best_Time_to_Buy_and_Sell_Stock_III2 {
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length == 0) {
                return 0;
            }
            int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
            int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
            for (int price : prices) {
                dp_i20 = Math.max(dp_i20, dp_i21 + price);
                dp_i21 = Math.max(dp_i21, dp_i10 - price);
                dp_i10 = Math.max(dp_i10, dp_i11 + price);
                dp_i11 = Math.max(dp_i11, -price);
            }
            return dp_i20;
        }
    }
}
