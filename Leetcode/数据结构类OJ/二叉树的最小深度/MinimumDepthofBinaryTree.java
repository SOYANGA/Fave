package MinimumDepthofBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: LeetCode
 * @Description: 111. 二叉树的最小深度
 * @Author: SOYANGA
 * @Create: 2019-06-04 22:27
 * @Version 1.0
 */
public class MinimumDepthofBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //1. BFS

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int len = queue.size();
            while ((len--) > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return level;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return level;
    }

    //2.DFS
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
        if (root.left != null) {
            return minDepth(root.left) + 1;
        }

        if (root.right != null) {
            return minDepth(root.right) + 1;
        }
        return 0;
    }


}
