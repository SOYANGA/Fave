# 一、概览

容器主要包括 **Collection和 Map两种**，Collection存储着对象的集合，而Map存储着键值对(两个对象)的映射表

## Collection

![Collection](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/73403d84-d921-49f1-93a9-d8fe050f3497.png)

### 1.Set

- TreeSet:基于**红黑树实现**，支持有序性操作，例如依据一个范围查找元素的操作，但是查找效率不如HashSet,HashSet(查找的时间的复杂度是O(1)，TreeSet则是O(logN))。
- HashSet：基于**哈希表实现**，支持快速查找，但不支持有序性操作，并且失去了元素的插入顺序信息，也就是说使用Iterator遍历HashSet得到的结果是不确定的。
- LinkedHashSet:具有HashSet的查找效率，并且每部使用双向链表维护元素的插入顺序。

### 2.List

- ArrayList:基于动态数组实现，支持随机访问。
- Vector：和ArrayList类似，但是它是线程安全的。
- LinkedList：基于双向链表的实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList还可以用作栈，队列和双向队列。

### 3.Queue

- LinkedList：可以用它来实现双向队列
- PriorityQueue：基于堆结构实现，可以用它来实现优先队列。

## Map

![Map](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/774d756b-902a-41a3-a3fd-81ca3ef688dc.png)

- TreeMap: 基于红黑树实现
- HashMap:基于哈希表实现
- HashTable:和HashMap类似，但它是线程安全的，着意味着同一时刻多个线程可以同时写入HashTable并且不会导致数据不一致，不应该使用它。现在可以使用ConcurrentHashMap来支持线程安全，并且ConcurrentHashMap的效率会更高，因为ConcurrentHashMap 引入了分段锁。
- LinkedHashMap:使用**双向链表来维护元素的顺序**，顺序为了**插入顺序或者最近最少使用(LRU)顺序**。



# 二、容器中的设计模式

## 迭代器设计模式

![迭代器设计模式](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/93fb1d38-83f9-464a-a733-67b2e6bfddda.png)

Collection继承了Iterable接口，其中iterator()方法能够产生一个Iterator()对象，通过这个对象就可以迭代遍历Collection中的元素。

从JDK1.5之后可以使用foreach方法来遍历实现Iterable接口的聚合对象

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
for (String item : list) {
    System.out.println(item);
}
```



## 适配器模式

java.util.Arrays.asList()可以把数组类型转换为List类型

```java
@SafeVarargs
public static <T> List<T> asList(T... a)
```

应该注意的是 asList() 的参数为泛型的变长参数，不能使用基本类型数组作为参数，只能使用相应的包装类型数组。

```java
Integer[] arr = {1, 2, 3};
List list = Arrays.asList(arr);Copy to clipboardErrorCopied
```

也可以使用以下方式调用 asList()：

```java
List list = Arrays.asList(1, 2, 3);
```



# 三、源码分析

JDK1.8源码



## ArrayList

### 1.概览

因为ArrayList是基于数组实现的，所以支持快速随机访问。RandomAccess接口标识着这类支持快速随机访问。

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

数组的默认大小是10.

```java
private static final int DEFAULT_CAPACITY = 10;
```



### 2.扩容

添加元素时使用 ensureCapacityInternal()方法来保证容量足够(初始化动态数组)，如果不够时，需要使用grow()方法进行扩容，新容量的大小为`oldCapacity + (oldCapacity >> 1)`,也就是旧容量的1.5倍。

扩容操作需要调用`Array.copyOf()`把原数组整个复制到新数组中，这个操作代价很高。**因此最好在创建ArrayList对象时就指定大概的容量大小，减少扩容操作次数。**

```java
public boolean add(E e) { //添加元素
    ensureCapacityInternal(size + 1);  // Increments modCount!! //size=0（在源码中未初始化）//记录数组修改次数
    elementData[size++] = e; 
    return true;  //只会返回true方法，要么就回报错（异常）
}
	
private void ensureCapacityInternal(int minCapacity) {
    ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
}
	
private static int calculateCapacity(Object[] elementData,int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {//为空数组
       return Math.max(DEFAULT_CAPACITY, minCapacity); //取两个数之中的最大值  minCapacity=(size+1)=1 DEFAULT_CAPACITY=10;
    }
    return minCapacity; // 返回10
}
	//private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	//DEFAULTCAPACITY_EMPTY_ELEMENTDATA 为空数组
	
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;  //记录数组修改次数
    // overflow-conscious code  //超出数组当前容量（懒加载策略）
	if (minCapacity - elementData.length > 0)
        grow(minCapacity);  //扩容方法
}
	
private void grow(int minCapacity) {
    // overflow-conscious code
   int oldCapacity = elementData.length; //记录旧容量
    int newCapacity = oldCapacity + (oldCapacity >> 1);//新容量 = 1.5旧容量
    if (newCapacity - minCapacity < 0) //开辟完后还是放下增加的元素
        newCapacity = minCapacity; //直接将需要的容量在数组上开辟
    if (newCapacity - MAX_ARRAY_SIZE > 0) //如果新容量大于这个数
        newCapacity = hugeCapacity(minCapacity);
		//就将Integer.MAX_VALUE（integer最大值）赋给新容量
		//看不懂这是在干什么？是要采取覆盖策略吗？
        // minCapacity is usually close to size, so this is a win: 要设定的容量往往接近size值所以是一个双赢？双赢在哪里
       elementData = Arrays.copyOf(elementData, newCapacity);//新数组
}

private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
//为什么设置了这个值？
	
private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
      throw new OutOfMemoryError(); //超出integer最大值异常
    return (minCapacity > MAX_ARRAY_SIZE) ?
      Integer.MAX_VALUE ://2^31-1
      MAX_ARRAY_SIZE;//2^31-1-8
}
	
重点：
int newCapacity = oldCapacity + (oldCapacity >> 1);//新容量 = 1.5旧容量
```



### 3.删除元素

需要调用System.arraycopy()将index + 1后面的元素都复制到index位置上，该操作的时间复杂度是O(N),可以看出ArrayList删除元素的代价是非常高的。

```java
public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work 8
    return oldValue;
}
```



### 4.Fail-Fast

modCount用来记录ArrayList结构发生变化的次数。结构发生变化是**指添加或者删除至少一个元素的所有操作**，**或者是调整内部数组的大小**，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代的时候，需要比较操作前后modCount是否改变，如果改变了需要抛出ConcurrentModificationException。

```java
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}
```



### 5.序列化

ArrayLisyt基于数组实现，并且具有动态扩容的特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。

保存元素的数组 elementData 使用 transient 修饰，该关键字声明数组默认不会被序列化。

```java
transient Object[] elementData; // non-private to simplify nested class access
```

ArrayList实现了writeObject()和readObject()来控制只序列化数组中有元素填充的那部分内容。(for循环size次遍历数组)

```java
private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    elementData = EMPTY_ELEMENTDATA;

    // Read in size, and any hidden stuff
    s.defaultReadObject();

    // Read in capacity
    s.readInt(); // ignored

    if (size > 0) {
        // be like clone(), allocate array based upon size not capacity
        ensureCapacityInternal(size);

        Object[] a = elementData;
        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            a[i] = s.readObject();
        }
    }
}
```

```java
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}
```

序列化时需要使用 ObjectOutputStream 的 writeObject() 将对象转换为字节流并输出。而 writeObject() 方法在传入的对象存在 writeObject() 的时候会去反射调用该对象的 writeObject() 来实现序列化。反序列化使用的是 ObjectInputStream 的 readObject() 方法，原理类似。

```java
ArrayList list = new ArrayList();
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
oos.writeObject(list);Copy to clipboardErrorCopied
```

## Vector

### 1.同步

它的实现于ArrayList类似，但是使用了synchronized 进行同步。

```java
	初始化策略：
	public Vector(int initialCapacity, int capacityIncrement) {
    super();
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    this.elementData = new Object[initialCapacity]; //初始化数组
    this.capacityIncrement = capacityIncrement;  //增量（默认是0，即在grow（扩容策略中使用容量扩大2倍策略）
	//如果设置则是按照自己设定的增容策略来进行的
}

