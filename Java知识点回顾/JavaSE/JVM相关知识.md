# JVM相关知识

## 1.运行时数据区域划分



## 二、垃圾收集

![java运行时数据区划分](C:\Users\32183\Desktop\秋招提前批Java复习\java运行时数据区划分.png)

## 2.判断一个对象是否可以被回收

垃圾收集主要是针对堆和方法区/（元空间）进行。程序计数器、虚拟机栈和本地方法栈这三个区域属于线程私有的，只存在于线程的生命周期内，线程结束之后就会消失，因此不需要对这三个区域进行垃圾回收



### 2.1引用计数法

为对象添加一个引用计数器，当对象增加一个引用时计数器加1，引用失效时计数器减1.引用计数为0的对象可被回收。

缺点：无法解决循环引用问题，出现循环引用时，引用计数器永远不为0，导致无法对它们进行回收。java虚拟机不采用引用计数法。

```java
public class Test {
	public Object instance = null;
    public static void main(String[] args) {
        Test a = new Test();
        Test b = new Test();
        a.instance = b;
        b.instance = a;
        a = null;
        b = null;
        doSomething();
    }
}
```

在上述代码中，a与b引用的对象实例互相持有对象的引用，因此当我们把对a对象与b对象的引用去除之后，由于两个对象还存在互相之间的引用，导致两个Test对象无法被回收。



### 2.2可达性分析算法

以GC Roots为起点进行搜索，可达的对象都是存活的，不可达的对象可被回收。

Java虚拟机使用该算法来判断是否可被回收，GC Roots一般内容如下：

- 虚拟机栈中局部变量表中引用的对象
- 本地方法栈中JNI中引用的对象
- 方法区中类静态属性引用对象
- 方法区中的常量引用的对象

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/83d909d2-3858-4fe1-8ff4-16471db0b180.png)

### 3.方法区的回收

因为方法区主要存放永久代的对象，而永久代的对象的回收率比新生代的低很多，所以在方法区上进行回收的性价比不高。**主要针对常量池的回收和对类的卸载。**

为了避免内存溢出，在大量的使用发射和动态代理的场景都需要虚拟机具备类卸载的功能。

类的卸载条件很多，都需要满足一下三个条件，并且满足以下三个条件都不一定会被卸载：

- 该类所有的实例都已经被回收，此时堆中不存在该类的任何实例
- 加载该类的ClassLoader已经被回收了
- 该类的对应的Class对象没有在任何地方被引用，也就无法在任何地方通过反射访问到该类方法

  

### 4.自救的一次机会finalize()

判断对象“已死”有两次筛选，第一在可达性分析中不可达的对象及逆行第一次标记（第一次筛选，筛选的条件是虚拟机是否已经调用了此对象的finalize()方法，已经调用过则此时对象直接判定为已经“死亡“”。

如果虚拟机未调用此对象的finalize()方法，那么该对象会被放置于一个F-Queue的队列中，并在稍后有一个虚拟机自动建立的低优先级的Finalizer线程去执行对象的finalize()方法，如果该对象在finalize()中成功自救自己(重新与引用链上的任何一个对象建立关联关系即可)。则在第二次标记时，该对象就会被移除队列；如果该对象还是没有逃脱，此时基本上他就是真的被回收了。（对象“已死”）。

**任何一个对象的 ﬁnalize()方法都只会被系统自动调用一次**



### 5.引用类型

无论是通过引用计数算法判断对象的引用数量，还是通过可达性分析算法判断对象是否可达，判定对象是否可被回收都与引用有关。

Java 提供了四种强度不同的引用类型。

**1.强引用（StrongReference)**

就是我们最常见的普通对象引用，使用new一个新对象的方式来创建强引用。只要对象被强引用指向，GC无法回收此对象。

```java
Object obj = new Object();
```



**2.软引用(SoftReference)**

是一种相对于强引用弱一些的引用，可以让对面豁免一些垃圾回收，只有JVM认为内存不足的时候，才会试图对软引用对象回收。JVM确保在抛出OutOfMemoryError之前，清理软引用指向的对象，

```java
Object obj = new Object();
SoftReference<Object> sf = new SoftReference<Object>(obj);
obj = null;  // 使对象只被软引用关联
```

**使用场景：**

