package Day_39;

/**
 * @program: EveryDayTest
 * @Description: 二维数组打印
 * @Author: SOYANGA
 * @Create: 2019-04-19 23:31
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        int[][] arrays = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[] re = arrayPrint2(arrays, 4);

    }

    public static int[] arrayPrint(int[][] arr, int n) {
        int[] result = new int[n * n];
        int row = 0;
        int low = 3;
        int index = 0;
        while (row < n) {
            int x = row;
            int y = low;
            while (x < n && y < n) {
                result[index++] = arr[x++][y++];
            }
            if (low > 0) {
                low--;
            } else {
                row++;
            }
        }
        return result;
    }

    public static int[] arrayPrint2(int[][] arr, int n) {
        int[] result = new int[n * n];
        int index = 0;

        for (int y = n - 1; y >= 0; y--) {
            int k = y;
            for (int x = 0; x < n - y; x++) {
                result[index++] = arr[x][k++];
            }
        }
        for (int x = 1; x < n; x++) {
            int k = x;
            for (int y = 0; y < n - x; y++) {
                result[index++] = arr[k++][y];
            }
        }
        return result;
    }

}
