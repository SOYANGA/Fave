import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: DataStruct
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-07 20:01
 * @Version 1.0
 */
public class MyBinarytree<Item> {
    public static class Node<T> {
        private T data;
        private Node<T> lchild;
        private Node<T> rchild;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getLchild() {
            return lchild;
        }

        public Node<T> getRchild() {
            return rchild;
        }

        @Override
        public String toString() {
            String lchildInfo = lchild == null ? null : lchild.getData().toString();
            String rchildInfo = rchild == null ? null : rchild.getData().toString();

            return "Node{" +
                    "data=" + data +
                    ", lchild=" + lchildInfo +
                    ", rchild=" + rchildInfo +
                    '}';
        }
    }

    /**
     * 树的的根节点
     */
    private Node<Item> root;

    /**
     * 树的节点总数
     */
    private int nodesNum;

    /**
     * 添加根节点
     *
     * @param data 数据
     */
    public void setRoot(Item data) {
        root = new Node<>(data);
        nodesNum++;
    }

    /**
     * 给父节点添加右孩子
     *
     * @param data   数据
     * @param parent 父节点
     */
    public void addLeftChild(Item data, Node<Item> parent) {
        parent.lchild = new Node<>(data);
        nodesNum++;
    }

    /**
     * 给父节点添加左孩子
     *
     * @param data   数据
     * @param parent 父节点
     */
    public void addRightChild(Item data, Node<Item> parent) {
        parent.rchild = new Node<>(data);
        nodesNum++;
    }

    /**
     * 从根节点开始查找某个节点
     *
     * @param node 要查找的节点
     * @return 找到的节点
     */
    public Node parentTo(Node<Item> node) {
        return parentTo(this.root, node);
    }

    public Node parentTo(Node<Item> currentNode, Node<Item> node) {
        if (currentNode == null) {
            return null;
        }
        if (node.equals(currentNode.lchild) || node.equals(currentNode.rchild)) {
            return currentNode;
        }

        //如果当前节点没有找到，递归查找其左右子树
        Node<Item> p;
        if ((p = parentTo(currentNode.lchild, node)) != null) {
            return p;
            //如果左子树中没有找到，返回右子树查找结果
        } else {
            return parentTo(currentNode.rchild, node);
        }
    }

    /**
     * 获取根节点
     *
     * @return 根节点
     */
    public Node<Item> root() {
        return root;
    }

    /**
     * 查看树的度
     *
     * @param node 要查看的节点
     * @return 子节点的个数
     */
    public int degreeForNode(Node<Item> node) {
        if (node.lchild != null && node.rchild != null) {
            return 2;
        } else if (node.lchild != null || node.rchild != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 查看树的度
     *
     * @return 树的度
     */
    public int degree() {
        //无非有三种情况
        //1.只有一个根节点，度为0
        //斜树 度为1
        //其余情况是 2
        if (root.lchild == null && root.rchild == null) {
            return 0;
            //斜树的结点个数等于其深度，包括了只有根节点的情况，所以上面的条件要先判断跟结点
        } else if (nodesNum == depth()) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 返回指定结点的深度
     *
     * @param node 结点
     * @return 树的深度
     */
    public int depthForSubTree(Node<Item> node) {
        if (node == null) {
            return 0;
        }
        //从上到下递归，从下到上分会深度，
        // 下面就是返回某结点两个孩子中深度最大的那个，加1继续返回到最上一层。
        int lDepth = depthForSubTree(node.lchild);
        int rDepth = depthForSubTree(node.rchild);
        return lDepth > rDepth ? lDepth + 1 : rDepth + 1;
    }

    /**
     * 返回数的深度
     *
     * @return 树的深度（根结点）
     */
    public int depth() {
        return depthForSubTree(root);
    }

    /**
     * 返回树的结点个数
     *
     * @return
     */
    public int nodesNum() {
        return nodesNum;
    }

    /**
     * 前序遍历--递归(自 左 右)
     */
    public void preOrder(Node<Item> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + " ");
        preOrder(node.lchild);
        preOrder(node.rchild);
    }

    /**
     * 前序遍历 --非递归
     * 用栈模拟递归调用的过程
     */
    public void preOrder2(Node<Item> node) {
        //用栈保存已经访问过的结点，便于返回到父节点 //链表
        LinkedList<Node<Item>> stack = new LinkedList<>();
        //当前根结点不为空，或者为空但有可以返回的父节点(栈中有其他结点)（可以进行pop()操作)都可以进入循环
        while (node != null || !stack.isEmpty()) {
            //只要当前结点不为空，就打印，同时入栈
            while (node != null) {
                stack.push(node);
                System.out.print(node.getData() + " ");
                node = node.lchild;
            }
            //上面while终止说明当前结点为空；返回到父结点并处理它的右子树。由于要执行pop操作，先判空

            //返回到父节点。由于左孩子为空返回时已经弹出过父节点了，所以若是由于右孩子为空返回，
            // 会一次性返回到多层
            node = stack.pop();
            //开始右子树的大循环(第一个while)
            //子问题访问左树
            node = node.rchild;
        }
    }

    /**
     * 中序遍历 --递归 （左自右）
     *
     * @param node
     */
    public void inOrder(Node<Item> node) {
        if (node == null) {
            return;
        }
        inOrder(node.lchild);
        System.out.print(node.getData() + " ");
        inOrder(node.rchild);
    }

    /**
     * 中序遍历非递归
     */
    public void inOrder2(Node<Item> node) {
        LinkedList<Node<Item>> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.lchild;
            }
            //和前序遍历唯一不同就是，前序遍历是入栈时打印。中序遍历是出栈时返回父节点才打印
            //和前序遍历一样，由于左孩子为空返回时已经弹出过父节点，所以若是由于右孩子为空返回，会一次性返回多层
            node = stack.pop();
            System.out.print(node.getData() + " ");
            //子问题访问右树
            node = node.rchild;
        }
    }

