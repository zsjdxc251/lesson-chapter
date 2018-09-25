package com.lesson.source.pattern.singleton.lazy;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class LazySample {

    private static LazySample lazySample;

    public static LazySample getInstance(){
        if (lazySample == null){
            lazySample = new LazySample();
        }

        return lazySample;

    }

    /**
     *  双重检查锁
     * @return
     */
    public static LazySample getSyncInstance(){

        if (lazySample == null){
            synchronized (LazySample.class) {
                if (lazySample != null) {
                    lazySample = new LazySample();
                }
            }
        }

        return lazySample;

    }


    public static LazySample getHolderInstance(){
        return LazyHolder.LAZY_SAMPLE;
    }

    private static class LazyHolder{
        private final static LazySample LAZY_SAMPLE = new LazySample();
    }
}
