package maxProfit;

/**
 * @program: TSRTOffer
 * @Description: 63. 股票的最大利润 贪心
 * @Author: SOYANGA
 * @Create: 2019-08-13 00:11
 * @Version 1.0
 */
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int soFarMin = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            soFarMin = Math.min(soFarMin, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - soFarMin);
        }
        return maxProfit;
    }

}
