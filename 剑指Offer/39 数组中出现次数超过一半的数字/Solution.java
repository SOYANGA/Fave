package MoreThanHalfNum_Solution;

/**
 * @program: TSRTOffer
 * @Description: 39数组中出现次数超过一半得数字
 * @Author: SOYANGA
 * @Create: 2019-07-15 17:51
 * @Version 1.0
 */
public class Solution {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int num = array[0];
        int times = 1;
        for (int i = 1; i < array.length; i++) {
            times = array[i] == num ? times + 1 : times - 1;
            if (times == 0) {
                //UPDTE
                num = array[i];
                times = 1;
            }
        }
        times = 0;
        for (int anArray : array) {
            if (anArray == num) {
                times++;
            }
        }
        return times > array.length / 2 ? num : 0;
    }
}
/*
多数投票问题，可以利用 Boyer-Moore Majority Vote Algorithm 来解决这个问题，使得时间复杂度为 O(N)。

使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，
令 cnt++，否则令 cnt--。如果前面查找了 i 个元素，且 cnt == 0，说明前 i 个元素没有 majority，
或者有 majority，但是出现的次数少于 i / 2 ，因为如果多于 i / 2 的话 cnt 就一定不会为 0 。
此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，因此继续查找就能找出 majority。
*/
