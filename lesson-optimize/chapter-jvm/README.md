# Jvm





## Jvm 入门

### Jvm 体系结构概览

![1555732940350](assets/1555732940350.png)

### 类装载器 `ClassLoader`

**负责加载Class文件，Class文件在文件开头有特定的文件标示，并且ClassLoader值负责class文件的加载，至于是否可用运行，则又`Execution Engine`决定**

* 图示：

  ![1555733103209](assets/1555733103209.png)

* `ClassLoader` 依次顺序
  * 虚拟机自带的加载器
  * 启动类加载器`Bootstrap` - C++
  * 扩展类加载器 `Extension` - Java
  * 应用程序类加载器 `AppClassLoader` - Java 系统类加载器，加载当前引用的`classpath`的所有类
  * 用户自定义加载器
    * `java.lang.ClassLoader` 的子类，用户可用定制类的加载方式



### `Execution Engine`

**执行引擎负责解释命令，提交操作系统执行**

* Native Interface 本地接口

  * 本地接口的作用是融合不同的编程语言为`Java`所用

* Native Method Stack

  * 它的具体做法是`Native Method Stack`中登记`native`方法，在`Execution Engine` 执行时加载本地方法库

* PC寄存器

  * 每个线程都有一个程序计数器，时线程私有的，就是一个指针，指向方法去中的方法字节码（用来存储指向下一个指令的地址，也即将要指向的指令代码），由执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不计。

* 方法区

  * `Method Area` 方法区是被所有线程共享，所有字段和方法字节码，以及一些特殊方法如构建函数，接口代码也在此定义。简单说，所有定义的方法的信息都保存在该区域，此区属于共享区间。静态变量+常量+类信息（构造方法/接口定义）+运行时常量池存在方法去

* 栈区

  * 栈也叫栈内存，主管Java程序的运行，是在线程创建时创建，它的生命期时跟随线程的生命期，线程结束栈内存也就释放，**对于栈来说不存在垃圾回收问题，只要线程一结束该栈就Over,生命周期和线程一致，是线程私有的。8种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存中分配。

  * 栈存储着什么

    * 本地变量`Local Variables`：输入参数和输出参数以及方法内的变量；
    * 栈操作`Operand Stack`: 记录出栈、入栈的操作

  * 示例图：

    ![1555735863441](assets/1555735863441.png)

