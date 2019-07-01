package LRUCache;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @program: LeetCode
 * @Description: 146. LRU缓存机制 HashMap + 双向链表 +伪节点（哨兵） 也可以使用Hashtable 回提高效率 不知道为啥
 * @Author: SOYANGA
 * @Create: 2019-07-02 21:37
 * @Version 1.0
 */
public class LRUCache {

    class LRUCache1 {

        /**
         * 内部双向链表的内部节点
         */
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }

        /**
         * head<=>tail（原本就存在的伪节点）添加节点从双向链表头部添加。
         */
        private void addNode(DLinkedNode node) {
            //将新加入的node节点前驱节点跟后驱节点处理好
            node.prev = head;
            node.next = head.next;

            //将head后的节点头插，处理好head，跟原本的head.next节点
            head.next.prev = node;
            head.next = node;
        }

        /**
         * 删除节点配合moveToHead使用-简单的双向链表删除节点
         */
        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /**
         * 访问了节点，所以需要将该节点移动至队列尾部
         */
        private void moveToHead(DLinkedNode node) {
            /**
             *将某些节点移动到头部
             */
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail() {
            /**
             *删除最近最少使用的节点，从尾部删除,tail为伪节点
             */
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        //哈希表实现O(1)查找
        private HashMap<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();

        private int size;
        private int capacity;
        private DLinkedNode head;
        private DLinkedNode tail;

        /**
         * LRU缓存池的初始化 初始化容量和头尾伪节点，和size
         */
        public LRUCache1(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new DLinkedNode();
            tail = new DLinkedNode();

            //构造伪节点
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 访问，假如该值存在就将该值放入队列头返回该节点的值 ，反之则返回-1；
         */
        public int get(int key) {
            DLinkedNode res = cache.get(key);
            if (res == null) {
                return -1;
            }
            moveToHead(res);
            return res.value;
        }

        /**
         * 将新节点初始化并插入队列和HashMap,size++中假如该节点不存在的化。
         * 假如节点存在则更新节点的value
         */
        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                //假如到HashMap 和双向链表中
                cache.put(key, newNode);
                size++;
                addNode(newNode);

                //如果已将超出缓存容量，则从双向队列和HashMap中去除该节点；
                if (size > capacity) {
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                //更新value;
                node.value = value;
                moveToHead(node);
            }
        }
    }


    public class LRUCache2 {

        /**
         * 内部双向链表的内部节点
         */
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }

        /**
         * head<=>tail（原本就存在的伪节点）添加节点从双向链表头部添加。
         */
        private void addNode(DLinkedNode node) {
            //将新加入的node节点前驱节点跟后驱节点处理好
            node.prev = head;
            node.next = head.next;

            //将head后的节点头插，处理好head，跟原本的head.next节点
            head.next.prev = node;
            head.next = node;
        }

        /**
         * 删除节点配合moveToHead使用-简单的双向链表删除节点
         */
        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /**
         * 访问了节点，所以需要将该节点移动至队列尾部
         */
        private void moveToHead(DLinkedNode node) {
            /**
             *将某些节点移动到头部
             */
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail() {
            /**
             *删除最近最少使用的节点，从尾部删除,tail为伪节点
             */
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        //哈希表实现O(1)查找
        private Hashtable<Integer, DLinkedNode> cache = new Hashtable<Integer, DLinkedNode>();

        private int size;
        private int capacity;
        private DLinkedNode head;
        private DLinkedNode tail;

        /**
         * LRU缓存池的初始化 初始化容量和头尾伪节点，和size
         */
        public LRUCache2(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new DLinkedNode();
            tail = new DLinkedNode();

            //构造伪节点
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 访问，假如该值存在就将该值放入队列头返回该节点的值 ，反之则返回-1；
         */
        public int get(int key) {
            DLinkedNode res = cache.get(key);
            if (res == null) {
                return -1;
            }
            moveToHead(res);
            return res.value;
        }

        /**
         * 将新节点初始化并插入队列和HashMap,size++中假如该节点不存在的化。
         * 假如节点存在则更新节点的value
         */
        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                //假如到HashMap 和双向链表中
                cache.put(key, newNode);
                size++;
                addNode(newNode);

                //如果已将超出缓存容量，则从双向队列和HashMap中去除该节点；
                if (size > capacity) {
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                //更新value;
                node.value = value;
                moveToHead(node);
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
