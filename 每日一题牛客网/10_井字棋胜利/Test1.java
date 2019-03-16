package Day_10;

import java.util.ArrayList;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-13 22:12
 * @Version 1.0
 */

//方法1：暴力笨办法
public class Test1 {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 0, 1}, {1, -1, -1}, {1, -1, 0}
        };
        System.out.println(checkWon(a));
    }

    public static boolean checkWon(int[][] board) {
        for (int i = 0; i < 3; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                str.append("" + board[i][j]);
            }
            if (str.toString().equals("111")) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                str.append("" + board[i][j]);
            }
            if (str.toString().equals("111")) {
                return true;
            }
        }
        StringBuilder str1 = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            str1.append(board[i][i]);
        }
        if (str1.toString().equals("111")) {
            return true;
        }

        StringBuilder str2 = new StringBuilder();
        for (int i = 2; i >= 0; i--) {
            str2.append(board[i][i]);
        }
        if (str2.toString().equals("111")) {
            return true;
        }

        return false;
    }
}

//方法2： 遍历棋盘所有棋子 计算总值，大于一，则胜利，反之失败
//    public boolean checkWon(int[][] board) {
//        int count = 0;
//        for(int i = 0; i < 3 ;i++ ){
//            for(int j = 0; j < 3 ;j++){
//                count += board[i][j];
//            }
//        }
//        if(count==1){
//            return true;
//        }else{
//            return false;
//        }
//    }