软引用的通常用来实现内存敏感的缓存，如过还有空闲内存，就可以暂时保留缓存，当内存不足的时候清理掉，这样就能保证使用缓存的同时不会耗尽内存。



**3.弱引用(weakReference)**

并不能使对象避免垃圾回收，仅仅使提供一种访问在弱引用状态下对象的途径，下一次垃圾回收无论是否还有空闲内存，都会对其进行回收。

```java
Object obj = new Object();
WeakReference<Object> wf = new WeakReference<Object>(obj);
obj = null;
```

**使用场景：**

可以用来构建一种没有特定约束的关系，比如，维护一种非强制性的映射关系，如果试图获取的对象还在，就使用它，否则重新实例化，同样是很多缓存的实现方式。



**4.虚引用(PhantomReference)**

一个对象是否具有虚引用，不会对其生存时间影响，也无法通过虚引用得到一个对象。

```java
Object obj = new Object();
PhantomReference<Object> pf = new PhantomReference<Object>(obj, null);
obj = null;
```

**使用场景**

幻象引用仅仅是提供了一种确保对象被fnalize以后，做某些事情的机制，比如，通常用来做所谓的PostMortem清理机制。例如Cleaner机制，也有人利用幻想引用监控对象的创建和销毁。



### 6.垃圾收集算法



#### 1.标记清除算法

在标记清除阶段，程序会检查每个对象是否为活动对象，如果是活动对象，则程序会在对象头打上标标志位

在清除阶段，会进行对象回收并取消标志位，另外还会判断回收之后的分块与前一个空闲分块是否连续，若连续，会合并这两个分块。回收对象就是把对象作为分块，连接到被称为“空闲链表”的单向链表，之后进行分配时只需要遍历这个空闲链表，就可以找到分块。

在分配时，程序会搜索空闲链表寻找空间大小等于新对象大小size的block，如果它找到的块等于size，则会返回这个分块；如果找到的块大于size，会将块分割大小为size与(block - size)的两个部分，返回大小为size的分块，并把大小为(block - size)的块返回给空闲链表。

**不足：**

- 标记和清除过程效率都不高；
- 会产生大量的不连续内存片段，导致无法给大对象分配内存，不得以触发另一次垃圾收集。



#### 2.标记-整理(老年代回收算法)

让存活的对象都想内存的一断移动，然后直接清理掉端边界以外的内存。

优点：

- 不会产生内存碎片

不足：

- 需要移动大量的对象，处理效率比较低

#### 3.复制算法（新生代回收算法）

将内存划分为大小相等的两块，每次只是使用其中一块，当这一块内存用完以后就将还存活的对象复制到另一块上面，然后再把使用过的内存空间进行一次清理。这样做的好处是每次都是对整个半区进行内存回收，内存分配时也就不需要考虑内存碎片等复杂情况，只需要移动堆顶指针，按顺序分配即可。此算法实现简单，运行高效。

**缺点**也是只使用了一半的内存区域,**内存利用率太低了，浪费空间。**

现在的商业虚拟机都采用这种收集算法回收新生代，但是并不是划分为大小相等的两块，而是一块较大的 Eden 空间和两块较小的 Survivor 空间（**两个 Survivor区域一个称为From区，另一个称为To区域**），每次使用 Eden 和其中一块 Survivor。**在回收时，将 Eden 和 Survivor 中还存活着的对象全部复制到另一块 Survivor 上，最后清理 Eden 和使用过的那一块 Survivor。**

HotSpot虚拟机的Eden和Survior大小比例默认是8：1，保证了内存的利用率90%，如果每次回收的多余10%的对象存活，那么一块 Survivor 就不够用了，此时需要**依赖于老年代进行空间分配担保**，也就是借用老年代的空间存储放不下的对象。

还有一种情况会进入让对象进入老年代

1. 当Eden区满的时候,会触发第一次Minor gc,把还活着的对象拷贝到Survivor From区；当Eden区再次触 发Minor gc的时候,会扫描Eden区和From区域,对两个区域进行垃圾回收,经过这次回收后还存活的对象, 则直接复制到To区域,并将Eden和From区域清空。 
2. 当后续Eden又发生Minor gc的时候,会对Eden和To区域进行垃圾回收,存活的对象复制到From区域,并将 Eden和To区域清空。  
3. **部分对象会在From和To区域中复制来复制去,如此交换15次(由JVM参数==MaxTenuringThreshold==决定,这个参数默认是15),终如果还是存活,就存入到老年代**

