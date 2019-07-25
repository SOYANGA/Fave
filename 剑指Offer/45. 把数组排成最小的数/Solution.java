package PrintMinNumber;

/**
 * @program: TSRTOffer
 * @Description: 45把数组排成最小的数
 * @Author: SOYANGA
 * @Create: 2019-07-27 00:42
 * @Version 1.0
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        int n = numbers.length;
        String[] strs = new String[n];
        //变成字符串
        for (int i = 0; i < n; i++) {
            strs[i] = numbers[i]+"";
        }
        //排序
        Arrays.sort(strs,(s1,s2) -> (s1 + s2).compareTo(s2 + s1));
        String ret = "";
        for (String str : strs) {
            ret += str;
        }
        return ret;
    }
}
/*
可以看成排序问题，比较两个字符串，java中比较字符串其实从个字符开始比较的
则可以比较s1+s2 和 s2+s1 那个小 小的在前，大的在后，最后再遍历数组，进行拼装
*/
