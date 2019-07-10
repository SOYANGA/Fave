package twoStackForQueue;

/**
 * @program: TSRTOffer
 * @Description: 9.用两个栈实现队列
 * @Author: SOYANGA
 * @Create: 2019-07-12 00:33
 * @Version 1.0
 */

import java.util.Stack;

public class Solution {
    Stack<Integer> in = new Stack<Integer>(); //入栈 专门入
    Stack<Integer> out = new Stack<Integer>(); //出栈 专门出 只要没有空就不用从入栈里面导出 反之导出即可

    public void push(int node) {
        in.push(node);
    }

    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            return -1;
        }
        return out.pop();
    }
}