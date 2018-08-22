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