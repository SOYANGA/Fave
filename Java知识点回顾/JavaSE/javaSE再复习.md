## 如何实现第三方类的比较(内部排序，外部排序)

**内部排序：Comparable**

当一个类实现了Comparable接口，**表示该类具备天然可比较特性**

```java
	int compareTo(T 0)
```



**外部排序：Comparator**(策略模式)

**类本身不具备可比较的特性，专门有一个类实现了Comparator来比较该类的大小(比较器)**

```java
int compare(T o1, T o2);
```

**要想将自定义类存储到TreeSet（ThreeMap）中，要么元素本身可以比较（实现Comparable接口）要么通过构造方法传入该类的比较器中(实现了Comparator接口)。**

创建的TreeSet时，作为参数传入，即可实现有序。



## HashMap、Hashtable、TreeMap的关系和区别

1. HashMap：哈希表+红黑树（JDK1.8只有之前哈希表）
2. Hashtable：哈希表
3. TreeMap：红黑树

只有HashTable是线程安全的(使用synchronized同步方法，读读互斥)



**HashMap内部实现原理**

1. 元素到底在那个数组下标位置存储：内部的哈希算法

```java
h == key.hashCode()^(h>>>16)
```

整数之间区别主要在高16位上，异或运算将高16位和低16位参与异或运算，减少哈希冲突。

哈希表：既不浪费空间，查询又快。



**树化逻辑：**

- 树化有两个要求
  - 链表个数位8
  - 且当前哈希表table.length >= 64 才会树化
- 解树化
  - 当红黑树的元素个数小于6时退还为链表，减少空间，查找效率与链表差不多。



**为何要将链表树化**

- 当前链表过长，会导致查找速率急速降低，因此将其树化可以大大提高查找速率(O(logn))



**扩容：**当前数组元素个数 > 容量 * 负载因子

**负载因子：**0.75f

**容量：**当前哈希表能存储的最大容量 默认是16

**容量必须都是2^n个，减少哈希冲突，让哈希数组每个索引下标都会被用到。**

i = （n-1）& hash 

> HashMap key与value都可以为null
>
> Hashtable key与value都不能为null
>
> TreeMap key不可以为null, value可以为null



## ConcurrentHashMap 在 JDK1.7与JDK1.8区别

### JDK1.7：用的Lock

> 默认初始化为16个Segement,初始化后不会扩容
>
> 每个Segement下面还是一个哈希表结构，可以扩容（扩容以segement为单位）
>
> Hashtable上锁的时候锁的是当前对象，即整个哈希表（一张表，一把锁）
>
> JDK1.7 ConcurrentHashMap锁的是当前操作的Segement（一张表，16把锁，将锁的粒度细化，不同Segement是可以异步的）



### JDK1.8:用的自旋（CAS）

> 取消了原先Segement的设计，现在的结构与HashMap相同。
>
> 保留了Segement但是无用
>
> 锁的是Hash表的数组元素，将锁进一步细化。进一步提高读写效率。（**锁的是每个Hash表的链表的头**）



索引 事务 视图  《岛上书店》

