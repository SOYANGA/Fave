package PrintFromTopToBottom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: TSRTOffer
 * @Description: 32 从上往下打印二叉树
 * @Author: SOYANGA
 * @Create: 2019-07-22 23:39
 * @Version 1.0
 */
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> ret = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode t = queue.poll();
                if (t == null)
                    continue;
                ret.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return ret;
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
