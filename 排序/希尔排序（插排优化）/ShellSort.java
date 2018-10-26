import com.sun.glass.ui.Size;

public class ShellSort {
    public static void main(String[] args) {
        int[] arrys = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        int size = arrys.length;
        ShellSort(arrys, size);
        Printarrys(arrys);
    }

    public static void Swap(int[] a, int x, int y) {
        assert (a != null);
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static void Printarrys(int[] arrys) {
        for (int temp : arrys) {
            System.out.print(temp + ",");
        }
        System.out.println();
    }

    public static void ShellSort(int[] arr, int sz) {
        assert (arr != null);
        int gap = 0;
        int i = 0;
        int j = 0;
        // gap 逐渐递减
        for (gap = sz / 2; gap > 0; gap /= 2) {
            // 按照gap值遍历数组
            for (i = gap; i < sz; i++) {
                // 确定要比较的的相邻（gap）值的两个元素 符合规则大的在后小的在前
                for (j = i - gap; j >= 0 && arr[j] > arr[j + gap]; j -= gap) {
                    Swap(arr, j, j + gap);
                }
            }
        }

    }
}
/**
 * 希尔排序 是插入排序的一种又称 "缩小增量排序"（Diminishing Increment Sort）
 * 使用简单的希尔增量序列，复杂的增量序列中Sedgewick 增量序列最坏时间复杂度达到O(n^1.3)
 * 时间复杂度取决于增量序列，空间复杂度O(1)，不稳定！！！
 * 
 * 适用场合：数据量较大，不要求稳定性
 *
 * 
 * 最坏时间复杂度：O(N^2) 平均时间复杂度：O(N^1.3) 最好时间复杂度：O(N)
 * 空间复杂度：O(1) 
 * 不稳定排序 
 */

 