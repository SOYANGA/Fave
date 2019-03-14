package Day_11;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-14 20:40
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {

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
