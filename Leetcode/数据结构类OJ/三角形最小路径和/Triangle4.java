package Triangle;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCode
 * @Description: 120 三角形最小路径和 动态规划(dp[][]二维数组法) 可初始化也可不初始化 ，即是否提前将最后一行放入dp中的选择
 * @Author: SOYANGA
 * @Create: 2019-06-25 16:30
 * @Version 1.0
 */
public class Triangle4 {
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

        System.out.println(minimumTotal(triangle));
    }


    public static int minimumTotal(List<List<Integer>> triangle) {
        // 参数防御工作
        if (triangle == null) {
            return 0;
        }
        int row = triangle.size();
        int col = triangle.get(row - 1).size();
        if (row == 0 || col == 0 || row != col) {
            return 0;
        }

        // 初始化结果的存储位置
        int[][] res = new int[row][row];
        // 初始化最后一行结果
        List<Integer> lastList = triangle.get(row - 1);
        for (int i = 0; i < row; i++) {
            res[row - 1][i] = lastList.get(i);
        }

        // 开始动态规划过程，计算子结果并存储
        for (int i = row - 2; i >= 0; i--) {
            List<Integer> currentList = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                res[i][j] = Math.min(res[i + 1][j], res[i + 1][j + 1]) + currentList.get(j);
            }
        }

        return res[0][0];
    }
}


