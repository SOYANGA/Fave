package Day_07;

import java.util.Scanner;

/*
正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，
设计一个算法，求输入A和B的最小公倍数。
 */
/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-10 21:36
 * @Version 1.0
 */

/**
 *最小公倍数 = 两数之积/最大公约数    求最大公约数即可
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        //排除 最小公倍数就在两数之中
        if (a % b == 0) {
            System.out.print(a > b ? a : b);
            return;
        }

        //方式1：最大公约数 肯定在两数小的数的范围内，
        int i = a > b ? b : a;
        while ((a % i) != 0 || (b % i) != 0) {
            i--;
        }
        //方式2：求最大公约数 --辗转相除
//        int A = a;
//        int B = b;
//        while (A % B != 0) {
//            int c = A % B;
//            A = B;
//            B = c;
//        }
        //最小公倍数 = 两数之积/最大公约数
        System.out.println(b / i * a);
    }
}
