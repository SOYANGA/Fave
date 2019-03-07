package Day_04;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-07 14:14
 * @Version 1.0
 */



import java.util.Scanner;



/*
小易去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装(包装不可拆分)。
可是小易现在只想购买恰好n个苹果，小易想购买尽量少的袋数方便携带。如果不能购买恰好n个苹果，
小易将不会购买。

输入描述：
输入一个整数n，表示小易想购买n(1 ≤ n ≤ 100)个苹果

输出描述:
输出一个整数表示最少需要购买的袋数，如果不能买恰好n个苹果则输出-1

示例1

输入
20
输出
3

 */

/**
 * 数字特征
 * 首先 6和8都是偶数，因此能凑出个数个数偶数，程序中苹果总数为奇数直接返回-1
 * 再次偶数个如果对8取模，其看结果只能时0 2 4 6
 * 由于数字6和8的特征，本方法只适用于本题
 *
 * 情况1：若num不是偶数，则直接返回-1
 * 情况2：若num%8 = 0，则返回num/8
 * 情况3：若num%8 != 0，则只需回溯1次或者2次8包装购买个数，就可以求解。
 * 回溯1次，其结果为n/8-1+2 = n/8+1；回溯1次，其结果为n/8-2+3 = n/8+1。
 * 因此，可以情况3下，可以返回n/8+1。
 */
public class BuyApple {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        if (n < 6 || n % 2 != 0) {
            System.out.println(-1);
            return;
        }
        if (n % 8 == 0) {
            System.out.println(n / 8);
        } else if ((n % 8) % 2 == 0 && n != 10) {
            System.out.println(n / 8 + 1);
        } else {
            System.out.println(-1);
        }
    }
}
