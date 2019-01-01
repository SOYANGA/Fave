package Day_27;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 整数与ip地址间的转换
 * @Author: SOYANGA
 * @Create: 2019-04-02 23:09
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String[] numStr = in.nextLine().split("\\.");
            String dotdecimalStr = in.nextLine();
            long num = Long.parseLong(dotdecimalStr);
            //处理IP地址
            BigInteger sum = BigInteger.valueOf(0);
            int rise = 0;
            for (int i = numStr.length - 1; i >= 0; i--) {
                BigInteger temp = BigInteger.valueOf(Long.parseLong(numStr[i]) << rise);
                sum = sum.add(temp);
                rise += 8;
            }
            System.out.println(sum);

            //处理点分十进制
            long[] dotdecimals = new long[4];
            for (int i = 0; i < 4; i++) {
                dotdecimals[i] = num & 255;
                num = num >> 8;
            }
            System.out.println(dotdecimals[3] + "." + dotdecimals[2] + "." + dotdecimals[1] + "." + dotdecimals[0]);
        }
    }
}
