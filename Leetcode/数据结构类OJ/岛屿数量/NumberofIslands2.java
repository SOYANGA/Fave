package Number_of_Islands;


/**
 * @program: LeetCode
 * @Description: 200. 岛屿数量 dfs+染色
 * @Author: SOYANGA
 * @Create: 2019-06-30 18:23
 * @Version 1.0
 */
public class NumberofIslands2 {
    class Solution {
        public int[] dx = new int[]{-1, 1, 0, 0};//方向数组
        public int[] dy = new int[]{0, 0, -1, 1};//反向数组
        public boolean[][] visited = null; //非侵入式做法，防止对grid原数组进行修改

        public int numIslands(char[][] grid) {
            int n = grid.length;
            if (n == 0) {
                return 0;
            }
            int m = grid[0].length;
            int count = 0;
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        dfsMarking(grid, i, j);//dfs+染色
                        count++;
                    }
                }
            }
            return count;
        }

        public void dfsMarking(char[][] grid, int i, int j) {
            if (!isValid(grid, i, j)) {
                return;
            }
            visited[i][j] = true;
            for (int k = 0; k <= 3; k++) {
                dfsMarking(grid, i + dx[k], j + dy[k]);
            }
        }

        /**
         *判断grid[i][j] 是否满足要求
         * @param grid
         * @param i
         * @param j
         * @return
         */
        public boolean isValid(char[][] grid, int i, int j) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||
                    grid[i][j] != '1' || visited[i][j]) {
                return false;
            }
            return true;
        }
    }
}