[JDK8 Docs](https://docs.oracle.com/javase/8/docs/)

[The class File Format](<https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html>)

[Java HotSpot VM Options](<https://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html#Options>)

## Jvm 实操

### JDK 命令信息

* `jps`     `java process status` 缩写

  * `jps -m` 运行传入主类的参数
  * `jps -l` 显示全类名
  * `jps -v` 显示虚拟机参数
  * `jps -q` 只输出 PID

* `jmap`

  * 命令格式 `jmap [option] PID`
  * `[option]`
    * `dump `: 生成堆转储快照
    * `finalizerinfo `: 显示在F-Queue队列等待Finalizer线程执行finalizer方法的对象
    * `heap `: 显示Java堆详细信息
    * `histo `: 显示堆中对象的统计信息
    * `permstat `: to print permanent generation statistics
    * `F` : 当-dump没有响应时，强制生成dump快照

  * `jmap -heap PID` 查看堆内存情况

  * `jmap -histo  PID` 打印该实例加载了哪些类

  * `jmap -dump:format=b,file=dump.log PID`  生成`dump`二进制文件  通过` jhat dump.log` 生成web默认`7000`端口可查看具体信息

* `jinfo`

  * 命令格式 `jinfo [option] [args] PID`
  * `[option]`
    * `-flag` : 输出指定args参数的值
    * `-flags` : 不需要args参数，输出所有JVM参数的值
    * `-sysprops` : 输出系统属性，等同于System.getProperties()

  * `jinfo -flags PID` 打印JVM配置的参数信息   `jinfo PID >> context.txt`  输出内容写入文件
  * `jinfo -flag MaxNewSize PID`

* `jstat`

  **jstat(JVM statistics Monitoring)是用于监视虚拟机运行时状态信息的命令，它可以显示出虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据。**

  * 命令格式 `jstat [option] LVMID [interval] [count]`

  * `[option]`

    * `[option]` : 操作参数
    * `LVMID `: 本地虚拟机进程ID `PID`
    * `[interval]` : 连续输出的时间间隔
    * `[count]` : 连续输出的次数

  * `option`参数总览

    | Option           | Displays…                                                    |
    | ---------------- | ------------------------------------------------------------ |
    | class            | class loader的行为统计。Statistics on the behavior of the class loader. |
    | compiler         | HotSpt JIT编译器行为统计。Statistics of the behavior of the HotSpot Just-in-Time compiler. |
    | gc               | 垃圾回收堆的行为统计。Statistics of the behavior of the garbage collected heap. |
    | gccapacity       | 各个垃圾回收代容量(young,old,perm)和他们相应的空间统计。Statistics of the capacities of the generations and their corresponding spaces. |
    | gcutil           | 垃圾回收统计概述。Summary of garbage collection statistics.  |
    | gccause          | 垃圾收集统计概述（同-gcutil），附加最近两次垃圾回收事件的原因。Summary of garbage collection statistics (same as -gcutil), with the cause of the last and |
    | gcnew            | 新生代行为统计。Statistics of the behavior of the new generation. |
    | gcnewcapacity    | 新生代与其相应的内存空间的统计。Statistics of the sizes of the new generations and its corresponding spaces. |
    | gcold            | 年老代和永生代行为统计。Statistics of the behavior of the old and permanent generations. |
    | gcoldcapacity    | 年老代行为统计。Statistics of the sizes of the old generation. |
    | gcpermcapacity   | 永生代行为统计。Statistics of the sizes of the permanent generation. |
    | printcompilation | HotSpot编译方法统计。HotSpot compilation method statistics.  |

  * 示例 `jstat -class PID`

    >Loaded  Bytes  Unloaded  Bytes     Time
    >   620  1243.9        0     0.0       0.16

    * `Loaded `: 加载class的数量
    * `Bytes `: class字节大小
    * `Unloaded `: 未加载class的数量
    * `Bytes `: 未加载class的字节大小
    * `Time `: 加载时间

  * ` jstat -compiler PID`

    >Compiled Failed Invalid   Time   FailedType FailedMethod
    >      93      0       0     0.12          0

    * Compiled : 编译数量
    * Failed : 编译失败数量
    * Invalid : 无效数量
    * Time : 编译耗时
    * FailedType : 失败类型
    * FailedMethod : 失败方法的全限定名

  * `jstat -gc PID`

    >  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
    > 8192.0 8192.0  0.0    0.0   49152.0   7946.8   131072.0     0.0     4480.0 775.8  384.0   76.4       0    0.000   0      0.000    0.000

    **C即Capacity 总容量，U即Used 已使用的容量**

    * S0C : survivor0区的总容量
    * S1C : survivor1区的总容量
    * S0U : survivor0区已使用的容量
    * S1U : survivor1区已使用的容量
    * EC : Eden区的总容量
    * EU : Eden区已使用的容量
    * OC : Old区的总容量
    * OU : Old区已使用的容量
    * PC	当前perm的容量 (KB)
    * PU	perm的使用 (KB)
    * YGC : 新生代垃圾回收次数
    * YGCT : 新生代垃圾回收时间
    * FGC : 老年代垃圾回收次数
    * FGCT : 老年代垃圾回收时间
    * GCT : 垃圾回收总消耗时间

    `jstat -gc 1262 2000 20` 这个命令意思就是每隔2000ms输出1262的gc情况，一共输出20次

### JDK自带监控工具







## 附件问题

1. Asm / Asm3.0实现
   * `Javassist`
   * `cglib`

2.  OpenJDK [官网](<http://openjdk.java.net/projects/jdk/>)
   * 下载路径 [JDK11](<http://jdk.java.net/11/>) [JDK12](<http://jdk.java.net/12/>)







## 常用调优参数

- \- : 标准VM选项，VM规范的选项
- -X: 非标准VM选项，不保证所有VM支持
- -XX: 高级选项，高级特性，但属于不稳定的选项

### 内存分配

* `-Xmx1024M` 最大堆内存`1024M` 等同于`-XX:MaxHeapSize`
* `-Xms1024M` 初始化堆内存 `1024M `  ，`ms`就是`memory size` 缩写
* `-Xmn1024` 堆中新生代初始及最大大小，如果需要进一步细化，初始化大小用`-XX:NewSize`，最大大小用`-XX:MaxNewSize`
* `-Xss256K` 线程栈大小，等同于`-XX:ThreadStackSize`   `ss`就是`stack size` 的缩写
* `-XX:NewRatio=5` 新生代老年代比例 `new:old=1:5`
* `-XX:SurvivorRatio=8`  `eden:from:to=8:1:1`

### 内存调试

* `-XX:+PrintGCDetails` 打印GC日志详情和堆内存情况
* `-verbose:gc` 执行GC时打印GC日志