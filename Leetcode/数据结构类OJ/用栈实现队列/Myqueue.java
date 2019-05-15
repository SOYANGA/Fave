package ImplementQueueUsingStacks;

import java.util.Stack;

/**
 * @program: LeetCode
 * @Description: 双栈+维护队列顶元素法
 * @Author: SOYANGA
 * @Create: 2019-05-15 22:54
 * @Version 1.0
 */
public class Myqueue {

    class MyQueue {
        private Stack<Integer> stackInput = new Stack<>();
        private Stack<Integer> stackOutput = new Stack<>();
        private int front;   //维护一个频繁操作的队列头的元素

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            if (stackInput.isEmpty()) {
                front = x;
            }
            stackInput.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (stackOutput.isEmpty()) {
                while (!stackInput.isEmpty()) {
                    stackOutput.push(stackInput.pop());
                }
            }
            return stackOutput.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (!stackOutput.isEmpty()) {
                front = stackOutput.peek();
            }
            return front;
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stackOutput.isEmpty() && stackInput.isEmpty();
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
