package com.lesson.concurrent.api.sample;

import org.apache.logging.log4j.core.async.ThreadNameCachingStrategy;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 *
 *    {@link Phaser}
 *
 *
 * @author zhengshijun
 * @version created on 2018/9/4.
 */
public class PhaserSample {

    private Phaser phaser;

    public static void main(String[] args){
        PhaserSample phaserSample = new PhaserSample();

        phaserSample.forPhaser();

    }




    /**
     *
     * {@code Phaser} 替代 {@link CountDownLatch} 玩法
     *
     *  CountDownLatch countDownLatch = new CountDownLatch(5);
     *     countDownLatch.countDown();
     *
     *  countDownLatch.await();
     *
     *
     */
    public void replaceCountDownLatch(){
        // 设置 5
        phaser = new Phaser(5);

        IntStream.range(0,5).forEach(i->{
            new Thread(()->{
                phaser.arrive();
                System.out.println(Thread.currentThread().getName());
            },"线程"+i).start();
        });



        int phaseCount = phaser.getPhase();
        System.out.println(phaseCount);

        phaser.awaitAdvance(phaseCount);

        System.out.println(phaser.getPhase());
        System.out.println("释放");

    }


    /**
     *
     *  {@code Phaser} 替代 {@link CyclicBarrier} 玩法
     *
     *
     *
     */
    public void replaceCyclicBarrier(){

        phaser = new Phaser(5);
        System.out.println(phaser.getPhase());
        IntStream.range(0,5).forEach(i->{
            new Thread(()->{
                System.out.println("全部到这里等待：1"+Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println("一起释放");
            },"线程"+i).start();
        });

    }


    /**
     *
     *  {@code Phaser} arriveAndAwaitAdvance 特性 可以重复调用多次 来递增计数器 phaser.getPhaser()
     *
     * @see ThreadLocalRandom
     * @see Random
     *
     */
    public void forPhaser(){
        String name = "明刚红丽黑白";
        phaser = new Phaser(name.length());
        Random random = ThreadLocalRandom.current();
        BlockingDeque<String>  backupName = new LinkedBlockingDeque<>();
        backupName.addLast("小蓝");
        backupName.addLast("小紫");
        backupName.addLast("小梅");
        backupName.addLast("小黄");
        IntStream.range(0,name.length()).forEach(i->{
            new Thread(new ForPhaser(phaser,10,random,backupName),"小"+name.toCharArray()[i]).start();
        });
    }


    private class ForPhaser implements Runnable{


        private final Phaser phaser;
        // 游玩地点数
        private final int loop;
        private final Random random;
        private final BlockingDeque<String>  backupName;

        public ForPhaser(Phaser phaser, int loop, Random random, BlockingDeque<String> backupName) {
            this.phaser = phaser;
            this.loop = loop;
            this.random = random;
            this.backupName = backupName;
        }

        @Override
        public void run() {
            startPlay();
        }

        public void startPlay(){
            if (phaser.getPhase() == 0){
                // 一开始集合
                phaser.arriveAndAwaitAdvance();
            }
            /***************************************/

            // 实现中途加入和退出
            int index = random.nextInt(100);
            if (index < 10) {
                // 中途加入
                if (!backupName.isEmpty()){
                    String threadName = backupName.removeFirst();
                    phaser.bulkRegister(1);
                    System.out.println(threadName+" 在第 "+phaser.getPhase()+" 轮加入");
                    new Thread(new ForPhaser(phaser,loop,random,backupName),threadName).start();
                }
            } else if (index > 90){
                // 中途退出
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName+" 在第 "+phaser.getPhase()+" 轮退出");
                backupName.addLast(threadName);
                phaser.arriveAndAwaitAdvance();
                return;
            }


            /**************************************/
            if (phaser.getPhase() > loop){
                System.out.println(Thread.currentThread().getName()+" ==> 游玩结束");
                return;
            }
            System.out.println(Thread.currentThread().getName()+" ==> 第 "+phaser.getPhase()+" 轮游玩");
            phaser.arriveAndAwaitAdvance();
            startPlay();

        }
    }
}
