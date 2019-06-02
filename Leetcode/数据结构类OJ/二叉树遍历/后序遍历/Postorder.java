package BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCode
 * @Description: 145. Binary Tree Postorder Traversal
 * @Author: SOYANGA
 * @Create: 2019-06-03 01:11
 * @Version 1.0
 */
public class Postorder {

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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList();
            LinkedList<TreeNode> stack = new LinkedList();
            LinkedList<Integer> visitedState = new LinkedList<>();
            while(!stack.isEmpty()||root!=null){
                while(root!=null){
                    stack.push(root);
                    root = root.left;
                    visitedState.push(1);
                }
                while(!stack.isEmpty()&&visitedState.peek()==2){
                    visitedState.pop();
                    list.add(stack.pop().val);
                }
                if(!stack.isEmpty()){
                    root = stack.peek();
                    root = root.right;
                    visitedState.pop();
                    visitedState.push(2);
                }
            }
            return list;
        }
    }
}
