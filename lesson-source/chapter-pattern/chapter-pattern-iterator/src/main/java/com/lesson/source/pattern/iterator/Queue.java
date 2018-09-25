package com.lesson.source.pattern.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class Queue<E> {

    private Object[] item;

    public Queue(int initialCapacity) {
        item = new Object[initialCapacity];
    }

    public Iterator<E> iterator(){
        return new Itr();
    }

    public class Itr implements Iterator<E>{

        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size();
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {

            int i = cursor;
            if (i >= size()) {
                throw  new NoSuchElementException();
            }
            E result = (E)item[i];
            cursor = i+1;
            return result;
        }
    }

    public int size(){
        return item == null ?0:item.length;
    }
}
