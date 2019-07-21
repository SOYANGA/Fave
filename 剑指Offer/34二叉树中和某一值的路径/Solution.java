package FindPath;
import java.util.ArrayList;

/**
 * @program: TSRTOffer
 * @Description: 34二叉树中和为某一值的路径
 * @Author: SOYANGA
 * @Create: 2019-07-22 23:56
 * @Version 1.0
 */

import java.util.ArrayList;

/**
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
    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        backtracking(root, target, new ArrayList());
        return ret;
    }

    private void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
        if(node == null) {
            return; //一条路径访完全问完毕
        }
        path.add(node.val);//当前非空结点加入path中
        target -= node.val;//更新目标值
        if (target == 0 && node.left == null && node.right == null) {
            ret.add(new ArrayList<>(path)); //找到一条路径，加入ret中
        } else {
            backtracking(node.left, target, path); //继续递归访问左子树
            backtracking(node.right, target, path);//递归访问右子树
        }
        path.remove((int)path.size()-1);//删除队列中的最后一个结点 即删除访问完毕的结点（左右子树均访问完毕），返回父节点，父节点再做判断
    }

}
/*
回溯的思想 ：从根节点开始前序遍历dfs并将结点放入list中,当遍历到叶子结点的时候判断是否满足该条件，满足则将结点加入结果中
需要注意的是：1.new ArrayList<>(list);将正确的list（路径）放入新的引用中
2.我们每次遍历完一跳路径时（先左后右），最终结点的左右系欸但为null时，我们的list中需要将当前结点删除，回退到父节点，
父节点再进行进一步判断
当我们访问完一个结点时我们需要回退当前结点返回父节点
*/
