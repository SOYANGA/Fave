package ImplementQueueUsingStacks;

import java.util.Stack;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-05-15 23:03
 * @Version 1.0
 */
public class MyQueue2 {
    class MyQueue {
        private Stack<Integer> s1;  //来回倒数据数据实现出队列入队列的栈
        private Stack<Integer> sQueue; //固定一个栈为存储数据的栈


        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            s1 = new Stack();
            sQueue = new Stack();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            sQueue.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {  //出队列的时候就将数据倒好
            if (sQueue.isEmpty()) {
                return -1;
            }
            while (!sQueue.isEmpty()) {
                s1.push(sQueue.pop());
            }
            int result = s1.pop();
            while (!s1.isEmpty()) {
                sQueue.push(s1.pop());
            }
            return result;

        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (sQueue.isEmpty()) {
                return -1;
            }
            while (!sQueue.isEmpty()) {
                s1.push(sQueue.pop());
            }
            int result = s1.peek();
            while (!s1.isEmpty()) {
                sQueue.push(s1.pop());
            }
            return result;
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return sQueue.isEmpty();
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */

    class Myqueue2 {
        private Stack<Integer> s1;  //来回倒数据数据实现出队列入队列的栈
        private Stack<Integer> sQueue; //固定一个栈为存储数据的栈


        /**
         * Initialize your data structure here.
         */
        public Myqueue2() {
            s1 = new Stack();
            sQueue = new Stack();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) { //入队列的时候就将数据倒好
            while (!sQueue.isEmpty()) {
                s1.push(sQueue.pop());
            }
            sQueue.push(x);
            while (!s1.isEmpty()) {
                sQueue.push(s1.pop());
            }
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (sQueue.isEmpty()) {
                return -1;
            }
            return sQueue.pop();

        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (sQueue.isEmpty()) {
                return -1;
            }
            return sQueue.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return sQueue.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */


}