    /**
     * 后续序遍历--递归（左右自）
     *
     * @param node
     */
    public void postOrder(Node<Item> node) {
        if (node == null) {
            return;
        }
        postOrder(node.lchild);
        postOrder(node.rchild);
        System.out.print(node.getData() + " ");
    }

    /**
     * 后续遍历--非递归
     */
    public void postOrder2(Node<Item> node) {
        LinkedList<Node<Item>> stack = new LinkedList<>();
        //存放系欸但被访问的信息，1表示只访问过左孩子，2表示右孩子访问过了（此时可以打印自结点）
        LinkedList<Integer> visitedState = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.lchild;
                //上面访问过左孩子，放入1
                visitedState.push(1);
            }
            //这个while和下面的if不可交换执行顺序，否则变成了中序遍历
            //用while儿不用if是因为：结点已经访问过它的两个孩子了，先不打印而处于等待状态。随即判断若它的右孩子不为空，则仍会被push进入，
            // 待右孩子处理完后按照递归思想应该返回到等待中父节点，由于父节点访问状态已经是2.直接打印
            while (!stack.isEmpty() && visitedState.peek() == 2) {
                visitedState.pop();
                System.out.println(stack.pop().getData() + " ");
            }
            if (!stack.isEmpty()) {
                //注意先取出来而不删除，等到访问状态为2才删除
                node = stack.peek();
                node = node.rchild;
                //上面访问过有孩子了，应该更新访问状态为2
                visitedState.pop();//弹出1，压入2
                visitedState.push(2);
            }
        }
    }

    /**
     * 层序遍历 --借助队列
     * 将元素添加到队列中快于打印，要动手在纸上画画
     */
    public void levelOrder(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            System.out.println(front.getData() + " "); //一次打印一个
            if (front.lchild != null) {
                queue.offer(front.lchild);
            }
            if (front.rchild != null) {
                queue.offer(front.rchild);
            }
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    public boolean isEmpty() {
        return nodesNum == 0;
    }

    /**
     * 实际上是删除该结点为根节点的子树，后续遍历删除
     */
    public void deleteSubTree(Node<Item> node) {
        if (node == null) {
            return;
        }
        // 结点信息被清空了，但是结点本身不是null，对data进行判断，如果data已经为空就不自减了
        if (node.data != null) {
            nodesNum--;
        }
        deleteSubTree(node.lchild);
        deleteSubTree(node.rchild);
        //删除根结点结点信息
        node.lchild = null;
        node.rchild = null;
        node.data = null;
    }

    /**
     * 清空树（销毁树）
     */
    public void clear() {
        deleteSubTree(root);
        //root.lchild 和root.rchild虽然为空了但是root还是不为空
        root = null;  //?
    }

    /**
     * 根据卡兰特数递推公式 h(n) = h(n-1)*(4*n-2)/(n+1)
     * 已知h(1)=1;
     * 无穷数列，越到后面数字也大，使用BigIjteger
     * @param n 二叉树的结点
     * @return  多少种组合方式
     */
    public static BigInteger numofTreeShape(int n) {
        BigInteger a = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            a = a.multiply(BigInteger.valueOf(4 * i - 2).divide(BigInteger.valueOf(i + 1)));
        }
        return a;
    }

    public static void main(String[] args) {
        MyBinarytree<String> tree = new MyBinarytree<>();
        tree.setRoot("A");
        Node<String> root = tree.root();
        tree.addLeftChild("B", root);
        tree.addRightChild("C", root);

        tree.addLeftChild("D", root.getLchild());
        tree.addLeftChild("E", root.getRchild());
        tree.addRightChild("F", root.getRchild());
        tree.addLeftChild("G", root.getLchild().getLchild());
        tree.addRightChild("H", root.getLchild().getLchild());
        tree.addRightChild("I", root.getRchild().getLchild());
        System.out.println("前序遍历如下");
        tree.preOrder();

        System.out.println("\n非递归前序遍历：");
        tree.preOrder2(tree.root());


        System.out.println("\n中序遍历如下");
        tree.inOrder();

        System.out.println("\n非递中序序遍历：");
        tree.inOrder2(tree.root());


        System.out.println("\n后序遍历如下");
        tree.postOrder();

        System.out.println("\n非递归后序遍历：");
        tree.postOrder2(tree.root());


        System.out.println("\n层序遍历：");
        tree.levelOrder(tree.root());
        System.out.println();

        System.out.println(root.getRchild().getLchild().getData() + "的父结点是" + tree.parentTo(root.getRchild().getLchild()).getData());
        System.out.println("树的深度是" + tree.depth());
        System.out.println("树的度是" + tree.degree());
        System.out.println("树的结点数是" + tree.nodesNum());
        System.out.println("接待你数为" + tree.nodesNum() + "的二叉树，共有" + numofTreeShape(tree.nodesNum()) + "不种同状态");

        // 删除左子树
        tree.deleteSubTree(root.getLchild());
        System.out.println("还剩" + tree.nodesNum() + "个结点");

        // 删除右结点的左子树
        tree.deleteSubTree(root.getRchild().getLchild());
        System.out.println("还剩" + tree.nodesNum() + "个结点");

        // 清空树
        tree.clear();
        System.out.println(tree.isEmpty());
    }
}

















