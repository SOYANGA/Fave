package QuickSortOp;

/**
 * @program: Sort
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-04 17:15
 * @Version 1.0
 */

/**
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 *
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 *
 * 测试样例：
 * [1,3,5,2,2],5,3
 * 返回：2
 */
public class QuickSortK {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 2, 2, 10};
        System.out.println(findKth(a, 6, 3));

    }

    public static int findKth(int[] a, int n, int k) {
        return find(a, 0, n - 1, k);
    }

    public static int find(int[] a, int start, int end, int k) {
        int part = partition(a, start, end);
        if (part == k - 1) {
            return a[part];
        } else if (part > k - 1) {
            return find(a, start, part - 1, k);
        } else {
            return find(a, part + 1, end, k);
        }
    }

    public static int partition(int[] a, int begin, int end) {
        int temp = a[end];
        int i = begin;
        for (int j = begin; j < end; j++) {
            if (a[j] > temp) {
                if (i == j) {
                    ++i;
                } else {
                    swap(a, i, j);
                    i++;
                }
            }
        }
        swap(a, i, end);
        return i;
    }

    public static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;

    }
}

