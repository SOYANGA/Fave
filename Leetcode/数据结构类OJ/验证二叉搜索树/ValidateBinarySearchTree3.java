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
public class ValidateBinarySearchTree3 {


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

        //3.
        public boolean isValidBST3(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        /**
         * 对于每一个结点 都有一个对于他的最小值和最大值
         * 比如root节点 他必须大于Long.minvalue 小于Long.maxvalue
         * root.left 必须大于Long.minvalue 小于root
         * root.right 必须大于root 小于Long.maxvalue
         */
        private boolean isValidBST(TreeNode root, long minValue, long maxValue) {
            if (root == null) {
                return true;
            }
            //当前节点应该大于左子树最大值，小于右子树小值
            if (root.val >= maxValue || root.val <= minValue) {
                return false;
            }
            return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
        }
    }

}
