package Print1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: TSRTOffer
 * @Description: 32.2 按之字形顺序打印二叉树
 * @Author: SOYANGA
 * @Create: 2019-07-22 23:46
 * @Version 1.0
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (reverse)
                Collections.reverse(list);
            reverse = !reverse;
            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }

    public class TreeNode {
        int val = 0;
        Print.Solution.TreeNode left = null;
        Print.Solution.TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }
}
