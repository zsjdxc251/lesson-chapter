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

        });
        executorChain.addExecutor((chain)->{
            System.out.println("2");
            chain.execute();
        });
        executorChain.addExecutor((chain)->{
            System.out.println("3");
            chain.execute();
        });
        executorChain.addExecutor((chain)->{
            System.out.println("4");
            chain.execute();
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

            Executor executor = executors.get(position);
            position ++;
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
