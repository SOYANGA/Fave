package QuickSortOp;


//快排优化
//引入三数取中(优化快排)
//1.影响快排得时间复杂度主要因素就是分区节点的选取，可以避免极端化的分区节点导致排序效率降低

//2.快排多用于处理数据量大的数据，对于数据量大的数据，影响快排性能的主要因素其实就是快排的递归深度。
//当快排递归分区到一定程度(分区小数据的元素时，此时就可以使用插排来进行排序减少快排递归时树的深度)

//插排也可以优化，希尔排序


import java.util.Random;

import MergeSort.MergeSort;
import SelectionSort.*;

/**
 * @program: Sort
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-12 16:55
 * @Version 1.0
 */

public class QuickSortOp {
    public static void main(String[] args) {

//        int[] num = new int[]{0, 8, 5, 9, 2, 7, 6, 3, 1, 4};
        //生成0-100000 的随机数，且生成100000个随随机数
        int[] num = new int[100000];
        Random random = new Random(100000);
        for (int i = 0; i < 100000; i++) {
            num[i] = (random.nextInt(100001) + 0);
        }

        //计算运行时间
        long satrtTime = System.currentTimeMillis();

        quickSortOP(num, 0, 99999);
//        insertSort(num, 0, 99999);
//        shellSort(num,0,99999);
//        SelectionSort.selectionSort(num, 100000);
//        SelectionSort.selectionSort2(num, 100000);
//        MergeSort.merge_Sort(num,0,99999);
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - satrtTime) + "ms");
//        for (int temp : num) {
//            System.out.print(temp + ",");
//        }
    }

    public static void quickSortOP(int[] a, int begin, int end) {
        quickSortInternallyOP(a, begin, end);

    }

    //快排递归函数，begin,end为下标 div为分区节点，下一次分区就不包含此分区节点
    private static void quickSortInternallyOP(int[] a, int begin, int end) {
        if (begin >= end) {
            return;
        }
        if (end - begin + 1 < 5) {  //小数据添加插排
            insertSort(a, begin, end);
        } else {
            int div = partition(a, begin, end);
            quickSortInternallyOP(a, begin, div - 1);
            quickSortInternallyOP(a, div + 1, end);
        }
    }

    /**
     * @param a     要分区的数组
     * @param begin 分区数组的开始
     * @param end   分区数组的结束
     * @return 返回为分区节点
     */

    private static int partition(int[] a, int begin, int end) {
        int mid = getMidIndex(a, begin, end); //三数取中
        swap(a, mid, end);
        int pivot = a[end];

        int i = begin;      //i当作“已排好序节点”
        for (int j = begin; j <= end; j++) {
            if (a[j] < pivot) { //比分区节点小的元素
                if (i == j) {   //有序性区无元素，或者已经有序
                    ++i;
                } else {
                    swap(a, i, j); //将无序区的元素交换到有序区后面，有序区+1，无序区-1
                    i++;
                }
            }
        }
        swap(a, i, end);

        return i;
    }

    /**
     * @param a 目标数组
     * @param i 要进行交换的下标
     * @param j 要进行交换的下标
     */
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    //快排优化***********************

    /**
     * 用于快排优化
     *
     * @param a     小数组
     * @param begin 目标数组的起始
     * @param end   目标数数组的结束
     */
    private static void insertSort(int[] a, int begin, int end) {
        if ((end - begin + 1) <= 1) {
            return;
        }
        for (int i = begin; i <= end; i++) {
            int value = a[i];  //要插入适当位置的值
            int j = i - 1; //有序区
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }


    /**
     * 用于快排优化
     *
     * @param a     小数组
     * @param begin 目标数组的起始
     * @param end   目标数数组的结束
     */
    private static void shellSort(int[] a, int begin, int end) {
        if ((end - begin + 1) <= 1) {
            return;
        }
        int n = end - begin + 1;
        int i = 0;
        int j = 0;
        int gap = 0;
        for (gap = (n / 2); gap > 0; gap /= 2) {
            for (i = gap; i < n; i++) {
                for (j = i - gap; j >= 0 && a[j] > a[j + gap]; j -= gap) {
                    swap(a, j, j + gap);
                }
            }
        }
    }


    //三数取中 目的使快拍更均匀
    private static int getMidIndex(int[] a, int begin, int end) {
        int mid = (begin & end) + ((begin ^ end) >> 1);//防止数据过大导致int放不下
        if (a[begin] < a[mid]) {
            if (a[begin] > a[end]) {
                return begin;
            }
            if (a[mid] < a[end]) {
                return mid;
            } else { //if(a[mid]>a[end]&&a[end]>a[begin])
                return end;
            }
        } else { //if(a[begin]>=a[mid]
            if (a[begin] < a[end]) {
                return begin;
            }
            if (a[mid] > a[end]) {
                return mid;
            } else { //if(a[end]>a[mid]&&a[end]<a[bgein]
                return end;
            }
        }
    }
}
