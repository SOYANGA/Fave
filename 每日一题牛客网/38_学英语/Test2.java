package Day_39;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:学英语
 * @Author: SOYANGA
 * @Create: 2019-04-19 23:31
 * @Version 1.0
 */

import java.util.Scanner;


public class Test2 {
    static String[] a = new String[]{
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };//定义数字1-9
    static String[] b = new String[]{
            "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };//定义数字10-19
    static String[] c = new String[]{
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };//定义数字整十

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String n = sc.next();
            StringBuilder sb = new StringBuilder();
            String regex = "^[0-9]{1,9}$";//正则表达式
            if (!n.matches(regex) || n.startsWith("0")) {//判定输入是否合法
                System.out.println("error");
            } else {
                /**
                 * 这里将数字串划分为三位一串，分别读取其英文字符串，然后再进行连接
                 */
                int m = n.length();
                int num = 0;
                while (m - 3 > 0) {//数字长度大于3时，切割，记录切割次数，如长度为9，能切割两次，长度为5，只能切割一次
                    num++;
                    m -= 3;
                }
                switch (num) {//这里根据切割次数情况不同进行不同输出，parse函数是读取小于三位的数字的英文字符串。
                    case 0:
                        System.out.println(parse(n.substring(0, m)));
                        break;//切割为0，说明数字长度小于3，直接输出parse函数的返回值就行
                    case 1:
                        if (parse(n.substring(m, m + 3)).equals("")) {//长度>3，但是<=6，若后三位为全零的情况
                            System.out.println(parse(n.substring(0, m)) + " thousand");
                        } else {
                            System.out.println(parse(n.substring(0, m)) + " thousand " + parse(n.substring(m, m + 3)));
                        }
                        break;
                    case 2://长度为7-9位
                        if (parse(n.substring(m, m + 3)).equals("") && parse(n.substring(m + 3, m + 6)).equals("")) {//后六位全为零输出
                            System.out.println(parse(n.substring(0, m)) + " million");
                        } else if (parse(n.substring(m, m + 3)).equals("") && !parse(n.substring(m + 3, m + 6)).equals("")) {//中间三位为0，后三位不为0
                            System.out.println(parse(n.substring(0, m)) + " million " + parse(n.substring(m + 3, m + 6)));
                        } else if (!parse(n.substring(m, m + 3)).equals("") && parse(n.substring(m + 3, m + 6)).equals("")) {//中间不为零，后三位为0
                            System.out.println(parse(n.substring(0, m)) + " million " + parse(n.substring(m, m + 3)));
                        } else {//都不为零
                            System.out.println(parse(n.substring(0, m)) + " million " + parse(n.substring(m, m + 3)) + " thousand " + parse(n.substring(m + 3, m + 6)));
                        }
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
            }
        }
        sc.close();
    }

    private static String parse(String ss) {
        int n = ss.length();
        int oo = Integer.parseInt(ss);//读取字符串的数值，防止中间位为020,002,000等情况，只读取有效位
        String s = "" + oo;
        int m = s.length();//有效位的长度
        StringBuilder str = new StringBuilder();
        switch (m) {
            case 1:
                str.append(a[Integer.parseInt(s)]);
                break;
            case 2:
                if (s.charAt(0) == '1') {
                    str.append(c[s.charAt(1) - '0']);
                } else if (s.charAt(1) == '0') {
                    str.append(b[s.charAt(0) - '0']);
                } else {
                    str.append(b[s.charAt(0) - '0'] + " " + a[s.charAt(1) - '0']);
                }
                break;
            case 3:
                if (s.charAt(1) == '0' && s.charAt(2) == '0') {
                    str.append(a[s.charAt(0) - '0'] + " hundred");
                } else if (s.charAt(1) == '0') {
                    str.append(a[s.charAt(0) - '0'] + " hundred and " + a[s.charAt(2) - '0']);
                } else if (s.charAt(1) == '1') {
                    str.append(a[s.charAt(0) - '0'] + " hundred and " + c[s.charAt(2) - '0']);
                } else if (s.charAt(2) == '0') {
                    str.append(a[s.charAt(0) - '0'] + " hundred and " + b[s.charAt(1) - '0']);
                } else {
                    str.append(a[s.charAt(0) - '0'] + " hundred and " + b[s.charAt(1) - '0'] + " " + a[s.charAt(2) - '0']);
                }
                break;
            default:
                System.out.println("error");
                break;
        }
        return str.toString();
    }

}

//public class Test2 {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String numstr = scanner.next();
//            if (numstr.length() <= 9 && !numstr.contains(".") && !numstr.contains("-")) {
//                String englishnum = parse(Long.parseLong(numstr));
//                System.out.println(englishnum);
//            } else {
//                System.out.println("error");
//            }
//        }
//    }
//
//    public static String parse(long num) {
//        HashMap number = new HashMap(10);
//        HashMap numberty = new HashMap(10);
//        HashMap count = new HashMap(5);
//        number.put(0, "zero");
//        number.put(1, "one");
//        number.put(2, "two");
//        number.put(3, "three");
//        number.put(4, "four");
//        number.put(5, "five");
//        number.put(6, "six");
//        number.put(7, "seven");
//        number.put(8, "eight");
//        number.put(9, "nine");
//
//        numberty.put(0,)
//
//
//    }
//}
//}

