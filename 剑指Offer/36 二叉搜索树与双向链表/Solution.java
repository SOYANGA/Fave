package Convert;

/**
 * @program: TSRTOffer
 * @Description: 36. 二叉搜索树与双向链表 二叉搜索树的中序遍历 + 左右指针作为双向链表的指针
 * @Author: SOYANGA
 * @Create: 2019-07-23 23:08
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
    private TreeNode lHead = null;
    private TreeNode rHead = null;

    public TreeNode Convert(TreeNode node) {
        if (node == null) {
            return null;
        }
        Convert(node.left);//中序遍历先遍历左节点 然后是根节点
        if (lHead == null) { //找到双向链表最左端点的结点
            lHead = rHead = node;
        } else { //其后的结点尾插在以lHead为首rHead为尾的结点之后，并不断更新rHead;
            rHead.right = node;
            node.left = rHead;
            rHead = node;
        }
        Convert(node.right); //最后是右子节点，右子节点直接尾插，跟新rHead结点即可
        return lHead; //返回左边头结点；
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