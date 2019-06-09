package FindWords;

import DailyTemperatures.Solution;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

/**
 * @program: LeetCode
 * @Description: 79. 单词搜索
 * @Author: SOYANGA
 * @Create: 2019-06-09 23:36
 * @Version 1.0
 */
public class FindWords {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(solution.exist(board, "ABCCED"));
    }

    static class Solution {
        char[][] board;
        String word;
        //设置数组判断这个位置是否走过。
        boolean[][] flag;

        public boolean exist(char[][] board, String word) {
            this.board = board;
            this.word = word;
            flag = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, int i, int j, int word_index) {
            if (word_index == word.length()) {
                return true;
            }
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || flag[i][j] || board[i][j] != word.charAt(word_index)) {
                return false;
            }
            char c = board[i][j];
            flag[i][j] = true;
            boolean result = dfs(board, i, j - 1, word_index + 1) ||
                    dfs(board, i, j + 1, word_index + 1) ||
                    dfs(board, i - 1, j, word_index + 1) ||
                    dfs(board, i + 1, j, word_index + 1);
            flag[i][j] = false;
            return result;
        }
    }
}
/*
 dfs+剪枝
 版本一：用String去比较，equls（），contains（）方法 耗时较久 导致超出时间限制
 版本二：优化使用char去比较，利用charAt(index)去确定words中的字符，进行比较，大大减少时间
 版本三：优化 判重节点方法 之间是 利用单独开辟一个boolean\[]\[]数组与board对应。
								直接在board上修改即可。（减少可读性，但是优化了性能）；
 */