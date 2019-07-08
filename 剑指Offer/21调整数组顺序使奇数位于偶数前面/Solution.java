package reOrderArray;

/**
 * @program: TSRTOffer
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-07-13 19:44
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        int[] array = new int[]{2, 8, 3, 1};
        reOrderArray(array);
        for (int num : array) {
            System.out.print(num + ",");
        }
    }


    public static void reOrderArray(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if ((array[j] & 1) == 0 && (array[j + 1] & 1) == 1) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
/*
冒泡排序版本 空间复杂度O(1) 时间复杂度O(n^2)
每一次将最右边的偶数放到最右端
*/
