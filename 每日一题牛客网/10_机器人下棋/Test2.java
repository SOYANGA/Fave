package Day_10;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-13 22:12
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        int factorialmn = factorial(2);
        int factorialn = factorial(1);
        int factorialm = factorial(1);
        System.out.println(factorialmn/factorialn / factorialm);


    }

    private static int factorial(int i) {
        int count = 1;
        for (int j = i; j > 0; j--) {
            count *= j;
        }
        return count;
    }

    public static int countWays(int x, int y) {
        if (x == 1 || y == 1) {
            return 1;
        }
        int result = countWays(x - 1, y) + countWays(x, y - 1);
        return result;
    }
}
