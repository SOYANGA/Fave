package InversePairs;

/**
 * @program: TSRTOffer
 * @Description: 51 数组中的逆序对 归并排序改造
 * @Author: SOYANGA
 * @Create: 2019-07-29 00:32
 * @Version 1.0
 */
public class Solution {
    private long cnt = 0;
    private int[] tmp;

    public int InversePairs(int [] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return (int)(cnt % 1000000007);
    }

    private void mergeSort(int[] nums, int l, int h) {
        if(h - l < 1) {
            return;
        }
        int m = l+(h - l)/2;
        mergeSort(nums, l, m); //分开
        mergeSort(nums, m + 1, h); //分开
        merge(nums, l, m, h); //排序合并
    }

    private void merge(int[] nums,int l, int m, int h) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= h) {
            if (i > m) {
                tmp[k] = nums[j++];
            } else if(j > h) {
                tmp[k] = nums[i++];
            } else if(nums[i] <= nums[j]) {
                tmp[k] = nums[i++];
            } else {
                tmp[k] = nums[j++];
                this.cnt += m - i+ 1; //nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j] i之后到mid的值都大于
            }
            k++;
        }
        for (k = l; k <= h; k++) {
            nums[k] = tmp[k];
        }
    }
}
