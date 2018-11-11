package MyQueue;

interface IQueue<Anytype> {
    int getSize();

    boolean isEmpty();

    void enqueue(Anytype data);  //队尾插入

    Anytype dequeue() throws RuntimeException;           //队头删除

    Anytype queueFront();//查看队头元素

    Anytype queueRear();//查看队尾元素

}

class QueueImpl implements IQueue<Object> {
    private Link front;
    private Link rear;
    private int size;

    public QueueImpl() {
        this.rear = this.front = null;
        this.size = 0;
    }

    //-------------------------------
    private class Link {
        private Link prev;
        private Object data;
        private Link next;

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
        Object data=front.data;
        front.data = null;
        front = front.next;
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
}

class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String msg) {
        super(msg);
    }
}

public class Queue {
    public static void main(String[] args) throws RuntimeException {
        IQueue queue = new QueueImpl();
        System.out.println(queue.getSize());
        System.out.println(queue.isEmpty());
        queue.enqueue("aaaaaaaa");
        queue.enqueue("bbbbbbbb");
        queue.enqueue("cccccccc");
        System.out.println(queue.getSize());
        System.out.println(queue.queueFront());
        System.out.println(queue.queueRear());
        System.out.println(queue.dequeue());

        System.out.println(queue.queueFront());
        System.out.println(queue.queueRear());
        System.out.println(queue.dequeue());

        System.out.println(queue.queueFront());
        System.out.println(queue.queueRear());
        System.out.println(queue.dequeue());

    }
}