/**
 * Constructs an empty vector with the specified initial capacity and
 * with its capacity increment equal to zero.
 *
 * @param   initialCapacity   the initial capacity of the vector
 * @throws IllegalArgumentException if the specified initial capacity
 *         is negative
 */
public Vector(int initialCapacity) {
    this(initialCapacity, 0);  //initialCapacity=10 Vector容量
}

/**
 * Constructs an empty vector so that its internal data array
 * has size {@code 10} and its standard capacity increment is
 * zero.
 */
public Vector() {
    this(10); //调用有参构造
}
	
	
	扩容策略：
	public synchronized boolean add(E e) {
        modCount++; //记录数组修改次数
        ensureCapacityHelper(elementCount + 1); 
		//确定容量  elementCount：Vector中有效数据长度-即size()
		//初始值为0
        elementData[elementCount++] = e;
        return true;
    }
	
	public synchronized int size() {
        return elementCount;  //size()
    }
	
	private void ensureCapacityHelper(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0) //elementData.length:初始长度是10
            grow(minCapacity);//增容机制
    }
	
	
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;//2^31-1

	private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length; //旧容量
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                         capacityIncrement : oldCapacity)
		//若果在Vector有参构造中设置capacityIncrement(增容策略)，则按照设置的增容策略进行扩容，反之按照扩大原来容量的两倍进行扩容。
        if (newCapacity - minCapacity < 0)//扩容后还是放不下
            newCapacity = minCapacity;//则按照增加后的容量设定数组大小
        if (newCapacity - MAX_ARRAY_SIZE > 0) 
            newCapacity = hugeCapacity(minCapacity); //同ArrayList
        elementData = Arrays.copyOf(elementData, newCapacity);  //新数组
    }
```

### 2.与ArrayList比较

- 版本
- 初始化策略
- 扩容机制
- 线程安全，效率
- 在遍历上的差异



**1.出现版本：**

**ArrayList:JDk1.2**

**Vector：JDk1.0(出现在List,Collection接口之前)**



**2.调用无参构造的区别(初始化策略)**

Vector在无参构造执行后,会调用有参构造**将对象数组大小初始化为10**

ArrayList采用**懒加载策略**，在构造方法阶段并不初始化对象数组。

在**第一次添加元素时才初始化对象数组大小为10（初始化策略）**



**3.扩容策略**

**ArrayList 扩容时，新数组大小变为原来数组的1.5倍。**

**假如要设置的值超出1.5倍旧值时 将容量设置为需要的值**

**Vector:扩容时，新数组大小变为原来数组的2倍。（无参构造）-可以自定义增容策略**

**假如要设置的值超出2倍旧值时 将容量设置为需要的值**

 **capacityIncrement:增容策略**

 **如不设置增容策略则是增加两倍** 



**4.Vector是同步的，因此开销就比ArrayList要大，访问速度更慢。最好使用ArrayList而不是Vector,因为同步操作完全可以由程序员自己来控制。**



**5.在遍历上的区别：**

**Vector可以使用较老的迭代器的Enumeration,****ArrayList不支持**

**输出形式：ArrayList支持Iterator、ListIterator、foreach；Vector支持Iterator、ListIterator、 foreach、Enumeration**

### 3.替代方案

可以使用`Collections.synchronizedList();`得到一个线程安全的ArrayList。

```java
List<String> list = new ArrayList<>();
List<String> synList = Collections.synchronizedList(list);
```

也可以使用 concurrent 并发包下的 CopyOnWriteArrayList 类。

```java
List<String> list = new CopyOnWriteArrayList<>();
```



## CopyOnWriteArrayList

### 读写分离

写操作在一个复制的数组上进行，读操作还是在原始数组上进行，读写分离，互不影响。

写操作需要加锁，防止并发写入时导致写入数据丢失。

写操作结束后需要把原始数组指向新的复制数组。

```java
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}

final void setArray(Object[] a) {
    array = a;
}
```



### 使用场景

CopyOnWriteArrayList在写操作的同时允许读操作，大大提高了读操作的性能，因此很适合读多写少的应用场景。

但是 CopyOnWriteArrayList 有其缺陷：

- 内存占用：在写操作时需要复制一个新的数组，使得内存占用为原来的两倍左右；
- 数据不一致：读操作不能读取实时性的数据，因为部分写操作的数据还未同步到读数组中。

所以 CopyOnWriteArrayList 不适合内存敏感以及对实时性要求很高的场景。



## LinkedList

### 1.概览

基于双向链表实现，使用Node存储链表节点信息。

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}
```

每个链表存储了first和last指针

```java
transient Node<E> first;
transient Node<E> last;
```

![内部结构图](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/c8563120-cb00-4dd6-9213-9d9b337a7f7c.png)

### 2.与ArrayList的比较

- ArrayList基于动态数组实现，LinkedList基于双向链表实现
- ArrayList支持随机访问，LinkedList不支持
- LinkedList在任何位置添加删除元素更快

## HashMap

为了便于理解，一下源码分析以JDK1.7为主。

### 1.存储结构

内部包含一个Entry类型的数组table;

```java
transient Entry[] table;
```

Entry存储键值对，它包含了四个字段，从next字段我们可以看出Entry是一个链表。即数组中的每个位置被当作一个桶，一个桶存放一个链表。HashMap使用拉链法来解决冲突。同一个链表存放哈希值和散列桶取模运算结果相同Entry。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/9420a703-1f9d-42ce-808e-bcb82b56483d.png)

```java
static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    Entry<K,V> next;
    int hash;

    Entry(int h, K k, V v, Entry<K,V> n) {
        value = v;
        next = n;
        key = k;
        hash = h;
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry e = (Map.Entry)o;
        Object k1 = getKey();
        Object k2 = e.getKey();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            Object v1 = getValue();
            Object v2 = e.getValue();
            if (v1 == v2 || (v1 != null && v1.equals(v2)))
                return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
```



