package Mysqrt;

/**
 * @program: LeetCode
 * @Description: 69. x 的平方根 二分法 类似于二分查找
 * @Author: SOYANGA
 * @Create: 2019-06-09 02:18
 * @Version 1.0
 */
public class Mysqrt {
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
                result = mid;
            }
        }
        return result;
    }
}
