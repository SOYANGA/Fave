package Day_48;

/**
 * @program: EveryDayTest
 * @Description: 顺时针打印矩阵  
 * @Author: SOYANGA
 * @Create: 2019-04-29 17:58
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        int[][] mat = new int[][]{{1, 2}, {3, 4}};
        int[] result = clockwisePrint(mat, 2, 2);
        for (int i : result) {
            System.out.print(i);
        }
    }

    public static int[] clockwisePrint(int[][] mat, int n, int m) {
        int startX = 0;
        int endX = m - 1;
        int startY = 0;
        int endY = n - 1;
        int index = 0;
        int[] result = new int[n * m];
        while (startX <= endX && startY <= endY) {
            // 从左到右打印
            if (startX <= endX) {
                for (int i = startX; i <= endX; i++) {
                    result[index++] = mat[startY][i];
                }
            }
            // 从上往下打印
            if (startY < endY) {
                for (int i = startY + 1; i <= endY; i++) {
                    result[index++] = mat[i][endX];
                }
            }
            // 从右往左打印
            if (startX < endX && endY > startY) {
                for (int i = endX - 1; i >= startX; i--) {
                    result[index++] = mat[endY][i];
                }
            }
            // 从下往上打印
            if (startY < endY && endX > startX) {
                for (int i = endY - 1; i >= startY + 1; i--) {
                    result[index++] = mat[i][startX];
                }
            }
            startX++;
            endX--;
            startY++;
            endY--;
        }
        return result;
    }
}

