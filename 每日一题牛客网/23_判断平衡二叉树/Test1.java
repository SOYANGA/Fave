package Day_23;


/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-29 21:30
 * @Version 1.0
 */


class TreeNode1 {
    int val = 0;
    TreeNode1 left = null;
    TreeNode1 right = null;

    public TreeNode1(int val) {
        this.val = val;
    }
}


public class Test1 {
    private boolean isPass = true;

    public static void main(String[] args) {

    }

    public boolean isBalance(TreeNode1 root) {
        getDepth(root);
        return isPass;
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getDepth(node.left);
        int right = getDepth(node.right);
        if (left - right > 1 || left - right < -1) {
            isPass = false;
        }
        return left > right ? left + 1 : right + 1;
    }
}
