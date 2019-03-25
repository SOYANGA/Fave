package Day_19;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-25 00:17
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        String[] p = new String[]{"a", "b", "c", "d"};
        String s = "abc";
        boolean[] booleans = chkSubStr(p, p.length, s);
        System.out.print(booleans[0]);
        for (int i = 1; i < booleans.length; i++) {
            System.out.print(" " + booleans[i]);
        }
    }

    public static boolean[] chkSubStr(String[] p, int n, String s) {
        boolean[] bools = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (s.contains(p[i])) {
                bools[i] = true;
            } else {
                bools[i] = false;
            }
        }
        return bools;
    }
}
