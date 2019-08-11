package Find_Smallest_Letter_Greater_Than_Target;

/**
 * @program: LeetCode
 * @Description: 744. 寻找比目标字母大的最小字母 二分查找
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:23
 * @Version 1.0
 */
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0;
        int h = n;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (letters[m] <= target) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return letters[l % n];
    }
}
