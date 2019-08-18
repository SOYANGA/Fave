package ReverseVowelsofaString;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: LeetCode
 * @Description: 345.反转元音字母 双指针法
 * @Author: SOYANGA
 * @Create: 2019-08-19 17:09
 * @Version 1.0
 */
class Solution {
    private final static HashSet<Character> VOWELS = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    );

    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] res = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!VOWELS.contains(ci)) {
                res[i++] = ci;
            } else if (!VOWELS.contains(cj)) {
                res[j--] = cj;
            } else {
                res[i++] = cj;
                res[j--] = ci;
            }
        }
        return new String(res);
    }
}
