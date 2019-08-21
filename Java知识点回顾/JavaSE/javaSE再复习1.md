# 判断一个对象是否存活

方法区中类静态属性引用的对象

方法区中的常量引用的对象

虚拟机栈追踪局部变量表中引用的对象

本地方法区中的引用对象



Java中是值传递 String不变性



finally 一定执行但是不一定被返回 反编译查看



# 多线程

## 1.进行与线程

进程是操作系统中资源分配的最小单元

线程是操作系统中任务分配的最小单元

线程依赖于进程。

线程间通信开销比进程间通信小的多

创建于销毁一个线程的开销要比一个进程小的多，线程间通信也比进程间通信容易的多。



## 2.多线程的常用操作方法

线程状态

sleep：运行–- >阻塞态 当前线程立刻交出CPU，进入阻塞态，不会释放对象锁。

yield:   运行-->就绪，系统调度交出CPU，进入就绪，不会释放锁，只会让相同优先级的线程获取CPU。

join：运行-->阻塞，进入阻塞态，会释放对象锁。

join实际上就是wait()方法 join内部实际上实际上就是wait()

wait:运行 –>阻塞，会释放对象锁，必须与synchronized搭配使用（进入同步代码块）

notify：阻塞-->就绪态，必须在同步方法或同步代码块中使用。

​			无限阻塞

创建   就绪+运行      终止

​			有限阻塞

守护线程与用户线程

默认创建的都是用户线程–-主线程

守护线程：在后台执行，只要JVM中存在任意一个用户线程没有终止，守护线程就一直在运行

当JVM中最后一个用户线程终止，守护线程会随JVM一同停止。GC线程



## 3.线程的同步与死锁

多个线程在访问同一个共享资源，导致多个线程数据不一致的问题。

原子性：一个或者一组操作要么执行，要么不执行

可见性：其中一个线程对属性或者资源的修改，其余线程立刻得知

有序性：写前一定发生在写后面的



任意一个条件不满足，则都存在安全问题。



解决这个问题

### 3.1synchronized实现线程安全



**同步代码块**

> ​	synchronized(锁对象) {}
>
> ​	-普通对象
>
> ​	-类.class

**同步方法**

> ​	-成员同步方法 锁的是当前对象this
>
> ​	-静态同步放法 锁的是当前类.class



## synchronized底层实现（对象Monitor机制)

任意一个对象都有Monitor,synchronized对象锁实际上就是获取该对象的Monitor

当前线程要想获取到该锁对象的Monitor

1. 判断当前对象的Monitor计数器的值是否为0

2. 为0表示当前Monitor未被任何线程所持有，当前线程获取Monitor，并将持有线程置为自己，并将Monitor的值+1

3. 不为0表示此时Monitor已经被线程持有，判断持有线程是否为当前线程，若是，Monitor值再次+1

   若持有线程不是当前线程，线程进入阻塞态，直到Monitor的值减为0。



## JDK1.6之前 synchronized的优化

将一个线程置为阻塞状态，需要操作系统从用户态切换到内核态，开销十分大。

JDK1.6对synchronized的优化

### CAS

CAS：(Compare And Swap)

CAS：(O V N)；当前线程认为主内存中的值，V主内存中的实际值，N希望更新的值



### 自旋

在处理器上跑无用之令，但是线程不阻塞。

### 自适应自旋：优化的重量级锁

JVM给一个时间段，在该时间内，线程是自旋状态，若在该时间内获得到锁，下一次适当延长自选时间；否则将线程阻塞，下一次适当缩短自旋时间。



**随着锁竞争的激烈程度不断升级，没有降级过程。**

偏向锁 -> 轻量级锁 ->重量级锁(JDK1.6之前synchronized默认实现)-线程获取锁失败进入阻塞态

![1563688132896](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1563688132896.png)

JDK1.6之后 默认先是偏向锁

**偏向锁：**(乐观锁，锁一直是一个线程来回的获取) 认为不存存在锁的竞争

当线程第一次获取锁时，将偏向锁线程置为当前线程，以后再次获取锁时，不再有加锁和解锁的过程，只是简单的判断下获取锁线程是否是当前线程。（**只要产生竞争就会将偏向锁变为轻量级锁**）。



**轻量级锁：**在不同时间段内有不同的线程尝试获取锁，此时就会把偏向锁升级为轻量级锁。

每次锁的获取，都需要加锁和解锁过程。线程在执行同步代码块之前JVM都会在当前线程的栈中开辟一块空间存放当前对象头的拷贝（Mark Word），然后线程尝试CAS将当前轻量级锁中指向栈中锁记录的指针指向这块空间，如果成功当前线程获得锁。（只要CAS加锁失败多次后，就会膨胀为重量级锁）



**重量锁：**在同一时刻有不同线程尝试获取锁，此时JVM会阻塞获取锁失败（CAS自旋多次未获取锁的线程）。



