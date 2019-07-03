package duplicate;

/**
 * @program: TSRTOffer
 * @Description: 3 数组中重复的数字 类似于计数排序，设置标志位
 * @Author: SOYANGA
 * @Create: 2019-07-12 00:19
 * @Version 1.0
 */
public class Solution2 {
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
            int index = numbers[i]%length;
            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }
            numbers[index] = numbers[index] + length;
        }
        return false;
    }
}
/*
 类似于计数排序 按照数组下标对数进行排序，出现重复的则就返回
不需要额外的数组或者hash table来保存，题目里写了数组里数字的范围保证在0 ~ n-1 之间，
所以可以利用现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n，之后再遇到相同的数时，
会发现对应位上的数已经大于等于n了，那么直接返回这个数即可。
*/
