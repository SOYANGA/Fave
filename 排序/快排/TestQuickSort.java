import java.util.Arrays;

public class TestQuickSort {
    public static void main(String[] args) {
        int[] temp = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, 99 };
        Sort quicksort = new Sort(temp);
        int left = 0;
        int right = temp.length - 1;
        // quicksort.setArr(temp);
        quicksort.QuickSort(temp, left, right);
        quicksort.printArr();
    }
}

class Sort {
    private int[] arr;

    public Sort(int[] temp) {
        this.arr = temp;
    }

    public void setArr(int[] temp) {
        this.arr = temp;
    }

    public int[] getArr() {
        return this.arr;
    }

    public void InsertSort(int[] a, int left, int right) { // 插排
        int temp = 0;
        int i = 0;
        int j = 0;
        for (i = left + 1; i <= right; i++) {
            temp = a[i];
            j = i - 1;
            while (j >= 0 && a[j] > temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }

    // public void InsertSort(int[] a) { // 插排
    // int temp = 0;
    // int i = 0;
    // int j = 0;
    // for (i = 1; i < a.length; i++) {
    // temp = a[i];
    // j = i - 1;
    // while (j >= 0 && a[j] > temp) {
    // a[j + 1] = a[j];
    // j--;
    // }
    // a[j + 1] = temp;
    // }
    // }

    public void Swap(int[] a1, int index1, int[] a2, int index2) {
        int temp = a1[index1];
        a1[index1] = a2[index2];
        a2[index2] = temp;
    }

    public void printArr() {
        for (int temp : arr) {
            System.out.print(temp + ",");
        }
        System.out.println();
    }

    public int Getmidindex(int[] a, int begin, int end) { // 三数取中
        int mid = begin + ((end - begin) >> 1);
        if (a[begin] < a[mid]) {
            if (a[begin] > a[end]) {
                return begin;
            }
            if (a[mid] < a[end]) {
                return mid;
            } else {
                return end;
            }
        } else { // if(a[mid]<a[begin])
            if (a[begin] < a[end]) {
                return begin;
            }
            if (a[end] < a[mid]) {
                return mid;
            } else {
                return end;
            }
        }
    }

    public int PartSort1(int[] a, int begin, int end) {
        int mid = this.Getmidindex(a, begin, end);
        // int n = a[mid];
        // a[mid] = a[end];
        // a[end] = n;
        this.Swap(a, mid, a, end);
        int key = a[end];
        int last = end;
        while (begin < last) {
            while (begin < last && a[begin] <= key) { // 左边找比key大的
                begin++;
            }
            while (begin < last && a[last] >= key) { // 右边找比key小的
                last--;
            }
            // int n = a[begin];
            // a[begin] = a[last];
            // a[last] = n;
            this.Swap(a, begin, a, last);

        }
        // int n = a[begin];
        // a[begin] = a[end];
        // a[end] = n;
        this.Swap(a, begin, a, end);
        return begin;

    }

    public void QuickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        if (right - left + 1 < 10) {
            // int[] temp = new int[right - left + 1];
            // System.arraycopy(a, left, temp, 0, right - left + 1);
            // this.InsertSort(temp);
            // System.arraycopy(temp, 0, a, left, right - left + 1);
            this.InsertSort(a, left, right);
        } else {
            int div = PartSort1(a, left, right);
            this.QuickSort(a, right, div - 1);
            this.QuickSort(a, div + 1, right);
        }
    }

}