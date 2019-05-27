package Sliding_Window_Maximum;

import java.util.LinkedList;

/**
 * @program: LeetCode
 * @Description: 239 滑动窗口最大值
 * @Author: SOYANGA
 * @Create: 2019-05-28 00:21
 * @Version 1.0
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return nums;
        }
        LinkedList<Integer> list = new LinkedList();
        int[] result = new int[nums.length - k + 1];
        //双端队列，保存当前窗口中最大值的下标，保证队列中数组位置的数按从大到小排序
        for (int i = 0; i < nums.length; i++) {
            while (!list.isEmpty() && nums[list.peekLast()] < nums[i]) {
                list.pollLast();
            }
            //将最大值放入队列中
            list.addLast(i);
            //如果滑动窗口滑动，如果滑动的是最大值，则将最大值移除队列。
            if (list.peek() <= i - k) {
                list.poll();
            }
            //窗口长度为k时，再保存当前窗口最大值
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[list.peek()];
            }
        }

        return result;
    }
}
