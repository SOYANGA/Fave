package DailyTemperatures;

import java.util.Stack;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-13 21:30
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 69, 69, 69, 76};

        for (int i : (new Solution()).dailyTemperatures(temperatures)) {
            System.out.println(i);
        }
    }


    /*解法1：Java: Stack
     * 栈，递减栈Descending Stack，新建一个长度和T相等的数组ret，来记录天数。
     * 遍历数组，如果栈为空，直接入栈。如果栈不为空，且当前数字（数组中对应下标那天温度）大于栈顶元素(下标=在找升温日子的那天)，pop出栈顶元素，求出
     * 下标差，也就是升温的天数，把这个差值记录给栈顶元素在ret中的位置。
     *
     * 然后继续看新的栈顶元素，直到当前数字小于等于栈顶元素停止。然后将当前数字入栈。最后返回res。
     */

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];//新建一个长度和T相等的数组ret，来记录天数。
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }

    public int[] dailyTemperatures1(int[] T) {
        if (T == null || T.length == 0) {
            return null;
        }
        int[] r = new int[T.length];
        int[] stack = new int[T.length];  //模拟栈
        int top = -1;
        for (int i = 0; i < T.length; i++) {
            while (top > -1 && T[i] > T[stack[top]]) {
                int index = stack[top--];
                r[index] = i - index;
            }
            stack[++top] = i;
        }
        return r;
    }
}
