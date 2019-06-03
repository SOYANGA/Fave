package BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: LeetCode
 * @Description: 102. Binary Tree Level Order Traversal DFS
 * @Author: SOYANGA
 * @Create: 2019-06-03 00:57
 * @Version 1.0
 */
public class LevelOrderTraversal2 {

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
        List<List<Integer>> levels = new ArrayList<List<Integer>>();

        public void helper(TreeNode node, int level) {
            //levels.size() = 深度（当前树的)判断当前层数
            if (levels.size() == level) {
                levels.add(new ArrayList<Integer>());
            }
            //添加元素
            levels.get(level).add(node.val);

            if (node.left != null) {
                helper(node.left, level + 1);
            }
            if (node.right != null) {
                helper(node.right, level + 1);
            }

        }


        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return levels;
            }
            helper(root, 0);
            return levels;
        }
    }
}
