package Day_15;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-20 23:16
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(addAB(3, 2));
    }

    public static int addAB(int A, int B) {
        while ((A--) > 0) {
            B++;
        }
        return B;
    }

}
