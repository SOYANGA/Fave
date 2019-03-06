package Day_03;

import java.util.Stack;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-06 14:35
 * @Version 1.0
 */
public class test3 {

    public static void main(String[] args) {
        String str = "()a()";
        int length = str.length();
        System.out.println(chkParenthesis(str, length));
    }

    public static boolean chkParenthesis(String A, int n) {
        Stack stack = new Stack();
        char[] charstr = A.toCharArray();
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            char c = charstr[i];
            switch (c) {
                case '(':
                    stack.add(c);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (!stack.isEmpty() && !stack.peek().equals('(')) {
                        stack.pop();
                        stack.pop();
                    } else if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    break;
                default:
                    if (stack.isEmpty()) {
                        return false;
                    }
                    break;
            }
        }
        if (!stack.isEmpty()) {
            flag = false;
        }
        return flag;
    }
}
