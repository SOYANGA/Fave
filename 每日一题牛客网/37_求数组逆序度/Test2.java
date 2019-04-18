package Day_37;

/**
 * @program: EveryDayTest
 * @Description: 求数组逆序度
 * @Author: SOYANGA
 * @Create: 2019-04-18 18:25
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4, 5, 6, 7, 0};
        System.out.println(count(A, 8));
    }

    public static int count(int[] A, int n) {
        if (n == 1) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (A[i] > A[j]) {
                    num++;
                }
            }
        }
        return num;
    }
}
