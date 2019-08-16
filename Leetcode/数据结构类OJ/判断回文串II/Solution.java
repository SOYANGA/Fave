package Valid_Palindrome_II;

/**
 * @program: LeetCode
 * @Description: 680. 验证回文字符串 Ⅱ
 * @Author: SOYANGA
 * @Create: 2019-08-19 17:24
 * @Version 1.0
 */
class Solution {
    public boolean validPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            //有一个元素不等于，则删除任意一边的元素看是否能够构成回文字符串
            if (s.charAt(i) != s.charAt(j)) {
                return validPalindrome(s, i, j - 1) || validPalindrome(s, i + 1, j);
            }
        }
        return true;
    }

    private boolean validPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}