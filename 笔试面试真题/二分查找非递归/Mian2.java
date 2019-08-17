package ACKDXF;

/**
 * @program: TSRTOffer
 * @Description: 二分查找非递归
 * @Author: SOYANGA
 * @Create: 2019-08-16 18:42
 * @Version 1.0
 */

import java.util.Scanner;

public class Mian2 {
    //目标数字的位置
    private static int index = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //要查找的数字19
        int n = 19;
        while (in.hasNextLine()) {
            String str = in.nextLine();
            //对输入的"{11,13,15,17,19,21}"进行处理,最终处理成int类型的数组
            String[] chars = str.substring(1, str.length() - 1).split(",");
            int[] nums = new int[chars.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.valueOf(chars[i]);
            }
            //调用二分查找查找目标数的位置
            int index = binarySearch(nums, n);
            System.out.println(index);

        }
    }

    private static int binarySearch(int[] nums, int n) {
        binarySearch(nums, 0, nums.length - 1, n);
        return index;
    }

    /**
     * @param nums  目标数组
     * @param left  数组的左边界
     * @param right 数字的右边界
     * @param n     要查找的目标值
     */
    private static void binarySearch(int[] nums, int left, int right, int n) {
        //递归终止条件
        if (left > right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        //找到目标数的位置则将index赋值
        if (nums[mid] == n) {
            index = mid + 1;
            return;
        } else if (nums[mid] > n) {
            binarySearch(nums, left, mid - 1, n);
        } else if (nums[mid] < n) {
            binarySearch(nums, mid + 1, right, n);
        }

    }
}
