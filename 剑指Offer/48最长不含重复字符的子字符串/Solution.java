package longestSubStringWithoutDuplication;

import java.util.Arrays;

/**
 * @program: TSRTOffer
 * @Description: 48最长不重复字符的子字符串 哈希表法存储的是下标 以更新最大值和当前最长不重复子串
 * @Author: SOYANGA
 * @Create: 2019-07-29 00:05
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(longestSubStringWithoutDuplication("arabcacfr"));
    }

    public static int longestSubStringWithoutDuplication(String str) {
        int curLen = 0;
        int maxLen = 0;
        int[] preIndexs = new int[26];
        Arrays.fill(preIndexs, -1);
        for (int curI = 0; curI < str.length(); curI++) {
            int c = str.charAt(curI) - 'a';
            int preI = preIndexs[c];
            if (preI == -1 || curI - preI > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = curI - preI;
            }
            preIndexs[c] = curI;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

}