**锁粗化:**

StringBuffer中对字符串append()，或者remove()会将三次本身每次执行该3个方法都会加解锁，但是JVM在遇到这种频繁的加减锁的情况下，为了性能的优化，将三次加减锁合并一次进行。减少加减锁的开销。



**锁消除：**

在虚拟机栈中的对象eg：StringBuffer的所有操作就不会进行加减锁。

在没有多线程共同访问的场景下，将锁直接消除。





## 可重入锁

持有锁的线程再次获取锁。



JDK1.6之后关于synchronized优化

偏向 -> 轻量级锁 -> 重量级锁（自适应自旋）

锁粗话

锁消除

Lock



## 死锁

死锁：对于资源的上锁成环了

死锁现象：程序出现“假死”现象。

死锁产生的四个条件同时满足

1. 互斥：共享资源x与y只能被一个线程占用
2. 占有且等待，线程T1已经拥有共享资源x,同时在等待共享资源y的时候，不释放资源
3. 不可抢占，其他线程不能抢占T1线程所持有的资源X
4. 循环等待：线程1等待线程2的资源，线程2等待线程1的资源



只能破坏2 3 4 使用2 3 就可以解决这三个问题。互斥的条件无法破坏

### 破坏死锁：银行家算法

1. 对于“占有且等待”我们可以一次性申请所有资源，这样就不存在等待了。
2. 对于“不可抢占”这个条件，占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源，这样不可抢占这个条件也就破坏掉了。
3. 对于“循环等待”这个条件，可以靠按序申请资源来预防。所谓按需申请，是指资源是线性顺序的，申请的时候可以先申请资源序号小的，再申请资源序号大的。这样线性化后自然就不存在循环了。

然后synchronized无法做到这几点，再synchronized申请资源的时候，如果申请不到，线程直接进入阻塞状态，而线程进入阻塞状态，啥都干不了，也释放不了线程已经占有的资源。

**JDK1.5 引入Lock体系来优雅的解决死锁问题**

1. **能够响应中断**，synchronized的问题是，持有A锁之后，如果尝试获取锁B失败，那么线程就进入了阻塞状态，一旦发生死锁，就没有任何机会来唤醒阻塞的线程。但是如果阻塞状态的线程能够响应中断信号，也就是说我们给阻塞的线程发送中断信号的时候，能够唤醒它，那它就会有机会释放曾经持有的锁A,这样就破坏掉了不可抢占条件了。
2. **支持超时**，如果线程在一段时间内没有获得锁，不是进入阻塞状态，而是返回一个错误，那这个线程也有机会释放曾经持有的锁，这样也能破坏不可抢占条件了。
3. **非阻塞的获取锁**。如果尝试获取锁失败，并不能进入阻塞状态，而是直接返回，那这个线程也有机会释放曾经持有的锁，这样也能破坏不可抢占条件。

## Lock

Lock的使用格式，需要显示的上锁，try finally中显示的解锁lock.unlock();

Lock的重要方法：

```java

// 支持中断 API void lockInterruptibly()   
throws InterruptedException; 

//支持非阻塞的获取锁 API 
boolean tryLock(); 

//支持超时APIboolean 
tryLock(long time, TimeUnit unit)   
throws InterruptedException;
```



### Lock常用子类

ReentrantLcik：可重入锁(Lock接口中常用子类，语义与synchronized基本一直也是独占锁的实现)



### 面试题：synchronized与ReentrantLock的关系与区别

**联系：**

1. synchronized与ReentrantLock都属于独占锁，都支持可重入。

**区别：**

1. synchronized是关键字，JVM层面的实现；ReentrantLcok是Java语言层面的实现
2. ReentrantLock具备一些synchronized不具备的特性，如响应中断、支持超时、支持非阻塞式的获取锁。并且ReentrantLock实现公平锁（默认实现非公平锁）保证系统更大的吞吐量
3. synchronized只有一个等待队列，而Lock调用newCondition()产生多个等待队列。



### **wait/notify**

Condition:await() / signal()

同步队列：所有获取锁失败的线程进入同步队列排队获取锁

等待队列：调用wait的线程置于等待队列，等待被唤醒(notify)



### 变种面试题：synchronized与Lock的关系与区别

**联系：**

1. synchronized与ReentrantLock都属于独占锁，都支持可重入。

**区别：**

1. synchronized是关键字，JVM层面的实现；ReentrantLcok是Java语言层面的实现
2. ReentrantLock具备一些synchronized不具备的特性，如响应中断、支持超时、支持非阻塞式的获取锁。并且ReentrantLock实现公平锁（默认实现非公平锁）保证系统更大的吞吐量
3. synchronized只有一个等待队列，而Lock调用newCondition()产生多个等待队列。
4. Lock可以实现ReentrantReadWriteLock：可重入读写锁



### 生产消费者 读者写者 哲学家进餐







