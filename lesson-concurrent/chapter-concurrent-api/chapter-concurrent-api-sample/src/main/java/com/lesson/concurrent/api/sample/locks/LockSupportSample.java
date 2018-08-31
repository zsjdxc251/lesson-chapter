package com.lesson.concurrent.api.sample.locks;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
/**
 *
 *
 *    <p>
 *        在 {@code LockSupport} 调用 park之前如果调用一次 unpark 当接下来在调用 park的话不会对该线程进行阻塞
 *        park 和 unpark 是幂等性的
 *    <p/>
 *
 *
 *
 *
 *     {@link LockSupport}
 *
 *
 *
 *
 *
 *
 * @author zhengshijun
 * @version created on 2018/8/31.
 */
public class LockSupportSample {

    public static void main(String[] args){
        final Thread parkThread = new Thread(()->{


            Object object = new Object();
            System.out.println("释放21");
            try {

                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("过了1秒");
            try {

                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("过了5秒");
            System.out.println(Thread.currentThread().getState());
            LockSupport.park();
            System.out.println(Thread.currentThread().getState());
            System.out.println("阻塞完成");


            System.out.println("释放2"+Thread.currentThread().getState());

        });


        Thread unparkThread = new Thread(()->{
            System.out.println("释放11");

            System.out.println(parkThread.getState());
            LockSupport.unpark(parkThread);
            System.out.println(parkThread.getState());
            System.out.println("释放1");

        });
        parkThread.start();
        unparkThread.start();
















    }
}
