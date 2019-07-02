package LRUCache;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: LeetCode
 * @Description: 146. LRU缓存机制 LinkedHashMap本身就是java实现的LRU 覆写removeEldestEntry方法即可
 * @Author: SOYANGA
 * @Create: 2019-07-02 21:37
 * @Version 1.0
 */
public class LRUCache2 {

    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true); //开启根据访问顺序进行更新
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> edlest) {
            return this.size() > capacity;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

}


