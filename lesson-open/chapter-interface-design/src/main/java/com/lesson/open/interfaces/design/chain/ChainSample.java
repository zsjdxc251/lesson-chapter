package com.lesson.open.interfaces.design.chain;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class ChainSample {


    public static void main(String[] args){

        DefualtExecutorChain executorChain = new DefualtExecutorChain();

        executorChain.addExecutor((chain)->{
            System.out.println("1");
            chain.execute();
            System.out.println("11");

        });
        executorChain.addExecutor((chain)->{
            System.out.println("2");
            chain.execute();
            System.out.println("22");
        });
        executorChain.addExecutor((chain)->{
            System.out.println("3");
            chain.execute();
            System.out.println("33");
        });
        executorChain.addExecutor((chain)->{
            System.out.println("4");
            chain.execute();
            System.out.println("44");
        });

        executorChain.execute();




    }


    public static class DefualtExecutorChain implements ExecutorChain {

        private LinkedList<Executor> executors = Lists.newLinkedList();

        private int position;

        public void addExecutor(Executor executor){
            executors.addLast(executor);
        }

        @Override
        public void execute() {

            if (position >= executors.size()){
                return;
            }

            Executor executor = executors.get(position ++);
            executor.execute(this);



        }
    }




    public interface Executor {

        /**
         *
         * @param executorChain
         */
        void execute(ExecutorChain executorChain);

    }

    public interface  ExecutorChain {

        /**
         *
         */
        void execute();
    }
}
