package GetNext;

/**
 * @program: TSRTOffer
 * @Description: 8二叉树的下一个结点 中序遍历的下一个结点 结合中序遍历的特点切入
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:15
 * @Version 1.0
 */
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
//左 中 右

public class Solution {
    public TreeLinkNode getNext(TreeLinkNode pNode) {
        //1.右子树不为空 找右子树的左叶子节点
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            //2.右子树为空 向上找第一个左链接指向的树包含该节点的祖先节点
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode) {
                    return parent;
                }
                pNode = pNode.next;
            }
        }
        return null;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}