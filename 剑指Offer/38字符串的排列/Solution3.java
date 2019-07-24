package permutation;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @program: TSRTOffer
 * @Description: 38字符串排列
 * @Author: SOYANGA
 * @Create: 2019-07-15 14:36
 * @Version 1.0
 */


public class Solution3 {

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> ret = new ArrayList<>();
        if (str != null || str.length() != 0) {
            backtracking(str.toCharArray(), 0, ret);
            Collections.sort(ret); //对结果进行升序排列要不然测试不通过
        }
        return ret;
    }

    private void backtracking(char[] chars, int from, ArrayList<String> ret) {
        if (from == chars.length - 1) {//排序到只剩一位了
            ret.add(new String(chars));
        } else {
            for (int i = from; i < chars.length; i++) {
                if (i != from && chars[i] == chars[from]) {
                    continue;
                }
                swap(from, i, chars);//一次固定住未确定的第一位
                backtracking(chars, from + 1, ret); //后面的进行全排序
                swap(i, from, chars);
            }
        }
    }

    private void swap(int i, int j, char[] chars) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
/*
全排列 问题 确定第一个元素 在后面元素中选一个元素根第一个元素进行交换即可，
*/
/*
全排列 问题 确定第一个元素 在后面元素中选一个元素根第一个元素进行交换即可，
*/