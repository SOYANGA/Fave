package minNumberInRotateArray;

/**
 * @program: TSRTOffer
 * @Description: 11旋转数组中的最小数字
 * @Author: SOYANGA
 * @Create: 2019-07-12 16:18
 * @Version 1.0
 */
public class Solution {
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = (left & right) + ((left ^ right) >> 1);
            //当特殊情况发生时 既：nums[left] == nums[mid] == nums[right] 则需要顺序遍历寻找
            if (array[left] == array[mid] && array[mid] == array[right]) {
                return minNumber(array, left, right);
            }
            //[mid - right] 为非递减数组 [left - mid] 为旋转数组（包含最小元素的）
            else if (array[mid] <= array[right]) {
                right = mid;
                //[left - mid] 为非递减数组 [mid+1 - right] 为旋转数组（包含最小元素的）
            } else {
                left = mid + 1;
            }
        }
        return array[left];
    }

    private int minNumber(int[] nums, int l, int r) {
        for (int i = l; i < r; i++) {
            if (nums[i] > nums[i + 1]) {
                return nums[i + 1];
            }
        }
        return nums[l];
    }
}

/*
 * 类似二分的情况进行查找
 * 非递减排序数组 （递增数组） 则array[n] <= array[n+1]
 * 利用二分查找将原数组分为两个数组 分别为 非递减数组 旋转数组 然后旋转数组可以进行继续拆分 子问题求解
 * O(logN)时间复杂度
 * 结果为旋转数组的首个元素
 */