### 2.拉链法的工作原理

```java
HashMap<String, String> map = new HashMap<>();
map.put("K1", "V1");
map.put("K2", "V2");
map.put("K3", "V3");
```

- 新建立一个HashMap，**默认初始化大小为16.即table的初始化大小为16**
- 插入<k1,V1>键值对，先计算hashCode为115，使用除留余数法得到所在的桶下标 115%16=3。
- 插入 <K2,V2> 键值对，先计算 K2 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6。
- 插入 <K3,V3> 键值对，先计算 K3 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6，插在 <K2,V2> 前面。

应该注意到链表的插入是以头插法进行的，例如上面<k3,V3>不是插入在<K2,V2>后面的，而是插入在链表的头部

查找需要分两步进行。

- 计算键值对所在的桶；
- 在链表上顺序查找，时间复杂度显然和链表的长度成正比。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/e0870f80-b79e-4542-ae39-7420d4b0d8fe.png)

### 3.put操作

```java
public V put(K key, V value) {
    if (table == EMPTY_TABLE) {
        inflateTable(threshold);
    }
    // 键为 null 单独处理
    if (key == null)
        return putForNullKey(value);
    int hash = hash(key);
    // 确定桶下标
    int i = indexFor(hash, table.length);
    // 先找出是否已经存在键为 key 的键值对，如果存在的话就更新这个键值对的值为 value
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    // 插入新键值对
    addEntry(hash, key, value, i);
    return null;
}
```

HashMap允许插入键值为null的键值对，但是因为无法调用null的hashCode()方法，也就无法确定该键值对的桶的下标，只能通过强制指定一个桶来存放。HashMap 使用第 0 个桶存放键为 null 的键值对。

```java
private V putForNullKey(V value) {
    for (Entry<K,V> e = table[0]; e != null; e = e.next) {
        if (e.key == null) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(0, null, value, 0);
    return null;
}
```



### 4.确定桶下标

很多操作需要确定一个键值对所在的桶下标

```java
int hash = hash(key);
int i = indexFor(hash, table.length);
```

#### 4.1计算hash值

```java
final int hash(Object k) {
    int h = hashSeed;
    if (0 != h && k instanceof String) {
        return sun.misc.Hashing.stringHash32((String) k);
    }

    h ^= k.hashCode();

    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```

```java
public final int hashCode() {
    return Objects.hashCode(key) ^ Objects.hashCode(value);
}
```

#### 4.2取模

令 x = 1<<4，即 x 为 2 的 4 次方，它具有以下性质：

```
x   : 00010000
x-1 : 00001111Copy to clipboardErrorCopied
```

令一个数 y 与 x-1 做与运算，可以去除 y 位级表示的第 4 位以上数：

```
y       : 10110010
x-1     : 00001111
y&(x-1) : 00000010Copy to clipboardErrorCopied
```

这个性质和 y 对 x 取模效果是一样的：

```
y   : 10110010
x   : 00010000
y%x : 00000010Copy to clipboardErrorCopied
```

我们知道，位运算的代价比求模运算小的多，因此在进行这种计算时用位运算的话能带来更高的性能。

确定桶下标的最后一步是将 key 的 hash 值对桶个数取模：hash%capacity，如果能保证 capacity 为 2 的 n 次方，那么就可以将这个操作转换为位运算。

```java
static int indexFor(int h, int length) {
    return h & (length-1);
}
```

### 5.扩容-基本原理

设HashMap的table长度为M,需要存储的键值对数量为N，如果哈希函数满足均匀性的要求，那么设每条链表长度大约为N/M,因此平均查找次数的时间复杂度是O(N/M)。

为了让查找的成本更低，应该尽可能使得N/M尽可能的小，因此需要办证M尽可能的大，也就是说table要尽可能的大。HashMap采用动态扩容来根据当前N值来调整M值，使得空间效率和时间效率都能够得到保证。

和扩容相关的参数主要有：capacity、size、threshold 和 load_factor。

| 参数       | 含义                                                         |
| ---------- | ------------------------------------------------------------ |
| capacity   | table 的容量大小，默认为 16。需要注意的是 capacity 必须保证为 2 的 n 次方。 |
| size       | 键值对数量。                                                 |
| threshold  | size 的临界值，当 size 大于等于 threshold 就必须进行扩容操作。 |
| loadFactor | 装载因子，table 能够使用的比例，threshold = capacity * loadFactor。 |

从下面的添加元素代码中可以看出，当需要扩容时，令 capacity 为原来的两倍

扩容使用 resize() 实现，需要注意的是，扩容操作同样需要把 oldTable 的所有键值对重新插入 newTable 中，因此这一步是很费时的。

### 6.扩容-重新计算桶下标

在进行扩容时，需要把键值对重新放到对应的桶上。HashMap 使用了一个特殊的机制，可以降低重新计算桶下标的操作。

假设原数组长度 capacity 为 16，扩容之后 new capacity 为 32

```java
capacity     : 00010000
new capacity : 00100000
```

对于一个 Key，

- 它的哈希值如果在第 5 位上为 0，那么取模得到的结果和之前一样；
- 如果为 1，那么得到的结果为原来的结果 +16。



**JDK1.8中采用的是(e.hash & oldCap) 方法不是JDK1.7中的（e.hash & newCap-1）**

**这样采用的好处是，(e.hash & oldCap)由于oldCap的值是2的n次方，所以答案只会有0 / oldCap。**

**简化计算。新的下标保持不变或者移动OldCap的距离 即（2次幂的位置）。**

https://blog.csdn.net/runrun117/article/details/80249556

https://www.cnblogs.com/shianliang/p/9204942.html

### 7.计算数组容量

HashMap构造函数允许用户传入的容量不是2的你次方，因为它可以自动将传入的容量转换1为2的n次方。

```java
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```



### 8.链表转化为红黑树

从 JDK 1.8 开始，一个桶存储的链表长度大于 8 时，且HashMap中的元素个数大于64个时会将链表转换为红黑树。

