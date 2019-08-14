package Single_Element_in_a_SortedArray;

/**
 * @program: LeetCode
 * @Description: 540. 有序数组中的单一元素
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:31
 * @Version 1.0
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if ((m & 1) == 1) {
                m--; // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[h];
    }
}
/*
关键点在偶数位上面，拿所有的偶数位跟奇数位区比较即可
 */