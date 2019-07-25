package GetLeastNumbers_Solution;

import java.util.ArrayList;

/**
 * @program: TSRTOffer
 * @Description: 40最小的k个数
 * @Author: SOYANGA
 * @Create: 2019-07-15 19:40
 * @Version 1.0
 */


public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 3, 2, 1, 0};
        System.out.println(GetLeastNumbers_Solution(nums, 4));
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (k > nums.length || k <= 0) {
            return ret;
        }
        findKthSmallest(nums, k - 1);
        for (int i = 0; i < k; i++) {
            ret.add(nums[i]);
        }
        return ret;
    }

    public static void findKthSmallest(int[] nums, int k) {
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            }
            if (j > k) {
                h = j - 1;
            } else {
                l = j + 1;
            }
        }
    }

    private static int partition(int[] a, int left, int right) {
        int randomIndex = (int) ((Math.random() * (right - left + 1)) + left);
        swap(a, right, randomIndex);
        //基准值
        int v = a[right];
        //a[left,i-1]   < v
        int i = left;
        //a[j+1,right] >v
        int j = right - 1;
        while (true) {
            //双指针前指针向后扫描
            while (i <= right && a[i] < v) {
                i++;
            }
            while (j >= left && a[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(a, i, j);
            i++;
            j--;
        }
        swap(a, i, right);
        return i;
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
/*
快速选择
复杂度：O(N) + O(1)
只有当允许修改数组元素时才可以使用
快速排序的 partition() 方法，会返回一个整数 j 使得 a[l..j-1] 小于等于 a[j]，
且 a[j+1..h] 大于等于 a[j]，此时 a[j] 就是数组的第 j 大元素。
可以利用这个特性找出数组的第 K 个元素，这种找第 K 个元素的算法称为快速选择算法。
 */