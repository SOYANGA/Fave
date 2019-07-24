package permutation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: TSRTOffer
 * @Description: 38字符串排列
 * @Author: SOYANGA
 * @Create: 2019-07-15 14:36
 * @Version 1.0
 */
public class Solution {

    //结果
    private static ArrayList<String> ret = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(Permutation("aac"));
    }

    public static ArrayList<String> Permutation(String str) {
        if (str == null || str.length() == 0) {
            return ret;
        }
        //1.先将str转变为char[],便于遍历排序，排序可以剔除重复元素,且构造StringBuilder拼接结果
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtracking(chars, new boolean[chars.length], new StringBuilder());
        return ret;
    }

    public static void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
        if (s.length() == chars.length) { //遍历结束
            ret.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) { /* 保证不重复 */
                continue;
            }
            hasUsed[i] = true;
            s.append(chars[i]);
            backtracking(chars, hasUsed, s);
            s.deleteCharAt(s.length() - 1);
            hasUsed[i] = false;
        }
    }
}
/*
回溯：
一定有一个判断是否已经访问过的标记
*/
