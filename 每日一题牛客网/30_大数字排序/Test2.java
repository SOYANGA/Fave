package Day_30;

import java.math.BigDecimal;
import java.util.*;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-06 23:27
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextBigDecimal()) {
            int count = in.nextInt();
            Set<BigDecimal> bigDecimals = new TreeSet<>();
            while (count-- > 0) {
                bigDecimals.add(in.nextBigDecimal());
            }
            for (BigDecimal bigDecimal : bigDecimals) {
                System.out.println(bigDecimal);
            }
        }
    }
}


//public class Test2 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            int count = Integer.parseInt(in.nextLine());
//            List<String> stringslist = new ArrayList();
//            while (count-- > 0) {
//                stringslist.add(in.nextLine());
//            }
//            Collections.sort(stringslist, new Comparator<String>() {
//                @Override
//                public int compare(String o1, String o2) {
//                    if (o1.length() > o2.length()) {
//                        return 1;
//                    } else if (o1.length() < o2.length()) {
//                        return -1;
//                    } else {
//                        if (o1.charAt(0) > o2.charAt(0)) {
//                            return 1;
//                        } else if (o1.charAt(0) < o2.charAt(0)) {
//                            return -1;
//                        } else {
//                            return 0;
//                        }
//
//                    }
//                }
//            });
//            for (String str : stringslist) {
//                System.out.println(str);
//            }
//        }
//    }
//}