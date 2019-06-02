package BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: LeetCode
 * @Description: 94. Binary Tree Inorder Traversal
 * @Author: SOYANGA
 * @Create: 2019-06-03 01:10
 * @Version 1.0
 */
public class Inorder {
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList();
            Stack<TreeNode> stack = new Stack();
            while(!stack.isEmpty()||root!=null){
                while(root!=null){
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
            return list;
        }
    }
}

