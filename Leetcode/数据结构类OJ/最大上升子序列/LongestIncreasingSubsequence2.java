package Longest_Increasing_Subsequence;

/**
 * @program: LeetCode
 * @Description: 300. 最长上升子序列 二分查找版本-贪心思想O(n*logn) 时间复杂度
 * @Author: SOYANGA
 * @Create: 2019-06-26 16:03
 * @Version 1.0
 */
public class LongestIncreasingSubsequence2 {
    public static void main(String[] args) {
        int right = 10;
        int left = 0;
        int midindex1 = left + (right - left) / 2;
        int midindex2 = (left & right) + ((left ^ right) >> 1);
        System.out.println(midindex1 + " " + midindex2);
    }


    public int lengthOfLIS(int[] nums) {
        //参数防御
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //维护的递增序列
        int[] LIS = new int[nums.length];
        LIS[0] = nums[0];
        //最后一个下标的位置
        int lastindex = 0;
        for (int num : nums) {
            if (num > LIS[lastindex]) {
                LIS[++lastindex] = num;
            } else {
                //二分查找替代法 替代第一个比该数大的数，让递增队列变得更长。
                int left = 0;
                int right = lastindex;
                while (left <= right) {
                    int midindex = (left & right) + ((left ^ right) >> 1);
                    if (LIS[midindex] == num) {
                        left = midindex;
                        break;
                    } else if (LIS[midindex] > num) {
                        right = midindex - 1;
                    } else if (LIS[midindex] < num) {
                        left = midindex + 1;
                    }
                }
                LIS[left] = num;
            }
        }
        return lastindex + 1;
    }
}
/*
二分查找替代法O(N*logN)-贪心算法
维护一个递增序列（有序列）
当遍历数组中的元素时，大于该数组的最后一个元素（即满足递增序列，将该元素加到该数组中去）
当遍历数组中的元素 小于或者等于时，需要在递增数组中去找它应该可以被替代的位置
即第一个大于它的数，让其变得更小，让后面来的数可以更多的加入其中，即让有序递增数列变得更长（贪心算法）
思路：每一次来一个新的数 num，在 tail 数组（tail 数组的定义在下面的示意图中有）中找大于等于 num 的那个数，试图让它变小，以致于新来的数有更多的可能性接在它后面，成为一个更长的“上升子序列”，这是“贪心算法”的思想。

在 tail 数组中找大于等于 num 的那个数，可以使用“二分法”
 */
