package Day_17;


import java.util.Stack;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-21 22:58
 * @Version 1.0
 */
public class Test1 {

}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}

class Solution2 {

    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null || node.right != null) {
                TreeNode pTemp = node.left;
                node.left = node.right;
                node.right = pTemp;
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
    }
}

//        TreeNode tmp = null;
//        if (root != null)
//        {
//        tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//        if (root.left != null)
//        Mirror(root.left);
//        if (root.right != null)
//        Mirror(root.right);
//        }

//        if (root == null) {
//                return;
//                }
//                if (root.left == null && root.right == null) {
//                return;
//                }
//                TreeNode pTemp = root.left;
//                root.left = root.right;
//                root.right = pTemp;
//
//                if (root.left != null) {
//                Mirror(root.left);
//                }
//                if (root.right != null) {
//                Mirror(root.right);
//                }