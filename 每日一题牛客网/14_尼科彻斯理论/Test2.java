package Day_14;

import java.util.Scanner;

/*
    尼科彻斯定理
 */

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-20 00:20
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        GetSequeOddNum();
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int num = in.nextInt();
//            //计算的num的立方
//            int sum = (int) Math.pow(num, 3);
//            //发现sum/num = 输出计数的最中间的偶数
//            int div = sum / num;
//            //利用偶数=i*2 奇数=i*2-1 和需要的奇数个数，之间的关系确定最开始的奇数对应的i的值startIndex=div/2-(num/2-1);
//            int startIndex = div / 2 - (num / 2 - 1);
//            //利用num奇数的个数确定最后一个素数对应的i
//            int endIndex = startIndex + num - 1;
//            //循环遍历打印startIndex~endIndex的素数即可
//            for (int i = startIndex; i <= endIndex; i++) {
//                if (i == endIndex) {
//                    System.out.println((2 * i - 1));
//                } else {
//                    System.out.print((2 * i - 1) + "+");
//                }
//            }
//        }
    }

    public static void GetSequeOddNum() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            int startNumber = num * (num - 1) + 1;
            for (int i = 0; i < num - 1; i++) {
                System.out.print(startNumber + 2 * i + "+");
            }
            System.out.println(startNumber + 2 * (num - 1));
        }
    }
}
