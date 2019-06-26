package Longest_Increasing_Subsequence;

/**
 * @program: LeetCode
 * @Description: 300. 最长上升子序列 动态规划版
 * @Author: SOYANGA
 * @Create: 2019-06-26 16:03
 * @Version 1.0
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 1;
        //定义状态方程
        int dp[] = new int[nums.length + 1];
        //初始化状态方程 即假如你序列的化，则每个节点的最大上升资料子序列就是1
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        //外层循环遍历数组的每个节点
        for (int i = 0; i < nums.length; i++) {
            //内存循环遍历当前节点（dp[i]）的前序节点的中的最大上升子序列的 最大值
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            //比较每一个节点的最大上升子序列的值，取最大值即为要求的数组的最大上升子序列的值
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
/*动态规划解法O(N^2)
状态方程为当前节点的最大生序列的值 = 前序节点的所有的最大生序列的值中的最大值+1（前提是当前节点的值大于该节点）
状态方程： dp[i] = Math.max(dp[i-1],dp[i-2].dp[0])+1;
最终返回值 = 所有节点的最大升序列的值的最大值
 */

