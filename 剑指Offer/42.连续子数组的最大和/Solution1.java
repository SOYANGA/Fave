package FindGreatestSumOfSubArray;

/**
 * @program: TSRTOffer
 * @Description: 42 连续子数组的最大和 dp空间优化
 * @Author: SOYANGA
 * @Create: 2019-07-27 00:29
 * @Version 1.0
 */
public class Solution1 {
    public int FindGreatestSumOfSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int greatestSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            sum = sum <= 0 ? val : sum + val;
            greatestSum = Math.max(greatestSum, sum);
        }
        return greatestSum;
    }

}
/*
dp[i] = max(dp[i-1] + a[i] , a[i]);
*/
