package LastRemaining_Solution;

/**
 * @program: TSRTOffer
 * @Description: 62圆圈中最后剩下的数 约瑟夫环问题 递归 /非递归
 * @Author: SOYANGA
 * @Create: 2019-08-12 23:40
 * @Version 1.0
 */
public class Solution {
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0) {
            return -1;
        }
        if (n == 1) { //递归出口
            return 0;
        }
        return (LastRemaining_Solution(n - 1, m) + m) % n ;
    }
    //非递归
    public int LastRemaining_Solution2(int n, int m) {
        if (n == 0) {
            return -1;
        }
        int result = 0; //n =1 时的解
        for (int i = 2; i <= n; i++) {
            result = (result + m) % i; //f[i] = (f[i-1] + m )% i (i>1)
        }
        return result;
    }

}
/*
该题标号是从0开始的，符合数组的下标。反之编号是从1开始的，则需要将result + 1即可
（大数）旧环中的数字 = ( （小数目）新环中的数字 + 最大报数值 ) %旧总人数
旧环-新环个数 = 1；
f[i] = (f[i-1] + m )% i (i>1)
f[1] = 0;   (i = 1);

则对于递归来说

F[n,m] = (F(n-1,m) + m)% n


算法数学推导讲解
https://blog.csdn.net/yanweibujian/article/details/50876631
https://blog.csdn.net/wusuopubupt/article/details/18214999
https://blog.csdn.net/u011500062/article/details/72855826
*/
