package Day_23;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-29 23:21
 * @Version 1.0
 */

/*
 * 数字分类 (20)
 * 题目描述
 * 给定一系列正整数，请按要求对数字进行分类，并输出以下5个数字：
 * A1 = 能被5整除的数字中所有偶数的和；
 * A2 = 将被5除后余1的数字按给出顺序进行交错求和，即计算n1-n2+n3-n4...；
 * A3 = 被5除后余2的数字的个数；
 * A4 = 被5除后余3的数字的平均数，精确到小数点后1位；
 * A5 = 被5除后余4的数字中最大数字。
 * 输入描述:
 * 每个输入包含1个测试用例。每个测试用例先给出一个不超过1000的正整数N，
 * 随后给出N个不超过1000的待分类的正整数。数字间以空格分隔。
 * 输出描述:
 * 对给定的N个正整数，按题目要求计算A1~A5并在一行中顺序输出。数字间以空格分隔，
 * 但行末不得有多余空格。
 * 若其中某一类数字不存在，则在相应位置输出“N”。
 * 输入例子:
 * 13 1 2 3 4 5 6 7 8 9 10 20 16 18
 * 输出例子:
 * 30 11 2 9.7 9
 */
public class Test21 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] array = new int[N];
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int temp = 1;
        boolean find2 = false;
        int sum4 = 0;
        int count4 = 0;
        int a5 = 0;
        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
            switch (array[i] % 5) {
                case 0:
                    if (array[i] % 2 == 0) {
                        a1 += array[i];
                    }
                    break;
                case 1:
                    find2 = true;
                    a2 += temp * array[i];
                    temp = (-1) * temp;
                    break;
                case 2:
                    a3++;
                    break;
                case 3:
                    sum4 += array[i];
                    count4++;
                    break;
                case 4:
                    if (a5 < array[i]) {
                        a5 = array[i];
                    }
                    break;
                default:
                    break;
            }
        }
        String A4 = "N";
        if (count4 > 0) {
            A4 = new DecimalFormat("0.0").format(sum4 * 1.0 / count4);
        }

        if (a1 == 0) {
            System.out.print("N" + " ");
        } else {
            System.out.print(a1 + " ");
        }
        if (find2) {
            System.out.print(a2 + " ");
        } else {
            System.out.print("N" + " ");
        }
        if (a3 == 0) {
            System.out.print("N" + " ");
        } else {
            System.out.print(a3 + " ");
        }
        System.out.print(A4 + " ");
        if (a5 == 0) {
            System.out.print("N");
        } else {
            System.out.print(a5);
        }

    }
}