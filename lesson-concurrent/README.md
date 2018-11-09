# 并发编程

## InheritableThreadLocal 原理

### InheritableThreadLocal 实现

* `InheritableThreadLocal`  继承了 `ThreadLocal` 三个方法 主要看 `getMap` 和 `CreateMap`

```java
public class InheritableThreadLocal<T> extends ThreadLocal<T> {
    protected T childValue(T parentValue) {
        return parentValue;
    }
    ThreadLocalMap getMap(Thread t) {
       return t.inheritableThreadLocals;
    }
 
    void createMap(Thread t, T firstValue) {
        t.inheritableThreadLocals = new ThreadLocalMap(this, firstValue);
    }
```

* 在看 `Thread` 实现 在 new 线程时 当前线程的 `inheritableThreadLocals` 赋值了给 新线程

```java
public class Thread {
    ...
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc,
                      boolean inheritThreadLocals) {
        ...
         if (inheritThreadLocals && parent.inheritableThreadLocals != null)
            this.inheritableThreadLocals =
                ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        ...
    }
    ...
}
```

* `InheritableThreadLocal` 没有实现`get`方法所以调用的是 `ThreadLocal` 的方法

```java
public class ThreadLocal<T> {
    ...
    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }
    ...
    
}
```

*当子线程调用`get`方法的时候直接调用到了`InheritableThreadLocal` 的`getMap`方法 所以会使用 `Thread` 类中的`inheritableThreadLocals` 属性值，知道在创建线程的时候父线程的 `inheritableThreadLocals` 传给 子线程*





## volatile

1. 可以保证可见性，防止内存重排序

2. #lock -> 缓存锁 （`MESI`）

3. 内存屏障 `storestore` `loadload` `loadstore`
## 并发包

### Locks

## 位运算

| 运算名称 | 运算符号 | 示例1  | 示例2  | 描述               |
| --------|-------- | ------ | ------ | ------------------ |
| 或运算   | \| | 1\|0=1 | 0\|0=0 | 只要有一个为1则为1 |
| 与运算   | & | 1&1=1 | 1&0=1 | 只有两个都为1的时候才为1 |
| 异或运算 | ^ | 1^1=0 | 1^0=1 | 标识不同的时候才是1 |
| 非运算 | ~ | ~1=0 | ~0=1 | 取相反值 |
| 有符号右移运算 | \>\> | 1\>\>4=1/2^4 | 16\>\>2=0..0100 | 表示右移，如果该数为正，则高位补`0`;若为负数，则高位补`1`； |
| 左移运算 | \<\< | 1\<\<4=1*2^4 | 1<<2=0..01000 | 暂无 |
| 无符号右移补零运算 | \>\>\> | 暂无 | 暂无 | 若该数为正，则高位补`0`，而若该数为负数，则右移后高位同样补`0` |



### 右移和逻辑右移的区别

* 若为正数--> 右移和逻辑右移都是 高位补`0`

  | 操作     | 计算   | 二进制                           |
  | -------- | ------ | -------------------------------- |
  | 原数据   | 20     | 00000000000000000000000000010100 |
  | 右移     | 20>>2  | 00000000000000000000000000000101 |
  | 逻辑右移 | 20>>>2 | 00000000000000000000000000000101 |

​      

* 若为负数-->右移高位补`1`,逻辑右移高位补`0`

  | 操作     | 计算    | 二进制                           |
  | -------- | ------- | -------------------------------- |
  | 原数据   | -20     | 01111111111111111111111111101100 |
  | 右移     | -20>>2  | 01111111111111111111111111111011 |
  | 逻辑右移 | -20>>>2 | 00111111111111111111111111111011 |

### 64 和 32的组成

* 在`java`中 `int` 和 `long` 都是有上下限的。`int` 是 `32`位 , `long` 是 `64`位 
* `int` 

  * 下限 `-2^31`
  * 上限`2^31－1`

  * 最大二进制是 `01111111111111111111111111111111`
  * 最小二进制是 `10000000000000000000000000000000`
* `long`
  * 下限 `-2^63`
  * 上限`2^63－1`
  * 最大二进制 `0111111111111111111111111111111111111111111111111111111111111111`
  * 最小二进制`1000000000000000000000000000000000000000000000000000000000000000`
* 如果按最大的计算出来去掉前面`0` 则是`31` 位和 `63` 位，而进制用第一位来做符号，`0`是正，`1`是负,也就是说真正的数字是`int`后面的31位和`long`后面的63位



