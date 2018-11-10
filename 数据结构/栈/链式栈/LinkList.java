package MyLinkList;

public class LinkList {
    public static void main(String[] args) {
        ILink link = Factory.getLinkInstance();
        link.add("火车头");
        link.add("车厢1");
        link.add("车厢2");
        link.add(null);
        link.add("车厢尾");
        System.out.println(link.set(0, null));

        System.out.println(link.get(0));

        System.out.println(link.get(5));
//        link.remove(null);
        link.printLink();
        System.out.println(link.size());

    }
}

class Factory {
    private Factory() {
    }

    public static ILink getLinkInstance() {
        return new LinkImpl();
    }
}

interface ILink {
    void add(Object object); //添加

    boolean remove(Object object);  //删除指定内容

    //提供一个find方法
    Object set(int index, Object newData); //修改指定位置节点内容 更新后的节点 并返回更新前的节点

    Object get(int dex); //得到指定位置节点的数据

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

    //-------------------------------------------------------
    public class Node {
        private Node prev;
        private Object data;
        private Node next;

        public Node(Node prev, Object data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    //--------------------------------------------------------

    @Override
    public void add(Object object) {
        Node temp = this.linklast;
        Node newnode = new Node(temp, object, null);
        this.linklast = newnode;
        if (this.linkhead == null) {
            this.linkhead = newnode;
        } else {
            temp.next = newnode;
        }
        this.size++;
    }

    @Override
    public boolean remove(Object object) {
        if (this.size == 0) {
            System.out.println("链表为空无法删除！");
        }
        Node temp = this.findContant(object);
        if (temp != null) {
            unlink(temp);
            return true;
        } else {
            System.out.println("链表中不存在要删除的元素！");
            return false;
        }
    }

    @Override
    public Object set(int index, Object newData) {
        if (this.size == 0) {
            System.out.println("链表为空，无法改变谋个元素的值！");
            return null;
        }
        if (!this.isLinkIndex(index)) {
            System.out.print("索引值非法输入！");
            return null;
        }
        Object element = this.findIndex(index).data;
        this.findIndex(index).data = newData;
        return element;
    }

    @Override
    public Object get(int index) {
        if (this.size == 0) {
            System.out.println("链表为空，无法查看！");
            return null;
        }
        if (!this.isLinkIndex(index)) {
            System.out.print("索引值非法输入！");
            return null;
        }
        return this.findIndex(index).data;
    }

    @Override
    public int contains(Object data) {  //需要判断equals调用的对象是否为null
        if (this.size == 0) {
            System.out.println("链表为空，无法判断！");
            return -1;
        }
        int i = 0;
        if (data == null) {
            for (Node temp = this.linkhead; temp != null; temp = temp.next) {
                if (temp.data == null) {
                    return i;
                }
                i++;
            }
        } else {
            for (Node temp = this.linkhead; temp != null; temp = temp.next) {
                if (temp.data.equals(data)) {
                    return i;
                }
                i++;
            }
        }
        System.out.println("您要查找的元素不存在！");
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        if (this.size == 0) {
            System.out.println("链表为空不用进行清空！");
        }
        for (Node temp = this.linkhead; temp != null; ) {
            Node next = temp.next;
            temp = temp.next = temp.prev = null;
            temp = next;
            this.size--;
        }
        this.linkhead = this.linklast = null;

    }

    @Override
    public Object[] toArray() {
        if (this.size == 0) {
            System.out.println("链表为空不能转换！");
        }
        Object[] arr = new Object[size];
        int i = 0;
        for (Node temp = this.linkhead; temp != null; temp = temp.next) {
            arr[i++] = temp.data;
        }
        return arr;
    }

    @Override
    public void printLink() {
        if (this.size == 0) {
            System.out.println("链表为空，不能进行打印！");
            return;
        }
        for (Object temp : this.toArray()) {
            System.out.print(temp + "->" + "<-");
        }
        System.out.println();
    }

    private Node findIndex(int index) {//查找指定位置的值 /set /get
        int i = 0;
        if (index < (size >> 1)) {
            Node temp = this.linkhead;
            for (i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        } else {
            Node temp = this.linklast;
            for (i = this.size - 1; i > index; i--) {
                temp = temp.prev;
            }
            return temp;
        }
    }

    private boolean isLinkIndex(int index) {  //判断给定得索引值是否合法 /set/get
        return index >= 0 && index < this.size;
    }

    public void unlink(Node node) { //解除节点之间的连接 /remove
        Node prev = node.prev;
        Node next = node.next;
        if (prev == null) {
            this.linkhead = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            this.linkhead = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
        this.size--;
    }

    public Node findContant(Object object) { //remove判断内容返回相符合的节点 判断object是否为null
        if (object == null) {
            for (Node temp = this.linkhead; temp != null; temp = temp.next) {
                if (temp.data == null) {
                    return temp;
                }
            }
        } else {
            for (Node temp = this.linkhead; temp != null; temp = temp.next) {
                if (object.equals(temp.data)) {
                    return temp;
                }
            }
        }
        return null;
    }
}

