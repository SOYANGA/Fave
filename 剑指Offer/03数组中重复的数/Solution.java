package duplicate;

/**
 * @program: TSRTOffer
 * @Description: 3 数组中重复的数字 类似于计数排序，设置标志位
 * @Author: SOYANGA
 * @Create: 2019-07-12 00:19
 * @Version 1.0
 */
public class Solution {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                //对数据经行交换，注意交换时由于讲数组中的内容作为了下标，则注意赋值操作。
                int t = numbers[i];
                numbers[i] = numbers[t];
                numbers[t] = t;
            }
        }
        return false;
    }
}
/*
 类似于计数排序 按照数组下标对数进行排序，出现重复的则就返回
*/
