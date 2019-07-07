package ConstructBinaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: TSRTOffer
 * @Description: 7重构二叉树 递归 + 记忆化
 * @Author: SOYANGA
 * @Create: 2019-07-12 00:38
 * @Version 1.0
 * <p>
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
//由于本题有一个特点就是没有重复元素所以，就可以使用记忆化+递归实现简化流程，减少递归的冗余


public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    //记忆化存储结构 存储中序遍历的结果。减少寻找中间根节点的时间复杂度。 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preL]); //根节点为前序遍历的首节点
        int inIndex = indexForInOrders.get(root.val);//找到中序遍历中根节点对应的下标，以此为分界线，划分左右子树
        int leftTreeSize = inIndex - inL;//找出左右子树的个数，中序遍历的起始位置，进而确定左右子树在前序遍历的下标。
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }
}