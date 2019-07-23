package serializeAndDeserialize;

/**
 * @program: TSRTOffer
 * @Description: 37. 序列化二叉树
 * @Author: SOYANGA
 * @Create: 2019-07-23 23:19
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
    private String deserializeStr;

    public String Serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }

    //将序列化号的字符串赋值，存储，在进行反序列化
    public TreeNode Deserialize(String str) {
        deserializeStr = str;
        return Deserialize();
    }

    //字符串进行反序列化
    public TreeNode Deserialize() {
        if (deserializeStr.length() == 0) {
            return null;
        }
        //定位根节点
        int index = deserializeStr.indexOf(" ");
        String nodeStr = index == -1 ? deserializeStr : deserializeStr.substring(0, index); //找到结点的值
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);//在str中删除找到的结点
        if (nodeStr.equals("#")) {//空结点返回null
            return null;
        }
        int val = Integer.valueOf(nodeStr); //取出结点的值
        TreeNode t = new TreeNode(val);//构造结点
        //由于是前序遍历的所以要前序遍历的来拆分
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
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
/*
对子问题进行拆解，拆分成子问题
子问题详细解决，最后递归即可
*/

