#### 4.分代收集

当前JVM垃圾收集都采用的是"分代收集(Generational Collection)"算法，这个算法并没有新思想，只是根据对象存 活周期的不同将内存划分为几块。

一般将堆分为新生代和老年代

- 新生代，每次垃圾回收就会有大批对象死去，只有少量存活，因此我们采用复制算法；
- 而老年代中对象存活率高，没有额外的空间对它进行分配担保，所以采用标记-整理算法。



### 7.垃圾收集器

HotSpot虚拟机中的7个垃圾收集器

- 单线程与多线程：
  - 单线程指得的是垃圾收集器只使一个线程
  - 而多线程使用多个线程
- 串行和并行和并发：
  - **串行**指的是垃圾收集器与用于程序交替执行，这意味着在执行垃圾收集的时候需要停顿用户程序（单CPU）
  - **并行**指的是垃圾收集器和用户程序并行工作，用户程序任然处于等待状态。
  - **并发**：指用户线程与垃圾收集线程同时执行（不一定并行，可能会交替执行），用户程序继续运行，而垃圾收集器程序在另外一个CPU上。
  - 除了**CMS和G1之外，其他都是以串行或者并行方式执行。**

#### 1.Serial收集器( Client 场景下的默认新生代收集器，串行GC新生代)

串行方式执行，且他是单线程收集器，只会使用一个线程进行垃圾收集工作。

优点：在单个CPU环境下，由于没有线程交互的开销，因此拥有最高的单线程收集效率。



#### 2.ParNew收集器(Server 场景下默认的新生代收集器，并行GC新生代)

他是Serial收集器的多线程版本

主要是因为除了 Serial 收集器，只有它能与 CMS 收集器配合使用。



#### 3.Parallel Scavenge收集器(采用复制算法的收集器，并行GC)

与ParNew一样是多线程收集器，他的目标是达到一个可控的吞吐量，因此也被称为“吞吐量收集优先“器。

吞吐量：指的是CPU用于运行用户程序的时间占总时间的比值。

可以通过一个开关参数打开 GC 自适应的调节策略（GC Ergonomics）`- XX:+UseAdaptiveSizePolicy `，就不需要手工指定新生代的大小（-Xmn）、Eden 和 Survivor 区的比例、晋升老年代对象年龄等细节参数了。虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以**提供最合适的停顿时间或者最大的吞吐量。**



#### 4.Serial Old收集器(Client 场景下老年代收集器，串行GC)

Serial Old是Serial收集器的老年版，它同样是一个单线程收集器，使用标记-整理算法。如果用在 Server 场景下，它有两大用途：

- 在 JDK 1.5 以及之前版本（Parallel Old 诞生以前）中与 **Parallel Scavenge** 收集器搭配使用。
- 作为 CMS 收集器的后备预案，在并发收集发生 Concurrent Mode Failure 时使用。



#### 5.Parallel Old收集器（老年代收集器，并行GC）

是 Parallel Scavenge 收集器的老年代版本。使用多线程和“标记－整理”算法

在注重吞吐量以及 CPU 资源敏感的场合，都可以优先考虑 Parallel Scavenge 加 Parallel Old 收集器。



#### 6.CMS收集器（老年代收集器，并发GC）

 CMS（Concurrent Mark Sweep）收集器是一种以获取短回收停顿时间为目标的收集器。目前很大一 部分的Java应用集中在互联网站或者B/S系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间短，以给用户带来较好的体验。CMS收集器就非常符合这类应用的需求。



CMS收集器是基于“标记—清除”算法实现的，它的运作过程相对于前面几种收集器来说更复杂一些，整个过程分为 

- **初始标记**：仅仅只是标记一下 GC Roots 能直接关联到的对象，速度很快，需要停顿。(Stop The World)
- **并发标记**：进行 GC Roots Tracing 的过程，它在整个回收过程中耗时最长，不需要停顿。
- **重新标记**：为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，需要停顿。
- **并发清除**：不需要停顿。

==在整个过程中耗时最长的并发标记和并发清除过程中，收集器线程都可以与用户线程一起工作，不需要进行停顿。==

**优点：**并发收集，低停顿。

**缺点：**

