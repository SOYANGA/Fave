package BinaryTreeMirror;

import java.util.Stack;

/**
 * @program: TSRTOffer
 * @Description: 27 二叉树的镜像 递归
 * @Author: SOYANGA
 * @Create: 2019-07-12 00:10
 * @Version 1.0
 */
public class Solution {

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
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            //交换左右子节点
            if (node.left != null || node.right != null) {
                TreeNode tempNode = node.left;
                node.left = node.right;
                node.right = tempNode;
            }
            //讲左右子节点不为空的节点压入栈中
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
    }
}

