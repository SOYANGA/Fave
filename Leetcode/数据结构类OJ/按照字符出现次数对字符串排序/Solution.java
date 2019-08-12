package SortCharactersByFrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: LeetCode
 * @Description: 451. 根据字符出现频率排序
 * @Author: SOYANGA
 * @Create: 2019-08-19 21:58
 * @Version 1.0
 */
class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        Map<Character, Integer> frequencyForNum = new HashMap<>();
        //统计出现次数 key为字母 v:次数
        for (char c : s.toCharArray()) {
            frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);
        }
        //转变为频率 key为出现次数，v:字母
        List<Character>[] frequencyBucket = new ArrayList[s.length() + 1];
        for (char c : frequencyForNum.keySet()) {
            int f = frequencyForNum.get(c);
            if (frequencyBucket[f] == null) {
                frequencyBucket[f] = new ArrayList<>();
            }
            frequencyBucket[f].add(c);
        }
        //构建字符串，将出现次数最高的数字先遍历放在前面
        StringBuilder str = new StringBuilder();
        for (int i = frequencyBucket.length - 1; i >= 0; i--) {
            if (frequencyBucket[i] == null) {
                continue;
            }
            //每一个字母出现几次，添加几次
            for (char c : frequencyBucket[i]) {
                for (int j = 0; j < i; j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }
}