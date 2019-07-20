package HasSubtree;

/**
 * @program: TSRTOffer
 * @Description: 26 树的子结构 递归解法 巧妙的递归大胆的先写
 * @Author: SOYANGA
 * @Create: 2019-07-13 23:45
 * @Version 1.0
 */

/**
 * public class TreeNode {
 * int val = 0;
 * TreeNode left = null;
 * TreeNode right = null;
 * <p>
 * public TreeNode(int val) {
 * this.val = val;
 * <p>
 * }
 * <p>
 * }
 */
public class Solution {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        //模板树的根，模板树的左子树，模板书的右子树
        return isSubtreeWithRoot(root1, root2) || isSubtreeWithRoot(root1.left, root2) || isSubtreeWithRoot(root1.right, root2);
    }

    public boolean isSubtreeWithRoot(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSubtreeWithRoot(root1.left, root2.left) && isSubtreeWithRoot(root1.right, root2.right);
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
