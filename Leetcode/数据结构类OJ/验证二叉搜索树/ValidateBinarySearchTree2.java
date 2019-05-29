package Validate_Binary_Search_Tree;

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
public class ValidateBinarySearchTree2 {


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

        //2.
        //上一个节点的值
        Integer last = Integer.MIN_VALUE;

        public boolean isValidBST2(TreeNode root) {
            if (root == null) {
                return true;
            }
            //从左子树的最左节点开始（list中的第一个节点开始）
            if (isValidBST2(root.left)) {
                //升序 当前节点大于中序遍历中的前前驱节点
                if (last < root.val) {
                    last = root.val;
                }
                //判断右子树
                return isValidBST2(root.right);
            }
            return false;
        }
    }
}
