package Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee;

/**
 * @program: LeetCode
 * @Description: 714. 买卖股票的最佳时机含手续费 经典dp解法
 * @Author: SOYANGA
 * @Create: 2019-06-29 02:16
 * @Version 1.0
 */
public class With_Transaction_Fee {
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            if(prices == null || prices.length == 0) {
                return 0;
            }
            int n = prices.length;
            int[][] mp = new int[n][2];
            mp[0][0] = 0;
            mp[0][1] = - prices[0]-fee;
            for (int i = 1; i < n; i++) {
                mp[i][0] = Math.max(mp[i-1][0], mp[i-1][1] + prices[i]);
                mp[i][1] = Math.max(mp[i-1][1], mp[i-1][0] - prices[i]-fee);
            }
            return mp[n-1][0];
        }
    }
}
/*
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)//设，前一天卖出交手续费（记完整一次）

> 解释：相当于买入股票的价格升高了。
> 在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
 */