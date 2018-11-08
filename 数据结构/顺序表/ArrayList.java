interface IArray<AnyType> {
    int getSize();//返回线性表的大小

    boolean isEmpty();//判断线性表是否为空true,非空返回false.

    int contains(AnyType data);//判断元素表中是否包含某个数据元素  //不在返回-1，在的话返回下标

    void insert(int index, AnyType data);//将数据插到表中下标为i的位置

    boolean removeindex(int index); //删除下标为index的元素

    boolean removecontent(AnyType data);//删除内容为data的元素 出现的第一个元素

    AnyType replace(int index, AnyType data);//替换下标为index的元素的内容 返回替换之前的元素

    AnyType get(int idex); //返回指定位置的数据

    void add(AnyType data); //添加线性表元素

    void clear();//清空线性表

    void printArray();//打印线性表

    void printCapacity();//打印当前顺序表容量；
}

class ArrayListImpl<AnyType> implements IArray<AnyType> {


    private static int DEFAULT_CAPACITY = 10;
    private int size;
    private AnyType[] items;

    public ArrayListImpl() {
        this.items = (AnyType[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int contains(AnyType data) {
        return find(data);
    }

    private int find(AnyType data) {
        if (data == null) {
            for (int i = 0; i < this.size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (items[i].equals(data)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean isArrayIndex(int index) {
        return index <= this.size;
    }

    @Override
    public void insert(int index, AnyType data) {
        if (!isArrayIndex(index)) {
            System.out.println("输入下标非法");
            return;
        }
        ensureCapacity(this.size);
        for (int i = this.size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = data;
        this.size++;
        return;
    }

    @Override
    public boolean removeindex(int index) {
        if (!isArrayIndex(index)) {
            System.out.println("非法索引下标！");
            return false;
        }
        for (int i = index; i <= this.size - 1; i++) {
            items[i] = items[i + 1];
        }
        this.size--;
        return true;
    }


    public boolean removecontent1(AnyType data) {
        int i = 0;
        int j = 0;
        AnyType[] newitem = (AnyType[]) new Object[DEFAULT_CAPACITY];
        if (data != null) {
            while (i < this.size) {
                if (!data.equals(items[i])) {
                    newitem[j] = items[i];
                    j++;
                    i++;
                } else {
                    i++;
                }
            }
        } else {
            while (i < this.size) {
                if (data == items[i]) {
                    newitem[j] = items[i];
                    j++;
                    i++;
                } else {
                    i++;
                }
            }
        }
        if (j == i) {
            System.out.println("你要删除的数据不存在");
            return false;
        }
        items = newitem;
        this.size = j;
        return true;
    }

    @Override
    public boolean removecontent(AnyType data) {
        int j = 0;
        if (data != null) {
            for (int i = 0; i < this.size; i++) {
                if (!data.equals(items[i])) {
                    items[j++] = items[i];
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (data != items) {
                    items[j++] = items[i];
                }
            }
        }
        if (this.size == j) {
            System.out.println("你要删除的元素不在顺序表中！");
            return false;
        }
        this.size = j;
        return true;
    }

    @Override
    public AnyType replace(int index, AnyType data) {
        if (!isArrayIndex(index)) {
            System.out.print("非法索引值！");
            return null;
        }
        AnyType temp = items[index];
        items[index] = data;
        return temp;
    }

    @Override
    public AnyType get(int index) {
        if (!isArrayIndex(index)) {
            System.out.print("非法下标！");
            return null;
        }
        return items[index];
    }

    @Override
    public void add(AnyType data) {
        ensureCapacity(this.size);
        items[this.size] = data;
        this.size++;
    }

    @Override
    public void clear() {
        this.size = 0;
    }

    @Override
    public void printArray() {
        if (this.size == 0) {
            System.out.println("顺序表为空");
            return;
        }
        for (int i = 0; i < this.size; i++) {
            if (i == this.size - 1) {
                System.out.println(items[i]);
            } else {
                System.out.print(items[i] + "-");
            }
        }
    }

    @Override
    public void printCapacity() {
        System.out.println(DEFAULT_CAPACITY);
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < DEFAULT_CAPACITY) {
            return;
        } else {
            DEFAULT_CAPACITY *= 2;
            AnyType[] old = items;
            items = (AnyType[]) new Object[DEFAULT_CAPACITY];
            for (int i = 0; i < this.size; i++) {
                items[i] = old[i];
            }
        }
    }
}

class ArrayFactory {
    private ArrayFactory() {

    }

    public static ArrayListImpl getArrayListInstance() {
        return new ArrayListImpl();
    }
}

public class ArrayList {
    public static void main(String[] args) {
//        IArray<Integer> array = ArrayFactory.getArrayListInstance();
//        array.add(1);
//        array.add(2);
//        array.add(3);
//        array.add(4);
//        array.add(5);
//        array.add(6);
//        array.add(7);
//        array.add(8);
//        array.add(9);
//        array.add(10);
//        array.add(11);
//        array.add(12);
//        array.add(13);
//        array.printCapacity();
//        System.out.println(array.getSize());
//        array.printArray();
//        System.out.println(array.removeindex(0));
//        System.out.println(array.getSize());
//        array.printArray();
//        System.out.println(array.get(13));
//        System.out.println(array.replace(0, 1));
//        System.out.println(array.replace(1, 1));
//        System.out.println(array.replace(2, 1));
//        array.printArray();
//        array.removecontent(1);
//        System.out.println(array.isEmpty());
//        array.insert(0,1);
//        System.out.println(array.contains(20));
//        array.printArray();
        IArray<String> array = ArrayFactory.getArrayListInstance();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        array.add("7");
        array.add("8");
        array.add("9");
        array.add("10");
        array.add("11");
        array.add("12");
        array.add("13");
        array.add("14");
        array.add("15");
        array.printCapacity();
        array.printArray();
        System.out.println(array.removeindex(0));
        array.printArray();
        System.out.println(array.getSize());
        System.out.println(array.get(15));
        System.out.println(array.replace(0, "1"));
        System.out.println(array.replace(1, "1"));
        System.out.println(array.replace(2, "1"));
        array.printArray();
        array.removecontent("1");
        array.printArray();
        System.out.println(array.isEmpty());
        array.insert(0, "null");
        System.out.println(array.contains("null"));
        System.out.println(array.replace(2, "null"));
        array.removecontent("null");
        array.printArray();
    }
}
