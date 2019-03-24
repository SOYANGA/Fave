package Day_18;

import java.util.*;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-24 21:14
 * @Version 1.0
 */
public class Test1 {

    private static Stack<String> stack1 = new Stack<String>();
    private static Stack<String> stack2 = new Stack<String>();
    private static List<String> list = new ArrayList<String>();

    public static void ff(String str) {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            list.add(str.trim());
            return;
        }
        if (!stack2.isEmpty()) {
            String str1 = stack2.pop();
            ff(str + " " + str1);
            stack2.push(str1);
        }
        if (!stack1.isEmpty()) {
            String str2 = stack1.pop();
            stack2.push(str2);
            ff(str);
            stack2.pop();
            stack1.push(str2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            scanner.nextLine();
            String str = scanner.nextLine();
            String[] ss = str.split(" ");
            for (int i = ss.length - 1; i >= 0; i--) {
                stack1.push(ss[i]);

            }
            ff("");
            Collections.sort(list);
            for (String s : list) {
                System.out.println(s);

            }
        }
    }
}


