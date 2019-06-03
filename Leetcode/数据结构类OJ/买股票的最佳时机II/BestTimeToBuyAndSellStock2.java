package Best_Time_to_Buy_and_Sell_Stock;

/**
 * @program: LeetCode
 * @Description: 122. Best Time to Buy and Sell Stock II 一天可以进行多次交易 贪心算法
 * @Author: SOYANGA
 * @Create: 2019-06-04 00:05
 * @Version 1.0
 */
public class BestTimeToBuyAndSellStock2 {
    class Solution {
        public int maxProfit(int[] prices) {
            int max = 0;
            for(int i = 1; i < prices.length;i++){
                if(prices[i-1]<prices[i]){
                    max+=prices[i]-prices[i-1];
                }
            }
            return max;
        }
    }
}
