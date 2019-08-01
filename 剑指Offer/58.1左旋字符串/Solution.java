package LeftRotateString;

/**
 * @program: TSRTOffer
 * @Description: 58.2左旋转字符串 分布反转，整体反转 ok 间接双指针法
 * @Author: SOYANGA
 * @Create: 2019-08-02 01:21
 * @Version 1.0
 */
public class Solution {
    public String LeftRotateString(String str,int n) {
        if(n >= str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0,n-1);
        reverse(chars, n, str.length()-1);
        reverse(chars, 0, str.length()-1);
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
