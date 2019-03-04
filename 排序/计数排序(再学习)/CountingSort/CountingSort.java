package CountingSort;

/**
 * @program: Sort
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-13 16:00
 * @Version 1.0
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] num = new int[]{0, 8, 5, 9, 2, 7, 6, 3, 1, 4};
        countingsort(num, 10);
        for (int temp : num) {
            System.out.print(temp + ",");
        }
    }

    public static void countingsort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        //计算数据的范围
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        //1.创建辅助数组
        int[] c = new int[max + 1];//申请一个计数数组，下标大小[0,max]
        for (int i = 0; i <= max; i++) {
            c[i] = 0;
        }

        //计算每个元素的个数，放入C中
        for (int i = 0; i < n; i++) {
            c[a[i]]++;
        }

        //依次累加  得到辅助数组C[]中存储的是小于等于该数的数据个数
        for (int i = 1; i <= max; i++) {
            c[i] = c[i - 1] + c[i];
        }

        //2.排序数组的创建，排序之后的结果
        int[] r = new int[n];
        //计算排序的关键步骤！！！
        for (int i = n-1; i >= n; i--) {
            int index = c[a[i]] - 1;  //该分数对应放在排序数组的下标位置
            r[index] = a[i];
            c[a[i]]--;//小于等于该分数的数据个数-1；
        }

        //将结果拷贝给a数组
        for (int i = 0; i < n; i++) {
            a[i] = r[i];
        }

    }
}
