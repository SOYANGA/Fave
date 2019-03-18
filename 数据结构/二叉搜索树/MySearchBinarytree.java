import javax.xml.transform.Source;
import java.util.LinkedList;

/**
 * @program: DataStruct
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-12 15:58
 * @Version 1.0
 */
public class MySearchBinarytree {
    private Node tree;
    private int size;


    public Node getTree() {
        return tree;
    }

    public int getSize() {
        return size;
    }

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insert(int data) {
        size++;
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else if (data < p.data) {  //data < p.data
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            } else {
                return;
            }
        }
    }

    public boolean delete(int data) {
        Node p = tree; //p指向要删除的节点，初始化指向根节点
        Node pp = null; //pp记录的是p的父节点
        //查找要删除的节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) {
            return false;       //没有找到 要删除的节点
        }

        //要删除的节点有两个子节点
        if (p.left != null && p.right != null) { //查找右子树中最小节点
            Node minp = p.right;
            Node minpp = p;  //minpp表示minp的父节点
            while (minp.left != null) {
                minpp = minp;
                minp = minp.left;
            }
            //找到要删除节点的右子树的最小值，即右子树的最端左孩子
            p.data = minp.data; //将要删除的节点的值更因为最左端的值
            p = minp; //将要删除的节点变为最左端节点
            pp = minpp; //更新要删除的节点的父节点
        }

        //将删除操作(叶子节点) 或者要删除的节点只有一个子节点
        Node child; //p 的子节点
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        if (pp == null) {
            tree = child;   //要删除的节点没有父节点，则要删除的是根节点
        } else if (pp.left == p) {
            pp.left = child;   //要删除的节点只有左子树
        } else {
            pp.right = child; //要删除的节点只有右子树
        }
        size--;
        return true;
    }

    public Node findMin() {
        if (tree == null) {
            return null;
        }
        Node p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node findMax() {
        if (tree == null) {
            return null;
        }
        Node p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    public void inOrder() {
        inOrder(tree);
    }

    /**
     * 二叉树搜索树中序遍历 可以达到排序的目的
     * 递归中序遍历
     *
     * @param node
     */
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void inOrder2(Node node) {
        LinkedList stack = new LinkedList();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = (Node) stack.pop();
            System.out.print(node.getData() + " ");
            node = node.right;
        }

    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public String toString() {

            int leftData = left.data;
            int rightData = right.data;
            return "Node{" +
                    "data=" + data +
                    ", left=" + leftData +
                    ", right=" + rightData +
                    '}';
        }
    }

    public static void main(String[] args) {
        MySearchBinarytree mySearchBinarytree = new MySearchBinarytree();
        mySearchBinarytree.insert(50);
        mySearchBinarytree.insert(20);
        mySearchBinarytree.insert(30);
        mySearchBinarytree.insert(80);
        mySearchBinarytree.insert(70);
        mySearchBinarytree.insert(90);
        mySearchBinarytree.insert(40);
        mySearchBinarytree.insert(10);
        mySearchBinarytree.insert(51);
        mySearchBinarytree.insert(52);
        mySearchBinarytree.insert(53);
        System.out.println(mySearchBinarytree.getSize());
        mySearchBinarytree.inOrder();
        System.out.println();
        mySearchBinarytree.inOrder2(mySearchBinarytree.tree);
        System.out.println();

        mySearchBinarytree.delete(51);
        mySearchBinarytree.delete(40);
        mySearchBinarytree.inOrder();
        System.out.println();
        mySearchBinarytree.inOrder2(mySearchBinarytree.tree);
        mySearchBinarytree.insert(50);  //无法插入相同的元素
        System.out.println();
        mySearchBinarytree.inOrder2(mySearchBinarytree.tree);
        System.out.println();
        System.out.println(mySearchBinarytree.findMax().getData());
        System.out.println(mySearchBinarytree.findMin().getData());
        System.out.println(mySearchBinarytree.getTree().getData());

    }
}
