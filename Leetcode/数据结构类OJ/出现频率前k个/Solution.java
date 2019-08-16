package TopKFrequentElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: LeetCode
 * @Description: 347. 前 K 个高频元素
 * @Author: SOYANGA
 * @Create: 2019-08-19 17:31
 * @Version 1.0
 */
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        //第一次遍历统计出现频率
        for (int n : nums) {
            map.put(n, map.getOrDefault(n,0)+1);
        }
        //统计各个数出现的频率
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int frequency = map.get(key); //获得出现次数
            if (buckets[frequency] == null) {
                buckets[frequency]  = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        //筛选出前k个
        List<Integer> top = new ArrayList<>();
        for (int i = buckets.length-1;i >=0 && top.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }
            //出现该频率的数字的个数小于剩余（k - top.size()）个数，则全部放入，否则阶段放入使用subList截断放入
            if (buckets[i].size() <= (k - top.size())) {
                top.addAll(buckets[i]);
            } else {
                top.addAll(buckets[i].subList(0,k - top.size()));
            }
        }
        return top;
    }
}
/*
遍历两遍
第一遍遍历到HashMap中存储出现次数

第二遍遍历在hashMap中的存储次数，转化为桶下标为出现频率的数字

第三遍遍历得按照个数取出出现频率前k的数字。
*/