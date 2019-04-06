package Day_30;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-06 23:27
 * @Version 1.0
 */
/*
奇偶校验位是一个表示给定位数的二进制数中 1 的个数是奇数还是偶数的二进制数。奇偶校验位是最简单的错误检测码。奇偶校验位有两种类型:偶校验位与奇校验位。如果一组给定数据位中 1 的个数是奇数，那么偶校验位就置为 1，从而使得总的 1 的个数是偶数;如果给定一组数据位中 1 的个数是偶数，那么奇校验位就置为 1，使得总的 1 的个数保持奇数不变。

如果是采用奇校验，在传送每一个字节的时候另外附加一位作为校验位，校验位在数据位后面，当实际数据中"1"的个数为偶数的时候，这个校验位就是"1"，否则这个校验位就是"0"，这样就可以保证传送数据满足奇校验的要求。在接收方收到数据时，将按照奇校验的要求检测数据中"1"的个数，如果是奇数，表示传送正确，否则表示传送错误。
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String string = in.nextLine();
            char[] chars = string.toCharArray();
            oddEvenCheck(chars);
        }
    }

    private static void oddEvenCheck(char[] chars) {
        int[] result = new int[8];
        for (int i = 0; i < chars.length; i++) {
            int n = 1;
            int count = 7;  //字符为一字节 8位 最高位为符号位 则只需判断7次即可
            int sum = 0;
            while (count > 0) {
                result[count] = (chars[i] & n) == 0 ? 0 : 1;
                if (result[count] == 1) {
                    sum++;
                }
                n = n << 1;
                count--;
            }
            if ((sum & 1) == 0) {  //判断奇数还是偶数
                result[0] = 1;//要校验的数的1的个数为偶数，则校验为1
            }
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[j]);
            }
            result[0] = 0;
            System.out.println();
        }

    }
}
