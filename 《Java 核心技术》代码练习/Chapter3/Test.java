package CHapter3;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.xml.crypto.Data;
import java.io.Console;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {

//        String str = "我hello";
//        System.out.println((str.substring(0, 3) + "0") == "Hello");
//        System.out.println((str + "o") == "Helloo");
//        System.out.println(str.compareTo(str));
//        System.out.println(str.length());
//        System.out.println(str.codePointCount(0, str.length()));
//        System.out.println(str);
//        char ch = str.charAt(0);
//        System.out.println(ch);
//        System.out.println(str.codePointAt(1));

//        Console cons = System.console();
//        String name = cons.readLine("sad");
//        char[] rs = cons.readPassword("sad");
//        Scanner scanner = new Scanner(System.in);
//        String names = scanner.nextLine();
//        String ps = scanner.next();
////        System.out.println(ps);
//        System.out.printf("%1$s %2$tB %2$te,%2$tY","Due date:", new Date());
////        System.out.printf(" %l$s %2$tB %2$te, %2$tY", "Due date:", new Date());
////        System.out.printf("%1$tB",new Date());
//        System.out.println();
//        System.out.printf("%s %tB %<te,%<tY","Due date:", new Date());
//        Console cons = System.console();
//        String username = cons.readLine("User name: ");
//        char[] passwd = cons.readPassword("Password:");

        Scanner in = new Scanner(Paths.get("D:\\1.txt"), "UTF-8");
        PrintWriter out1 = new PrintWriter("D:\\1.txt", "UTF-8");
        out1.append("哈哈1\n");
        out1.append("哈哈2\n");
        out1.append("哈哈3\n");
        out1.write("哈哈4\n");
        out1.print("哈哈5\n");
        out1.close();//输出流需要在读取之前关闭保存
        System.out.println(in.next());
        out1.print("哈哈8\n");
        out1.close();//输出流需要在读取之前关闭保存
        System.out.println(in.next());
        System.out.println(in.next());
        in.close();
    }
}


