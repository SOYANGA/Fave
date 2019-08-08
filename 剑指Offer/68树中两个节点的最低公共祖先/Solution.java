package lowestCommonAncestor;

/**
 * @program: TSRTOffer
 * @Description: 68最近公共祖先 递归bfs + 巧妙的处理返回值
 * @Author: SOYANGA
 * @Create: 2019-08-13 00:56
 * @Version 1.0
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == q || root == p || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q); //左树递归查找
        TreeNode right = lowestCommonAncestor(root.right, p, q); //右树递归查找
        return left == null ? right : right == null ? left : root; //判断返回的左右树进行判断，自下向上
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}