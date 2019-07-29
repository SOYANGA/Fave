package GetNumberOfK;

/**
 * @program: TSRTOffer
 * @Description: 53. 数字在排序数组中出现的次数 二分查找2*logn时间复杂度
 * @Author: SOYANGA
 * @Create: 2019-08-02 00:52
 * @Version 1.0
 */
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        int first = binarySearch(array, k); //找到他的·第一位
        int last = binarySearch(array, k+1); //找到他的最后一位
        return (first == array.length || array[first] != k) ? 0 : last - first;
    }

    private int binarySearch(int[] nums,int k) {
        int l = 0,h = nums.length;
        while(l < h) {
            int m = (l & h) +(( l ^ h) >> 1);
            if (nums[m] >= k) {
                h = m;
            }else {
                l = m +1; //保证查找的是第一个元素
            }
        }
        return l;
    }
}