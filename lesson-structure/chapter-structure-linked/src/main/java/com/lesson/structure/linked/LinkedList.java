package com.lesson.structure.linked;

import java.util.function.Consumer;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class LinkedList<T> {

    private transient Node start;

    private transient Node last;

    class Node {
        T value;
        Node pre;
        Node next;
        private Node(T value) {
            this.value = value;
        }
    }

    public void add(T value) {
        if (this.start == null){
            this.start = new Node(value);
        } else {
            Node next = start;
            while (next.next != null){
                next = next.next;
            }
            next.next = new Node(value);
        }
    }

    public void addCycle(){

        Node next = start;
        while (next.next != null){
            next = next.next;
        }

        next.next = start;



    }

    private void add(Node node){
        if (this.start == null){
            this.start = node;
        } else {
            Node next = start;
            while (next.next != null){
                next = next.next;
            }
            next.next = node;
        }
    }


    public void forEach(Consumer<T> consumer) {
        if (start != null) {
            Node next = this.start;
            while (next != null) {
                consumer.accept(next.value);
                next = next.next;
            }
        }
    }


    /**
     *
     *   pre null
     *   start 1   start.next null
     *   next 2
     *
     *
     *   pre 1
     *   start 2   start.next 1
     *   next 3
     *
     */
    public void reverse(){

        Node pre = null;

        Node next = null;

        while (start != null) {
            // 找本次循环的下一个  如果当前循环到的是1   那么下一个就是 2
            next = start.next;

            // 关键操作 ： 调换引用 相当于把 当前的下一个之下当前的上一个调换一下
            start.next = pre;

            // 把本次的实例为下一次的pre
            pre = start;
            // 这次循环完 就循环下一个
            start = next;

        }

        this.start = pre;



    }





    public T middle(){
        if (start == null) {
            return null;
        } else if (start.next == null){
            return start.value;
        }
        Node fast = start;
        Node slow = start;
        while (fast.next !=null && (fast = fast.next.next) != null) {

            slow = slow.next;
        }
        return slow.value;

    }

    private Node merge(Node node){
        if (node == null){
            return this.start;
        } else if (this.start == null){
            return node;
        }
        Node next = this.start;

        if (next.value instanceof Number){

        } else {
            add(node);

        }
        return next;
    }
    public LinkedList<T> merge(LinkedList<T> origin) {
        if (origin != null){
            this.start = origin.merge(this.start);
        }
        return this;
    }

    /**
     *  判断列表是否有环 主要是看首尾是否相连
     *
     *   使用
     *
     * @return
     */
    public boolean hasCycle(){
        Node fast = start ,slow = start;

        while (slow != null && fast != null) {
            slow = slow.next;
            if (fast.next != null){
                fast = fast.next.next;
            } else {
                fast = null;
            }

            if (slow != null && slow == fast){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
//        linkedList.add("4");
//        linkedList.add("5");
//        linkedList.reverse();
//        linkedList.forEach(System.out::println);

       //linkedList.addCycle();
        System.out.println(linkedList.hasCycle());
//        LinkedList<String> linkedList2 = new LinkedList<>();
//        linkedList2.add("34");
//        linkedList2.add("5");
//
//        linkedList2 = linkedList.merge(linkedList2);
//
//
//        //System.out.println(linkedList.middle());


    }
}
