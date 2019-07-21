package VerifySquenceOfBST;

/**
 * @program: TSRTOffer
 * @Description: 33二叉搜索树的后序遍历序列
 * @Author: SOYANGA
 * @Create: 2019-07-22 23:52
 * @Version 1.0
 */
public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1) {
            return true;
        }
        return verify(sequence,0,sequence.length-1);
    }

    private boolean verify(int[] sequence, int first, int last) {
        if (first >= last) { //因为需要构成左右根树
            return true;
        }
        int rootVal = sequence[last];
        int cutIndex = first;
        //从头（左数）开始找到第一个大于rootval的结点，则为右树的结点 分隔左右树
        while(cutIndex < last && sequence[cutIndex] <= rootVal) {
            cutIndex++;//找到左右树的分割点
        }
        //遍历右树，需要满足均大于根节点，否则不满足二叉搜索树的后序遍历的特点
        for(int i = cutIndex; i < last; i++) {
            if(sequence[i] < rootVal) {
                return false;
            }
        }
        return verify(sequence, first, cutIndex-1) && verify(sequence, cutIndex+1, last);
    }
}
/*
分治思想+递归
*/
