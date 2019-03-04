/**
 * @program: Sort
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-04 22:09
 * @Version 1.0
 */

import java.util.Scanner;


/**
 *
 * “回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。花花非常喜欢这种拥有对称美的回文串，生日的时候她得到两个礼物分别是字符串A和字符串B。现在她非常好奇有没有办法将字符串B插入字符串A使产生的字符串是一个回文串。你接受花花的请求，帮助她寻找有多少种插入办法可以使新串是一个回文串。如果字符串B插入的位置不同就考虑为不一样的办法。
 * 例如：
 * A = “aba”，B = “b”。这里有4种把B插入A的办法：
 * * 在A的第一个字母之前: "baba" 不是回文
 * * 在第一个字母‘a’之后: "abba" 是回文
 * * 在字母‘b’之后: "abba" 是回文
 * * 在第二个字母'a'之后 "abab" 不是回文
 * 所以满足条件的答案为2
 *
 * 判断回文
 * StringBuilder reverse()使用
 * 字符串插入
 * StringBuilder insert()使用
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            int count = 0;
            for (int i = 0; i <=a.length(); i++) {
                StringBuilder s1 = new StringBuilder(a);
                s1.insert(i, b);
                String s = new String(s1);
                String s2 = new StringBuilder(s1).reverse().toString();
                if (s.equals(s2)) {
                    System.out.println(s1);
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}


//public class Main {
//    public static boolean huiwen(String s) {
//        int i = 0;
//        int j = s.length() - 1;
//        while (i < j) {
//            if (s.charAt(i) != s.charAt(j)) {
//                return false;
//            }
//            i++;
//            j--;
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Scanner sc = new Scanner(System.in);
//        String str1 = sc.nextLine();
//        String str2 = sc.nextLine();
//
//        //StringBuffer sb = new StringBuffer(str1);
//        //System.out.println(sb.toString());
////        if(huiwen(sb.toString())==true)System.out.println(1);
////        else {
////            System.out.println(0);
////        }
////        int count = 0;
////        for(int i = 0;i<=str1.length();i++){
////            String string = str1.substring(0,i)+str2+str1.substring(i,str1.length());
////            if(huiwen(string)==true){
////                count++;
////            }
////        }
//        int count = 0;
//        for (int i = 0; i <= str1.length(); i++) {
//            StringBuilder sb = new StringBuilder(str1);
//            sb.insert(i, str2);
//            if (huiwen(sb.toString())) {
//                System.out.println(sb);
//                count++;
//            }
//        }
//        System.out.println(count);
//    }
//
//}