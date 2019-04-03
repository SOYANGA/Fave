package Day_28;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-03 23:45
 * @Version 1.0
 */
public class Test1 {


    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 2, 3, 4};
        int[] B = multiply(A);
        for (int e : B) {
            System.out.println(e);
        }
    }

    public static int[] multiply(int[] A) {
        int lengthA = A.length;
        int[] newNum = new int[lengthA];
        int ret = 1;
        for (int i = 0; i < lengthA; ret *= A[i++]) {
            newNum[i] = ret;
        }
        ret = 1;
        for (int i = lengthA - 1; i >= 0; ret *= A[i--]) {
            newNum[i] *= ret;
        }
        return newNum;
    }
}
