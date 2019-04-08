package Day_32;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-08 22:21
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
        int countCoupons = in.nextInt();
        long[] coupons = new long[countCoupons];
        while (countCoupons-- > 0) {
            coupons[countCoupons] = in.nextLong();
        }
        int countProduct = in.nextInt();
        long[] porducts = new long[countProduct];
        while (countProduct-- > 0) {
            porducts[countProduct] = in.nextLong();
        }
        Arrays.sort(coupons);
        Arrays.sort(porducts);
        long sum = 0;
        int couponsIndex = 0;
        int porductsIndex = 0;

        for (int i = 0; ; i++) {
            if (coupons[i] > 0) {
                couponsIndex = i;
                break;
            }
        }

        for (int i = 0; ; i++) {
            if (porducts[i] > 0) {
                porductsIndex = i;
                break;
            }
        }
        for (int i = 0; i < couponsIndex && i < porductsIndex; i++) {
            sum += (coupons[i] * porducts[i]);
        }
        for (int i = coupons.length - 1, j = porducts.length - 1; i >= couponsIndex && j >= porductsIndex; i--, j--) {
            sum += (coupons[i] * porducts[j]);
        }
        System.out.println(sum);
    }
}


//package Day_32;
//
//        import java.util.Arrays;
//        import java.util.Scanner;
//
///**
// * @program: EveryDayTest
// * @Description:
// * @Author: SOYANGA
// * @Create: 2019-04-08 22:21
// * @Version 1.0
// */
//public class Test2 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//        int countCoupons = in.nextInt();
//        long[] coupons = new long[countCoupons];
//        while (countCoupons-- > 0) {
//            coupons[countCoupons] = in.nextLong();
//        }
//        int countProduct = in.nextInt();
//        long[] porducts = new long[countProduct];
//        while (countProduct-- > 0) {
//            porducts[countProduct] = in.nextLong();
//        }
//        Arrays.sort(coupons);
//        Arrays.sort(porducts);
//        long sum = 0;
////            int minLength = Math.min(coupons.length, porducts.length);
//        for (int i = 0, j = 0; i < coupons.length && j < porducts.length; i++, j++) {
//            if (coupons[i] < 0 && porducts[j] < 0) {
//                sum += (coupons[i] * porducts[j]);
//            }
//            if (coupons[i] >= 0 || porducts[i] >= 0) {
//                break;
//            }
//        }
//        for (int i = coupons.length - 1, j = porducts.length - 1; i >= 0 && j >= 0; i--, j--) {
//            if (coupons[i] > 0 && porducts[j] > 0) {
//                sum += (coupons[i] * porducts[j]);
//            }
//            if (coupons[i] <= 0 || porducts[i] <= 0) {
//                break;
//            }
//        }
//        System.out.println(sum);
//    }
//}


