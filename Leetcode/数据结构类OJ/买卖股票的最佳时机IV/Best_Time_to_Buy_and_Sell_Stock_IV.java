package Best_Time_to_Buy_and_Sell_Stock_IV;

/**
 * @program: LeetCode
 * @Description: 188. 买卖股票的最佳时机 IV dp经典解法
 * @Author: SOYANGA
 * @Create: 2019-06-29 02:09
 * @Version 1.0
 */
public class Best_Time_to_Buy_and_Sell_Stock_IV {
    class Solution {
        public int maxProfit(int k, int[] prices) {
            if(prices==null || k==0 || prices.length==0){
                return 0;
            }
            //k>n/2 即为 买卖股票的最佳时机 II k为无穷 ，使用其方法即可
            if(k>prices.length/2){
                return maxProfit(prices);
            }
            int [][][] mp = new int [prices.length][k+1][2];
            int max = 0;
            for(int i = 0;i < prices.length; i++){
                for(int n = k; n > 0  ; n--){ //交易次数
                    if(i-1 == -1){
                        //初始化
                        mp[i][n][0] =  0;
                        mp[i][n][1] = -prices[i];
                        continue;
                    }
                    mp[i][n][0] = Math.max(mp[i-1][n][0], mp[i-1][n][1] + prices[i]);
                    mp[i][n][1] = Math.max(mp[i-1][n][1], mp[i-1][n-1][0] - prices[i]);
                }
            }
            return mp[prices.length-1][k][0];
        }

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
/*
base case：
dp[-1][k][0] = dp[i][0][0] = 0
dp[-1][k][1] = dp[i][0][1] = -infinity

状态转移方程：
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

 */