- CMS收集器对CPU资源非常敏感

  在并发阶段虽然不会导致用户线程停顿，但是会因为占用了一部分线程（CPU资源）导致应用程序运行变慢，总吞吐量会变低。

- CMS收集器无法处理浮动垃圾

  浮动垃圾就是在CMS并发清理阶段线程还运行着，伴随程序运行自然会有新垃圾不断产生，这一部分垃圾出现在标记过程之后，CMS无法集中处理，只好等到下一次GC在清理掉。

  由于垃圾收集阶段用户线程还需要运行，那就还需要预留有足够的内存空间给用户线程使用，因此CMS收集器不能像其他收集器那样等到老年代几乎完全被填满了 再进行收集，需要预留一部分空间提供并发收集时的程序运作使用。要是CMS运行期间预留的内存无法 满足程序需要，就会出现一次“Concurrent Mode Failure”失败，这时虚拟机将启动后备预案：临时启用 Serial Old收集器来重新进行老年代的垃圾收集，这样停顿时间就很长了。 

- CMS收集器会产生大量空间碎片 

  - 标记 - 清除算法导致的空间碎片，往往出现老年代空间剩余，但无法找到足够大连续空间来分配当前对象，不得不提前触发一次 Full GC。

#### 7.G1收集器

G1，它是一款面向服务端应用的垃圾收集器，在多 CPU 和大内存的场景下有很好的性能。HotSpot 开发团队赋予它的使命是未来可以替换掉 CMS 收集器。

堆被分为新生代和老年代，其它收集器进行收集的范围都是整个新生代或者老年代，而 G1 可以直接对新生代和老年代一起回收。

G1 把堆划分成多个大小相等的独立区域（Region），新生代和老年代不再物理隔离。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/9bbddeeb-e939-41f0-8e8e-2b1a0aa7e0a7.png)

通过引入 Region 的概念，从而将原来的一整块内存空间划分成多个的小空间，使得每个小空间可以单独进行垃圾回收。这种划分方法带来了很大的灵活性，使得可预测的停顿时间模型成为可能。通过记录每个 Region 垃圾回收时间以及回收所获得的空间（这两个值是通过过去回收的经验获得），并维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的 Region。

每个 Region 都有一个 Remembered Set，用来记录该 Region 对象的引用对象所在的 Region。通过使用 Remembered Set，在做可达性分析的时候就可以避免全堆扫描。



如果不计算维护 Remembered Set 的操作，G1 收集器的运作大致可划分为以下几个步骤：

- **初始标记：**

- **并发标记**：

- **最终标记**：为了修正在并发标记期间因用户程序继续运作而导致标记产生变动的那一部分标记记录，虚拟机将这段时间对象变化记录在线程的 Remembered Set Logs 里面，最终标记阶段需要把 Remembered Set Logs 的数据合并到 Remembered Set 中。这阶段需要停顿线程，但是可并行执行。

- **筛选回收**：首先对各个 Region 中的回收价值和成本进行排序，根据用户所期望的 GC 停顿时间来制定回收计划。此阶段其实也可以做到与用户程序一起并发执行，但是因为只回收一部分 Region，时间是用户可控制的，而且停顿用户线程将大幅度提高收集效率。

  具备如下特点：

- **空间整合：**整体来看是基于“标记 - 整理”算法实现的收集器，从局部（两个 Region 之间）上来看是基于“复制”算法实现的，这意味着运行期间不会产生内存空间碎片。
- **可预测的停顿**：能让使用者明确指定在一个长度为 M 毫秒的时间片段内，消耗在 GC 上的时间不得超过 N 毫秒。

## 三、内存分配与回收策略

**Minor GC**:新生代的GC（复制算法）非常频繁，回收速度较快

**Full GC**：又称为 老年代GC或者Major GC指发生在老年代的垃圾收集。出现了Major GC，经常会伴随 至少一次的Minor GC(并非绝对，在Parallel Scavenge收集器中就有直接进行Full GC的策略选择过程)。 Major GC的速度一般会比Minor GC慢10倍以上。 



### 1.对象优先在Eden分配

大多数情况下，对象在新生代Eden上分配，当Eden空间不够时，会触发Minor GC。



### 2.大对象直接进入老年代

大对象是指连续内存空间的对象，最经典的大对象是那种很长的字符串以及数组。**经常出现大对象会提前触发垃圾收集以获取足够的连续空间分配给大对象。**

