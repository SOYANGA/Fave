package BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: LeetCode
 * @Description: 102. Binary Tree Level Order Traversal
 * @Author: SOYANGA
 * @Create: 2019-06-03 00:57
 * @Version 1.0
 */
public class LevelOrderTraversal {

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> resList = new ArrayList();
            if (root == null) {
                return resList;
            }
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                resList.add(new ArrayList<Integer>());
                int level_length = queue.size();
                //将当前层中的所有元素加入队列中
                while (level_length > 0) {
                    TreeNode front = queue.remove();
                    resList.get(level).add(front.val);
                    if (front.left != null) {
                        queue.add(front.left);
                    }
                    if (front.right != null) {
                        queue.add(front.right);
                    }
                    level_length--;
                }
                level++;
            }
            return resList;
        }
    }
}
