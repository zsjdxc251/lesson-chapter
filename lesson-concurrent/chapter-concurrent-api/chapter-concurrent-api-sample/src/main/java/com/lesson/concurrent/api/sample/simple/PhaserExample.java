package com.lesson.concurrent.api.sample.simple;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

/**
 * 可分段使用 phaser
 */
public class PhaserExample {
    Phaser phaser = new Phaser(5);

    public void execute(){

        System.out.println("所有线程都到此次等待，等待所有线程都到此次，然后放行");
        phaser.arriveAndAwaitAdvance();

        execute2();

    }

    public void execute2(){
        System.out.println("所有线程都到此次等待，等待结束");
        phaser.arriveAndAwaitAdvance();



        System.out.println("结束");

    }

    /**
     * Phaser 替代 CyclicBarrier用法
     */
    public static void example1(){
        PhaserExample phaserExample = new PhaserExample();

        IntStream.range(0,5).forEach(x->{

            new Thread(phaserExample::execute).start();
        });
    }

    /**
     * Phaser 替代 CountDownLatch
     */
    public static void example2(){
        final Phaser phaser = new Phaser(5);


        IntStream.range(0,5).forEach(x->{

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"下机");
                phaser.arrive();
            }).start();

        });

        System.out.println("等待清理工作");
        System.out.println(phaser.getPhase());
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println(phaser.getPhase());
        System.out.println("开始打扫工作");

    }

    Phaser phaserExample3 = new Phaser(3);
    Random random = new Random();
    final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    {
        threadLocal.set(0);
    }

    public void run(){

        //System.out.println(phaserExample3.getPhase());

        if (threadLocal.get() == null){
            // phaserExample3.getPhase() 所有线程都进入了哪一轮，在中间插入线程的话，跑起来也应该是和当前其他线程同步到一个位置
            threadLocal.set(phaserExample3.getPhase()+1);
        }

        int r = random.nextInt(100);
        if (r < 10){
            System.out.println(Thread.currentThread().getName() + ":在这里竟然遇到了"+1+"个朋友,他们说要一起去旅游...");
            phaserExample3.bulkRegister(1);
            final PhaserExample phaserExample = this;
            new Thread(()->{
                phaserExample.run();
            }).start();
        } else if (r > 90){
            phaserExample3.arriveAndDeregister();
            System.out.println(Thread.currentThread().getName()+"离开");
            return;
        }
        System.out.println(Thread.currentThread().getName()+" 当前第"+threadLocal.get()+"轮");
        threadLocal.set(threadLocal.get()+1);
        phaserExample3.arriveAndAwaitAdvance();

        if (threadLocal.get() <= 5){
            run();
        }


    }

    public static void example3(){

        PhaserExample phaserExample = new PhaserExample();

        IntStream.range(0,3).forEach(x->{

            new Thread(()->{
                phaserExample.run();
            }).start();

        });
    }


    public static void main(String[] args){
        example3();

    }
}