XX:PretenureSizeThreshold，大于此值的对象直接在老年代分配，避免在 Eden 和 Survivor 之间的大量内存复制。

### 3.长期存活的对象进入老年代

为对象定义年龄计数器，对象在 Eden 出生并经过 Minor GC 依然存活，将移动到 Survivor 中，年龄就增加 1 岁，增加到一定年龄则移动到老年代中。(默认为15岁)

-XX:MaxTenuringThreshold 用来定义年龄的阈值。



### 4.动态年龄判定

虚拟机并不是永远要求对象的年龄必须达到 MaxTenuringThreshold 才能晋升老年代，如果在 **Survivor** 中相同年龄所有对象大小的**总和大于 Survivor 空间的一半**，**则年龄大于或等于该年龄的对象可以直接进入老年代，无需等到 MaxTenuringThreshold 中要求的年龄。**



### 5.空间分配担保

在发生Minor GC之前，虚拟机会检查**老年代最大可用的连续空间**是否大于**新生代所有对象的总空间**， 

- 如果大于，则此次**Minor GC是安全的** 
- 如果小于，则虚拟机会查看**HandlePromotionFailure**设置值是否允许担保失败。 如果HandlePromotionFailure=true，那么会继续检查老年代大可用连续空间是否**大于历次晋升到老年代的对象大小的平均大小**，如果大于，则尝试进行一次Minor GC，但这次Minor GC依然是有风险的；如果小于或者 HandlePromotionFailure=false，则改为进行一次Full GC

**取平均值**仍然是一种**概率性的事件**，如果某次Minor GC后存活对象陡增，远高于平均值的话，必然导致担保失 败，如果出现了分配担保失败，**就只能在失败后重新发起一次Full GC**。虽然存在发生这种情况的概率，但**大部分 时候都是能够成功分配担保**的，这样就**避免了过于频繁执行Full GC**。



### FUll GC的触发条件

对于 Minor GC，其触发条件非常简单，当 Eden 空间满时，就将触发一次 Minor GC。而 Full GC 则相对复杂，有以下条件：

### 1. 调用 System.gc()

只是建议虚拟机执行 Full GC，但是虚拟机不一定真正去执行。不建议使用这种方式，而是让虚拟机管理内存。



### 2. 老年代空间不足

老年代空间不足的常见场景为前文所讲的大对象直接进入老年代、长期存活的对象进入老年代等。

为了避免以上原因引起的 Full GC，应当尽量不要创建过大的对象以及数组。除此之外，可以通过 -Xmn 虚拟机参数调大新生代的大小，让对象尽量在新生代被回收掉，不进入老年代。还可以通过 -XX:MaxTenuringThreshold 调大对象进入老年代的年龄，让对象在新生代多存活一段时间。



### 3.空间分配担保失败

使用复制算法的 Minor GC 需要老年代的内存空间作担保，如果担保失败会执行一次 Full GC。具体内容请参考上面的第 5 小节。



### 4.JDK1.7及以前的永久代空间不足

在 JDK 1.7 及以前，HotSpot 虚拟机中的方法区是用永久代实现的，永久代中存放的为一些 Class 的信息、常量、静态变量等数据。

当系统中要加载的类、反射的类和调用的方法较多时，永久代可能会被占满，在未配置为采用 CMS GC 的情况下也会执行 Full GC。如果经过 Full GC 仍然回收不了，那么虚拟机会抛出 java.lang.OutOfMemoryError。

为避免以上原因引起的 Full GC，可采用的方法为增大永久代空间或转为使用 CMS GC。



### 5. Concurrent Mode Failure

执行 CMS GC 的过程中同时有对象要放入老年代，而此时老年代空间不足（可能是 GC 过程中浮动垃圾过多导致暂时性的空间不足），便会报 Concurrent Mode Failure 错误，并触发 Full GC。



## 四、类加载机制

**类是在运行期间第一次使用时动态加载的**，而不是一次性加载所有类。因为如果一次性加载，那么会占用很多的内存。



### 类的生命周期

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/335fe19c-4a76-45ab-9320-88c90d6a0d7e.png)

### 类加载过程

包含了加载、验证、准备、解析、初始化这5个阶段。 加载 链接 初始化

### 1.加载

- **通过类的完全限定名称获取定义该类的二进制字节流**
- 将该字节流表示的静态存储结构转换为**方法区的运行时存储结构**
- **在内存中生成一个代表该类的Class对象，作为方法区中该类各种数据的访问入口。**

