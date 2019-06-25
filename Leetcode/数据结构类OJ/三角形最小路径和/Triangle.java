package Triangle;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-06-25 16:30
 * @Version 1.0
 */
public class Triangle {
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
        int row = triangle.size();
        List<Integer> mini = triangle.get(row - 1);
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                mini.set(j, triangle.get(i).get(j) + Math.min(mini.get(j), mini.get(j + 1)));
            }
        }
        return mini.get(0);
    }


}