## JDK1.8的HashMap

  [JDK1.8的HashMap](https://www.wonderheng.top/2019/02/16/（转）Java中HashMap底层实现原理（JDK1-8）源码分析/)

HashMap的存取机制

- get(key)方法时获取key的hash值，计算hash&(n-1)得到在链表数组中的位置first=tab[hash&(n-1)],先判断first的key是否与参数key相等，不等就遍历后面的链表找到相同的key值返回对应的Value值即可

- 下面简单说下添加键值对put(key,value)的过程：
  1、判断键值对数组tab[]是否为空或为null，否则以默认大小resize()； 后面的HashMap是先插入元素再resize(),仅仅只有首次是需要先resize()的
  2、根据键值key计算hash值得到插入的数组索引i，如果tab[i]==null，直接新建节点添加，否则转入3
  3、判断当前数组中处理hash冲突的方式为链表还是红黑树(check第一个节点类型即可),分别处理

  4、当前数组中处理hash冲突的方式是链表的化，就在链表的尾部添加元素（尾插法），假如在插入过程中出现链表的个数大于等于8时，判断当前链表元素个数是否达到了64个，达到的化就将链表变成红黑树。

  反之则进行常规的扩容。

HashMap的扩容机制

**构造hash表时，如果不指明初始大小，默认大小为16（即Node数组大小16），如果Node[]数组中的元素达到（填充比\*Node.length）重新调整HashMap大小 变为原来2倍大小,**扩容很耗时。



JDK1.7和1.8红黑树对比：在jdk8中对HashMap的源码进行了优化，在jdk7中，HashMap处理“碰撞”的时候，都是采用链表来存储，当碰撞的结点很多时，查询时间是O（n）。
在jdk8中，**HashMap处理“碰撞”增加了红黑树这种数据结构，当碰撞结点较少时，采用链表存储，当较大时（>8个），采用红黑树（特点是查询时间是O（logn））存储（有一个阀值控制，大于阀值(8个)，将链表存储转换成红黑树存储）**

## ConCurrentHashMap

### [1. 存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-存储结构-1)

```java
static final class HashEntry<K,V> {
    final int hash;
    final K key;
    volatile V value;
    volatile HashEntry<K,V> next;
}Copy to clipboardErrorCopied
```

ConcurrentHashMap 和 HashMap 实现上类似，最主要的差别是 ConcurrentHashMap 采用了分段锁（Segment），每个分段锁维护着几个桶（HashEntry），多个线程可以同时访问不同分段锁上的桶，从而使其并发度更高（并发度就是 Segment 的个数）。

Segment 继承自 ReentrantLock（可重入锁）。

# [CS-Notes](https://cyc2018.github.io/CS-Notes/)

- [一、概览](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=一、概览)

- - [Collection](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=collection)

  - - [1. Set](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-set)
    - [2. List](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-list)
    - [3. Queue](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-queue)

  - [Map](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=map)

- [二、容器中的设计模式](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=二、容器中的设计模式)

- - [迭代器模式](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=迭代器模式)
  - [适配器模式](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=适配器模式)

- [三、源码分析](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=三、源码分析)

- - [ArrayList](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=arraylist)

  - - [1. 概览](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-概览)
    - [2. 扩容](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-扩容)
    - [3. 删除元素](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-删除元素)
    - [4. Fail-Fast](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_4-fail-fast)
    - [5. 序列化](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_5-序列化)

  - [Vector](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=vector)

  - - [1. 同步](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-同步)
    - [2. 与 ArrayList 的比较](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-与-arraylist-的比较)
    - [3. 替代方案](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-替代方案)

  - [CopyOnWriteArrayList](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=copyonwritearraylist)

  - - [读写分离](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=读写分离)
    - [适用场景](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=适用场景)

  - [LinkedList](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=linkedlist)

  - - [1. 概览](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-概览-1)
    - [2. 与 ArrayList 的比较](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-与-arraylist-的比较-1)

  - [HashMap](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=hashmap)

  - - [1. 存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-存储结构)
    - [2. 拉链法的工作原理](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-拉链法的工作原理)
    - [3. put 操作](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-put-操作)
    - [4. 确定桶下标](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_4-确定桶下标)
    - [5. 扩容-基本原理](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_5-扩容-基本原理)
    - [6. 扩容-重新计算桶下标](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_6-扩容-重新计算桶下标)
    - [7. 计算数组容量](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_7-计算数组容量)
    - [8. 链表转红黑树](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_8-链表转红黑树)
    - [9. 与 HashTable 的比较](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_9-与-hashtable-的比较)

  - [ConcurrentHashMap](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=concurrenthashmap)

  - - [1. 存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-存储结构-1)
    - [2. size 操作](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-size-操作)
    - [3. JDK 1.8 的改动](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-jdk-18-的改动)

  - [LinkedHashMap](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=linkedhashmap)

  - - [存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=存储结构)
    - [afterNodeAccess()](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=afternodeaccess)
    - [afterNodeInsertion()](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=afternodeinsertion)
    - [LRU 缓存](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=lru-缓存)

  - [WeakHashMap](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=weakhashmap)

  - - [存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=存储结构-1)
    - [ConcurrentCache](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=concurrentcache)

- [参考资料](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=参考资料)

- [微信公众号](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=微信公众号)

# [一、概览](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=一、概览)

容器主要包括 Collection 和 Map 两种，Collection 存储着对象的集合，而 Map 存储着键值对（两个对象）的映射表。

## [Collection](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=collection)

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/73403d84-d921-49f1-93a9-d8fe050f3497.png)



### [1. Set](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-set)

- TreeSet：基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如 HashSet，HashSet 查找的时间复杂度为 O(1)，TreeSet 则为 O(logN)。
- HashSet：基于哈希表实现，支持快速查找，但不支持有序性操作。并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的。
- LinkedHashSet：具有 HashSet 的查找效率，且内部使用双向链表维护元素的插入顺序。

### [2. List](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-list)

- ArrayList：基于动态数组实现，支持随机访问。
- Vector：和 ArrayList 类似，但它是线程安全的。
- LinkedList：基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList 还可以用作栈、队列和双向队列。

### [3. Queue](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-queue)

- LinkedList：可以用它来实现双向队列。
- PriorityQueue：基于堆结构实现，可以用它来实现优先队列。

## [Map](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=map)

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/774d756b-902a-41a3-a3fd-81ca3ef688dc.png)



- TreeMap：基于红黑树实现。
- HashMap：基于哈希表实现。
- HashTable：和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，不应该去使用它。现在可以使用 ConcurrentHashMap 来支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁。
- LinkedHashMap：使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。

# [二、容器中的设计模式](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=二、容器中的设计模式)

## [迭代器模式](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=迭代器模式)

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/93fb1d38-83f9-464a-a733-67b2e6bfddda.png)



Collection 继承了 Iterable 接口，其中的 iterator() 方法能够产生一个 Iterator 对象，通过这个对象就可以迭代遍历 Collection 中的元素。

从 JDK 1.5 之后可以使用 foreach 方法来遍历实现了 Iterable 接口的聚合对象。

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
for (String item : list) {
    System.out.println(item);
}Copy to clipboardErrorCopied
```

## [适配器模式](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=适配器模式)

java.util.Arrays#asList() 可以把数组类型转换为 List 类型。

```java
@SafeVarargs
public static <T> List<T> asList(T... a)Copy to clipboardErrorCopied
```

应该注意的是 asList() 的参数为泛型的变长参数，不能使用基本类型数组作为参数，只能使用相应的包装类型数组。

```java
Integer[] arr = {1, 2, 3};
List list = Arrays.asList(arr);Copy to clipboardErrorCopied
```

也可以使用以下方式调用 asList()：

```java
List list = Arrays.asList(1, 2, 3);Copy to clipboardErrorCopied
```

# [三、源码分析](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=三、源码分析)

如果没有特别说明，以下源码分析基于 JDK 1.8。

在 IDEA 中 double shift 调出 Search EveryWhere，查找源码文件，找到之后就可以阅读源码。

## [ArrayList](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=arraylist)

### [1. 概览](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-概览)

因为 ArrayList 是基于数组实现的，所以支持快速随机访问。RandomAccess 接口标识着该类支持快速随机访问。

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.SerializableCopy to clipboardErrorCopied
```