其中二进制字节流可以从以下方式中获取：

- 从 ZIP 包读取，成为 JAR、EAR、WAR 格式的基础。
- 从网络中获取，最典型的应用是 Applet。
- 运行时计算生成，例如动态代理技术，在 java.lang.reflect.Proxy 使用 ProxyGenerator.generateProxyClass 的代理类的二进制字节流。
- 由其他文件生成，例如由 JSP 文件生成对应的 Class 类。



### 2.验证

确保 Class 文件的字节流中**包含的信息符合当前虚拟机的要求**，**并且不会危害虚拟机自身的安全**。



### 3.准备

类变量是被 static 修饰的变量，准备阶段为类变量分配内存并设置初始值，使用的是方法区的内存

实例变量不会在这阶段分配内存，它会在对象实例化时随着对象一起被分配在堆中。应该注意到，实例化不是类加载的一个过程，类加载发生在所有实例化操作之前，并且类加载只进行一次，实例化可以进行多次。

初始值一般为 0 值，例如下面的类变量 value 被初始化为 0 而不是 123。

```java
public static int value = 123;Copy to clipboardErrorCopied
```

如果类变量是常量，那么它将初始化为表达式所定义的值而不是 0。例如下面的常量 value 被初始化为 123 而不是 0。

```java
public static final int value = 123;
```



### 4.解析

将常量池的符号引用替换为直接引用的过程

其中解析过程在某些情况下可以初始化阶段之后再开始，这是为了支持Java的动态绑定。



### 5.初始化

初始化阶段才真正开始执行类中定义的 Java 程序代码。初始化阶段是虚拟机执行类构造器 <clinit>() 方法的过程。**在准备阶段，类变量已经赋过一次系统要求的初始值，而在初始化阶段，根据程序员通过程序制定的主观计划去初始化类变量和其它资源。**

<clinit>() 是由编译器自动收集类中所有类变量的赋值动作和静态语句块中的语句合并产生的，编译器收集的顺序由语句在源文件中出现的顺序决定。特别注意的是，**静态语句块只能访问到定义在它之前的类变量，定义在它之前的类变量，定义在它之后的类变量只能赋值，不能访问。**例如以下代码：

```java
public class Test {
    static {
        i = 0;                // 给变量赋值可以正常编译通过
        System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }
    static int i = 1;
}
```

接口中不可以使用静态语句块，但仍然有类变量初始化的赋值操作，因此接口与类一样都会生成 <clinit>() 方法。但接口与类不同的是，执行接口的 <clinit>() 方法不需要先执行父接口的 <clinit>() 方法。只有当父接口中定义的变量使用时，父接口才会初始化。另外，接口的实现类在初始化时也一样不会执行接口的 <clinit>() 方法。(接口并不需要先执行父接口的初始化)

**虚拟机会保证一个类的 <clinit>() 方法在多线程环境下被正确的加锁和同步，如果多个线程同时初始化一个类，只会有一个线程执行这个类的 <clinit>() 方法，其它线程都会阻塞等待，直到活动线程执行 <clinit>() 方法完毕。**如果在一个类的 <clinit>() 方法中有耗时的操作，就可能造成多个线程阻塞，在实际过程中此种阻塞很隐蔽。



## 类与类加载器

两个类相等，需要类本身相等，并且使用同一个类加载器进行加载，这是因为每**一个类加载器都拥有一个独立的类名称空间。**

这里的相等，包括类的 Class 对象的 equals() 方法、isAssignableFrom() 方法、isInstance() 方法的返回结果为 true，也包括使用 instanceof 关键字做对象所属关系判定结果为 true。



### 类加载器的分类

从 Java 开发人员的角度看，类加载器可以划分得更细致一些：

