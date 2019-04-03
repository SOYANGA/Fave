package Day_28;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: ]密码验证合格程序
 * @Author: SOYANGA
 * @Create: 2019-04-03 23:45
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String password = in.nextLine();
            //判断长度
            if (password != null && password.length() <= 8) {
                System.out.println("NG");
                continue;
            }
            int lowercaseType = 0;
            int numberType = 0;
            int uppercaseType = 0;
            int otherType = 0;
            char[] passwords = password.toCharArray();
            for (char c : passwords) {
                if (c >= '0' && c <= '9') {
                    numberType = 1;
                } else if (c >= 'a' && c <= 'z') {
                    lowercaseType = 1;
                } else if (c >= 'A' && c <= 'Z') {
                    uppercaseType = 1;
                } else {
                    otherType = 1;
                }
            }
            if (lowercaseType + numberType + uppercaseType + otherType < 3) {
                System.out.println("NG");
                continue;
            }

            boolean isrepetition = false;
            //判断重复
            for (int i = 0; i < password.length() - 3; i++) {
                if (password.substring(i + 3).contains(password.substring(i, i + 3))) {
                    isrepetition = true;
                    break;
                }
            }
            if (isrepetition) {
                System.out.println("NG");
            } else {
                System.out.println("OK");
            }
        }
    }
}
