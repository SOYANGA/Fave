package Day_18;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-24 21:15
 * @Version 1.0
 */

/*
sn = a1(1-q^n)/(1-q) = 2^30-1
an = a1*q^(n-1);
 */
public class Test2 {
    public static void main(String[] args) {
//        int sum = 0;
//        int money = 1;
//        for (int i = 0; i < 30; i++) {
//            sum += money;
//            money *= 2;
//        }
        System.out.print(10 * 30 + " " + ((1<<30)-1));
    }
}
