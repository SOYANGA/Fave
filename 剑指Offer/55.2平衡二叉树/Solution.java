package IsBalanced_Solution;

/**
 * @program: TSRTOffer
 * @Description: 55.2平衡二叉树 dfs
 * @Author: SOYANGA
 * @Create: 2019-08-02 01:01
 * @Version 1.0
 */
public class Solution {
    private boolean isPass = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        getDepth(root);
        return isPass;
    }

    public int getDepth(TreeNode node) {
        if (node == null || !isPass) {
            return 0;
        }
        int left = getDepth(node.left); //左树的最大深度
        int right = getDepth(node.right);//右数的最大深度
        if (left - right > 1 || left - right < -1) { //判断
            isPass = false;
        }
        return left > right ? left + 1 : right + 1; //返回左树或者右数的最大高度；
    }

    private class TreeNode {
        TreeNode left;
        TreeNode right;
    }
}