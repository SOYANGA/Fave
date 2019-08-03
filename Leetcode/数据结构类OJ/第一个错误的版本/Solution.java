package FirstBadVersion;

/**
 * @program: LeetCode
 * @Description: 278. 第一个错误的版本 二分查找
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:19
 * @Version 1.0
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

//public class Solution extends VersionControl {
//    public int firstBadVersion(int n) {
//        int l = 1, h = n;
//        while (l < h) {
//            int mid = l + ((h - l) >> 1);
//            if (isBadVersion(mid)) {
//                h = mid;
//            } else {
//                l = mid + 1;
//            }
//        }
//        return l;
//    }
//}
