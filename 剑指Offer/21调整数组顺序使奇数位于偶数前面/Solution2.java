package reOrderArray;

/**
 * @program: TSRTOffer
 * @Description: 21调整数组顺序使计数位于偶数前面
 * @Author: SOYANGA
 * @Create: 2019-07-13 19:44
 * @Version 1.0
 */
public class Solution2 {
    public static void main(String[] args) {
        int[] array = new int[]{2, 8, 3, 1};
        reOrderArray(array);
        for (int num : array) {
            System.out.print(num + ",");
        }
    }


    public static void reOrderArray(int[] nums) {
        // 奇数个数
        int oddCnt = 0;
        for (int x : nums) {
            if (!isEven(x)) {
                oddCnt++;
            }
        }
        int[] copy = nums.clone();
        int i = 0, j = oddCnt;
        for (int num : copy) {
            if (num % 2 == 1) {
                nums[i++] = num;
            } else {
                nums[j++] = num;
            }
        }
    }

    private boolean isEven(int x) {
        return x % 2 == 0;
    }
}
/*
空间复杂度O(N) 时间复杂度O(N)
创建一个新数组
*/
