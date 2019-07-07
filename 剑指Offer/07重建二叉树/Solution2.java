package ConstructBinaryTree;

import java.util.Arrays;

/**
 * @program: TSRTOffer
 * @Description:7 重建二叉树 递归
 * @Author: SOYANGA
 * @Create: 2019-07-12 00:38
 * @Version 1.0
 * <p>
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
//由于本题有一个特点就是没有重复元素所以，就可以使用记忆化+递归实现简化流程，减少递归的冗余


public class Solution2 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0 || pre == null || in == null) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]); //构建每一个根节点
        //分成左右两颗子树进行拆分，在进行相同的操作，最终构建完成整棵树 直接使用Arrays.copyOfRange方法[)左闭右开的
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }
}