- 启动类加载器（Bootstrap ClassLoader）此类加载器负责将存放在 <JRE_HOME>\lib 目录中的，或者被 -Xbootclasspath 参数所指定的路径中的，并且是虚拟机识别的（仅按照文件名识别，如 rt.jar，名字不符合的类库即使放在 lib 目录中也不会被加载）类库加载到虚拟机内存中。**启动类加载器无法被 Java 程序直接引用**，用户在编写自定义类加载器时，如果需要把加载请求委派给启动类加载器，直接使用 null 代替即可。
- 扩展类加载器（Extension ClassLoader）这个类加载器是由 ExtClassLoader（sun.misc.Launcher$ExtClassLoader）实现的。它负责将 <JAVA_HOME>/lib/ext 或者被 java.ext.dir 系统变量所指定路径中的所有类库加载到内存中，**开发者可以直接使用扩展类加载器**。
- 应用程序类加载器（Application ClassLoader）这个类加载器是由 AppClassLoader（sun.misc.Launcher$AppClassLoader）实现的。由于这个类加载器是 ClassLoader 中的 getSystemClassLoader() 方法的返回值，因此一般称为**系统类加载器。它负责加载用户类路径（ClassPath）上所指定的类库，开发者可以直接使用这个类加载器，如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。**

## 双亲委派模型

应用程序是由三种类加载器互相配合从而实现类加载，除此之外还可以加入自己定义的类加载器。

下图展示了类加载器之间的层次关系，称为双亲委派模型（Parents Delegation Model）。该模型要求除了顶层的启动类加载器外，其它的**类加载器都要有自己的父类加载器**。**这里的父子关系一般通过组合关系（Composition）来实现，而不是继承关系（Inheritance）。**

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/0dd2d40a-5b2b-4d45-b176-e75a4cd4bdbf.png)

### 1.工作过程

一个类加载器首先将类加载请求转发到父类加载器，只有当父类加载器无法完成时才尝试自己加载。

### 2.好处

使得 Java 类随着它的类**加载器一起具有一种带有优先级的层次关系，从而使得基础类得到统一**。

### 3.实现



## Java内存模型

JVM定义了一种Java内存模型(Java Memory Model,JMM)来**屏蔽掉各种硬件和操作系统的内存访问差异**，以实现让 Java程序在**各种平台下都能达到一致的内存访问效果**。

#### 1.主内存和工作内存

Java内存模型的主要目标是定义程序中各个变量的访问规则，即在JVM中将变量存储到内存和从内存中取出变量这 样的底层细节。此处的变量**包括实例字段、静态字段和构成数组对象的元素**，但**不包括局部变量和方法参数**，因为后两者是线程私有的，不会被线程共享。

Java内存模型规定了**所有的变量都存储在主内存中**。**每条线程还有自己的工作内存，线程的工作内存中保存了被该 线程使用到的变量的主内存副本拷贝，线程对变量的所有操作(读取、赋值等)都必须在工作内存进行，而不能直接 读写主内存中的变量**。不**同的线程之间也无法直接访问对方工作内存中的变量，线程间变量值的传递均需要通过主内存来完成**。线程、主内存、工作内存三者的交互关系如下所示 :

![1563462236470](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1563462236470.png)

### 2.内存之间交互操作（交互协议定义了8种操作）

JVM实现时必须保证下面提及的每一种操作的原子的、不可再分的。

- **lock**(锁定) : 作用于主内存的变量，它把一个变量标识为一条线程独占的状态 
- **unlock**(解锁) : 作用于主内存的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线 程锁定。 
- **read**(读取) : 作用于主内存的变量，它把**一个变量的值**从主内存传输到线程的工作内存中，以便随后的load动 作使用。 
- **load**(载入) : 作用于工作内存的变量，它把read操作从主内存中得到的**变量值放入工作内存的变量副本**中。 
- **use**(使用) : 作用于工作内存的变量，它把**工作内存中一个变量的值**传递给**执行引擎**。 
- **assign**(赋值) : 作用于工作内存的变量，它把一个**从执行引擎接收到的值赋给工作内存的变量**。 
- **store**(存储) : 作用于工作内存的变量，它把**工作内存中一个变量的值传送到主内存中**，以便后续的write操作 使用。 
- **write**(写入) : 作用于主内存的变量，它把store操作从**工作内存中得到的变量的值**放入**主内存的变量中**

### Java内存模型的三大特性 : 

#### 1.原子性 :

 由Java内存模型来直接保证的原子性变量操作包括read、load、assign、use、store和read。大致可以认为，**基本数据类型的访问读写是具备原子性的**。如若需要更大范围的原子性，需要synchronized关键字约束。(即一个操作或者多个操作要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行) 



#### 2.可见性 : 

可见性是指当一个线程修改了共享变量的值，其他线程能够立即得知这个修改**。volatile、 synchronized、ﬁnal**三个关键字可以实现可见性。 



