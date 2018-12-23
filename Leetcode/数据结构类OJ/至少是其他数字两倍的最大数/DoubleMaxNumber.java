package DoubleMaxNumber;

class Solution {
    public int dominantIndex(int[] nums) {
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;  //确保所有值都能遍历
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {  //最大元素
                max2 = max;
                max = nums[i];
                index = i;
            } else if (nums[i] > max2) {  //第二大元素
                max2 = nums[i];
            }

        }
        if (max >= 2 * max2)  //满足最大数大于等于所有数的两倍
            return index;
        else
            return -1;
    }
}