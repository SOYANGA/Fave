package Triangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCode
 * @Description: 120 三角形最小路径和 递归+记忆法
 * @Author: SOYANGA
 * @Create: 2019-06-25 16:30
 * @Version 1.0
 */
public class Triangle2 {
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


    //初始化记忆数组 + 执行递归调用 + 数字中存储Integer.MIN_VALUE 标记已经计算过
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        List<List<Integer>> memo = new ArrayList<>();
        for (int i = 0; i < triangle.size(); i++) {
            memo.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                if (i == triangle.size() - 1) {
                    memo.get(i).add(triangle.get(i).get(j));
                }
                memo.get(i).add(Integer.MIN_VALUE);

            }
        }
        return minimumTotal(triangle, memo, 0, 0);

    }

    private static int minimumTotal(List<List<Integer>> triangle, List<List<Integer>> memo, int row, int col) {
        int temp = triangle.get(row).get(col);
        if (row + 1 == triangle.size()) {
            return temp;
        }
        if (memo.get(row).get(col) != Integer.MIN_VALUE) {
            return memo.get(row).get(col);
        }
        int left = minimumTotal(triangle,memo,row+1,col)+temp;//计算左三角
        int right = minimumTotal(triangle,memo,row+1,col+1)+temp;//计算右三角
        memo.get(row).set(col, Math.min(left, right)); //更新memo的值
        return memo.get(row).get(col);
    }
}
