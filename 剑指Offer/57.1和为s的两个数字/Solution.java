package FindNumbersWithSum;

/**
 * @program: TSRTOffer
 * @Description: 57.1 和为 S 的两个数字 双指针法
 * @Author: SOYANGA
 * @Create: 2019-08-02 01:07
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            int cur = array[i] + array[j];
            if (cur == sum) {
                return new ArrayList<>(Arrays.asList(array[i], array[j]));
            }
            if (cur < sum) {
                i++;
            } else {
                j--;
            }
        }
        return new ArrayList<>();
    }
}
/*
使用双指针，一个指针指向元素较小的值，一个指针指向元素较大的值。
指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。

如果两个指针指向元素的和 sum == target，那么得到要求的结果；
如果 sum > target，移动较大的元素，使 sum 变小一些；
如果 sum < target，移动较小的元素，使 sum 变大一些。
*/

//找到的第一组（相差最大的）就是乘积最小的。可以这样证明：
//考虑x+y=C（C是常数），x*y的大小。不妨设y>=x，y-x=d>=0，
//即y=x+d, 2x+d=C, x=(C-d)/2, x*y=x(x+d)=(C-d)(C+d)/4=(C^2-d^2)/4，
//也就是x*y是一个关于变量d的二次函数，对称轴是y轴，开口向下。
//d是>=0的，d越大, x*y也就越小。
