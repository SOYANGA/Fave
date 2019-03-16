package Day_12;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-16 19:10
 * @Version 1.0
 */

/*
   使用+ 或者 * 均可 但是会有溢出的风险 所以使用^
 */
public class Test1 {
    public static void main(String[] args) {
        int[] AB = new int[]{1, 2};
        exchangeAB(AB);
        for (int i : AB) {
            System.out.println(i);
        }
    }

    public static int[] exchangeAB(int[] AB) {
        AB[0] = AB[0] ^ AB[1];
        AB[1] = AB[0] ^ AB[1];
        AB[0] = AB[0] ^ AB[1];

        return AB;
    }
}
