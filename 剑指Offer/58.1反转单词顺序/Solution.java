package ReverseSentence;

/**
 * @program: TSRTOffer
 * @Description: 58.1反转单词顺序 单词反转，整体反转。 间接双指针
 * @Author: SOYANGA
 * @Create: 2019-08-02 01:17
 * @Version 1.0
 */
public class Solution {
    public String ReverseSentence(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0;
        int j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reverse(chars, i ,j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(chars, 0, n-1);
        return new String(chars);
    }
    private void reverse(char[] chars, int i, int j) {
        while(i < j) {
            swap(chars, i++, j--);
        }
    }
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
/*
两次反转，1.反转单词 2.整个str进行反转
*/
