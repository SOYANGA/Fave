package Day_25;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-31 23:51
 * @Version 1.0
 */

public class Test2 {
    static char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    static char[] unitLast = {'角', '分'};
    static char[] unit = {' ', '拾', '佰', '仟'};
    static char[] lastfix = {'万', '亿'};

    public static String processPre(char[] pre) {
        StringBuilder str = new StringBuilder();
        str.append('元');
        int count = 0;
        boolean flag = false;
        boolean zero = false;
        boolean first = false;
        boolean input = false;
        if (pre[pre.length - 1] == '0') {
            first = true;
        }
        for (int i = pre.length - 1; i >= 0; i--) {
            count++;
            if (count % 4 == 1 && count != 1) {
                str.append(lastfix[count / 4 - 1]);
            }
            if (pre[i] != '0') {
                flag = true;
                first = false;
                input = true;
            } else {
                zero = true;
            }
            if (first && pre[i] == '0') {
                zero = false;
            }
            if (flag && unit[(pre.length - 1 - i) % 4] != ' ') {
                str.append(unit[(pre.length - 1 - i) % 4]);
            }
            if (count != pre.length || count % 4 != 2) {
                if (flag) {
                    str.append(digit[pre[i] - '0']);
                    flag = false;
                }
                if (zero && input) {
                    str.append('零');
                    input = false;
                    zero = false;
                }
                if (!input && zero) {
                    zero = false;
                }

            }
            if (count % 4 == 2 && pre[i] != '0') {
                flag = false;
            }

        }
        return str.reverse().toString();
    }

    public static String processLast(char[] last) {
        String str = "";
        for (int i = 0; i < last.length; i++) {
            if (last[i] != '0') {
                str += digit[last[i] - '0'];
                str += unitLast[i];
            }
        }
        return str;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String[] money = cin.next().split("\\.");
            String rmb = "人民币";
            char[] pre = money[0].toCharArray();
            boolean flagLast = true;
            boolean flagPre = true;
            char[] last = null;
            if (money.length == 1) {
                flagLast = true;
            } else {
                last = money[1].toCharArray();
                for (int i = 0; i < last.length; i++) {
                    if (last[i] != '0') {
                        flagLast = false;
                    }
                }
            }
            for (int i = 0; i < pre.length; i++) {
                if (pre[i] != '0') {
                    flagPre = false;
                }
            }
            if (flagPre && flagLast) {
                System.out.println("零元零角零分");
            }
            if (flagLast && !flagPre) {
                rmb += processPre(pre);
                rmb += '整';
            }
            if (flagPre && !flagLast) {
                rmb += processLast(last);
            }
            if (!flagLast && !flagPre) {
                rmb += processPre(pre);
                rmb += processLast(last);
            }
            System.out.println(rmb);

        }
    }

}