数组的默认大小为 10。

```java
private static final int DEFAULT_CAPACITY = 10;Copy to clipboardErrorCopied
```

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/52a7744f-5bce-4ff3-a6f0-8449334d9f3d.png)



### [2. 扩容](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-扩容)

添加元素时使用 ensureCapacityInternal() 方法来保证容量足够，如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 `oldCapacity + (oldCapacity >> 1)`，也就是旧容量的 1.5 倍。

扩容操作需要调用 `Arrays.copyOf()` 把原数组整个复制到新数组中，这个操作代价很高，因此最好在创建 ArrayList 对象时就指定大概的容量大小，减少扩容操作的次数。

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}

private void ensureCapacityInternal(int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}Copy to clipboardErrorCopied
```

### [3. 删除元素](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-删除元素)

需要调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，该操作的时间复杂度为 O(N)，可以看出 ArrayList 删除元素的代价是非常高的。

```java
public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work
    return oldValue;
}Copy to clipboardErrorCopied
```

### [4. Fail-Fast](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_4-fail-fast)

modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代等操作时，需要比较操作前后 modCount 是否改变，如果改变了需要抛出 ConcurrentModificationException。

```java
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}Copy to clipboardErrorCopied
```

### [5. 序列化](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_5-序列化)

ArrayList 基于数组实现，并且具有动态扩容特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。

保存元素的数组 elementData 使用 transient 修饰，该关键字声明数组默认不会被序列化。

```java
transient Object[] elementData; // non-private to simplify nested class accessCopy to clipboardErrorCopied
```

ArrayList 实现了 writeObject() 和 readObject() 来控制只序列化数组中有元素填充那部分内容。

```java
private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    elementData = EMPTY_ELEMENTDATA;

    // Read in size, and any hidden stuff
    s.defaultReadObject();

    // Read in capacity
    s.readInt(); // ignored

    if (size > 0) {
        // be like clone(), allocate array based upon size not capacity
        ensureCapacityInternal(size);

        Object[] a = elementData;
        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            a[i] = s.readObject();
        }
    }
}Copy to clipboardErrorCopied
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}Copy to clipboardErrorCopied
```

序列化时需要使用 ObjectOutputStream 的 writeObject() 将对象转换为字节流并输出。而 writeObject() 方法在传入的对象存在 writeObject() 的时候会去反射调用该对象的 writeObject() 来实现序列化。反序列化使用的是 ObjectInputStream 的 readObject() 方法，原理类似。

```java
ArrayList list = new ArrayList();
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
oos.writeObject(list);Copy to clipboardErrorCopied
```

## [Vector](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=vector)

### [1. 同步](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-同步)

它的实现与 ArrayList 类似，但是使用了 synchronized 进行同步。

```java
public synchronized boolean add(E e) {
    modCount++;
    ensureCapacityHelper(elementCount + 1);
    elementData[elementCount++] = e;
    return true;
}

public synchronized E get(int index) {
    if (index >= elementCount)
        throw new ArrayIndexOutOfBoundsException(index);

    return elementData(index);
}Copy to clipboardErrorCopied
```

### [2. 与 ArrayList 的比较](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-与-arraylist-的比较)

- Vector 是同步的，因此开销就比 ArrayList 要大，访问速度更慢。最好使用 ArrayList 而不是 Vector，因为同步操作完全可以由程序员自己来控制；
- Vector 每次扩容请求其大小的 2 倍空间，而 ArrayList 是 1.5 倍。

### [3. 替代方案](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-替代方案)

可以使用 `Collections.synchronizedList();` 得到一个线程安全的 ArrayList。

```java
List<String> list = new ArrayList<>();
List<String> synList = Collections.synchronizedList(list);Copy to clipboardErrorCopied
```

也可以使用 concurrent 并发包下的 CopyOnWriteArrayList 类。

```java
List<String> list = new CopyOnWriteArrayList<>();Copy to clipboardErrorCopied
```

## [CopyOnWriteArrayList](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=copyonwritearraylist)

### [读写分离](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=读写分离)

写操作在一个复制的数组上进行，读操作还是在原始数组中进行，读写分离，互不影响。

写操作需要加锁，防止并发写入时导致写入数据丢失。

写操作结束之后需要把原始数组指向新的复制数组。

```java
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}

final void setArray(Object[] a) {
    array = a;
}Copy to clipboardErrorCopied
@SuppressWarnings("unchecked")
private E get(Object[] a, int index) {
    return (E) a[index];
}Copy to clipboardErrorCopied
```

### [适用场景](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=适用场景)

CopyOnWriteArrayList 在写操作的同时允许读操作，大大提高了读操作的性能，因此很适合读多写少的应用场景。

但是 CopyOnWriteArrayList 有其缺陷：

- 内存占用：在写操作时需要复制一个新的数组，使得内存占用为原来的两倍左右；
- 数据不一致：读操作不能读取实时性的数据，因为部分写操作的数据还未同步到读数组中。

所以 CopyOnWriteArrayList 不适合内存敏感以及对实时性要求很高的场景。

## [LinkedList](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=linkedlist)

### [1. 概览](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-概览-1)

基于双向链表实现，使用 Node 存储链表节点信息。

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}Copy to clipboardErrorCopied
```

每个链表存储了 first 和 last 指针：

```java
transient Node<E> first;
transient Node<E> last;Copy to clipboardErrorCopied
```

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/c8563120-cb00-4dd6-9213-9d9b337a7f7c.png)



### [2. 与 ArrayList 的比较](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-与-arraylist-的比较-1)

- ArrayList 基于动态数组实现，LinkedList 基于双向链表实现；
- ArrayList 支持随机访问，LinkedList 不支持；
- LinkedList 在任意位置添加删除元素更快。

## [HashMap](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=hashmap)

为了便于理解，以下源码分析以 JDK 1.7 为主。

### [1. 存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-存储结构)

内部包含了一个 Entry 类型的数组 table。

```java
transient Entry[] table;Copy to clipboardErrorCopied
```

Entry 存储着键值对。它包含了四个字段，从 next 字段我们可以看出 Entry 是一个链表。即数组中的每个位置被当成一个桶，一个桶存放一个链表。HashMap 使用拉链法来解决冲突，同一个链表中存放哈希值和散列桶取模运算结果相同的 Entry。

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/9420a703-1f9d-42ce-808e-bcb82b56483d.png)



```java
static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    Entry<K,V> next;
    int hash;

    Entry(int h, K k, V v, Entry<K,V> n) {
        value = v;
        next = n;
        key = k;
        hash = h;
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry e = (Map.Entry)o;
        Object k1 = getKey();
        Object k2 = e.getKey();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            Object v1 = getValue();
            Object v2 = e.getValue();
            if (v1 == v2 || (v1 != null && v1.equals(v2)))
                return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}Copy to clipboardErrorCopied
```

