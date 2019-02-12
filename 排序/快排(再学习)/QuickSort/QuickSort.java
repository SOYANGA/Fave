package QuickSort;

/**
 * @program: Sort
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-12 16:13
 * @Version 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] num = new int[]{0, 8, 5, 9, 2, 7, 6, 3, 1, 4};
        quickSort(num, 0, 9);
        for (int temp : num) {
            System.out.print(temp + ",");
        }
    }

    /**
     * @param a     :要排序数组
     * @param begin :数组开始下标
     * @param end   :数组结束下标
     */
    public static void quickSort(int[] a, int begin, int end) {
        quickSortInternally(a, begin, end);

    }

    //快排递归函数，begin,end为下标 div为分区节点，下一次分区就不包含此分区节点
    private static void quickSortInternally(int[] a, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int div = partition(a, begin, end);
        quickSortInternally(a, begin, div - 1);
        quickSortInternally(a, div + 1, end);

    }

    /**
     * @param a     要分区的数组
     * @param begin 分区数组的开始
     * @param end   分区数组的结束
     * @return 返回为分区节点
     */

    private static int partition(int[] a, int begin, int end) {
        int pivot = a[end];
        int i = begin;      //i当作“已排好序节点”
        for (int j = begin; j < end; j++) {
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

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
