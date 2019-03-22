package Day_17;

import java.util.Stack;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-21 22:58
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(10);
        solution.push(20);
        solution.push(30);
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
    }

}

class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();//转换栈
    Stack<Integer> stack2 = new Stack<Integer>();//存储栈

    public void push(int node) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop()); //将存储栈中的元素放到转换栈中
        }
        stack2.push(node); //将要新元素插入到栈底（队列头)
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());  //进行转换
        }
    }

    public int pop() {
        if (stack2.isEmpty()) {
            return -1;
        }
        return stack2.pop();
    }
}