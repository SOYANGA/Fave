package Find_Minimum_in_Rotated_Sorted_Array;

/**
 * @program: LeetCode
 * @Description: 153. 寻找旋转排序数组中的最小值 二分法
 * @Author: SOYANGA
 * @Create: 2019-08-19 23:17
 * @Version 1.0
 */
class Solution {
    public int findMin(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] <= nums[h]) { //有序则区间m的为左边
                h = m;
            } else { //无序则为区间m的右面
                l = m + 1;
            }
        }
        return nums[l];
    }
}
