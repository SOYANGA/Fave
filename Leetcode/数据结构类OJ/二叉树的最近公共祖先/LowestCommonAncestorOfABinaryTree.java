package Lowest_Common_Ancestor_of_a_Binary_Tree;

/**
 * @program: LeetCode
 * @Description: 236. 二叉的最近公共祖先
 * @Author: SOYANGA
 * @Create: 2019-05-30 23:11
 * @Version 1.0
 */
public class LowestCommonAncestorOfABinaryTree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
//在左右子树 分别遍历
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == q || root == p || root == null) {
                return root;
            }
            //在子树下遍历
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // 如果p,q分别在root的左右子树中，则返回root
            // 如果p,q均在root的左子树中，则返回root的左子树
            // 如果p,q均在root的右子树中，则返回root的右子树
            // 如果p,q均不在root中，则返回null
            return left == null ? right : right == null ? left : root;
        }
    }
}
