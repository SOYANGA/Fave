package MyHeap;



/*
    堆的底层数组中下标从1开始存储---虽然浪费了一个空间，但是少了很多的"+"操作如下
    下标为i的节点的
    左节点就是 i*2的节点
    右节点就是下标为 i*2+1的系欸但，
    父节点就是下标为i/2的节点
    如果一个数没有左孩子则 2*i<=n
    没有右节点 2*i+1<=n

    当底层数组从数组开始存储数据则
    下标为i的节点的
    左节点就是 2*i+1
    右节点就是 i*2+2
    父节点就是下标为i/2的节点
    如果一个数没有左孩子则 2*i+1<=n
    没有右节点 2*i+2<=n

    少了一个加法的运算

 */

import javafx.geometry.VerticalDirection;

/**
 * @program: DataStruct
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-18 17:18
 * @Version 1.0
 */
public class MyHeap {
    private int[] a;//数组，下标从1开始的存储数据
    private int n;//堆中可以存储的最大数据个数
    private int count;//堆中已经存储的数据个数

    public MyHeap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    private static void buildHeap(int[] a, int n) {  //构建堆
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    private static void heapify(int[] a, int n, int i) {//自上往下堆化
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }


    public void insert(int data) {
        if (count >= n) {
            System.out.println("堆已经满了");
            return;   //堆已经满了
        }
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) { //在下往上树化
            swap(a, i, i / 2); //交换
            i = i / 2;
        }
    }


    public void removeMax() {
        if (count == 0) {
            System.out.println("堆中没有数据");
            return;
        }
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }


    private static void swap(int[] a, int i1, int i2) {
        int temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }

    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{0, 7, 5, 19, 8, 4, 1, 20, 13, 16};
        sort(a, 9);
        for (int i : a) {
            System.out.print(i + " ");
        }

    }
}
