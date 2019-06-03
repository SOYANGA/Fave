package Best_Time_to_Buy_and_Sell_Stock;

/**
 * @program: LeetCode
 * @Description: 121. Best Time to Buy and Sell Stock 只进行一次交易 波谷（最低）到波谷后的波峰（最大）
 * @Author: SOYANGA
 * @Create: 2019-06-04 00:05
 * @Version 1.0
 */
public class BestTimeToBuyAndSellStock {
    class Solution {
        public int maxProfit(int[] prices) {
            int min = Integer.MAX_VALUE; //波谷
            int max = 0;
            for (int price : prices) {
                if (price < min) {
                    min = price;
                } else if (price - min > max) {
                    max = price - min;
                }
            }
            return max;
        }
    }
}
