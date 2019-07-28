package KthNode;

/**
 * @program: TSRTOffer
 * @Description: 54二叉查找树的第K个结点 中序遍历特点有序
 * @Author: SOYANGA
 * @Create: 2019-08-02 00:56
 * @Version 1.0
 */
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    private TreeNode ret;
    private int cnt = 0;

    public TreeNode KthNode(TreeNode pRoot, int k) {
        inOrder(pRoot, k);
        return ret;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null || cnt >= k) {
            return;
        }
        inOrder(root.left, k); //左
        cnt++;
        if (cnt == k) { //中
            ret = root;
        }
        inOrder(root.right, k);//右
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}