### [2. 拉链法的工作原理](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-拉链法的工作原理)

```java
HashMap<String, String> map = new HashMap<>();
map.put("K1", "V1");
map.put("K2", "V2");
map.put("K3", "V3");Copy to clipboardErrorCopied
```

- 新建一个 HashMap，默认大小为 16；
- 插入 <K1,V1> 键值对，先计算 K1 的 hashCode 为 115，使用除留余数法得到所在的桶下标 115%16=3。
- 插入 <K2,V2> 键值对，先计算 K2 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6。
- 插入 <K3,V3> 键值对，先计算 K3 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6，插在 <K2,V2> 前面。

应该注意到链表的插入是以头插法方式进行的，例如上面的 <K3,V3> 不是插在 <K2,V2> 后面，而是插入在链表头部。

查找需要分成两步进行：

- 计算键值对所在的桶；
- 在链表上顺序查找，时间复杂度显然和链表的长度成正比。

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/e0870f80-b79e-4542-ae39-7420d4b0d8fe.png)



### [3. put 操作](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_3-put-操作)

```java
public V put(K key, V value) {
    if (table == EMPTY_TABLE) {
        inflateTable(threshold);
    }
    // 键为 null 单独处理
    if (key == null)
        return putForNullKey(value);
    int hash = hash(key);
    // 确定桶下标
    int i = indexFor(hash, table.length);
    // 先找出是否已经存在键为 key 的键值对，如果存在的话就更新这个键值对的值为 value
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }

    modCount++;
    // 插入新键值对
    addEntry(hash, key, value, i);
    return null;
}Copy to clipboardErrorCopied
```

HashMap 允许插入键为 null 的键值对。但是因为无法调用 null 的 hashCode() 方法，也就无法确定该键值对的桶下标，只能通过强制指定一个桶下标来存放。HashMap 使用第 0 个桶存放键为 null 的键值对。

```java
private V putForNullKey(V value) {
    for (Entry<K,V> e = table[0]; e != null; e = e.next) {
        if (e.key == null) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(0, null, value, 0);
    return null;
}Copy to clipboardErrorCopied
```

使用链表的头插法，也就是新的键值对插在链表的头部，而不是链表的尾部。

```java
void addEntry(int hash, K key, V value, int bucketIndex) {
    if ((size >= threshold) && (null != table[bucketIndex])) {
        resize(2 * table.length);
        hash = (null != key) ? hash(key) : 0;
        bucketIndex = indexFor(hash, table.length);
    }

    createEntry(hash, key, value, bucketIndex);
}

void createEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    // 头插法，链表头部指向新的键值对
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    size++;
}Copy to clipboardErrorCopied
Entry(int h, K k, V v, Entry<K,V> n) {
    value = v;
    next = n;
    key = k;
    hash = h;
}Copy to clipboardErrorCopied
```

### [4. 确定桶下标](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_4-确定桶下标)

很多操作都需要先确定一个键值对所在的桶下标。

```java
int hash = hash(key);
int i = indexFor(hash, table.length);Copy to clipboardErrorCopied
```

**4.1 计算 hash 值**

```java
final int hash(Object k) {
    int h = hashSeed;
    if (0 != h && k instanceof String) {
        return sun.misc.Hashing.stringHash32((String) k);
    }

    h ^= k.hashCode();

    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}Copy to clipboardErrorCopied
public final int hashCode() {
    return Objects.hashCode(key) ^ Objects.hashCode(value);
}Copy to clipboardErrorCopied
```

**4.2 取模**

令 x = 1<<4，即 x 为 2 的 4 次方，它具有以下性质：

```
x   : 00010000
x-1 : 00001111Copy to clipboardErrorCopied
```

令一个数 y 与 x-1 做与运算，可以去除 y 位级表示的第 4 位以上数：

```
y       : 10110010
x-1     : 00001111
y&(x-1) : 00000010Copy to clipboardErrorCopied
```

这个性质和 y 对 x 取模效果是一样的：

```
y   : 10110010
x   : 00010000
y%x : 00000010Copy to clipboardErrorCopied
```

我们知道，位运算的代价比求模运算小的多，因此在进行这种计算时用位运算的话能带来更高的性能。

确定桶下标的最后一步是将 key 的 hash 值对桶个数取模：hash%capacity，如果能保证 capacity 为 2 的 n 次方，那么就可以将这个操作转换为位运算。

```java
static int indexFor(int h, int length) {
    return h & (length-1);
}Copy to clipboardErrorCopied
```

### [5. 扩容-基本原理](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_5-扩容-基本原理)

设 HashMap 的 table 长度为 M，需要存储的键值对数量为 N，如果哈希函数满足均匀性的要求，那么每条链表的长度大约为 N/M，因此平均查找次数的复杂度为 O(N/M)。

为了让查找的成本降低，应该尽可能使得 N/M 尽可能小，因此需要保证 M 尽可能大，也就是说 table 要尽可能大。HashMap 采用动态扩容来根据当前的 N 值来调整 M 值，使得空间效率和时间效率都能得到保证。

和扩容相关的参数主要有：capacity、size、threshold 和 load_factor。

| 参数       | 含义                                                         |
| ---------- | ------------------------------------------------------------ |
| capacity   | table 的容量大小，默认为 16。需要注意的是 capacity 必须保证为 2 的 n 次方。 |
| size       | 键值对数量。                                                 |
| threshold  | size 的临界值，当 size 大于等于 threshold 就必须进行扩容操作。 |
| loadFactor | 装载因子，table 能够使用的比例，threshold = capacity * loadFactor。 |

```java
static final int DEFAULT_INITIAL_CAPACITY = 16;

static final int MAXIMUM_CAPACITY = 1 << 30;

static final float DEFAULT_LOAD_FACTOR = 0.75f;

transient Entry[] table;

transient int size;

int threshold;

final float loadFactor;

transient int modCount;Copy to clipboardErrorCopied
```

从下面的添加元素代码中可以看出，当需要扩容时，令 capacity 为原来的两倍。

```java
void addEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    if (size++ >= threshold)
        resize(2 * table.length);
}Copy to clipboardErrorCopied
```

扩容使用 resize() 实现，需要注意的是，扩容操作同样需要把 oldTable 的所有键值对重新插入 newTable 中，因此这一步是很费时的。

```java
void resize(int newCapacity) {
    Entry[] oldTable = table;
    int oldCapacity = oldTable.length;
    if (oldCapacity == MAXIMUM_CAPACITY) {
        threshold = Integer.MAX_VALUE;
        return;
    }
    Entry[] newTable = new Entry[newCapacity];
    transfer(newTable);
    table = newTable;
    threshold = (int)(newCapacity * loadFactor);
}

void transfer(Entry[] newTable) {
    Entry[] src = table;
    int newCapacity = newTable.length;
    for (int j = 0; j < src.length; j++) {
        Entry<K,V> e = src[j];
        if (e != null) {
            src[j] = null;
            do {
                Entry<K,V> next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            } while (e != null);
        }
    }
}Copy to clipboardErrorCopied
```

