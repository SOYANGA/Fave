package Day_06;

import java.util.*;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/eac8c671a0c345b38aa0c07aba40097b
 * 来源：牛客网
 * 输入描述：
 * 每个测试输入包含n个空格分割的n个整数，n不超过100，其中有一个整数出现次数大于等于n / 2。
 *
 * 输出描述：
 * 输出出现次数大于等于N / 2的数。
 * 示例1
 * 输入
 * 3 9 3 2 5 6 7 3 2 3 3 3
 * 输出
 * 3
 *
 *
 * 思路：hashMap存储数值，和出现次数，遍历map即可得到次数大于n/2的元素
 *
 * 思路2:排序+取下标为n/2的元素返回即可
 */
public class Test2 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap();
        Scanner in = new Scanner(System.in);
        int n = 0;
        while (in.hasNextInt()) {
            int num = in.nextInt();
            n++;
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //1.将Map集合转变为Set集合
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        //2.获取iterator对象
        Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();
        //3.输出
        while (iterator.hasNext()) {
            //取出每个Map.Entry对象
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue() >= (n / 2)) {
                System.out.println(entry.getKey());
            }
        }
    }
}
