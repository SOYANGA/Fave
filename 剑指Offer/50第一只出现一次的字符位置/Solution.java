package FirstNotRepeatingChar;

import java.util.*;

/**
 * @program: TSRTOffer
 * @Description: 50第一个只出现一次的字符 biteset减少空间消耗 而且 get 返回true或者false
 * @Author: SOYANGA
 * @Create: 2019-07-29 00:29
 * @Version 1.0
 */


public class Solution {
    public int FirstNotRepeatingChar(String str) {
        //统计字符出现次数表示 0 或者 1
        BitSet bs1 = new BitSet(256);
        //统计字符出现次数 表示 更大（0 / 1） 1表示更大
        BitSet bs2 = new BitSet(256);
        for (char c : str.toCharArray()) {
            if (!bs1.get(c) && !bs2.get(c)) { //没有出现的时候
                bs1.set(c); //0 0 -> 0 1;
            } else if (bs1.get(c) && !bs2.get(c)) {
                bs2.set(c); // 0 1 -> 1 1
            }
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c)) { //0 1
                return i;
            }
        }
        return -1;
    }
}