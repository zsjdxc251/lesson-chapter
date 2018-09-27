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

    public void reverse(){
        Node pre = null; //   1   2
        Node next; //   2 null
        // start           2 null
        // start.next null  start.next 1

        //  1 2 3 4

//        while (start != null) {
//            next = start.next;
//
//            start.next = pre;
//            pre = start;
//
//            start = next;
//        }
//        this.start = pre;



        while (start != null) {
            next = start.next;
            start.next = pre;
            pre = start;
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

    public static void main(String[] args){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
//        linkedList.add("4");
//        linkedList.add("5");
        linkedList.reverse();
        linkedList.forEach(System.out::println);
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
