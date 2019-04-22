package Day_41;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 坐标移动
 * @Author: SOYANGA
 * @Create: 2019-04-21 23:14
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        int row = 0;
        int column = 0;
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] operators = in.nextLine().split(";");
            for (int i = 0; i < operators.length; i++) {
                String operator = operators[i];
                if (operator.length() <= 1) {
                    continue;
                } else {
                    if (operator.startsWith("W")) {
                        if (strIsDigit2(operator.substring(1))) {
                            row += Integer.parseInt(operator.substring(1));
                        } else {
                            continue;
                        }
                    } else if (operator.startsWith("A")) {
                        if (strIsDigit2(operator.substring(1))) {
                            column -= Integer.parseInt(operator.substring(1));
                        } else {
                            continue;
                        }
                    } else if (operator.startsWith("S")) {
                        if (strIsDigit2(operator.substring(1))) {
                            row -= Integer.parseInt(operator.substring(1));
                        } else {
                            continue;
                        }

                    } else if (operator.startsWith("D")) {
                        if (strIsDigit2(operator.substring(1))) {
                            column += Integer.parseInt(operator.substring(1));
                        } else {
                            continue;
                        }
                    }
                }
            }
            System.out.println(column + "," + row);
            column = 0;
            row = 0;
        }
    }

    //Java自带方法
    private static boolean strIsDigit(String substring) {
        char[] num = substring.toCharArray();
        boolean flag = true;
        for (char c : num) {
            if (!Character.isDigit(c)) {
                flag = false;
            }
        }
        return flag;
    }

    //正则表达式
    private static boolean strIsDigit2(String substring) {
        return substring.matches("\\d+");
    }
}

