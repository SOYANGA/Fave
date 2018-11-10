package MyStack;


interface IStack<AnyType> {
    int getSize();

    boolean isEmpty();

    void push(AnyType data);

    void pop() throws Exception;

    AnyType top() throws Exception;
}

//链式栈
class LinkStackImpl implements IStack<Object> {
    private int size;
    private SLNode topnode;


    //-------------------------------------
    private class SLNode {
        private SLNode next;
        private Object data;
        private SLNode prev;

        public SLNode(Object data, SLNode prev, SLNode next) {
            this.next = next;
            this.data = data;
            this.prev = prev;
        }
    }

    //--------------------------------------------------
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(Object data) {
        SLNode temp = this.topnode;
        SLNode newnode = new SLNode(data, temp, null);
        if (this.topnode == null) {
            this.topnode = newnode;
        } else {
            temp.next = newnode;
            this.topnode = newnode;
        }
        this.size++;
    }

    @Override
    public void pop() throws Exception {
        if (this.topnode == null) {
            throw new StackEmptyException("错误，堆栈为空！");
        }
        Object data = this.topnode.data;
        this.topnode = this.topnode.prev;
        this.size--;
    }

    @Override
    public Object top() throws Exception {
        if (this.topnode == null) {
            throw new StackEmptyException("错误，堆栈为空！");
        }
        return this.topnode.data;
    }
}

class StackEmptyException extends Exception {
    public StackEmptyException(String msg) {
        super(msg);
    }
}


//顺序栈
class ArrayStackImpl<AnyType> implements IStack<AnyType> {
    private static int LEN = 10;//数组默认大小（容量）
    private AnyType[] arr;      //数组（泛型）
    private int top;            //栈顶下标 ，+1是数组目前长度

    public ArrayStackImpl() {
        top = -1;
        arr = (AnyType[]) new Object[LEN];
    }

    @Override
    public int getSize() {
        return this.top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    private void expendSpace() {
        AnyType[] newArr = (AnyType[]) new Object[this.arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    @Override
    public void push(AnyType data) {
        if (this.top + 1 == arr.length) {
            expendSpace(); //扩容
        }
        arr[++top] = data;
    }

    @Override
    public void pop() throws Exception {
        if (isEmpty()) {
            throw new StackEmptyException("错误 堆栈为空！");
        }
        arr[top--] = null;
    }

    @Override
    public AnyType top() throws Exception {
        if (isEmpty()) {
            throw new StackEmptyException("错误 堆栈为空！");
        }
        return arr[top];
    }
}

public class Stack {
    public static void main(String[] args) throws Exception {
//        IStack stack = new LinkStackImpl();
//        System.out.println(stack.getSize());
//        stack.push("adad");
//        System.out.println(stack.top());
//        stack.push(1);
//        System.out.println(stack.top());
//        stack.pop();
//        System.out.println(stack.top());
//        stack.pop();
//        System.out.println(stack.top());
        IStack<String> stack = new ArrayStackImpl();
        stack.push("hahah");
        System.out.println(stack.top());
//        System.out.println(stack.getSize());
        stack.push("11111111111");
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.top());
    }
}