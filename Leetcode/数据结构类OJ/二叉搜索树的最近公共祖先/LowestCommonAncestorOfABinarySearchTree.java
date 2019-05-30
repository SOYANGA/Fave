package Lowest_Common_Ancestor_of_a_Binary_Search_Tree;

/**
 * @program: LeetCode
 * @Description: 235. 二叉搜索树的最近公共祖先
 * @Author: SOYANGA
 * @Create: 2019-05-30 23:07
 * @Version 1.0
 */
public class LowestCommonAncestorOfABinarySearchTree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     * (p.val<root.val && q.val<root.val
     * p.val>root.val && q.val>root.val
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //利用二叉搜索树性质，可以判断出最近公共祖先在那个子树上，进而缩小祖先范围 公共祖先一定是小于大的，大于小的
    class Solution {

        //非递归
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            while (root != null) {
                if (p.val < root.val && q.val < root.val) {
                    root = root.left;
                } else if (p.val > root.val && q.val > root.val) {
                    root = root.right;
                } else {
                    return root;
                }
            }
            return root;
        }

        //递归写法
        public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
            if (p.val < root.val && q.val < root.val) {
                return lowestCommonAncestor(root.left, p, q);
            }
            if (p.val > root.val && q.val > root.val) {
                return lowestCommonAncestor(root.right, p, q);
            }
            return root;
        }
    }
}
