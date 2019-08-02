package Sqrtx;

/**
 * @program: LeetCode
 * @Description: 69. x 的平方根 二分法
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:17
 * @Version 1.0
 */
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 0;
        int right = x;
        int result = 0;
        while (left <= right) {
            int mid = (left & right) + ((left ^ right) >> 1);
            if (mid == x / mid) {
                return mid;
            } else if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}