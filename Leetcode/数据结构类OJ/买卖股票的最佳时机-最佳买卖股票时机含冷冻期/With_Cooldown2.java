package Best_Time_to_Buy_and_Sell_Stock_with_Cooldown;

/**
 * @program: LeetCode
 * @Description:  309. 最佳买卖股票时机含冷冻期 dp经典解法（基于II）
 * @Author: SOYANGA
 * @Create: 2019-06-29 02:13
 * @Version 1.0
 */
public class With_Cooldown2 {
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length == 0) {
                return 0;
            }
            int n = prices.length;
            int mp[][] = new int [n][3]; //由于可以无限次交易，所以只定义两个维度，第一个维度是天数，第二个维度表示是否持有股票，0表示不持有，1表示持有，2表示过渡期
            mp[0][0] = 0;
            mp[0][1] = -prices[0];
            mp[0][2] = 0;
            for(int i = 1;i < n; i++) {
                //第i天不持有股票的情况有两种
                //a.第i-1天也不持有股票
                //b.第i-1天是过渡期
                mp[i][0] = Math.max(mp[i-1][0], mp[i-1][2]);
                //第i天持有股票有两种情况
                //a.第i-1天也持有股票，第i天不操作，
                //b.第i-1天不持有股票，在第i天买入
                mp[i][1] = Math.max(mp[i-1][1], mp[i-1][0] - prices[i]);
                //第i天是冷冻期只有一种情况，第i-1天持有股票且卖出
                mp[i][2] = mp[i-1][1] + prices[i];
            }
            //最后最大利润为最后一天，不持有股票或者进入冷冻期的情况
            return Math.max(mp[n-1][0], mp[n-1][2]);
        }
    }

}

/*
由于可以无限次交易，所以只定义两个维度，第一个维度是天数，第二个维度表示是否持有股票，0表示不持有，1表示持有，2表示过渡期

                //第i天不持有股票的情况有两种
                //a.第i-1天也不持有股票
                //b.第i-1天是过渡期
                mp[i][0] = Math.max(mp[i-1][0], mp[i-1][2]);
                //第i天持有股票有两种情况
                //a.第i-1天也持有股票，第i天不操作，
                //b.第i-1天不持有股票，在第i天买入
                mp[i][1] = Math.max(mp[i-1][1], mp[i-1][0] - prices[i]);
                //第i天是冷冻期只有一种情况，第i-1天持有股票且卖出
                mp[i][2] = mp[i-1][1] + prices[i];

 */
