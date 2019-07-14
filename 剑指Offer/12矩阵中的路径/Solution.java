package hasPath;

/**
 * @program: TSRTOffer
 * @Description: 12 矩阵中的路径
 * @Author: SOYANGA
 * @Create: 2019-07-13 16:49
 * @Version 1.0
 */
public class Solution {
    /**
     * 方向数组用于控制递归的遍历
     */
    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    /**
     * 行和列（全局变量）
     */
    private int rows;
    private int cols;


    public boolean hasPath(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) {
            return false;
        }
        this.cols = cols;
        this.rows = rows;
        boolean[][] marked = new boolean[rows][cols];
        //重构数组为二维数组
        char[][] matrix = buildMatrix(array);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, str, marked, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] matrix, char[] str, boolean[][] marked, int word_index, int r, int c) {
        if (word_index == str.length) {
            return true;
        }
        if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] != str[word_index] || marked[r][c]) {
            return false;
        }
        marked[r][c] = true;
        for (int[] n : next) {
            if (dfs(matrix, str, marked, word_index + 1, r + n[0], c + n[1])) {
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }

    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];
        for (int r = 0, idx = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = array[idx++];
            }
        }
        return matrix;
    }


}