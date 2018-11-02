public class LinkList {
    public static void main(String[] args) {


    }
}

interface ILink {
    void add(Object object); //添加

    boolean remove(Object object);  //删除指定内容

    //提供一个find方法
    Object set(int index, Object newData); //修改指定位置节点内容 更新后的节点 并返回更新前的节点

    Object get(int dex); //删除指定位置节点

    int contains(Object data);//判断指定内容是否在链表中   找到：返回指定位置  没找到：返回 -1；

    int size();//返回链表长度

    void clear();//清空链表 关系 数据

    Object[] toArray(); //链表转换为数组

    void printLink();//打印链表  （用过修改后数组打印即可）


}

class LinkImpl implements ILink {
    private Node linkhead;
    private Node linklast;
    private int size;

    private class Node {
        private Node prev;
        private Object data;
        private Node next;

        public Node(Node prev, Object data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public void add(Object object) {
        if (this.linkhead == null) {
            this.linkhead.data = object;
        }
        Node newNode = new Node(this.linklast, object, null);
        this.linklast.next = newNode;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }


    private Node find(int index) {
        int i = 0;
        for (Node temp = this.linkhead; temp != null; temp = temp.next) {
            i++;
            if (i == index) {
                return temp;
            }

        }
        return null;

    }


    @Override
    public Object set(int index, Object newData) {
        return null;
    }

    @Override
    public Object get(int dex) {
        return null;
    }

    @Override
    public int contains(Object data) {
        for (Node temp =)
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        for (Node temp = this.linkhead; temp != null; ) {
            Node next = temp.next;
            temp = temp.next = temp.prev = null;
            temp = next;
        }

    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (Node temp = this.linkhead; temp != null; temp = temp.next) {
            arr[i++] = temp.data;
        }
        return arr;
    }

    @Override
    public void printLink() {
        for (Object temp : this.toArray()) {
            System.out.print(temp + "->" + "<-");
        }
        System.out.println();
    }
}

