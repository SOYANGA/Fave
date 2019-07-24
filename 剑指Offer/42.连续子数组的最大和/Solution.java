package FindGreatestSumOfSubArray;

/**
 * @program: TSRTOffer
 * @Description: 42连续子数组的最大和 dp
 * @Author: SOYANGA
 * @Create: 2019-07-27 00:29
 * @Version 1.0
 */
public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int maxSum = dp[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            maxSum = maxSum > dp[i] ? maxSum : dp[i];
        }
        return maxSum;
    }
}
/*
dp[i] = max(dp[i-1] + a[i] , a[i]);
*/
