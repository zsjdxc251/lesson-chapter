package com.lesson.concurrent.api.sample.simple;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    private static final Logger log = LoggerFactory.getLogger(ForkJoinExample.class);

    /**
     * 有返回值
     */
    public static class ResultRecursive extends RecursiveTask<Integer> {

        private final static int THRESHOLD = 200;
        private int[] src; //表示我们要实际统计的数组
        private int fromIndex;//开始统计的下标
        private int toIndex;//统计到哪里结束的下标

        public ResultRecursive(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
        @Override
        protected Integer compute() {
            if(toIndex-fromIndex < THRESHOLD) {
                int count = 0;
                for(int i=fromIndex;i<=toIndex;i++) {

                    count = count + src[i];
                }
                return count;
            }else {
                int mid = (fromIndex+toIndex)/2;
                ResultRecursive left = new ResultRecursive(src,fromIndex,mid);
                ResultRecursive right = new ResultRecursive(src,mid+1,toIndex);
                invokeAll(left,right);
                return left.join()+right.join();
            }
        }
    }

    /**
     * 没有返回值
     */
    public static class VoidRecursive extends RecursiveAction {

        private File file;

        private VoidRecursive(File file){
            this.file = file;
        }
        @Override
        protected void compute() {
            List<VoidRecursive> recursiveList = new ArrayList<>();
            File[] files = file.listFiles();
            if (files != null && files.length > 0){
                for (File f : files) {
                    if (f.isDirectory()){
                        recursiveList.add(new VoidRecursive(f));
                    } else {
                        log.info("filename:{}",f.getPath());
                    }
                }
                if (recursiveList.size() > 0){
                    invokeAll(recursiveList).forEach(VoidRecursive::join);
                }
            }

        }
    }

    public static void exeVoid(ForkJoinPool pool){

        VoidRecursive voidRecursive = new VoidRecursive(new File("f:/"));
        // 异步调用
        pool.execute(voidRecursive);

        voidRecursive.join();
    }

    public static void exeResult(ForkJoinPool pool){
        ResultRecursive resultRecursive = new ResultRecursive(new int[300],0,300-1);

        // 同步调用
        //pool.invoke(resultRecursive);
        ForkJoinTask forkJoinTask = pool.submit(resultRecursive);
        try {
            forkJoinTask.get();
        } catch (Exception e ) {
            log.error(StringUtils.EMPTY,e);
        }
    }

    public static void main(String[] args){

        ForkJoinPool pool = new ForkJoinPool(4);


        //exeVoid(pool);

        exeResult(pool);



    }
}