### 读写锁实现：ReentrantReadWriteLock(实现缓存HashMap + ReentrantWriteLock)

读锁：ReadLock多个线程在同一时刻可以共同取得读锁。

写锁：WriteLock，独占锁，多个线程在同一时刻只有一个线程可以取得该锁。

共享锁：多个线程可以同时取得该锁，读锁ReadLock共享锁=无锁？

当写线程开始工作时，所有其他线程（包含读线程）全部进入阻塞状态。



## 线程池

使用线程池的优点：

1. **降低资源消耗**：通过重复利用已经创建的线程创建和销毁的带来的损耗
2. **提高响应速度**：当任务到达时，任务可以不需要等待线程创建就能立即执行
3. **提高线程的可管理性**：使用线程池可以统一进行线程分配、调度和监控

![1563698678010](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1563698678010.png)

ThreadPoolExecutor 的execute()方法执行流程



**核心池**

1.如果当前运行的线程少于corePoolSize,则创建新线程来执行任务（注意，执行这一步骤需要获取全局锁）

**阻塞队列**

2.如果运行线程等于或者多于corePoolSize，则将任务加入BlockingQueue.

**最大线程池**

3.如果无法将任务加入BlockingQueue(队列已满)，则创建新的线程来处理任务（注意，执行这一步骤需要获取全局锁）。

**拒绝策略**

4.如果创建新线程将使当前运行的线程超过maximumPoolSize,任务将被拒绝，并调用RejectExecutionHandler.rejectedExecution()方法。（拒绝策略）



## ThreadPoolExecutor:线程池的核心类

```java
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
```

- **corePoolsize(线程池的基本大小)**：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时，就不会再创建。如果调用了线程池的prestartAllCoreThreads()方法，线程会提前创建并启动所有基本线程。
- **workQueue：用于保存等待执行的任务的阻塞队列**。可以选择以下几个阻塞队列
  - **ArrayBlockQueue**:是一个基于数组结构的有界阻塞队列，此队列按FIFO(先进先出)原则对元素进行排序。
  - **LinkedBlockQueue(无界队列):**一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通常要高于**ArrayBlockingQueue**。静态工厂方法**Executors.newFixedThreadPool()**使用了这个队列。
  - **SynchronousQueue:**（**无界队列**）一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态。吞吐量通常高于LinkedBlockQueue,静态工厂方法**Executors.newCachedThreadPool()**使用了这个队列。
  - **PriorityBlockingQueue**（**无界队列**）:一个具有优先级的无限阻塞队列。
- **maximumPoolSize（线程池的最大线程数量**）：线程池允许创建的最大线程数，如果队列满了，并且已经创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是，如果使用了无界阻塞队列该参数就没有意义了。
- **keepAliveTime**(**线程活动保持时间**)：线程池的工作线程空闲后，保持存活的时间。所以，如果任务很多，并且每个任务执行的时间比较短，**可以调大时间，提高线程的利用率**。
- **TimeUnit unit**（**线程活动保持时间单位**）：可选择的有天，小时，分钟，毫秒，微妙，纳秒。
- **RejectedExecutionHandler(饱和策略)**：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy,表示**无法处理新任务时抛出异常。**
  - JDK1.5中提供了以下集中参数
    - AbortPolicy:**直接抛出异常(默认采用此策略)**
    - CallerRunsPolicy:**只用调用者所在线程来运行任务。**
    - DiscardOldestPolicy:**丢弃队列里最近的一个任务，并执行当前任务**
    - DisCardPolicy:**不处理，直接丢弃掉。**

核心线程池基本大小

线程池的最大线程数

线程活动保持时间

线程活动保持时间单位

包和策略



## Executor（顶层父接口）框架



### ExecutorService：普通调度池接口

### Executors:线程池工具类



### SingleThreadExecutor:单线程池

任何时刻，都只有一个线程工作

### FixedThreadPool：固定大小线程池

系统资源紧张，适用于资源较重的系统



### CachedThreadPool：缓存池

当提交任务速度远大于任务的处理速度，不断产生新线程。

如果任务处理的速度要比任务提交的速度快，只有一个线程。

服务器负载较轻，适用于很多短期的异步小任务。

### ScheduledExecutorService :定时调度池

需要执行定时任务场合









线程池：

1. 线程池的工作流程
2. 如何自定义线程池
   1. 核心线程池类ThreadPoolExecutor参数配置（如何自定义参数设置）
   2. 手线程池Work(源码)
3. 在何种场景下选用何种线程池













## Future - FutureTask

Future:取得Callable接口返回值

get() :V 当前线程阻塞直到有返回值为止

FutureTask:在多线程并发下可以保证任务（传入Callable或则Runnable）只执一次。





get方法的特点

线程池中提交任务的方法

void execute(Runnable r);

v submit(Runnable/Callable r/c);



# CompletableFuture详解