### [6. 扩容-重新计算桶下标](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_6-扩容-重新计算桶下标)

在进行扩容时，需要把键值对重新放到对应的桶上。HashMap 使用了一个特殊的机制，可以降低重新计算桶下标的操作。

假设原数组长度 capacity 为 16，扩容之后 new capacity 为 32：

```html
capacity     : 00010000
new capacity : 00100000
```

对于一个 Key，

- 它的哈希值如果在第 5 位上为 0，那么取模得到的结果和之前一样；
- 如果为 1，那么得到的结果为原来的结果 +16。

### [7. 计算数组容量](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_7-计算数组容量)

HashMap 构造函数允许用户传入的容量不是 2 的 n 次方，因为它可以自动地将传入的容量转换为 2 的 n 次方。

先考虑如何求一个数的掩码，对于 10010000，它的掩码为 11111111，可以使用以下方法得到：

```
mask |= mask >> 1    11011000
mask |= mask >> 2    11111110
mask |= mask >> 4    11111111Copy to clipboardErrorCopied
```

mask+1 是大于原始数字的最小的 2 的 n 次方。

```
num     10010000
mask+1 100000000Copy to clipboardErrorCopied
```

以下是 HashMap 中计算数组容量的代码：

```java
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}Copy to clipboardErrorCopied
```

### [8. 链表转红黑树](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_8-链表转红黑树)

从 JDK 1.8 开始，一个桶存储的链表长度大于 8 时会将链表转换为红黑树。

### [9. 与 HashTable 的比较](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_9-与-hashtable-的比较)

- HashTable 使用 synchronized 来进行同步。
- HashMap 可以插入键为 null 的 Entry。
- HashMap 的迭代器是 fail-fast 迭代器。
- HashMap 不能保证随着时间的推移 Map 中的元素次序是不变的。

## [ConcurrentHashMap](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=concurrenthashmap)

### [1. 存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_1-存储结构-1)

```java
static final class HashEntry<K,V> {
    final int hash;
    final K key;
    volatile V value;
    volatile HashEntry<K,V> next;
}Copy to clipboardErrorCopied
```

ConcurrentHashMap 和 HashMap 实现上类似，最主要的差别是 ConcurrentHashMap 采用了分段锁（Segment），每个分段锁维护着几个桶（HashEntry），多个线程可以同时访问不同分段锁上的桶，从而使其并发度更高（并发度就是 Segment 的个数）。

Segment 继承自 ReentrantLock。

```java
static final class Segment<K,V> extends ReentrantLock implements Serializable {

    private static final long serialVersionUID = 2249069246763182397L;

    static final int MAX_SCAN_RETRIES =
        Runtime.getRuntime().availableProcessors() > 1 ? 64 : 1;

    transient volatile HashEntry<K,V>[] table;

    transient int count;

    transient int modCount;

    transient int threshold;

    final float loadFactor;
}Copy to clipboardErrorCopied
final Segment<K,V>[] segments;Copy to clipboardErrorCopied
```

```java
final Segment<K,V>[] segments;
```


默认的并发级别为 16，也就是说默认创建 16 个 Segment。

```java
static final int DEFAULT_CONCURRENCY_LEVEL = 16;
```

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/db808eff-31d7-4229-a4ad-b8ae71870a3a.png)



### [2. size 操作](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=_2-size-操作)

每个 Segment 维护了一个 count 变量来统计该 Segment 中的键值对个数。

```java
/**
 * The number of elements. Accessed only either within locks
 * or among other volatile reads that maintain visibility.
 */
transient int count;
```

在执行 size 操作时，需要遍历所有 Segment 然后把 count 累计起来。

ConcurrentHashMap 在执行 size 操作时先尝试不加锁，如果连续两次不加锁操作得到的结果一致，那么可以认为这个结果是正确的。

尝试次数使用 RETRIES_BEFORE_LOCK 定义，该值为 2，retries 初始值为 -1，因此尝试次数为 3。

如果尝试的次数超过 3 次，就需要对每个 Segment 加锁。

```java

/**
 * Number of unsynchronized retries in size and containsValue
 * methods before resorting to locking. This is used to avoid
 * unbounded retries if tables undergo continuous modification
 * which would make it impossible to obtain an accurate result.
 */
static final int RETRIES_BEFORE_LOCK = 2;

public int size() {
    // Try a few times to get accurate count. On failure due to
    // continuous async changes in table, resort to locking.
    final Segment<K,V>[] segments = this.segments;
    int size;
    boolean overflow; // true if size overflows 32 bits
    long sum;         // sum of modCounts
    long last = 0L;   // previous sum
    int retries = -1; // first iteration isn't retry
    try {
        for (;;) {
            // 超过尝试次数，则对每个 Segment 加锁
            if (retries++ == RETRIES_BEFORE_LOCK) {
                for (int j = 0; j < segments.length; ++j)
                    ensureSegment(j).lock(); // force creation
            }
            sum = 0L;
            size = 0;
            overflow = false;
            for (int j = 0; j < segments.length; ++j) {
                Segment<K,V> seg = segmentAt(segments, j);
                if (seg != null) {
                    sum += seg.modCount;
                    int c = seg.count;
                    if (c < 0 || (size += c) < 0)
                        overflow = true;
                }
            }
            // 连续两次得到的结果一致，则认为这个结果是正确的
            if (sum == last)
                break;
            last = sum;
        }
    } finally {
        if (retries > RETRIES_BEFORE_LOCK) {
            for (int j = 0; j < segments.length; ++j)
                segmentAt(segments, j).unlock();
        }
    }
    return overflow ? Integer.MAX_VALUE : size;
}
```

### 3.JDK1.8的改动

JDK 1.7 使用分段锁机制来实现并发更新操作，核心类为 Segment，它继承自重入锁 ReentrantLock，并发度与 Segment 数量相等。

JDK 1.8 使用了 CAS 操作来支持更高的并发度，在 CAS 操作失败时使用内置锁 synchronized。

并且 JDK 1.8 的实现也在链表过长时会转换为红黑树。

## LinkedHashMap

### 存储结构

继承自HashMap，因此具有和HashMap一样的快速查找特性。

```java
public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
```

内部维护了一个双向链表，用来维护插入顺序或者LRU顺序。

```java
/**
 * The head (eldest) of the doubly linked list.
 */
transient LinkedHashMap.Entry<K,V> head;

/**
 * The tail (youngest) of the doubly linked list.
 */
transient LinkedHashMap.Entry<K,V> tail;
```

accessOrder 决定了顺序，默认为 false，此时维护的是插入顺序。

```java
final boolean accessOrder;
```

LinkedHashMap 最重要的是以下用于维护顺序的函数，它们会在 put、get 等方法中调用。

