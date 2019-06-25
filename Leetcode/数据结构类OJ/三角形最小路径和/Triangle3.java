package Triangle;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCode
 * @Description: 120 三角形最小路径和
 *                                  动态规划(dp[]一维数组法)发现只需要用钱一行的dp表示当前行的dp即可则转变为一位数组
 *                                 dp[j] = Math.min(dp[j], dp[j + 1]) + tempList.get(j);
 * @Author: SOYANGA
 * @Create: 2019-06-25 16:30
 * @Version 1.0
 */
public class Triangle3 {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new LinkedList<>();
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        List<Integer> list3 = new LinkedList<>();
        List<Integer> list4 = new LinkedList<>();
        list1.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);

        System.out.println(minimumTotal1(triangle));
    }

    //初始化dp
    public static int minimumTotal1(List<List<Integer>> triangle) {
        //参数防御
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int m = triangle.size();
        int dp[] = new int[m+1];

        //初始化dp数组 将最后一行提前存入
        List<Integer> lastList = triangle.get(m - 1);
        for (int i = 0; i < lastList.size(); i++) {
            dp[i] = lastList.get(i);
        }

        // 开始动态规划过程，计算子结果并存储
        for (int i = m - 2; i >= 0; i--) {
            List<Integer> tempList = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + tempList.get(j);
            }
        }
        return dp[0];
    }

    //未初始化 dp
    public static int minimumTotal2(List<List<Integer>> triangle) {
        //参数防御
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int m = triangle.size();
        int dp[] = new int[m+1];
        // 开始动态规划过程，计算子结果并存储
        for (int i = m - 1; i >= 0; i--) {
            List<Integer> tempList = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + tempList.get(j);
            }
        }
        return dp[0];
    }
}
