package MergeSort;

import java.util.Arrays;

/**
 * @program: Sort
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-10 23:27
 * @Version 1.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] num = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        merge_Sort(num, 0, 9);
        for (int temp : num) {
            System.out.print(temp + ",");
        }
    }

    //begin:是传入数组的起是位置，end:是数组结尾位置
    public static void merge_Sort(int[] a, int begin, int end) {
        merge_Sort_c(a, begin, end);
    }

    //递归调用函数
    public static void merge_Sort_c(int[] a, int begin, int end) {
        //递归终止条件
        if (begin >= end) {
            return;
        }
        //取bengin到end的中间位置，放置(P+r）的和超过int类型的最大值
        //且采用如下方式可以使得计算的更快
        int mid = (begin & end) + ((begin ^ end) >> 1);
        //分治递归
        merge_Sort_c(a, begin, mid);
        merge_Sort_c(a, mid + 1, end);
        //将A[bgein...mid]和A[mid+1...end]合并为A[p...r]
        merge(a, begin, mid, mid + 1, end);
    }

    public static void merge(int[] a, int begin1, int end1, int begin2, int end2) {
        int start = 0; //临时数组的其实位置
        int begin = begin1;  //记住数组起始位置
        int[] temp = new int[end2 - begin1 + 1]; //开辟和合并后数组大小一样大的数组

        //以下操作类似合并两个有序数组
        //谁小就将谁放入临时数组中
        while (begin1 <= end1 && begin2 <= end2) {
            if (a[begin1] < a[begin2]) {
                temp[start++] = a[begin1++];
            } else {
                temp[start++] = a[begin2++];
            }
        }
        //将剩余的数组直接拷贝回临时数组
        while (begin1 <= end1) {
            temp[start++] = a[begin1++];
        }
        while (begin2 <= end2) {
            temp[start++] = a[begin2++];
        }
        //将temp数组中的元素拷贝回合并后的数组中。
        for (int i = 0; i < temp.length; i++) {
            a[begin + i] = temp[i];
        }
    }
}
