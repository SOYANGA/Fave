package Maximum_Product_Subarray;

/**
 * @program: LeetCode
 * @Description: 152. 乘积最大子序列 动态规划专属版（只针对该种题）
 * @Author: SOYANGA
 * @Create: 2019-06-25 22:12
 * @Version 1.0
 */
public class MaximumProductSubarray3 {

    public int maxProduct(int[] nums) {
        //一个保存最大的，一个保存最小的。
        int max = nums[0], imax = 1, imin = 1;
        for (int num : nums) {
            //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            if (num < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);
            max = Math.max(max, imax);
        }
        return max;
    }
}
/*
**1.解法一:动态规划（标准)**
  dp[][] 二维数组 x：0 1表示当前最大最小值，于前一个最大最小值 -循环滚动
**2.解法二-本题特点**

将数组以0为分界点分别求出左右两边的最大值，再整合最大值

思路： 求最大值，可以看成求被0拆分的各个子数组的最大值。

当一个数组中没有0存在，则分为两种情况：

1.负数为偶数个，则整个数组的各个值相乘为最大值；

2.负数为奇数个，则从左边开始，乘到最后一个负数停止有一个“最大值”，从右边也有一个“最大值”，比较，得出最大值。

**3.解法三-类比53**

* 此题与53题类似，不同处是53题的运算是加法，本题是乘法。
	* 对于加法，在遍历数组中始终取max(ma + nums[i], nums[i])即可，因为无论nums[i]的正负如何，
	* 对与乘法，在遍历数组中，若nums[i]是负数，那么当前最大值ma * nums[i]会变成当前最小值（负数），因此不能简单的只记录最大值。
* 本题的解题思路是同时记录当前最大值和最小值ma, mi：
	* 当nums[i]是正数时，ma, mi * nums[i]仍然是最大值，最小值；
	* 当nums[i]是负数时，ma, mi * nums[i]将变成最小值， 最大值；
	* 因此，当nums[i] < 0时，我们交换ma, mi。
* 在遍历nums过程中，每次更新res获

 */
