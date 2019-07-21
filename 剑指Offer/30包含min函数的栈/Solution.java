package minStack;

import java.util.Stack;

/**
 * @program: TSRTOffer
 * @Description: 30包含min函数的栈
 * @Author: SOYANGA
 * @Create: 2019-07-22 23:29
 * @Version 1.0
 */
public class Solution {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

}
