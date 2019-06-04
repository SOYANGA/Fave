package MaximumDepthofBinaryTree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: LeetCode
 * @Description: 104. 二叉树的最大深度
 * @Author: SOYANGA
 * @Create: 2019-06-04 22:30
 * @Version 1.0
 */
public class MaximumDepthofBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //1. DFS
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = maxDepth(root.left);
        int rDepth = maxDepth(root.right);
        return lDepth > rDepth ? lDepth + 1 : rDepth + 1;
    }

    //2. BFS
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList();
        int level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int level_size = queue.size();
            while (level_size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                level_size--;
            }
            level++;
        }
        return level;
    }


}
