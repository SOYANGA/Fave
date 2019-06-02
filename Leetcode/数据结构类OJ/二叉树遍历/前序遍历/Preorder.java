package BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: LeetCode
 * @Description: 144. Binary Tree Preorder Traversal
 * @Author: SOYANGA
 * @Create: 2019-06-03 01:10
 * @Version 1.0
 */
public class Preorder {

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

        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList();
            Stack<TreeNode> stack = new Stack();
            while(!stack.isEmpty()||root!=null){
                while(root!=null){
                    stack.push(root);
                    list.add(root.val);
                    root = root.left;
                }
                root = stack.pop();
                root = root.right;
            }
            return list;
        }
    }
}
