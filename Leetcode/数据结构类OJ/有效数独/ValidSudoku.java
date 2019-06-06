package ValidSudoku;

/**
 * @program: LeetCode
 * @Description: 36. 有效的数独
 * @Author: SOYANGA
 * @Create: 2019-06-05 23:56
 * @Version 1.0
 */
public class ValidSudoku {
    public static void main(String[] args) {
        int row = 7;
        int clo = 7;
        for (int i = 0; i < 9; i++) {
            int blockIndex = row / 3 * 3 ;
            int blockIndex2 = clo / 3 * 3 ;
            System.out.println(blockIndex+"+"+i/3+"--"+blockIndex2+"+"+i%3);
        }
    }

    class Solution {
        public boolean isValidSudoku(char[][] board) {
            boolean[][] row = new boolean[9][9];
            boolean[][] col = new boolean[9][9];
            boolean[][] block = new boolean[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1';
                        int blockIndex = i / 3 * 3 + j / 3;
                        if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                            return false;
                        } else {
                            row[i][num] = true;
                            col[j][num] = true;
                            block[blockIndex][num] = true;
                        }
                    }
                }
            }
            return true;
        }
    }
}
/*
循环遍历 需要有3个缓存
**1. 行**  `boolean[][] row = new boolean[9][9];`
**2. 列**  `boolean[][] col = new boolean[9][9];`
**3. 3 3小方块**  `boolean[][] block = new boolean[9][9];`

并将其中每每一位都映射进去，对应的位置，加入对应位置已经有数，则表示不是有效数独。反之则是。

构建映射
将表中数据'1'-'9' 转表为 0-8（数字）` int num = board[i][j] - '1';`
行 ： row[i][num]  i为表中行
列 ： col[j][num]   j为表中列
由于小方块是3 3 的所以要进行将 每个3 3小方块的数映射到一行上 则`int blockIndex = i / 3 * 3 + j / 3;`  `num`为列。
每次进行判断，如果没有进行填充，如果重复填充同一个位置则说明数独无效。

 */