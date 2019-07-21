package IsPopOrder;

import java.util.Stack;

/**
 * @program: TSRTOffer
 * @Description: 31 栈的压入、弹出序列
 * @Author: SOYANGA
 * @Create: 2019-07-22 23:24
 * @Version 1.0
 */

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>(); //实体辅助栈
        int n = pushA.length; //长度
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushA[pushIndex]); //不断将按顺序压入栈
            while(popIndex < n && !stack.isEmpty() && stack.peek() == popA[popIndex]) { //从顺序栈中不断取出
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}
/*
利用一个实体辅助栈来模拟压入和弹出的情况
*/