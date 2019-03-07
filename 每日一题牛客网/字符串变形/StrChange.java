package Day_04;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-07 16:38
 * @Version 1.0
 */




/*
字符串变形
对于一个给定的字符串，我们需要在线性(也就是O(n))的时间里对它做一些变形。首先这个字符串中包含着一些空格，
就像"Hello World"一样，然后我们要做的是把着个字符串中由空格隔开的单词反序，
同时反转每个字符的大小写。比如"Hello World"变形后就变成了"wORLD hELLO"。


输入描述:
给定一个字符串s以及它的长度n(1≤n≤500)


输出描述:
请返回变形后的字符串。题目保证给定的字符串均由大小写字母和空格构成。

示例1
输入
"This is a sample",16
输出
"SAMPLE A IS tHIS"
 */
/**
 * 将字符串拆解成单个字符
 * 字符先进行大小写转换（不是空格时）
 * 当c字符为空格时将 字符串+空格放在结果要返回的字符串前，一直头插。
 * tempStr = c + tempStr;
 * result = tempStr + result;
 * 然后清空tempStr
 *
 * 最后将最后一个字符头插到字符串前;
 */
public class StrChange {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String n = s.nextLine();
        System.out.println(trans(n, 16));
    }


    public static String trans(String s, int n) {
        String result = "";
        String tempStr = "";

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                tempStr += (char) (c - 32);
            } else if (c >= 'A' && c <= 'Z') {
                tempStr += (char) (c + 32);
            } else {
                tempStr = c + tempStr;
                result = tempStr + result;
                tempStr = "";
            }
        }
        result = tempStr + result;
        return result;
    }
}
