package Validate_Binary_Search_Tree;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @program: LeetCode
 * @Description: 98. Validate Binary Search Tree 验证二叉搜索树
 * @Author: SOYANGA
 * @Create: 2019-05-30 00:10
 * @Version 1.0
 */

/*
    1.中序遍历 判断升序即可
    2.中序遍历变形，在递归中判断当前节点大于前驱节点即可
    3.优化不需要遍历完整的数，只需要判断当前大于左子树最大节点，小于右子树最小节点
 */
public class ValidateBinarySearchTree {


    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {

        //1.
        public boolean isValidBST1(TreeNode root) {
            LinkedList<Integer> list = new LinkedList<>();
            inorderPro(root, list);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1) >= list.get(i)) {
                    return false;
                }
            }
            return true;
        }


        public void inorder(TreeNode node, LinkedList list) {
            if (node == null) {
                return;
            }
            inorder(node.left, list);
            list.add(node.val);
            inorder(node.right, list);
        }


        public void inorderPro(TreeNode node, LinkedList list) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || node != null) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
    }
}
