package Sliding_Window_Maximum;

import java.util.LinkedList;

/**
 * @program: LeetCode
 * @Description: 239 滑动窗口最大值
 * @Author: SOYANGA
 * @Create: 2019-05-28 00:21
 * @Version 1.0
 */
public class SlidingWindowMaximum2 {
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length < 2) {
            return nums;
        }
        int max = Integer.MIN_VALUE;
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (max <= nums[i]) {
                max = nums[i];
            } else {
                if (i > k - 1 && max == nums[i - k]) {
                    max = Integer.MIN_VALUE;
                    for (int j = i - k + 1; j <= i; j++) {
                        if (max < nums[j]) {
                            max = nums[j];
                        }
                    }
                }
            }
            if (i >= k - 1) {
                result[i - k + 1] = max;
            }
        }
        return result;
    }
}
