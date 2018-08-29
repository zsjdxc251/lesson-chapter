package com.lesson.concurrent.api.sample;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**

 *
 *                    ForkJoinTask
 *                        ||
 *  RecursiveTask<String>    RecursiveAction<Void>
 *       ||                       ||
 *  ForkJoinTask                 Void
 *
 *   {@code ForkJoinPool} 通过提交(submit)和执行(execute) 处理任务
 *
 *   <li>ForkJoinWorkerThread 继承了 Thread 就是一个线程</li>
 *   <li>
 *        ForkJoinPool 里面的 ForkJoinWorkerThreadFactory 获取的对象 ForkJoinWorkerThread
 *        在{@code ForkJoinPool}执行work 使用的就是 ForkJoinWorkerThread线程
 *   </li>
 *
 *
 *   {@code RecursiveTask},{@code RecursiveAction},{@code CountedCompleter} extends {@code ForkJoinTask} implements {@link Future}
 *
 *
 *   {@link Executor} -> {@link ExecutorService} -> {@link AbstractExecutorService} -> {@code java.util.concurrent.ForkJoinPool}
 *
 *
 *  {@link RecursiveTask}
 *  {@link RecursiveAction}
 *  {@link CountedCompleter}
 *
 *  {@link ForkJoinPool}
 *  {@link ForkJoinTask}
 *  {@link ForkJoinWorkerThread}
 *  {@link ExecutionException}
 *
 * @see InterruptedException
 *
 *
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class ForkJoinSample {

    public static void main(String[] args){


        System.out.println(ForkJoinPool.commonPool().getParallelism());


        ForkJoinPool forkJoinPool= new ForkJoinPool();


        // 没有返回值
        RecursiveAction recursiveAction = new RecursiveAction(){
            @Override
            protected void compute() {

            }
        };
        forkJoinPool.execute(recursiveAction);




        // 有返回值
        RecursiveTask<String> recursiveTask = new RecursiveTask<String>(){
            @Override
            protected String compute() {

                return null;
            }
        };

        ForkJoinTask<String> forkJoinTask = forkJoinPool.submit(recursiveTask);
        String result = null;
        try {
            result = forkJoinTask.get();
        } catch (InterruptedException| ExecutionException e) {
            e.printStackTrace();
        }


        CountedCompleter<String> countedCompleter = new CountedCompleter<String>(){

            @Override
            public void compute() {

                System.out.println("11");

            }

            @Override
            public void complete(String rawResult) {
                super.complete(rawResult);
                System.out.println("22");
            }
        };

        forkJoinPool.execute(countedCompleter);

    }
}
