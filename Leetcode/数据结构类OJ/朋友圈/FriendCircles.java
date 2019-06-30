package Friend_Circles;

/**
 * @program: LeetCode
 * @Description: 547. 朋友圈 dfs+染色
 * @Author: SOYANGA
 * @Create: 2019-06-30 19:05
 * @Version 1.0
 */
public class FriendCircles {
    class Solution {
        /**
         * 使用一个flag数组, 依次判断每个节点, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点
         */
        public boolean[] flag;

        public int findCircleNum(int[][] M) {
            int n = M.length;
            if (n == 0) {
                return 0;
            }
            flag = new boolean[n];
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!flag[i]) {
                    dfsMarking(M, i);
                    count++;
                }
            }
            return count;
        }

        public void dfsMarking(int[][] M, int from) {
            for (int to = 0; to < M.length; to++) {
                if (!flag[to] && M[from][to] == 1) {
                    flag[from] = true;
                    dfsMarking(M, to);
                }
            }
        }
    }
}
/*
### 解法一：dfs+染色 类似于岛屿数量 / bfs+染色
使用一个visited数组, 依次判断每个节点, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点

### 解法二：并查集
将相邻为1的点合并为一个父节点，最终统计父节点的个数
 */
