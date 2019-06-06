package SudokuSolver;

/**
 * @program: LeetCode
 * @Description: 37. 解数独 DFS+剪枝+回溯+3*3 单元格判断方法
 * @Author: SOYANGA
 * @Create: 2019-06-06 01:36
 * @Version 1.0
 */
public class SudokuSolver {
    class Solution {
        public void solveSudoku(char[][] board) {
            if(board==null||board.length==0){
                return;
            }
            slove(board);
        }
        public boolean slove(char[][] board){
            for(int i = 0; i <9;i++){
                for(int j = 0;j<9;j++){
                    if(board[i][j] == '.'){
                        for(char c = '1'; c<='9';c++){
                            if(isValid(board,i,j,c)){
                                board[i][j] = c;
                                //如果后序填入的数不和法就会恢复到填着这个数前的现场，重新选择一个数进行填充，反之就                              会一直填下去直到，返回填完最后一个数返回true ，则递归层层结束，最后返回true。
                                if(slove(board)){
                                    return true;
                                }else{
                                    board[i][j] = '.';
                                }
                            }
                        }
                        //遍历完所有的数都不合法，则上次填充的数有问题，返回false，让上次的数恢复现场并且重新填充
                        return false;
                    }
                }
            }
            return true;
        }

        //判断填入的数 是否合法
        public boolean isValid(char[][ ]board,int row,int col,char c){
            for(int i = 0;i<9;i++){
                if(board[i][col]!='.'&&board[i][col] == c) {
                    return false;
                }
                if(board[row][i]!='.'&&board[row][i] == c) {
                    return false;
                }
                if(board[row/3*3+i/3][col/3*3+i%3]!='.'&&
                        board[row/3*3+i/3][col/3*3+i%3]==c){
                    return false;
                }
            }
            return true;
        }
    }
}