#### 3. 有序性 :

 如果在本线程内观察，所有的操作都是有序的；如果在线程中观察另外一个线程，所有的操作都是无序的。前半句是指"线程内表现为串行"，后半句是指"指令重排序"和"工作内存与主内存同步延迟"现象。

Java内存模型具备一些先天的**“有序性”**，即不需要通过任何手段就能够得到保证的有序性，这个通常也称为 **happens-before** 原则。如果两个操作的执行次序无法从happens-before原则推导出来，**那么它们就不能保证它们 的有序性，虚拟机可以随意地对它们进行重排序。**



### Happens-Before原则

下面就来具体介绍下**happens-before原则（先行发生原则）：**

- 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作 
- 锁定规则：一个unLock操作先行发生于后面对同一个锁的lock操作 
- **volatile变量规则**：对一个变量的写操作先行发生于后面对这个变量的读操作 
- 传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C 
- 线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作 
- 线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生 
- 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、 Thread.isAlive()的返回值手段检测到线程已经终止执行 
- 对象终结规则：一个对象的初始化完成先行发生于他的ﬁnalize()方法的开始

**也就是说，要想并发程序正确地执行，必须要保证原子性、可见性以及有序性。只要有一个没有被保证，就有可能 会导致程序运行不正确。** 



## Volatile关键字

关键字volatile可以说是**JVM提供的轻量级的同步机制**，但是它并不容易完全被正确理解和使用。JVM内存模型对 volatile专门定义了一些特殊的访问规则。 

当一个变量定义为volatile之后，它将具备两种特性。

### 第一是保证此变量对所有线程的可见性

这里的"可见性"是指 : 当一条线程修改了这个变量的值，新值对于其他线程来说是可以立即得知的。而普通变量做不到这一点，普通变量的值在线程间传递均需要通过主内存来完成。例如: 线程A修改一个普通变量的值，然后向主内存进行回写，另外一条线程B在线程A回写完成之后再从主内存进行读取 操作，新值才会对线程B可见。 

关于volatile变量的可见性，经常会被开发人员误解。volatile变量在各个线程中是一致的，但是volatile变量的运 算在并发下一样是不安全的。**原因在于Java里面的运算并非原子操作**。

> 问题就在于**num++**之中，实际上num++等同于num = num+1。**volatile关键字保证了num的值在取值时是正确 的，但是在执行num+1的时候，其他线程可能已经把num值增大了，这样在+1后会把较小的数值同步回主内存之中。**



### 使用volatile变量的第二个语义是禁止指令重排序

普通的变量仅仅会保证在该方法的执行过程中所有依赖赋值结 果的地方都能获取到正确的结果，而不能保证变量赋值操作的顺序和程序代码中执行的顺序一致。

volatile关键字禁止指令重排序有两层意思： 

- 当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经 对后面的操作可见；在其后面的操作肯定还没有进行；
- 在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句 放到其前面执行。



### 双重检验锁模式（double checked locking pattern)实现单例

主要在于`instance = new Singleton()`这句，这并非是一个原子操 作，事实上在 JVM 中这句话大概做了下面 3 件事情。 

1. 给 instance 分配内存 （堆上的空间）

2. 调用 Singleton 的构造函数来初始化成员变量 

3. 将栈上的instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了） 

   **但是在 JVM 的即时编译器中 存在指令重排序的优化**。也就是说上面的第二步和第三步的顺序是不能保证的，终的执行顺序可能是 1-2-3 也可 能是 1-3-2。如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，这时 instance 已经是非 null了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。 我们只需要将 instance 变 量声明成 volatile 就可以了。

```java
Class Singleton{
	private static final Singleton instance = null;
    
    private Singleton() {
        
    }
    
    public static Singleton getInstance () {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```



## 深浅拷贝

**深浅拷贝**：新的对象

**浅拷贝**：牵一发动全身



**浅拷贝**：拷贝对象内部的引用复用，并不会产生新引用。

**深拷贝**：拷贝内部引用会产生一个新对象



实现Cloneable接口（标记接口）

使用Object的clone()方法。



实现深拷贝

1. 递归实现Cloneable接口（内部属性，变量都实现了Cloneable接口）
2. 使用序列化（推荐）序列化的对象就是一个新的拷贝对象。

