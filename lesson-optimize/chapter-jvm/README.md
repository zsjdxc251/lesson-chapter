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

## Jvm 实操

### JDK自带监控工具







## 附件问题

1. Asm / Asm3.0实现
   * `Javassist`
   * `cglib`

2.  OpenJDK [官网](<http://openjdk.java.net/projects/jdk/>)
   * 下载路径 [JDK11](<http://jdk.java.net/11/>) [JDK12](<http://jdk.java.net/12/>)

