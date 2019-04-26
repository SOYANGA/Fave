package Day_45;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 简单错误查询
 * @Author: SOYANGA
 * @Create: 2019-04-26 22:25
 * @Version 1.0
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        while (sc.hasNext()) {
            String str = sc.next();
            int linenum = sc.nextInt();
            String[] arr = str.split("\\\\");  //根据\切割
            String s = arr[arr.length - 1];
            if (s.length() > 16) {
                //截取
                s = s.substring(s.length() - 16);
            }
            String key = s + " " + linenum;
            int value = 1;
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, value);
            }
        }
        int count = 0;
        for (String string : map.keySet()) {
            count++;
            if (count > (map.keySet().size() - 8)) {
                //输出最后八个记录
                System.out.println(string + " " + map.get(string));
            }
        }
    }
}
