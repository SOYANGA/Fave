import java.util.*;
/*

  有一个由很多木棒构成的集合，每个木棒有对应的长度，请问能否用集合中的这些木棒以某个顺序首尾相连构成一个面积大于 0 的简单多边形且所有木棒都要用上，
  简单多边形即不会自交的多边形。

  初始集合是空的，有两种操作，要么给集合添加一个长度为 L 的木棒，要么删去集合中已经有的某个木棒。
  每次操作结束后你都需要告知是否能用集合中的这些木棒构成一个简单多边形。

  输入描述:
  每组测试用例仅包含一组数据，每组数据第一行为一个正整数 n 表示操作的数量(1 ≤ n ≤ 50000) ， 接下来有n行，每行第一个整数为操作类型 i (i ∈ {1,2})，
  第二个整数为一个长度 L(1 ≤ L ≤ 1,000,000,000)。如果 i=1 代表在集合内插入一个长度为 L 的木棒，如果 i=2 代表删去在集合内的一根长度为 L 的木棒。
  输入数据保证删除时集合中必定存在长度为 L 的木棒，且任意操作后集合都是非空的。

  输出描述:
  对于每一次操作结束有一次输出，如果集合内的木棒可以构成简单多边形，输出 "Yes" ，否则输出 "No"。

  示例1
  输入
  5
  1 1
  1 1
  1 1
  2 1
  1 2

  输出
  No
  No
  Yes
  No
  No
 */

/**
 * 分析：
 * 问题的本质其实就是，
 * 给定n条边，问能否构成n边形，
 * 然而给定n条边，问能否构成n边形的条件是任意n-1条边之和大于另一条边(最大边满足即可)。
 * 因此本题可以从此处着手。
 */
public class Ispolygon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> num = new ArrayList<>(n);
        while ((n--) != 0) {
            int param = scanner.nextInt();  //操作数
            int length = scanner.nextInt(); //木棍长度
            if (1 == param) {
                num.add(length);    //添加
            } else if (num.contains(length) && 2 == param && !num.isEmpty()) {
                int index = num.indexOf(length);  //找到要删除木棍长度对用的元素的下标
                num.remove(index); //删除该下标的元素
            }
            if (isPolygon(num)) {   //进行多边形的判断
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    /**
     * 判断是否可以组成多边形
     *
     * @param num  存放木棍的集合
     * @return true/fasle
     */
    public static boolean isPolygon(ArrayList num) {
        boolean flag = true;
        int n = num.size();
        if (n < 3) {
            return false;
        }
        Collections.sort(num);    //对集合进行排序
        int max = (int) num.get(n - 1);  //获取集合中的最大值
        int count = 0;                   //其余n-1个木棍长度之和
        for (int i = 0; i < n - 1; i++) {
            count += (int) num.get(i);
        }
        if (count <= max) {   //任意n-1条边之和大于另一条边(最大边满足则其余边都满足)。
            flag = false;
        }
        return flag;
    }
}
