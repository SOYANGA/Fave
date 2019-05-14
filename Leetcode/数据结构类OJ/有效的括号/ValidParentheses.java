package ValidParentheses;

import java.util.HashMap;
import java.util.Stack;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-05-14 23:55
 * @Version 1.0
 */
public class ValidParentheses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "{[]}{[]}(){}()(){}{}{[()]}";
        System.out.println(solution.isValid(s));
    }
}

class Solution {
    public boolean isValid(String s) {
        int length;
        do {
            length = s.length();
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        } while (length != s.length());
        return s.length() == 0;
    }
}

class Solution2 {

    private HashMap<Character, Character> maps; //存储括号的对应值

    public Solution2() {
        this.maps = new HashMap<Character, Character>();
        this.maps.put(')', '(');
        this.maps.put('}', '{');
        this.maps.put(']', '[');
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        char[] c = s.toCharArray();
        for (char aC : c) {
            if (this.maps.containsKey(aC)) {
                if (stack.isEmpty() || !stack.pop().equals(this.maps.get(aC))) {
                    return false;
                }
            } else {
                stack.push(aC);
            }
        }
        return stack.isEmpty();
    }
}
