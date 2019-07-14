package robotMovingCount;

/**
 * @program: TSRTOffer
 * @Description: 13 机器人的运动范围 dfs + 简单处理
 * @Author: SOYANGA
 * @Create: 2019-07-13 17:27
 * @Version 1.0
 */
public class Solution {

    /**
     * 方向数组，用于遍历
     */
    private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    /**
     * 全局的计数器哦
     */
    private int cnt = 0;
    /**
     * 行列
     */
    private int rows;
    private int cols;
    /**
     * 表示给定值（行+列的每一位不能超过的值）
     */
    private int threshold;
    /**
     * 初始化将矩阵中每个结点得值变为 行列得每一位之和
     */
    private int[][] difitSum;

    public int movingCount(int threshold, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        //初始化矩阵中得值，将其变为行列得每一位得之和
        initDigitSum();
        boolean[][] marked = new boolean[rows][cols];
        dfs(marked, 0, 0);
        return cnt;
    }

    private void dfs(boolean[][] marked, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || marked[r][c]) {
            return;
        }
        marked[r][c] = true;
        if (this.difitSum[r][c] > this.threshold) {
            return;
        }
        cnt++;
        for (int[] n : next) {
            dfs(marked, r + n[0], c + n[1]);
        }
    }

    /**
     * 初始化矩阵中得值，将其变为行列得每一位得之和
     */
    private void initDigitSum() {
        //行列中得值都是自然数，选择大得一个得值，可以囊括小的一方得值
        int[] digitSumOne = new int[Math.max(rows, cols)];

        for (int i = 0; i < digitSumOne.length; i++) {
            int n = i; //行或者列对应得值
            //求行或者列得每位之和
            while (n > 0) {
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        this.difitSum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.difitSum[i][j] = digitSumOne[i] + digitSumOne[j];
            }
        }
    }
}
