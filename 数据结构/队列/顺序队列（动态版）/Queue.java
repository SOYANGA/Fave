package MyQueue;

interface IQueue<Anytype> {
    int getSize(); //返回队列长度

    boolean isEmpty(); //判空

    void enqueue(Anytype data);  //队尾插入

    Anytype dequeue() throws RuntimeException;           //队头删除

    Anytype queueFront();//查看队头元素

    Anytype queueRear();//查看队尾元素

    void printQueue();//打印队列

}


////////////////////////////////////
//--顺序队列
class QueueArrayImpl<Anytype> implements IQueue<Anytype> {
    private Anytype[] array;//数据元素数组
    private static final int CAP = 3;//对列默认长度
    private int size;  //数据长度
    private int capacity;//动态容量
    private int front;  //队头
    private int rear;   //队尾


    public QueueArrayImpl() {
        this(CAP);
    }

    private QueueArrayImpl(int cap) {
        array = (Anytype[]) new Object[CAP];
        capacity = CAP;   //取与的时候好取
        size = 0;
        front = rear = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override  //队尾入
    public void enqueue(Anytype data) {
        if (size == capacity) {
            expandSpace();
        }
        array[rear] = data;
        rear = (rear + 1) % capacity;
        size++;
    }

    private void expandSpace() {
        Anytype[] a = (Anytype[]) new Object[array.length * 2];
        int i = front;
        int j = 0;
        do {
            a[j++] = array[i];
            i = (i + 1) % capacity;
        } while (i != rear);
        array = a;
        this.capacity = array.length;
        front = 0;
        rear = j;

    }

    @Override //队头出
    public Anytype dequeue() throws RuntimeException {
        if (this.size == 0) {
            throw new QueueEmptyException("错误，队列为空");
        }
        Anytype temp = array[front];
//        array[front] = null;
        front = (front + 1) % capacity;
        size--;
        return temp;

    }

    @Override //队头元素
    public Anytype queueFront() {
        if (this.size == 0) {
            throw new QueueEmptyException("错误：队列为空！");
        }
        return array[front];
    }

    @Override //队尾元素
    public Anytype queueRear() {
        if (this.size == 0) {
            throw new QueueEmptyException("错误：队列为空！");
        }
        int temp = rear;
        if (temp == 0) {
            temp = size - 1;
            return array[temp];
        }
        return array[rear - 1];
    }

    @Override //打印队列
    public void printQueue() {
        if (this.size == 0) {
            throw new QueueEmptyException("错误：队列为空！");
        }
        int i = front;
        System.out.print("(队头)");
        do {
            System.out.print(array[i] + "-");
            i = (i + 1) % capacity;
        } while (i != rear);
        System.out.println("（队尾）   容量为：" + (capacity) + "  队列长度为：" + this.size);
    }
}

////////////////////-----------------------------------
//链式队列

class QueueLinkImpl implements IQueue<Object> {
    private Link front;  //队头
    private Link rear;  //队尾
    private int size;  //对列长度

    public QueueLinkImpl() {
        this.rear = this.front = null;
        this.size = 0;
    }

    //-------------------------------
    private class Link {
        private Link prev;  //前一个
        private Object data; //数据存放
        private Link next; //后一个

        public Link(Link prev, Object data, Link next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    //-------------------
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(Object data) {//队尾插入
        Link newLink = new Link(rear, data, null);
        if (this.rear == null) {
            this.rear = this.front = newLink;
        } else {
            this.rear.next = newLink;
            this.rear = newLink;
        }
        this.size++;

    }

    @Override
    public Object dequeue() throws RuntimeException { //队头删除
        if (size == 0) {
            throw new QueueEmptyException("错误：队列为空！");
        }
        Object data = front.data;
        front.data = null;
        front = front.next;
        this.size--;
        return data;
    }

    @Override
    public Object queueFront() {  //查看队头
        if (this.size == 0) {
            throw new QueueEmptyException("错误：队列为空！");
        }
        return this.front.data;
    }

    @Override
    public Object queueRear() {  //查看队尾
        if (this.size == 0) {
            throw new QueueEmptyException("错误：队列为空！");
        }
        return this.rear.data;
    }

    @Override
    public void printQueue() {  //打印队列
        System.out.print("(队头)");
        for (Link temp = front; temp != null; temp = temp.next) {
            if (temp.next == null) {
                System.out.print(temp.data);
            } else {
                System.out.print(temp.data + "<——>");
            }
        }
        System.out.println("（队尾）   队列长度为：" + this.size);
    }

}

class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String msg) {
        super(msg);
    }
}

public class Queue {
    public static void main(String[] args) throws RuntimeException {
        IQueue queue = new QueueLinkImpl();
        System.out.println(queue.getSize());
        System.out.println(queue.isEmpty());
        queue.enqueue("aaaaaaaa");
        queue.enqueue("bbbbbbbb");
        queue.enqueue("cccccccc");
        queue.enqueue("dddddddd");
        queue.enqueue("eeeeeeee");
        queue.enqueue("ffffffff");
        System.out.println(queue.queueFront());
        System.out.println(queue.queueRear());
        System.out.println(queue.dequeue());
        queue.printQueue();


        System.out.println(queue.queueFront());
        System.out.println(queue.queueRear());
        System.out.println(queue.dequeue());
        queue.printQueue();


        System.out.println(queue.queueFront());
        System.out.println(queue.queueRear());
        System.out.println(queue.dequeue());
        queue.printQueue();


        System.out.println("/////////////////////////////////////////////////////////////");

        IQueue<String> queue2 = new QueueArrayImpl<>();
        System.out.println(queue2.getSize());
        System.out.println(queue2.isEmpty());
        queue2.enqueue("aaaaaaaa");
        queue2.enqueue("bbbbbbbb");
        queue2.enqueue("cccccccc");
        queue2.enqueue("dddddddd");
        queue2.enqueue("eeeeeeee");
        queue2.enqueue("ffffffff");
        System.out.println();
        queue2.printQueue();
        System.out.println(queue2.getSize());

        System.out.println();

        System.out.println(queue2.queueFront());
        System.out.println(queue2.queueRear());
        System.out.println(queue2.dequeue());
        queue2.enqueue("aaaaaaaa");
        queue2.printQueue();


        System.out.println();

        System.out.println(queue2.queueFront());
        System.out.println(queue2.queueRear());
        System.out.println(queue2.dequeue());
        queue2.enqueue("bbbbbbbb");
        queue2.printQueue();

        System.out.println();

        System.out.println(queue2.queueFront());
        System.out.println(queue2.queueRear());
        System.out.println(queue2.dequeue());
//        queue2.enqueue("cccccccc");
        queue2.printQueue();


    }
}