```java
void afterNodeAccess(Node<K,V> p) { }
void afterNodeInsertion(boolean evict) { }
```

### [afterNodeAccess()](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=afternodeaccess)

当一个节点被访问时，如果 accessOrder 为 true，则会将该节点移到链表尾部。也就是说指定为 LRU 顺序之后，在每次访问一个节点时，会将这个节点移到链表尾部，保证链表尾部是最近访问的节点，那么链表首部就是最近最久未使用的节点。

```java
void afterNodeAccess(Node<K,V> e) { // move node to last
    LinkedHashMap.Entry<K,V> last;
    if (accessOrder && (last = tail) != e) {
        LinkedHashMap.Entry<K,V> p =
            (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
        p.after = null;
        if (b == null)
            head = a;
        else
            b.after = a;
        if (a != null)
            a.before = b;
        else
            last = b;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
        tail = p;
        ++modCount;
    }
}
```

### [afterNodeInsertion()](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=afternodeinsertion)

在 put 等操作之后执行，当 removeEldestEntry() 方法返回 true 时会移除最晚的节点，也就是链表首部节点 first。

evict 只有在构建 Map 的时候才为 false，在这里为 true。

```java
void afterNodeInsertion(boolean evict) { // possibly remove eldest
    LinkedHashMap.Entry<K,V> first;
    if (evict && (first = head) != null && removeEldestEntry(first)) {
        K key = first.key;
        removeNode(hash(key), key, null, false, true);
    }
}
```

removeEldestEntry() 默认为 false，如果需要让它为 true，需要继承 LinkedHashMap 并且覆盖这个方法的实现，这在实现 LRU 的缓存中特别有用，通过移除最近最久未使用的节点，从而保证缓存空间足够，并且缓存的数据都是热点数据。

```java
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return false;
}
```

### [LRU 缓存](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=lru-缓存)

以下是使用 LinkedHashMap 实现的一个 LRU 缓存：

- 设定最大缓存空间 MAX_ENTRIES 为 3；
- 使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
- 覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。

```java
class LRUCache<K,V> extends LinkedHashMap<K,V> {
	private static final int MAX_ENTRIES = 3;
	
	protected bollean removeEldestEntry(Map.Entry eldest) {
		return size() > Max_ENTRIES;
	}
	LRUCache() {
		super(MAX_ENTRIES,0.75f,true);
	}
}
```



## WeakHashMap

### [存储结构](https://cyc2018.github.io/CS-Notes/#/notes/Java 容器?id=存储结构-1)

WeakHashMap 的 Entry 继承自 WeakReference，被 WeakReference 关联的对象在下一次垃圾回收时会被回收。

WeakHashMap 主要用来实现缓存，通过使用 WeakHashMap 来引用缓存对象，由 JVM 对这部分缓存进行回收。

```java
private static class Entry<K,V> extends WeakReference<Object> implem
```



## ConCurrentCache

Tomcat中的ConcurrentCache使用的WeakHashMap来实现缓存功能。

ConCurrentCache采取的是分代缓存。

- 经常使用的对象放入eden中，eden使用ConcurrentHashMap实现，不用担心被回收（伊甸区）；
- 不常用的对象放入longterm,longterm使用WeakHashMap实现，这些对象会被垃圾收集器回收。
- 当调用get()方法时，会先从eden区获取，如果没有找到的话，再到longterm获取，当从longterm获取到就把对象放入eden中，从而保证经常被访问节点不容易被回收。
- 当调用put()方法时，如果eden的大小超过了size,那么就将eden中的所有对象都放入longterm中，利用虚拟机回收掉一部分不经常使用的对象。

```java
public final class ConcurrentCache<K, V> {

    private final int size;

    private final Map<K, V> eden;

    private final Map<K, V> longterm;

    public ConcurrentCache(int size) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longterm = new WeakHashMap<>(size);
    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            v = this.longterm.get(k);
            if (v != null)
                this.eden.put(k, v);
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            this.longterm.putAll(this.eden);
            this.eden.clear();
        }
        this.eden.put(k, v);
    }
}

```





# 类集面试题（多线程+数据结构）

## 1.ArrayList、LinkedList、Vector的关系于区别



**区别：**

**底层实现**

- ArrayList是基于数组实现List,采用懒加载策略，当一次调用add方法时，数组才会初始化为10(默认)
- Vector是基于数组实现的List集合，当调用无参构造时，产生Vector对象时，就初始化大小为10的数组。

**源码方面底层扩容机制**

- ArrayList每次扩容为原先数组的容量的1.5倍
- Vector每次扩容为原先数组的容量的2倍

**线程安全**

- ArrayList性能高效，异步操作性能较高
- Vector（JDK1.0）采用同步方法保证线程安全，性能低(读读互斥)

**LinkedList**

- LinkedList是基于双向循环链表实现的List集合,采用异步处理，线程不安全。

- LinkedList在中间位置插入的性能比ArrayList快，但是在普通的添加元素在尾部插入的情况下ArrayList的性能高。
- LinkedList在任意位置插入或者删除时考虑使用LinkedList或需要使用队列的场合.因为LinkedList不仅实现了List接口还实现了了Deque接口
- ArrayList和Vector实现了RandomAccess标志接口，在使用Collections工具方法的排序或者查找方法时会采用for循环的方式遍历效率高，LinkedList未实现RandomAccess标志接口,在遍历时就会采用迭代器方式去遍历。



## Fail-Fast机制

**快速失败：优先考虑有限异常，当异常产生时，直接抛出，程序终止。**

`ConcurrentModificationException`

为了保证遍历集合的时候不会产生脏数据

**快速失败的作用**：在迭代输出集合过程中修改了结构(remove、add)抛出此异常，为了保证多线程场景下多线程取得数据正确性。



**如何产生的？**

modCount记录当前集合被修改的次数

expectedModeCount取得迭代器时，会创建迭代器且构造方法会初始化itr，itr中的expectedModeCount会初始化为modCount（当前集合修改次数）。next每次执行一次就会对当前的expectedModeCount进行检查。不相等则抛出异常。

**如何解决**

- 迭代输出尽量不要修改集合中的数据
- 使用JUC包下的线程安全集合如CopyOnWriteArrayList,ConCurrentHashMap(fail-safe集合)或者Collections工具类中的安全集合synchronizedList。

```
List<String> synList = Collections.synchronizedList(list);
```



## set与Map有什么关系

- set实际就是Map：set集合中的值都是在内部Map的key中，共享一个null的Object作为内部Map的值



## HashCode 与equals

HashCode：将任意一个对象按照特定的哈希算法变为整数（对象中所有属性值均参与运算）

equals:比较两个对象是否相等(属性值)



equals相等时，hashCode一定相等

hashCode相等，equals不一定相等，万一哈希冲突嘞



## 如何实现第三方类的比较(内部排序，外部排序)

**内部排序：Comparable**

当一个类实现了Comparable接口，表示该类具备天然可比较特性

​	int compareTo()

外部排序：Comparator

​	