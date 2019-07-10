package BinaryTreeMirror;

import java.util.Stack;

/**
 * @program: TSRTOffer
 * @Description: 27 二叉树的镜像 递归
 * @Author: SOYANGA
 * @Create: 2019-07-12 00:10
 * @Version 1.0
 */
public class Solution2 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        swap(root);
        Mirror(root.left);
        Mirror(root.right);
    }

    private void swap(TreeNode root) {
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }
}

