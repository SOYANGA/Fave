package print1ToMaxOfNDigits;

/**
 * @program: TSRTOffer
 * @Description: 17 打印从1到最大的n位数
 * @Author: SOYANGA
 * @Create: 2019-07-12 17:40
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        print1ToMaxOfNDigits(2);
    }

    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        char[] number = new char[n]; //创建一个n位数的数组
        print1ToMaxOfNDigits(number, 0);

    }

    private static void print1ToMaxOfNDigits(char[] number, int digit) {
        //将number 中填充构digit个字符之后进行打印
        if (digit == number.length) {
            printNumber(number);
            return;
        }
        //对每一位进行填充
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }

    private static void printNumber(char[] number) {
        int index = 0;
        //除去数组中的最前面的0
        while (index < number.length && number[index] == '0') {
            index++;
        }
        //打印有效位
        while (index < number.length) {
            System.out.print(number[index++]);
        }
        System.out.println();
    }


}
/*
题目描述
输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999

解题关键
由于 n 可能会非常大，因此不能直接用 int 表示数字，而是用 char 数组进行存储。
回溯法
 */